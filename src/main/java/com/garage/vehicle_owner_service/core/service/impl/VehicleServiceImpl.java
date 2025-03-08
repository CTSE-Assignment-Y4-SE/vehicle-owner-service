package com.garage.vehicle_owner_service.core.service.impl;

import com.garage.vehicle_owner_service.core.constant.ApplicationMessages;
import com.garage.vehicle_owner_service.core.exception.ModuleException;
import com.garage.vehicle_owner_service.core.mapper.MapStructMapper;
import com.garage.vehicle_owner_service.core.model.Vehicle;
import com.garage.vehicle_owner_service.core.model.VehicleOwner;
import com.garage.vehicle_owner_service.core.payload.common.ResponseEntityDto;
import com.garage.vehicle_owner_service.core.payload.request.VehicleRequestDto;
import com.garage.vehicle_owner_service.core.payload.response.UserResponseDto;
import com.garage.vehicle_owner_service.core.payload.response.VehicleResponseDto;
import com.garage.vehicle_owner_service.core.repository.VehicleOwnerRepository;
import com.garage.vehicle_owner_service.core.repository.VehicleRepository;
import com.garage.vehicle_owner_service.core.service.UserService;
import com.garage.vehicle_owner_service.core.service.VehicleService;
import jakarta.transaction.Transactional;
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
public class VehicleServiceImpl implements VehicleService {

	@NonNull
	private final VehicleRepository vehicleRepository;

	@NonNull
	private final MapStructMapper mapStructMapper;

	@NonNull
	private final UserService userService;

	@NonNull
	private final VehicleOwnerRepository vehicleOwnerRepository;

	@NonNull
	private final MessageSource messageSource;

	@Override
	@Transactional
	public ResponseEntityDto addVehicle(VehicleRequestDto vehicleRequestDto) {
		log.debug("VehicleServiceImpl.addVehicle(): execution started");

		UserResponseDto currentUser = userService.getCurrentUser();
		Optional<VehicleOwner> optionalVehicleOwner = vehicleOwnerRepository.findByUserId(currentUser.getUserId());
		if (optionalVehicleOwner.isEmpty()) {
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_VEHICLE_OWNER_NOT_FOUND, null, Locale.ENGLISH));
		}

		Vehicle vehicle = mapStructMapper.vehicleRequestDtoToVehicle(vehicleRequestDto);
		vehicle.setVehicleOwner(optionalVehicleOwner.get());
		Vehicle savedVehicle = vehicleRepository.save(vehicle);

		VehicleResponseDto vehicleResponseDto = mapStructMapper.vehicleToVehicleResponseDto(savedVehicle);

		log.debug("VehicleServiceImpl.addVehicle(): execution ended");
		return new ResponseEntityDto(true, vehicleResponseDto);
	}

	@Override
	public ResponseEntityDto getVehicles() {
		log.debug("VehicleServiceImpl.getVehicles(): execution started");

		UserResponseDto currentUser = userService.getCurrentUser();
		Optional<VehicleOwner> optionalVehicleOwner = vehicleOwnerRepository.findByUserId(currentUser.getUserId());
		if (optionalVehicleOwner.isEmpty()) {
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_VEHICLE_OWNER_NOT_FOUND, null, Locale.ENGLISH));
		}

		List<Vehicle> vehicles = optionalVehicleOwner.get().getVehicles();

		List<VehicleResponseDto> vehicleResponseDtos = mapStructMapper.vehicleListToVehicleResponseDtoList(vehicles);

		log.debug("VehicleServiceImpl.getVehicles(): execution ended");
		return new ResponseEntityDto(true, vehicleResponseDtos);
	}

	@Override
	public ResponseEntityDto getVehicle(Long vehicleId) {
		log.debug("VehicleServiceImpl.getVehicle(): execution started");

		UserResponseDto currentUser = userService.getCurrentUser();

		Vehicle vehicle = getVehicleById(vehicleId);

		if (!vehicle.getVehicleOwner().getUserId().equals(currentUser.getUserId())) {
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_VEHICLE_OWNER_NOT_FOUND, null, Locale.ENGLISH));
		}

		VehicleResponseDto vehicleResponseDto = mapStructMapper.vehicleToVehicleResponseDto(vehicle);

		log.debug("VehicleServiceImpl.getVehicle(): execution ended");
		return new ResponseEntityDto(true, vehicleResponseDto);
	}

	private Vehicle getVehicleById(Long vehicleId) {
		Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleId(vehicleId);
		if (optionalVehicle.isEmpty()) {
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_VEHICLE_NOT_FOUND, null, Locale.ENGLISH));
		}
		return optionalVehicle.get();
	}

	@Override
	@Transactional
	public ResponseEntityDto updateVehicle(Long vehicleId, VehicleRequestDto vehicleRequestDto) {
		log.debug("VehicleServiceImpl.updateVehicle(): execution started");

		UserResponseDto currentUser = userService.getCurrentUser();

		Vehicle vehicle = getVehicleById(vehicleId);

		if (!vehicle.getVehicleOwner().getUserId().equals(currentUser.getUserId())) {
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_VEHICLE_OWNER_NOT_FOUND, null, Locale.ENGLISH));
		}

		if (vehicleRequestDto.getBrand() != null) {
			vehicle.setBrand(vehicleRequestDto.getBrand());
		}

		if (vehicleRequestDto.getModel() != null) {
			vehicle.setModel(vehicleRequestDto.getModel());
		}

		if (vehicleRequestDto.getYear() != null) {
			vehicle.setYear(vehicleRequestDto.getYear());
		}

		if (vehicleRequestDto.getLicensePlate() != null) {
			vehicle.setLicensePlate(vehicleRequestDto.getLicensePlate());
		}

		if (vehicleRequestDto.getImage() != null) {
			vehicle.setImage(vehicleRequestDto.getImage());
		}

		Vehicle savedVehicle = vehicleRepository.save(vehicle);

		VehicleResponseDto vehicleResponseDto = mapStructMapper.vehicleToVehicleResponseDto(savedVehicle);

		log.debug("VehicleServiceImpl.updateVehicle(): execution ended");
		return new ResponseEntityDto(true, vehicleResponseDto);
	}

	@Override
	@Transactional
	public ResponseEntityDto deleteVehicle(Long vehicleId) {
		log.debug("VehicleServiceImpl.deleteVehicle(): execution started");

		UserResponseDto currentUser = userService.getCurrentUser();

		Vehicle vehicle = getVehicleById(vehicleId);

		if (!vehicle.getVehicleOwner().getUserId().equals(currentUser.getUserId())) {
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_VEHICLE_OWNER_NOT_FOUND, null, Locale.ENGLISH));
		}

		vehicleRepository.delete(vehicle);

		log.debug("VehicleServiceImpl.deleteVehicle(): execution ended");
		return new ResponseEntityDto(true, null);
	}

}
