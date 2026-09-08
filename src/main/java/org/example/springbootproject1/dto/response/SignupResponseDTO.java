package org.example.springbootproject1.dto.response;

public record SignupResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        String gender,
        String profileImage,
        String roleName
) {
}
