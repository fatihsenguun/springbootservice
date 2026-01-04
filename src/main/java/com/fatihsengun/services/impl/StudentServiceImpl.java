package com.fatihsengun.services.impl;

import java.util.Optional;
import com.fatihsengun.starter.FirstProjectApplication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoStudent;
import com.fatihsengun.dto.DtoStudentIU;
import com.fatihsengun.entities.Student;
import com.fatihsengun.repository.StudentRepository;
import com.fatihsengun.services.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {


	
	@Autowired
    private	StudentRepository studentRepository;

	@Override
	public DtoStudent addStudent(DtoStudentIU dtoStudentIU) {
		Student student = new Student();
		Student responseStudent = new Student();
		DtoStudent dtoStudent = new DtoStudent();
		
		
		BeanUtils.copyProperties(dtoStudentIU, student);
		responseStudent= studentRepository.save(student);
		BeanUtils.copyProperties(responseStudent, dtoStudent);

		return dtoStudent;
	}

	@Override
	public DtoStudent getStudentById(Long id) {
		DtoStudent dtoStudent=new DtoStudent();
		Optional<Student> student=studentRepository.findById(id);
		
		if (student.isEmpty()) {
			return null;
		}
		Student responseStudent = student.get();
		BeanUtils.copyProperties(responseStudent, dtoStudent);
		
		return dtoStudent;
	}

	@Override
	public DtoStudent updateStudent(DtoStudent dtoStudent) {
		Student student = new Student();
		DtoStudent response = new DtoStudent();
		
		
		Optional<Student> optional= studentRepository.findById(dtoStudent.getId());
		Student responseStudent= optional.get();
		
		if (responseStudent==null) {
		 return null;
		}
		else {
			student.setId(responseStudent.getId());
			if (responseStudent.getFirstName().equals(dtoStudent.getFirstName())) {
				
				student.setFirstName(responseStudent.getFirstName());
			}else {
				student.setFirstName(dtoStudent.getFirstName());
				
			}
			if (responseStudent.getLastName().equals(dtoStudent.getLastName())) {
				student.setLastName(responseStudent.getLastName());
			}else {
				student.setLastName(dtoStudent.getLastName());
				
			}
			BeanUtils.copyProperties(studentRepository.save(student), response);
			
			
			return response;
			
		}
		
	}

}
