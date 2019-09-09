package com.carbonit.gpe.workshops.library_api;

public class FeaturesConfig {
    private static final boolean JWT_AUTHENTICATION_ACTIVATED = false;
    private static final boolean AUTHORIZATION_ACTIVATED = false;
    private static final boolean VALIDATION_ACTIVATED = false;

    public static boolean isJwtAuthenticationDisabled() {
        return !JWT_AUTHENTICATION_ACTIVATED;
    }

    public static boolean isAuthorizationDisabled() {
        return !AUTHORIZATION_ACTIVATED;
    }

    public static boolean isValidationDisabled() {
        return !VALIDATION_ACTIVATED;
    }

    public static Long getDefaultUserId() {
        return 1L;
    }
}
