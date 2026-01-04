package com.fatihsengun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatihsengun.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
