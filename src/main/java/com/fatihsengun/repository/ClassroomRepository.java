package com.fatihsengun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatihsengun.entities.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
