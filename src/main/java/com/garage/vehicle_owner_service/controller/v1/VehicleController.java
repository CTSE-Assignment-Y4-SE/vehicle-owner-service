package com.garage.vehicle_owner_service.controller.v1;

import com.garage.vehicle_owner_service.core.payload.common.ResponseEntityDto;
import com.garage.vehicle_owner_service.core.payload.request.VehicleRequestDto;
import com.garage.vehicle_owner_service.core.service.VehicleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

	@NonNull
	private final VehicleService vehicleService;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('VEHICLE_OWNER')")
	public ResponseEntity<ResponseEntityDto> addVehicle(@RequestBody VehicleRequestDto vehicleRequestDto) {
		ResponseEntityDto response = vehicleService.addVehicle(vehicleRequestDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('VEHICLE_OWNER')")
	public ResponseEntity<ResponseEntityDto> getVehicles() {
		ResponseEntityDto response = vehicleService.getVehicles();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('VEHICLE_OWNER')")
	public ResponseEntity<ResponseEntityDto> getVehicle(@PathVariable Long vehicleId) {
		ResponseEntityDto response = vehicleService.getVehicle(vehicleId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping(value = "/{vehicleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('VEHICLE_OWNER')")
	public ResponseEntity<ResponseEntityDto> updateVehicle(@PathVariable Long vehicleId,
			@RequestBody VehicleRequestDto vehicleRequestDto) {
		ResponseEntityDto response = vehicleService.updateVehicle(vehicleId, vehicleRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('VEHICLE_OWNER')")
	public ResponseEntity<ResponseEntityDto> deleteVehicle(@PathVariable Long vehicleId) {
		ResponseEntityDto response = vehicleService.deleteVehicle(vehicleId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
