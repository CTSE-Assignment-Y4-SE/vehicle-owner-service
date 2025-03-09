package com.garage.vehicle_owner_service.controller.v2;

import com.garage.vehicle_owner_service.core.payload.common.ResponseEntityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/health")
@RequiredArgsConstructor
public class HealthController {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntityDto> getHealth() {
        ResponseEntityDto response = new ResponseEntityDto("App is runningggg!", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
