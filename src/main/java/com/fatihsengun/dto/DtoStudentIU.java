package com.fatihsengun.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudentIU {

	@NotEmpty(message = "Firstname field cannot be empty! ")
	private String firstName;
	
	@NotEmpty(message = "Lastname field cannot be empty!")
	private String lastName;
	
	@Size(min = 11,message = "tckn size cannot be different 11")
	@NotEmpty(message = "tckn field cannot be empty!")
	private String tckn;
	
	private DtoAddressIU address;
	
	
}
