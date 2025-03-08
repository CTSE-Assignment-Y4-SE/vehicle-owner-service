package com.garage.vehicle_owner_service.core.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "vehicle_owner")
public class VehicleOwner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicle_owner_id")
	private Long vehicleOwnerId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "first_name", nullable = true, length = 100)
	private String firstName;

	@Column(name = "last_name", nullable = true, length = 100)
	private String lastName;

	@Column(name = "phone_number", unique = true, length = 15)
	private String phoneNumber;

	@Column(name = "created_at", updatable = false)
	private Instant createdAt = Instant.now();

	@Column(name = "updated_at")
	private Instant updatedAt = Instant.now();

	@OneToMany(mappedBy = "vehicleOwner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vehicle> vehicles;

}
