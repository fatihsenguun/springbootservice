package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;

public interface IAddressController {
	
	public DtoAddress addAddress(DtoAddressIU dtoAddressIU);

}
