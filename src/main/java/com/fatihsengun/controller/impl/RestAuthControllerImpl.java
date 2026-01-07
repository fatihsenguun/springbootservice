package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IRestAuthController;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.entities.RootEntity;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.RefreshTokenRequest;
import com.fatihsengun.services.IAuthService;
import com.fatihsengun.services.IRefreshTokenService;

import jakarta.validation.Valid;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

	@Autowired
	private IAuthService authService;

	@Autowired
	private IRefreshTokenService refreshTokenService;

	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest request) {

		return RootEntity.ok(authService.register(request));
	}

	@Override
	@PostMapping("/authenticate")
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {

		return RootEntity.ok(authService.authenticate(request));
	}

	@Override
	@PostMapping("/refreshtoken")
	public RootEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {

		return RootEntity.ok(refreshTokenService.refreshToken(request));
	}

}
