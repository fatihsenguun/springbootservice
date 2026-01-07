package com.fatihsengun.services.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.entities.RefreshToken;
import com.fatihsengun.entities.RootEntity;
import com.fatihsengun.entities.User;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.JwtService;
import com.fatihsengun.repository.UserRepository;
import com.fatihsengun.services.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {


	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private RefreshTokenServiceImpl refreshTokenServiceImpl;

	@Override
	public DtoUser register(AuthRequest request) {
		
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new BaseException(new ErrorMessage(MessageType.USER_ALREADY_EXISTS,request.getUsername()));
		}
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		User savedUser = userRepository.save(user);
		
		DtoUser dto = new DtoUser();
		BeanUtils.copyProperties(savedUser, dto);

		return dto;
	}

	@Override
	public AuthResponse authenticate(AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(),
					request.getPassword());
			authenticationProvider.authenticate(auth);
			Optional<User> optional = userRepository.findByUsername(request.getUsername());
			String accessToken = jwtService.generateToken(optional.get());
			RefreshToken refreshToken = refreshTokenServiceImpl.saveRefreshToken(optional.get());
		
		return new AuthResponse(accessToken, refreshToken.getRefreshToken());
		} catch (Exception e) {
			// exception
			System.out.println("Kullanıcı adı veya şifre hatalı");
		}

		return null;
	}

}
