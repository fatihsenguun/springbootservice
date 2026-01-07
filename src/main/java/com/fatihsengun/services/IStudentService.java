package com.fatihsengun.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;
import com.fatihsengun.entities.Student;

public interface IStudentService {
	
	public DtoStudent addStudent(DtoStudentIU dtoStudentIU);
	public DtoStudent getStudentById(Long id);
	public DtoStudent updateStudent(DtoStudentIU dtoStudent, Long id );
	public DtoStudent deleteStudentById(Long id);
	public Page<Student>  findAllPageable(Pageable pageable);
	public  List<DtoStudent> toDTOList(List<Student> list);

	
	
	

}
