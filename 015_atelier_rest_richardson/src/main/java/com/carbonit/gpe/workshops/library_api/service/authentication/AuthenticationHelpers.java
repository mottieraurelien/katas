package com.carbonit.gpe.workshops.library_api.service.authentication;


import com.carbonit.gpe.workshops.library_api.service.exceptions.AuthenticationException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AuthenticationHelpers {

    static String stripTypeFromAuthHeader(String authHeader, String authType) {
        int indexOf;
        if (authHeader == null || (indexOf = authHeader.indexOf(authType)) == -1)
            throw new AuthenticationException("Authorization Header did not contained " + authType);
        return authHeader.substring(indexOf + authType.length()).trim();
    }

    static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedInput = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().encodeToString(hashedInput);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Note: Assumes input string in {@link StandardCharsets#ISO_8859_1} and outputs into {@link StandardCharsets#UTF_8}
     **/
    static String base64UrlDecode(String input) {
        return new String(Base64.getUrlDecoder().decode(input), UTF_8);
    }

    /**
     * Note: Assumes input string in {@link StandardCharsets#UTF_8} and outputs into {@link StandardCharsets#ISO_8859_1}
     **/
    static String base64UrlEncode(String input) {
        return Base64.getUrlEncoder().encodeToString(input.getBytes(UTF_8));
    }
}
