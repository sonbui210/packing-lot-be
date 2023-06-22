package com.sobu.parkinglot.api.user.payload;

import lombok.Data;

@Data
public class AddTokenFirebaseDto {
    private long id;

    private String username;

    private String token;
}
