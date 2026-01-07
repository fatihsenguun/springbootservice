package com.fatihsengun.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;
import com.fatihsengun.entities.RootEntity;
import com.fatihsengun.entities.Student;
import com.fatihsengun.utils.RestPageableEntity;
import com.fatihsengun.utils.RestPageableRequest;

public interface IStudentController {
	
	public RootEntity<DtoStudent> saveStudent(DtoStudentIU dtoStudentIU);
	public RootEntity<DtoStudent>  getStudentById(Long id);
	public RootEntity<DtoStudent> updateStudent(DtoStudentIU dtoStudentIU,Long id );
	public RootEntity<DtoStudent> deleteStudentById(Long id);
	public RootEntity<RestPageableEntity<DtoStudent>>  findAllPageable(RestPageableRequest pageable);

	

}
