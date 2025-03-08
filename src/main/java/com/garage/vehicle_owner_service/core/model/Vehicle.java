package com.garage.vehicle_owner_service.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "vehicle")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicle_id")
	private Long vehicleId;

	@Column(name = "brand")
	private String brand;

	@Column(name = "model")
	private String model;

	@Column(name = "year")
	private Long year;

	@Column(name = "license_plate")
	private String licensePlate;

	@Column(name = "image")
	private String image;

	@Column(name = "created_at", updatable = false)
	private Instant createdAt = Instant.now();

	@Column(name = "updated_at")
	private Instant updatedAt = Instant.now();

	@ManyToOne
	@JoinColumn(name = "vehicle_owner_id", nullable = false)
	private VehicleOwner vehicleOwner;

}
