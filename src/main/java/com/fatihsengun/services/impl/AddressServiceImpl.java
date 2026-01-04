package com.fatihsengun.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;
import com.fatihsengun.entities.Address;
import com.fatihsengun.repository.AddressRepository;
import com.fatihsengun.services.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public DtoAddress addAddress(DtoAddressIU dtoAddressIU) {
		Address address = new Address();
		DtoAddress response = new DtoAddress();
		
		BeanUtils.copyProperties(dtoAddressIU, address);
		BeanUtils.copyProperties(addressRepository.save(address), response);
		
		return response;
	}

}
