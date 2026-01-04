package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IStudentController;
import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;
import com.fatihsengun.services.IStudentService;

@RestController
@RequestMapping("/rest/api/student")
public class StudentControllerImpl implements IStudentController {

	@Autowired
	private IStudentService studentService;
	
	@Override
	@PostMapping(path = "/add")
	public DtoStudent saveStudent(@RequestBody DtoStudentIU dtoStudentIU) {
		System.out.println(dtoStudentIU.getFirstName());
		return studentService.addStudent(dtoStudentIU) ;
	}

	@Override
	@GetMapping("/get/{id}")
	public DtoStudent getStudentById(@PathVariable(name = "id") Long id) {

		return studentService.getStudentById(id);
	}

	@Override
	@PostMapping(path = "/update")
	public DtoStudent updateStudent(@RequestBody DtoStudent dtoStudent) {
		

		return studentService.updateStudent(dtoStudent);
	}
	

}
