package com.garage.vehicle_owner_service.core.mapper;

import com.garage.vehicle_owner_service.core.model.Vehicle;
import com.garage.vehicle_owner_service.core.model.VehicleOwner;
import com.garage.vehicle_owner_service.core.payload.request.VehicleRequestDto;
import com.garage.vehicle_owner_service.core.payload.response.VehicleOwnerResponseDto;
import com.garage.vehicle_owner_service.core.payload.response.VehicleResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	VehicleOwnerResponseDto vehicleToVehicleOwnerResponseDto(VehicleOwner savedVehicleOwner);

	Vehicle vehicleRequestDtoToVehicle(VehicleRequestDto vehicleRequestDto);

	VehicleResponseDto vehicleToVehicleResponseDto(Vehicle vehicle);

	List<VehicleResponseDto> vehicleListToVehicleResponseDtoList(List<Vehicle> vehicles);

	List<VehicleOwnerResponseDto> vehicleOwnerListToVehicleOwnerResponseDtoList(List<VehicleOwner> vehicleOwners);

}
