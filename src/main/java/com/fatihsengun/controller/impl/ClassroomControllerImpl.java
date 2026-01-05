package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IClassroomController;
import com.fatihsengun.dto.DtoClassroom;
import com.fatihsengun.dto.DtoClassroomIU;
import com.fatihsengun.entities.RootEntity;
import com.fatihsengun.services.IClassroomService;

@RestController
@RequestMapping("/rest/api/classroom")
public class ClassroomControllerImpl extends RestBaseController implements IClassroomController {

	@Autowired
	private IClassroomService classroomService;
	
	@Override
	@PostMapping(path = "/add")
	public RootEntity<DtoClassroom> addClassroom(@RequestBody DtoClassroomIU dtoClassroomIU) {
		
		return ok(classroomService.addClassroom(dtoClassroomIU)) ;
	}

}
