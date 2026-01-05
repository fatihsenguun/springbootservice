package com.fatihsengun.controller.impl;

import org.springframework.http.ResponseEntity;

import com.fatihsengun.entities.RootEntity;
import com.fatihsengun.handler.ApiError;

public class RestBaseController {

	
	public <T> RootEntity<T> ok(T data){
		return RootEntity.ok(data);
	}
	 public <T> RootEntity<T> error(ApiError apiError){
		 return RootEntity.error(apiError);
		 
	 }
	
	
}
