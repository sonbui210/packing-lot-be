package com.sobu.parkinglot.api.user;


import com.sobu.parkinglot.api.user.payload.UpdateUserDto;
import com.sobu.parkinglot.data.models.User;
import com.sobu.parkinglot.data.repository.UserRepository;
import com.sobu.parkinglot.payload.app.entity.AppException;
import com.sobu.parkinglot.payload.app.entity.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@RestController
//@RequestMapping("/api/user")
//@Slf4j
//@RequiredArgsConstructor
public class UserController {

    /*private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity getAllUser() {
        return Response.data(userRepository.findAll());
    }

    @GetMapping("/seach")
    public ResponseEntity serachUser(@RequestParam String searchKey) {
        return Response.data(userRepository.searchUser(searchKey));
    }

    @GetMapping("/findById")
    public ResponseEntity findById(@RequestParam long id) {
        return Response.data(userRepository.findById(id));
    }*/

    /*@PostMapping("/update")
    public ResponseEntity updateUserInfo(@RequestBody UpdateUserDto request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user != null) {
            user.setEmail(request.getEmail());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setName(request.getName());

            user = userRepository.save(user);
            return Response.data(user);
        } else  {
            throw new AppException("User does not exist!");
        }

    }*/


}
