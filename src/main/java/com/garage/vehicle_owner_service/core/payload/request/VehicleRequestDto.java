package com.garage.vehicle_owner_service.core.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleRequestDto {

	private String brand;

	private String model;

	private Long year;

	private String licensePlate;

	private String image;

}
