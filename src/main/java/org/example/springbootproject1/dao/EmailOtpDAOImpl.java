package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.EmailOtpRequestDTO;
import org.example.springbootproject1.dto.request.VerifyOtpRequestDTO;
import org.example.springbootproject1.dto.response.EmailOtpResponseDTO;
import org.example.springbootproject1.entity.EmailOtp;
import org.example.springbootproject1.entity.Users;
import org.example.springbootproject1.exception.OtpException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.repository.EmailOtpRepository;
import org.example.springbootproject1.repository.UsersRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailOtpDAOImpl implements EmailOtpDAO{

    private final EmailOtpRepository emailOtpRepository;
    private final UsersRepository usersRepository;
    private final JavaMailSender javaMailSender;
    private static final int OTP_EXPIRATION_MINUTES = 2;

    @Override
    public EmailOtpResponseDTO sendOtp(EmailOtpRequestDTO dto) {
        Users users = usersRepository.findByEmail(dto.email()).orElseThrow(()->new ResourceNotFoundException("Email doesn't exist."));
        String otp = generateOtp();
        LocalDateTime now = LocalDateTime.now();
        EmailOtp emailOtp = EmailOtp.builder().email(dto.email()).otp(otp).createdAt(now).expiresAt(now.plusMinutes(OTP_EXPIRATION_MINUTES)).verified(false).build();
        emailOtpRepository.save(emailOtp);
        sendEmail(dto.email(),otp);

        return new EmailOtpResponseDTO(dto.email());
    }

    @Override
    public EmailOtpResponseDTO verifyOtp(VerifyOtpRequestDTO dto) {
        Users user = usersRepository.findByEmail(dto.email()).orElseThrow(() -> new ResourceNotFoundException("Email does not exist"));
        EmailOtp emailOtp = emailOtpRepository.findTopByEmailOrderByCreatedAtDesc(dto.email()).orElseThrow(() -> new OtpException("OTP not found"));
        if (emailOtp.isVerified()) {
            throw new OtpException("OTP has already been verified");
        }
        if (emailOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new OtpException("OTP has expired");
        }
        if (!emailOtp.getOtp().equals(dto.otp())) {
            throw new OtpException("Invalid OTP");
        }
        emailOtp.setVerified(true);
        emailOtpRepository.save(emailOtp);
        return new EmailOtpResponseDTO(dto.email());
    }

    private void sendEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email Verification OTP");
        message.setText("Your OTP is: " + otp + "\n\nThis OTP will expire in 2 minutes." + "\n\nPlease do not share this OTP with anyone.");

        javaMailSender.send(message);
    }

    private String generateOtp() {
        SecureRandom secureRandom = new SecureRandom();
        int number = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(number);
    }
}
