package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.entities.RootEntity;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.RefreshTokenRequest;

public interface IRestAuthController {
	public RootEntity<DtoUser> register(AuthRequest request);
	public RootEntity<AuthResponse> authenticate(AuthRequest request);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
