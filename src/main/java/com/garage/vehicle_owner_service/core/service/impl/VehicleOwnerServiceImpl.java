package com.garage.vehicle_owner_service.core.service.impl;

import com.garage.vehicle_owner_service.core.constant.ApplicationMessages;
import com.garage.vehicle_owner_service.core.exception.ModuleException;
import com.garage.vehicle_owner_service.core.grpc.server.VehicleOwnerAccountCreateRequest;
import com.garage.vehicle_owner_service.core.mapper.MapStructMapper;
import com.garage.vehicle_owner_service.core.model.VehicleOwner;
import com.garage.vehicle_owner_service.core.payload.common.ResponseEntityDto;
import com.garage.vehicle_owner_service.core.payload.request.VehicleOwnerPatchRequestDto;
import com.garage.vehicle_owner_service.core.payload.response.UserResponseDto;
import com.garage.vehicle_owner_service.core.payload.response.VehicleOwnerResponseDto;
import com.garage.vehicle_owner_service.core.repository.VehicleOwnerRepository;
import com.garage.vehicle_owner_service.core.service.UserService;
import com.garage.vehicle_owner_service.core.service.VehicleOwnerService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleOwnerServiceImpl implements VehicleOwnerService {

	@NonNull
	private final VehicleOwnerRepository vehicleOwnerRepository;

	@NonNull
	private final MapStructMapper mapStructMapper;

	@NonNull
	private final MessageSource messageSource;

	@NonNull
	private final UserService userService;

	@Override
	public ResponseEntityDto createVehicleOwnerAccount(
			VehicleOwnerAccountCreateRequest vehicleOwnerAccountCreateRequest) {
		log.debug("VehicleOwnerServiceImpl.createVehicleOwnerAccount(): execution started");

		VehicleOwner vehicleOwner = new VehicleOwner();
		vehicleOwner.setUserId(vehicleOwnerAccountCreateRequest.getUserId());

		VehicleOwner savedVehicleOwner = vehicleOwnerRepository.save(vehicleOwner);
		VehicleOwnerResponseDto responseDto = mapStructMapper.vehicleToVehicleOwnerResponseDto(savedVehicleOwner);

		log.debug("VehicleOwnerServiceImpl.createVehicleOwnerAccount(): execution ended");
		return new ResponseEntityDto(true, responseDto);
	}

	@Override
	public ResponseEntityDto updateVehicleOwner(VehicleOwnerPatchRequestDto vehicleOwnerPatchRequestDto) {
		log.debug("VehicleOwnerServiceImpl.updateVehicleOwner(): execution started");

		UserResponseDto currentUser = userService.getCurrentUser();

		Optional<VehicleOwner> vehicleOwnerOptional = vehicleOwnerRepository.findByUserId(currentUser.getUserId());
		if (vehicleOwnerOptional.isEmpty()) {
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_VEHICLE_OWNER_NOT_FOUND, null, Locale.ENGLISH));
		}
		VehicleOwner vehicleOwner = vehicleOwnerOptional.get();

		if (vehicleOwnerPatchRequestDto.getFirstName() != null) {
			vehicleOwner.setFirstName(vehicleOwnerPatchRequestDto.getFirstName());
		}

		if (vehicleOwnerPatchRequestDto.getLastName() != null) {
			vehicleOwner.setLastName(vehicleOwnerPatchRequestDto.getLastName());
		}

		if (vehicleOwnerPatchRequestDto.getPhoneNumber() != null) {
			vehicleOwner.setPhoneNumber(vehicleOwnerPatchRequestDto.getPhoneNumber());
		}

		VehicleOwner savedVehicleOwner = vehicleOwnerRepository.save(vehicleOwner);

		VehicleOwnerResponseDto vehicleOwnerResponseDto = mapStructMapper
			.vehicleToVehicleOwnerResponseDto(savedVehicleOwner);

		log.debug("VehicleOwnerServiceImpl.updateVehicleOwner(): execution ended");
		return new ResponseEntityDto(true, vehicleOwnerResponseDto);
	}

	@Override
	public ResponseEntityDto getVehicleOwner() {
		log.debug("VehicleOwnerServiceImpl.getVehicleOwner(): execution started");

		UserResponseDto currentUser = userService.getCurrentUser();

		Optional<VehicleOwner> vehicleOwnerOptional = vehicleOwnerRepository.findByUserId(currentUser.getUserId());
		if (vehicleOwnerOptional.isEmpty()) {
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_VEHICLE_OWNER_NOT_FOUND, null, Locale.ENGLISH));
		}
		VehicleOwner vehicleOwner = vehicleOwnerOptional.get();

		VehicleOwnerResponseDto vehicleOwnerResponseDto = mapStructMapper
			.vehicleToVehicleOwnerResponseDto(vehicleOwner);

		log.debug("VehicleOwnerServiceImpl.getVehicleOwner(): execution ended");
		return new ResponseEntityDto(true, vehicleOwnerResponseDto);
	}

	@Override
	public List<VehicleOwnerResponseDto> getAllVehicleOwners() {
		log.debug("VehicleOwnerServiceImpl.getAllVehicleOwners(): execution started");

		List<VehicleOwner> vehicleOwners = vehicleOwnerRepository.findAll();

		List<VehicleOwnerResponseDto> vehicleOwnerResponseDtos = mapStructMapper
			.vehicleOwnerListToVehicleOwnerResponseDtoList(vehicleOwners);

		log.debug("VehicleOwnerServiceImpl.getAllVehicleOwners(): execution ended");
		return vehicleOwnerResponseDtos;
	}

}
