package com.garage.vehicle_owner_service.core.grpc.server;

import com.garage.vehicle_owner_service.core.payload.response.VehicleOwnerResponseDto;
import com.garage.vehicle_owner_service.core.service.VehicleOwnerService;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@AllArgsConstructor
public class VehicleOwnerAccountServiceImpl extends VehicleOwnerAccountServiceGrpc.VehicleOwnerAccountServiceImplBase {

	@NonNull
	private final VehicleOwnerService vehicleOwnerService;

	@Override
	public void createVehicleOwnerAccount(VehicleOwnerAccountCreateRequest vehicleOwnerAccountCreateRequest,
			StreamObserver<VehicleOwnerAccountCreateResponse> responseStreamObserver) {
		VehicleOwnerResponseDto vehicleOwnerResponseDto = (VehicleOwnerResponseDto) vehicleOwnerService
			.createVehicleOwnerAccount(vehicleOwnerAccountCreateRequest)
			.getResults()
			.getFirst();

		VehicleOwnerAccountCreateResponse tokenValidateResponse = VehicleOwnerAccountCreateResponse.newBuilder()
			.setVehicleOwnerId(vehicleOwnerResponseDto.getVehicleOwnerId())
			.setUserId(vehicleOwnerResponseDto.getUserId())
			.build();
		responseStreamObserver.onNext(tokenValidateResponse);
		responseStreamObserver.onCompleted();
	}

	@Override
	public void getAllVehicleOwners(Empty request, StreamObserver<VehicleOwnerAccountListResponse> responseObserver) {
		List<VehicleOwnerResponseDto> vehicleOwners = vehicleOwnerService.getAllVehicleOwners();

		VehicleOwnerAccountListResponse.Builder listResponseBuilder = VehicleOwnerAccountListResponse.newBuilder();

		for (VehicleOwnerResponseDto owner : vehicleOwners) {
			VehicleOwnerAccountCreateResponse ownerResponse = VehicleOwnerAccountCreateResponse.newBuilder()
				.setVehicleOwnerId(owner.getVehicleOwnerId())
				.setUserId(owner.getUserId())
				.setFirstName(owner.getFirstName() != null ? owner.getFirstName() : "")
				.setLastName(owner.getLastName() != null ? owner.getLastName() : "")
				.setPhoneNumber(owner.getPhoneNumber() != null ? owner.getPhoneNumber() : "")
				.build();

			listResponseBuilder.addVehicleOwners(ownerResponse);
		}

		responseObserver.onNext(listResponseBuilder.build());
		responseObserver.onCompleted();
	}

}
