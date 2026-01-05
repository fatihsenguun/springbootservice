package com.fatihsengun.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoClassroom;
import com.fatihsengun.dto.DtoClassroomIU;
import com.fatihsengun.entities.Classroom;
import com.fatihsengun.repository.ClassroomRepository;
import com.fatihsengun.services.IClassroomService;

@Service
public class ClassroomServiceImpl implements IClassroomService {

	@Autowired
	private ClassroomRepository classroomRepository;
	
	@Override
	public DtoClassroom addClassroom(DtoClassroomIU dtoClassroomIU) {
		Classroom classroom = new Classroom();
		DtoClassroom response = new DtoClassroom();
		
		BeanUtils.copyProperties(dtoClassroomIU, classroom);
		BeanUtils.copyProperties(classroomRepository.save(classroom), response);
		
		return response;
	}

}
