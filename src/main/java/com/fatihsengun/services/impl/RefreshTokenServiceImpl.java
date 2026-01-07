package com.fatihsengun.services.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.entities.RefreshToken;
import com.fatihsengun.entities.User;
import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.JwtService;
import com.fatihsengun.jwt.RefreshTokenRequest;
import com.fatihsengun.repository.RefreshTokenRepository;
import com.fatihsengun.services.IRefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public RefreshToken saveRefreshToken(User	user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
		refreshToken.setUser(user);

		
		return refreshTokenRepository.save(refreshToken);
	}
	
	public boolean isRefreshTokenExpired(Date expireDate) {
		return new Date().before(expireDate);
	}
	
	

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
	System.out.println(request.getRefreshtoken());
		Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshtoken());
		
		if (optional.isEmpty()) {
			//hata
			System.out.println("RefreshToken Gecersiz");
		}
		RefreshToken refreshToken = optional.get();
		
		if (!isRefreshTokenExpired(refreshToken.getExpireDate())) {
			System.out.println("Refresh token expired");
			//hata
		}
		String accessToken = jwtService.generateToken(refreshToken.getUser());
		RefreshToken savedRefreshToken = saveRefreshToken(refreshToken.getUser());
		
		
		
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

}
