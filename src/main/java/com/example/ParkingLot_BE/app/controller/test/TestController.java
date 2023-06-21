package com.example.ParkingLot_BE.app.controller.test;


import com.example.ParkingLot_BE.app.entity.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Slf4j
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public ResponseEntity searchUser(@RequestParam String searchKey) {
        return Response.data("Hello kitt");
    }
}
