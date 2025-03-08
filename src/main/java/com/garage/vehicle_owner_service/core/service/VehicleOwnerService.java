package com.garage.vehicle_owner_service.core.service;

import com.garage.vehicle_owner_service.core.grpc.server.VehicleOwnerAccountCreateRequest;
import com.garage.vehicle_owner_service.core.payload.common.ResponseEntityDto;
import com.garage.vehicle_owner_service.core.payload.request.VehicleOwnerPatchRequestDto;
import com.garage.vehicle_owner_service.core.payload.response.VehicleOwnerResponseDto;

import java.util.List;

public interface VehicleOwnerService {

	ResponseEntityDto createVehicleOwnerAccount(VehicleOwnerAccountCreateRequest vehicleOwnerAccountCreateRequest);

	ResponseEntityDto updateVehicleOwner(VehicleOwnerPatchRequestDto vehicleOwnerPatchRequestDto);

	ResponseEntityDto getVehicleOwner();

	List<VehicleOwnerResponseDto> getAllVehicleOwners();

}
