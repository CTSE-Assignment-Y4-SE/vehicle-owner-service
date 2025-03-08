package com.garage.vehicle_owner_service.core.service;

import com.garage.vehicle_owner_service.core.grpc.server.TokenValidateResponse;
import com.garage.vehicle_owner_service.core.payload.response.UserResponseDto;
import lombok.NonNull;

public interface UserService {

	TokenValidateResponse isTokenValid(@NonNull String token);

	UserResponseDto getCurrentUser();

}
