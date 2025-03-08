package com.garage.vehicle_owner_service.core.payload.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleOwnerResponseDto {

	private Long vehicleOwnerId;

	private Long userId;

	private String firstName;

	private String lastName;

	private String phoneNumber;

}
