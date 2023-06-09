package com.example.ParkingLot_BE.app.controller.auth.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private String username ;

    @NotBlank
    private String password;

}
