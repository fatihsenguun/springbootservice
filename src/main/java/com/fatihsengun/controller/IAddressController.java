package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;
import com.fatihsengun.entities.RootEntity;

public interface IAddressController {
	
	public RootEntity<DtoAddress>  addAddress(DtoAddressIU dtoAddressIU);

}
