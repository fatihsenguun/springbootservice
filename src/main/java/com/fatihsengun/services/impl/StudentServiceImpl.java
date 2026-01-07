package com.fatihsengun.services.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;
import com.fatihsengun.dto.DtoClassroom;
import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;
import com.fatihsengun.entities.Address;
import com.fatihsengun.entities.Classroom;
import com.fatihsengun.entities.Student;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.repository.AddressRepository;
import com.fatihsengun.repository.ClassroomRepository;
import com.fatihsengun.repository.StudentRepository;
import com.fatihsengun.services.IStudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

	private final StudentRepository studentRepository;

	private final AddressRepository addressRepository;

	private final ClassroomRepository classroomRepository;

	
	
	@Override
	public DtoStudent addStudent(DtoStudentIU dtoStudentIU) {
		Student student = new Student();
		Address address = new Address();

		Address responseAddress = new Address();
		DtoStudent dtoStudent = new DtoStudent();
		DtoAddress dtoAddress = new DtoAddress();

		// save address
		BeanUtils.copyProperties(dtoStudentIU.getAddress(), address);
		responseAddress = addressRepository.save(address);
		BeanUtils.copyProperties(responseAddress, dtoAddress);

		// classroom
		Optional<Classroom> optional = classroomRepository.findById(dtoStudentIU.getClassroomId());
		if (Objects.nonNull(optional) && !optional.isEmpty()) {
			if (Objects.nonNull(optional.get())) {
				Classroom classroom = new Classroom();
				DtoClassroom dtoClassroom = new DtoClassroom();
				classroom = optional.get();
				student.setClassroom(classroom);
				BeanUtils.copyProperties(classroom, dtoClassroom);
				dtoStudent.setClassroom(dtoClassroom);
			}
		} 

		BeanUtils.copyProperties(dtoStudentIU, student);
		student.setAddress(responseAddress);
		BeanUtils.copyProperties(studentRepository.save(student), dtoStudent);
		dtoStudent.setAddress(dtoAddress);

		return dtoStudent;
	}

	
	
	@Override
	public DtoStudent getStudentById(Long id) {
		DtoStudent dtoStudent = new DtoStudent();
		DtoAddress dtoAddress = new DtoAddress();

		Optional<Student> student = studentRepository.findById(id);
		if (student.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST ,id.toString()));
		}

		Student responseStudent = student.get();
		Classroom responseClassroom = responseStudent.getClassroom();
		if (responseClassroom != null) {
			DtoClassroom dtoClassroom = new DtoClassroom();
			Optional<Classroom> optionalClassroom = classroomRepository.findById(responseClassroom.getId());
			if (optionalClassroom.isEmpty()) {
				dtoClassroom = null;
			}else {
				BeanUtils.copyProperties(optionalClassroom.get(), dtoClassroom);
				dtoStudent.setClassroom(dtoClassroom);
			}

		}

		Optional<Address> address = addressRepository.findById(responseStudent.getAddress().getId());
		if (address.isEmpty()) {
			dtoAddress = null;
		} else {
			BeanUtils.copyProperties(address.get(), dtoAddress);
		}

		BeanUtils.copyProperties(responseStudent, dtoStudent);
		dtoStudent.setAddress(dtoAddress);

		return dtoStudent;
	}

	@Override

	public DtoStudent updateStudent(DtoStudentIU dtoStudentIU, Long id) {

		DtoStudent response = new DtoStudent();
		DtoAddress dtoAddress1 = new DtoAddress();
		DtoClassroom dtoClassroom = new DtoClassroom();

		Optional<Student> optional = studentRepository.findById(id);

		if (optional.isEmpty()) {
			return null;
		} else {
			DtoAddressIU dtoAddress = dtoStudentIU.getAddress();
			Student responseStudent = optional.get();
			Address responseAddress = responseStudent.getAddress();
			if (Objects.nonNull(dtoStudentIU.getFirstName())) {
				responseStudent.setFirstName(dtoStudentIU.getFirstName());
			}
			if (Objects.nonNull(dtoStudentIU.getLastName())) {
				responseStudent.setLastName(dtoStudentIU.getLastName());
			}
			if (Objects.nonNull(dtoStudentIU.getTckn())) {
				responseStudent.setTckn(dtoStudentIU.getTckn());
			}
			if (Objects.nonNull(dtoStudentIU.getClassroomId())) {
				Optional<Classroom> clasroomOptional = classroomRepository.findById(dtoStudentIU.getClassroomId());
				if (!clasroomOptional.isEmpty()) {
					Classroom responseClassroom = clasroomOptional.get();
					responseStudent.setClassroom(responseClassroom);
					BeanUtils.copyProperties(responseClassroom, dtoClassroom);
					response.setClassroom(dtoClassroom);
				}
			}
			if (Objects.nonNull(dtoAddress)) {
				if (Objects.nonNull(dtoAddress.getDescription())
						&& dtoAddress.getDescription() != responseAddress.getDescription()) {

					responseAddress.setDescription(dtoAddress.getDescription());
				}
			}
			responseStudent.setAddress(responseAddress);
			BeanUtils.copyProperties(addressRepository.save(responseAddress), dtoAddress1);
			BeanUtils.copyProperties(studentRepository.save(responseStudent), response);
			response.setAddress(dtoAddress1);
			return response;
		}
	}

	@Override
	public DtoStudent deleteStudentById(Long id) {
		DtoStudent dtoStudent = new DtoStudent();
		DtoAddress dtoAddress = new DtoAddress();
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}

		if (optional.get() == null) {
			return null;
		}

		studentRepository.deleteById(id);
		addressRepository.deleteById(optional.get().getAddress().getId());
		BeanUtils.copyProperties(optional.get(), dtoStudent);
		BeanUtils.copyProperties(optional.get().getAddress(), dtoAddress);
		dtoStudent.setAddress(dtoAddress);

		return dtoStudent;
	}

	@Override
	public Page<Student> findAllPageable(Pageable pageable) {

		Page<Student> page = studentRepository.findAll(pageable);
		
		return page;
	}
	
	public List<DtoStudent> toDTOList(List<Student> students){
		List<DtoStudent> list = new ArrayList<>() ;
		for (Student student : students) {
			DtoStudent dtoStudent = new DtoStudent();
			DtoAddress dtoAddress = new DtoAddress();
			DtoClassroom dtoClassroom = new DtoClassroom();
			
			BeanUtils.copyProperties(student, dtoStudent);
			BeanUtils.copyProperties(student.getClassroom(), dtoClassroom);
			BeanUtils.copyProperties(student.getAddress(), dtoAddress);
			
			dtoStudent.setAddress(dtoAddress);
			dtoStudent.setClassroom(dtoClassroom);
			
			list.add(dtoStudent);
		}
		return list;
		
	}

}
