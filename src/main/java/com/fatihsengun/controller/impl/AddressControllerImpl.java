package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IAddressController;
import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;
import com.fatihsengun.services.IAddressService;

@RestController
@RequestMapping(path = "rest/api/address")
public class AddressControllerImpl implements IAddressController {

	@Autowired
	private IAddressService addressService;
	
	
	@Override
	@PostMapping("/add")
	public DtoAddress addAddress(@RequestBody DtoAddressIU dtoAddressIU) {
		return addressService.addAddress(dtoAddressIU);
	}

}
