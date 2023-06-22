package com.sobu.parkinglot.data.enums

enum class ECodeError(val code: Int) {
    SUCCESS(1),
    USERNAME_ALREADY(100),
    EMAIL_ALREADY(101),
    PHONENUMBER_ALREADY(102)

}