package com.management.library.util;

import com.management.library.enums.ResponseCode;
import com.management.library.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
@Slf4j
@Component
public class ApplicationUtil {
    private static final int NUMBER_LENGTH = 7;
    private static final Random random = new Random();
    private static final Set<String> generatedCodes = new HashSet<>();

    public static String generateUniqueCode(String prefix) {
        if (prefix == null || prefix.length() != 2) {
            throw new ApiException(ResponseCode.PREFIX_ERROR);
        }
        String code;
        do {
            code = generateCode(prefix);
        } while (!generatedCodes.add(code)); // Add returns false if the set already contains the code

        return code;
    }

    private static String generateCode(String prefix) {
        StringBuilder sb = new StringBuilder(prefix);
        sb.append("-");
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            sb.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        return sb.toString();
    }
}
