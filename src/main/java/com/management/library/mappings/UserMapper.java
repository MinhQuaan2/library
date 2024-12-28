package com.management.library.mappings;

import com.management.library.dto.user.request.SignupRequest;
import com.management.library.dto.user.response.LoginResponse;
import com.management.library.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest request);
//    LoginResponse toLoginResponse(User user);
}
