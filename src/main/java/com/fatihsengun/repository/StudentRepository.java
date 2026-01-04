package com.fatihsengun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatihsengun.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


	
}
