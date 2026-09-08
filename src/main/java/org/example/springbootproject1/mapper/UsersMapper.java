package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.SignupRequestDTO;
import org.example.springbootproject1.dto.response.SignupResponseDTO;
import org.mapstruct.Mapper;

import org.example.springbootproject1.entity.Role;
import org.example.springbootproject1.entity.Users;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profileImage", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Users toEntity(SignupRequestDTO request);

    @Mapping(target = "roleName", expression = "java(getRoleName(users.getRoles()))")
    SignupResponseDTO toResponse(Users users);

    default String getRoleName(java.util.Set<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }

        return roles.iterator().next().getName();
    }
}
