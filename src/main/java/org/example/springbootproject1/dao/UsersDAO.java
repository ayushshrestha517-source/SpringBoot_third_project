package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.LoginRequestDTO;
import org.example.springbootproject1.dto.request.SignupRequestDTO;
import org.example.springbootproject1.dto.response.LoginResponseDTO;
import org.example.springbootproject1.dto.response.SignupResponseDTO;

public interface UsersDAO {
    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO);
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
