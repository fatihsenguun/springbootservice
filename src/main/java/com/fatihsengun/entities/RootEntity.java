package com.fatihsengun.entities;

import com.fatihsengun.handler.ApiError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RootEntity<T> {

	private boolean result;
	
	private ApiError errorMessage;
	
	private T data;
	
	public static <T> RootEntity<T> ok(T data){
		RootEntity<T> rootEntity = new RootEntity<>();
		rootEntity.setData(data);
		rootEntity.setResult(true);
		rootEntity.setErrorMessage(null);
		return rootEntity;
	}
	public static <T> RootEntity<T> error(ApiError errorMessage){
		RootEntity<T> rootEntity = new RootEntity<>();
		rootEntity.setData(null);
		rootEntity.setErrorMessage(errorMessage);
		rootEntity.setResult(false);
		return rootEntity;
		
	}
	
	
}
