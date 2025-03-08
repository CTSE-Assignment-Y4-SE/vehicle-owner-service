package com.garage.vehicle_owner_service.controller.v1;

import com.garage.vehicle_owner_service.core.payload.common.ResponseEntityDto;
import com.garage.vehicle_owner_service.core.payload.request.VehicleOwnerPatchRequestDto;
import com.garage.vehicle_owner_service.core.service.VehicleOwnerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle/account")
@RequiredArgsConstructor
public class VehicleOwnerController {

	@NonNull
	private final VehicleOwnerService vehicleOwnerService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('VEHICLE_OWNER')")
	public ResponseEntity<ResponseEntityDto> getVehicleOwner() {
		ResponseEntityDto response = vehicleOwnerService.getVehicleOwner();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('VEHICLE_OWNER')")
	public ResponseEntity<ResponseEntityDto> updateVehicleOwner(
			@RequestBody VehicleOwnerPatchRequestDto vehicleOwnerPatchRequestDto) {
		ResponseEntityDto response = vehicleOwnerService.updateVehicleOwner(vehicleOwnerPatchRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
