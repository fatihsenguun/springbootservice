package com.fatihsengun.services;

import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;

public interface IStudentService {
	
	public DtoStudent addStudent(DtoStudentIU dtoStudentIU);
	public DtoStudent getStudentById(Long id);
	public DtoStudent updateStudent(DtoStudent dtoStudent );
	
	

}
