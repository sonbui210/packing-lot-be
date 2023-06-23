package com.sobu.parkinglot.data.model;

import lombok.Data;

@Data
public class Place {
    private String id;

    private String icon;

    private String name;

    private Location location;
}
