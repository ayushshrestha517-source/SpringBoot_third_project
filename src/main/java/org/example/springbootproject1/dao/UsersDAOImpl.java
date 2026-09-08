package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.LoginRequestDTO;
import org.example.springbootproject1.dto.request.SignupRequestDTO;
import org.example.springbootproject1.dto.response.LoginResponseDTO;
import org.example.springbootproject1.dto.response.SignupResponseDTO;
import org.example.springbootproject1.entity.Role;
import org.example.springbootproject1.entity.Users;
import org.example.springbootproject1.exception.AlreadyExistsException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.UsersMapper;
import org.example.springbootproject1.repository.RoleRepository;
import org.example.springbootproject1.repository.UsersRepository;
import org.example.springbootproject1.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersDAOImpl implements UsersDAO{
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        if(usersRepository.findByUsername(signupRequestDTO.username()).isPresent()){
            throw new AlreadyExistsException("Username already exists.");
        }

        Users users = usersMapper.toEntity(signupRequestDTO);
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        Role role = roleRepository.findByName(signupRequestDTO.roleName()).orElseThrow(()->new ResourceNotFoundException("Role not found"));
        users.setRoles(Set.of(role));

        Users savedUser = usersRepository.save(users);
        return usersMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.username(),
                        loginRequestDTO.password()
                )
        );

        Users users = usersRepository
                .findByUsername(loginRequestDTO.username())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Set<String> roles = users.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        String token = jwtUtil.generateToken(
                users.getUsername(),
                roles
        );

        return new LoginResponseDTO(token, roles);
    }
}
