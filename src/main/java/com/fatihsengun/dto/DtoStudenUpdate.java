package com.fatihsengun.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudenUpdate {
	private Long id;
	

	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private DtoAddress address;
	
}
