package com.example.ParkingLot_BE.app.controller.user;


import com.example.ParkingLot_BE.app.controller.user.payload.ChangePasswordDto;
import com.example.ParkingLot_BE.app.controller.user.payload.UpdateUserDto;
import com.example.ParkingLot_BE.app.entity.AppException;
import com.example.ParkingLot_BE.app.entity.Response;
import com.example.ParkingLot_BE.data.model.User;
import com.example.ParkingLot_BE.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity getAllUsers() {
        return Response.data(userRepository.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity searchUser(@RequestParam String searchKey) {
        return Response.data(userRepository.searchUser(searchKey));
    }

    @GetMapping("/findById")
    public ResponseEntity findById(@RequestParam long id) {
        return Response.data(userRepository.findById(id));
    }

    @PostMapping("/update")
    public ResponseEntity updateUserInfo(@RequestBody UpdateUserDto request) {
        User user = userRepository.findById(request.getId());
        if (user != null) {
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());

            user = userRepository.save(user);
            return Response.data(user);
        } else {
            throw new AppException("User does not exist");
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDto request) {
        User user = userRepository.findById(request.getUserId());
        if (user != null) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));

            user = userRepository.save(user);
            return Response.data(user);
        } else {
            throw new AppException("User does not exist");
        }
    }
}
