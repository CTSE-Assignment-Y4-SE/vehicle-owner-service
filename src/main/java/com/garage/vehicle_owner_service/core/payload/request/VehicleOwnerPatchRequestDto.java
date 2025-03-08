package com.garage.vehicle_owner_service.core.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleOwnerPatchRequestDto {

	private String firstName;

	private String lastName;

	private String phoneNumber;

}
