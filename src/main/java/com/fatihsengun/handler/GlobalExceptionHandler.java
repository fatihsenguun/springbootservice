package com.fatihsengun.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.fatihsengun.controller.impl.RestBaseController;
import com.fatihsengun.entities.RootEntity;
import com.fatihsengun.exception.BaseException;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends RestBaseController {

	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<RootEntity> handleBaseException(BaseException exception, WebRequest request) {
		return ResponseEntity.badRequest().body(
				error(createApiError(exception.getMessage(), request,null)) ) ;
	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<RootEntity> handleBaseException(AccessDeniedException exception, WebRequest request) {
		return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(
				error(createApiError(exception.getMessage(), request,HttpServletResponse.SC_UNAUTHORIZED)) ) ;
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RootEntity> handleValidationExceptions(MethodArgumentNotValidException exception,
			WebRequest request) {
		Map<String, List<String>> errorsMap = new HashMap<>();

		for (ObjectError objError : exception.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError) objError).getField();

			if (errorsMap.containsKey(fieldName)) {
				errorsMap.put(fieldName, addMapValue(errorsMap.get(fieldName), objError.getDefaultMessage()));
			} else {
				errorsMap.put(fieldName, addMapValue(new ArrayList<>(), objError.getDefaultMessage()));
			}
			/**
			 * String fieldName = ((FieldError)objError).getField(); String errorMsg =
			 * objError.getDefaultMessage(); errorsMap.computeIfAbsent(fieldName, key -> new
			 * ArrayList<>()).add(errorMsg);
			 **/

		}

		return ResponseEntity.badRequest().body(
				error(createApiError(errorsMap, request,null)));
	}

	private List<String> addMapValue(List<String> list, String newValue) {
		list.add(newValue);
		return list;
	}

	private String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.out.println("Hostname hatası : " + e);
		}
		return null;
	}

	public <E> ApiError<E> createApiError(E message, WebRequest request, Integer status) {
		ApiError<E> apiError = new ApiError<>();
		apiError.setStatus(status);
		if (status==null) {
			apiError.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		

		Exception<E> exception = new Exception<>();
		exception.setCreateTime(new Date());
		exception.setHostName(getHostName());
		exception.setPath(request.getDescription(false));
		exception.setMessage(message);

		apiError.setException(exception);

		return apiError;
	}

}
