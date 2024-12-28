package com.management.library.controller;

import com.management.library.dto.user.request.LoginRequest;
import com.management.library.dto.user.request.SignupRequest;
import com.management.library.dto.user.response.LoginResponse;
import com.management.library.dto.user.response.SignupResponse;
import com.management.library.service.intf.UserService;
import com.management.library.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request){
        return ResponseEntity.ok(userService.signup(request));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
    @GetMapping("/verify")
    public String verifyToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "Token is valid";
        } else {
            return "Token is invalid";
        }
    }
    @GetMapping("/decode")
    public String decodeToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
    @GetMapping("/current-user")
    public ResponseEntity<String> getCurrentUser(String jwt) {
        var claims = jwtUtil.extractAllClaims(jwt);
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Roles: " + claims.get("roles"));
        System.out.println("Issued at: " + claims.getIssuedAt());
        System.out.println("Expiration: " + claims.getExpiration());
        return ResponseEntity.ok(claims.getSubject());
    }
}
