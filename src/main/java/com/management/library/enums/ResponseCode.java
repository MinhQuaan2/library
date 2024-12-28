package com.management.library.enums;

public enum ResponseCode {
    PREFIX_ERROR(500, "Prefix must be exactly 2 letters"),
    INVALID_USERNAME_OR_PASSWORD(102, "Invalid username or password"),

    UNAUTHORIZED(401, "Unauthorized"),

    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    EMAIL_IN_USED(409, "This email is already used"),
    SIGNUP_FAILED(500, "Signup failed"),

    ROLE_NOT_FOUND(404, "Role not found")
    ;
    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
