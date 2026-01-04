package com.fatihsengun.controller;

import java.util.List;

import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;

public interface IStudentController {
	
	public DtoStudent saveStudent(DtoStudentIU dtoStudentIU);
	public DtoStudent getStudentById(Long id);
	public DtoStudent updateStudent(DtoStudentIU dtoStudentIU,Long id );
	public DtoStudent deleteStudentById(Long id);

	

}
