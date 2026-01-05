package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoClassroom;
import com.fatihsengun.dto.DtoClassroomIU;
import com.fatihsengun.entities.RootEntity;

public interface IClassroomController {
	public RootEntity<DtoClassroom> addClassroom(DtoClassroomIU dtoClassroomIU);
}
