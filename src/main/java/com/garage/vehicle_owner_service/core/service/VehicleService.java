package com.garage.vehicle_owner_service.core.service;

import com.garage.vehicle_owner_service.core.payload.common.ResponseEntityDto;
import com.garage.vehicle_owner_service.core.payload.request.VehicleRequestDto;

public interface VehicleService {

	ResponseEntityDto addVehicle(VehicleRequestDto vehicleRequestDto);

	ResponseEntityDto getVehicles();

	ResponseEntityDto getVehicle(Long vehicleId);

	ResponseEntityDto updateVehicle(Long vehicleId, VehicleRequestDto vehicleRequestDto);

	ResponseEntityDto deleteVehicle(Long vehicleId);

}
