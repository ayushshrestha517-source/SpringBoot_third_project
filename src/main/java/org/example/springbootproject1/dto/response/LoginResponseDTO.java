package org.example.springbootproject1.dto.response;

import java.util.Set;

public record LoginResponseDTO(
        String token,
        Set<String> roles
) {
}
