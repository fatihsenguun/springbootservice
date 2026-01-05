package com.fatihsengun.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IStudentController;
import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;
import com.fatihsengun.entities.RootEntity;
import com.fatihsengun.services.IStudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/student")
public class StudentControllerImpl extends RestBaseController implements IStudentController {

	@Autowired
	private IStudentService studentService;
	
	@Override
	@PostMapping(path = "/add")
	public RootEntity<DtoStudent> saveStudent(@Valid @RequestBody DtoStudentIU dtoStudentIU) {
		
		return ok(studentService.addStudent(dtoStudentIU)) ;
	}

	@Override
	@GetMapping("/get/{id}")
	public RootEntity<DtoStudent> getStudentById(@PathVariable(name = "id") Long id) {

		return ok(studentService.getStudentById(id));
	}
	@Override
	@GetMapping(path = "/delete/{id}")
	public RootEntity<DtoStudent> deleteStudentById(@PathVariable(name ="id") Long id) {

		return ok(studentService.deleteStudentById(id)) ;
	}

	@Override
	@PostMapping(path = "/update/{id}")
	public RootEntity<DtoStudent> updateStudent(@RequestBody DtoStudentIU dtoStudentIU, @PathVariable(name = "id") Long id) {
		

		return ok(studentService.updateStudent(dtoStudentIU,id));
	}



	
	

}
