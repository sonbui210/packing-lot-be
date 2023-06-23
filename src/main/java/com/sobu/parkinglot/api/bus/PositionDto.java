package com.sobu.parkinglot.api.bus;

import lombok.Data;

@Data
public class PositionDto {

    private Long busId;

    private double lng;

    private double lat;
}
