package com.management.library.service.imp;

import com.management.library.dto.user.request.LoginRequest;
import com.management.library.dto.user.request.SignupRequest;
import com.management.library.dto.user.response.LoginResponse;
import com.management.library.dto.user.response.SignupResponse;
import com.management.library.enums.RoleName;
import com.management.library.enums.ResponseCode;
import com.management.library.exception.ApiException;
import com.management.library.mappings.UserMapper;
import com.management.library.model.User;
import com.management.library.repository.RoleRepository;
import com.management.library.repository.UserRepository;
import com.management.library.service.intf.UserService;
import com.management.library.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.management.library.model.Role;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    @Override
    public SignupResponse signup(SignupRequest request) {
        try{
            var existedUser = userRepository.existsByEmail(request.getEmail());
            if (existedUser)
                throw new ApiException(ResponseCode.EMAIL_IN_USED);
            User user = userMapper.toEntity(request);
            user.setEnabled(true);
            Role role = roleRepository.findByName(RoleName.USER.toString()).orElseThrow(
                    () -> new ApiException(ResponseCode.ROLE_NOT_FOUND)
            );
            Set<Role> roles= new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
            return new SignupResponse(user.getEmail(), user.getEnabled());
        }
        catch (Exception ex) {
            log.error("Authentication error: {}", ex.getMessage());
        }
        throw new ApiException(ResponseCode.SIGNUP_FAILED);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        try{
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(
                            () -> new ApiException(ResponseCode.INVALID_USERNAME_OR_PASSWORD)
                    );
            if (!user.getPassword().equals(request.getPassword()))
                throw new ApiException(ResponseCode.INVALID_USERNAME_OR_PASSWORD);
            var jwt = jwtUtil.generateToken(user);
            LoginResponse response = new LoginResponse();
            response.setToken(jwt);
            return response;
        }
        catch(ApiException e){
            throw new ApiException(ResponseCode.INVALID_USERNAME_OR_PASSWORD);
        }
        catch (Exception ex) {
            log.error("Authentication error: {}", ex.getMessage());
        }
        throw new ApiException(ResponseCode.SIGNUP_FAILED);
    }
}
