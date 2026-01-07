package com.fatihsengun.services;

import com.fatihsengun.entities.RefreshToken;
import com.fatihsengun.entities.User;
import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {
	public RefreshToken saveRefreshToken(User user);
	public AuthResponse refreshToken(RefreshTokenRequest request);

}
