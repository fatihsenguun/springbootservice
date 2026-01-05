package com.fatihsengun.controller;

import java.util.List;

import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;
import com.fatihsengun.entities.RootEntity;

public interface IStudentController {
	
	public RootEntity<DtoStudent> saveStudent(DtoStudentIU dtoStudentIU);
	public RootEntity<DtoStudent>  getStudentById(Long id);
	public RootEntity<DtoStudent> updateStudent(DtoStudentIU dtoStudentIU,Long id );
	public RootEntity<DtoStudent> deleteStudentById(Long id);

	

}
