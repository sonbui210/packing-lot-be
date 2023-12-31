package com.example.ParkingLot_BE.app.controller.auth;


import com.example.ParkingLot_BE.app.controller.auth.payload.LoginRequest;
import com.example.ParkingLot_BE.app.controller.auth.payload.RegisterRequest;
import com.example.ParkingLot_BE.app.entity.Response;
import com.example.ParkingLot_BE.app.helper.UserHelper;
import com.example.ParkingLot_BE.app.mess.Message;
import com.example.ParkingLot_BE.app.security.CurrentUser;
import com.example.ParkingLot_BE.app.security.JwtAuthenticationResponse;
import com.example.ParkingLot_BE.app.security.JwtTokenProvider;
import com.example.ParkingLot_BE.app.service.UserService;
import com.example.ParkingLot_BE.data.model.CustomUserDetails;
import com.example.ParkingLot_BE.data.model.Role;
import com.example.ParkingLot_BE.data.model.User;
import com.example.ParkingLot_BE.data.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserHelper userHelper;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity authenticateUser(@CurrentUser CustomUserDetails userDetails) {
        if (userDetails != null) {
            return Response.data(userDetails.getUser());
        } else {
            return Response.error(HttpStatus.UNAUTHORIZED, Message.DANG_NHAP_LAI);
        }

    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest request) {
        try {
            // Xác thực từ username và password.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            String jwt = tokenProvider.generateToken(authentication);
            return Response.data(new JwtAuthenticationResponse(jwt, authentication.getPrincipal()));
        } catch (BadCredentialsException e) {
            return Response.error(HttpStatus.BAD_REQUEST, "Tên đăng nhập hoặc tài khoản không chính xác.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest request) {
        String error = userHelper.checkRegisterUserRequest(request);

        if (StringUtils.isEmpty(error)) {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());

            user = userService.save(user);

            return Response.data(user);
        } else {
            return Response.error(HttpStatus.BAD_REQUEST, error);
        }
    }
}
