package com.management.library.service.intf;

import com.management.library.dto.user.request.LoginRequest;
import com.management.library.dto.user.request.SignupRequest;
import com.management.library.dto.user.response.LoginResponse;
import com.management.library.dto.user.response.SignupResponse;

public interface UserService {
    SignupResponse signup(SignupRequest request);
    LoginResponse login(LoginRequest request);

}
