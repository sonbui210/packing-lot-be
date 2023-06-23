package com.sobu.parkinglot.api.bus;

import com.sobu.parkinglot.data.model.Route;
import lombok.Data;

import java.util.Set;

@Data
public class BusDto {
    private Long id;

    private String licensePlate;

    private Route route;

    private String code;

    private String driver;

    private String phone;

    private Set<String> waypoint;
}
