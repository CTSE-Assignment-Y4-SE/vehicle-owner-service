package com.garage.vehicle_owner_service.core.payload.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleResponseDto {

	private Long vehicleId;

	private String brand;

	private String model;

	private Long year;

	private String licensePlate;

	private String image;

	private VehicleOwnerResponseDto vehicleOwner;

}
