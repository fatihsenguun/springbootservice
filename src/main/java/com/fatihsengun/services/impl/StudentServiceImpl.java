package com.fatihsengun.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;
import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;
import com.fatihsengun.entities.Address;
import com.fatihsengun.entities.Student;
import com.fatihsengun.repository.AddressRepository;
import com.fatihsengun.repository.StudentRepository;
import com.fatihsengun.services.IStudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

	private final StudentRepository studentRepository;

	private final AddressRepository addressRepository;

	@Override
	public DtoStudent addStudent(DtoStudentIU dtoStudentIU) {
		Student student = new Student();
		Address responseAddress = new Address();
		DtoStudent dtoStudent = new DtoStudent();
		Address address = new Address();
		DtoAddress dtoAddress = new DtoAddress();

		BeanUtils.copyProperties(dtoStudentIU.getAddress(), address);
		responseAddress = addressRepository.save(address);
		BeanUtils.copyProperties(responseAddress, dtoAddress);

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
			return null;
		}

		Student responseStudent = student.get();

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
			if (Objects.nonNull(dtoAddress)) {
				if ( Objects.nonNull(dtoAddress.getDescription())
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

}
