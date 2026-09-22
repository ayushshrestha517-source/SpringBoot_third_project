package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.EmailOtpRequestDTO;
import org.example.springbootproject1.dto.request.VerifyOtpRequestDTO;
import org.example.springbootproject1.dto.response.EmailOtpResponseDTO;

public interface EmailOtpDAO {
    EmailOtpResponseDTO sendOtp(EmailOtpRequestDTO dto);
    EmailOtpResponseDTO verifyOtp(VerifyOtpRequestDTO dto);
}
