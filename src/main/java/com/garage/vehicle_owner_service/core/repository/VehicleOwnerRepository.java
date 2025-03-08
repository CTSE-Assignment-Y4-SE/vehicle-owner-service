package com.garage.vehicle_owner_service.core.repository;

import com.garage.vehicle_owner_service.core.model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, Long> {

	Optional<VehicleOwner> findByVehicleOwnerId(Long vehicleId);

	Optional<VehicleOwner> findByUserId(Long vehicleId);

}
