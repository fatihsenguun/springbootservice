package com.fatihsengun.exception;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

	private String id;
	
	private Date errorTime;
	
	private Map<String, List<String>> errors;
	
}
