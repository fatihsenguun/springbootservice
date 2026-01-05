package com.fatihsengun.handler;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exception<E> {
	
	private String hostName;
	
	private String path;
	
	private Date createTime;
	
	private E message;

}
