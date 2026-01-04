package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;

public interface IStudentController {
	
	public DtoStudent saveStudent(DtoStudentIU dtoStudentIU);
	public DtoStudent getStudentById(Long id);
	public DtoStudent updateStudent(DtoStudent dtoStudent);
	

}
