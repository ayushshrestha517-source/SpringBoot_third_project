package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.EmailOtpDAO;
import org.example.springbootproject1.dto.request.EmailOtpRequestDTO;
import org.example.springbootproject1.dto.request.VerifyOtpRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.EmailOtpResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailOtpController {
    private final EmailOtpDAO emailOtpDAO;

    @PostMapping("/sendotp")
    public ResponseEntity<ApiResponse<EmailOtpResponseDTO>> sendOtp(@Valid @RequestBody EmailOtpRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(emailOtpDAO.sendOtp(dto),"OTP sent successfully."));
    }

    @PostMapping("/verifyotp")
    public ResponseEntity<ApiResponse<EmailOtpResponseDTO>> verifyOtp(@Valid @RequestBody VerifyOtpRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(emailOtpDAO.verifyOtp(dto),"OTP verified successfully."));
    }
}
