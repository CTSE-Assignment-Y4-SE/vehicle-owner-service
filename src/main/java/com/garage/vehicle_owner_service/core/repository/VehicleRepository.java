package com.garage.vehicle_owner_service.core.repository;

import com.garage.vehicle_owner_service.core.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	Optional<Vehicle> findByVehicleId(Long vehicleId);

}
