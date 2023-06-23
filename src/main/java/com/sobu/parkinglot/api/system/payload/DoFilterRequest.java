package com.sobu.parkinglot.api.system.payload;

import lombok.Data;

@Data
public class DoFilterRequest {
    private int page;

    private int pageSize;

    private String searchKey;

    private String status;
}
