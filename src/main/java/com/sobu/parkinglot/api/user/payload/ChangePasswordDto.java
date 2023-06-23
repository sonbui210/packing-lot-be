package com.sobu.parkinglot.api.user.payload;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private long userId;

    private String newPassword;
}
