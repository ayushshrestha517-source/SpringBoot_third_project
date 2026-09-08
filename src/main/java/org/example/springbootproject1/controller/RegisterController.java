package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.UsersDAO;
import org.example.springbootproject1.dto.request.LoginRequestDTO;
import org.example.springbootproject1.dto.request.SignupRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.LoginResponseDTO;
import org.example.springbootproject1.dto.response.SignupResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegisterController {
    final UsersDAO usersDAO;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<SignupResponseDTO>> signup(@Valid @RequestBody SignupRequestDTO signupRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(usersDAO.signup(signupRequestDTO),"User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(usersDAO.login(loginRequestDTO),"User logged in successfully"));
    }
}
