package com.fatihsengun.services;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.jwt.AuthResponse;

public interface IAuthService {
	public DtoUser register(AuthRequest request);
	public AuthResponse authenticate(AuthRequest request);

}
