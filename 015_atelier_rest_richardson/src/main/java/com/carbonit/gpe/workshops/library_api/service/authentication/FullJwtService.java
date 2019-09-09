package com.carbonit.gpe.workshops.library_api.service.authentication;

import com.carbonit.gpe.workshops.library_api.FeaturesConfig;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.IUserService;
import com.carbonit.gpe.workshops.library_api.service.exceptions.AuthenticationException;
import com.carbonit.gpe.workshops.library_api.service.exceptions.MissingRessourceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Clock;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FullJwtService implements IJwtService {

    private static final TypeReference<HashMap<String, String>> MAP_TYPE_REFERENCE = new TypeReference<HashMap<String, String>>() {
    };
    private static final String JWT_HEADER = AuthenticationHelpers.base64UrlEncode("{'typ': 'JWT', 'alg': 'HS256'}");
    private static final AtomicLong IAT_GENERATOR = new AtomicLong(0L);

    private static final String IAT = "iat";
    private static final String EXP = "exp";
    private static final String USER_ID = "userId";
    private static final String SECRET = "S3cr3t";
    private static final ObjectMapper mapper = new ObjectMapper();

    private final IUserService userService;

    public FullJwtService(IUserService userService) {
        this.userService = userService;
    }

    static Clock clock = Clock.systemUTC();

    static String userIdFromJwt(String jwt) {
        String[] jwtParts = jwt.split("\\.");
        String claims = jwtParts[1];
        String jwtSignature = jwtParts[2];
        String computedSignature = computeJwtSignature(claims);
        if (!computedSignature.equals(jwtSignature)) {
            throw new AuthenticationException("Could not certify integrity for token " + jwt);
        }
        return deserializeUserIdFromClaims(claims);
    }

    private static String deserializeUserIdFromClaims(String claims) {
        try {
            byte[] decodedClaims = Base64.getUrlDecoder().decode(claims);
            Map<String, String> claimsMap = mapper.readValue(decodedClaims, MAP_TYPE_REFERENCE);
            Long expiration = Long.valueOf(claimsMap.get(EXP));
            if (clock.millis() > expiration) {
                throw new AuthenticationException("JWT already expired");
            }
            return claimsMap.get(USER_ID);
        } catch (IOException e) {
            throw new AuthenticationException("Error while reading claims token part: " + e.getMessage());
        }
    }

    private static String computeJwtSignature(String claims) {
        String hash1 = AuthenticationHelpers.sha256(JWT_HEADER + "." + claims);
        String hash2 = AuthenticationHelpers.sha256(hash1 + SECRET);
        return hash2;
    }

    private static String userIdToJwtClaimsPart(String userId) {
        Map<String, String> claims = new HashMap<>();
        claims.put(IAT, String.valueOf(IAT_GENERATOR.incrementAndGet()));
        claims.put(EXP, String.valueOf(clock.millis() + 3600_000L));
        claims.put(USER_ID, userId);
        try {
            return Base64.getUrlEncoder().encodeToString(mapper.writeValueAsBytes(claims));
        } catch (JsonProcessingException e) {
            throw new AuthenticationException("Error while generating claims token part: " + e.getMessage());
        }
    }


    public Long deserializeUserIdFromAuthHeader(String authHeader) {
        if(FeaturesConfig.isJwtAuthenticationDisabled()){
            return FeaturesConfig.getDefaultUserId();
        }
        String jwt = AuthenticationHelpers.stripTypeFromAuthHeader(authHeader, BEARER);
        return Long.valueOf(userIdFromJwt(jwt));
    }

    @Override
    public IUser jwtAuth(String authorizationHeader) {
        try {
            return this.userService.findById(deserializeUserIdFromAuthHeader(authorizationHeader));
        } catch (MissingRessourceException e) {
            throw new AuthenticationException("Invalid Java Web Token");
        }
    }

    @Override
    public String generateJwtFromUserId(String userId) {
        String claims = userIdToJwtClaimsPart(userId);
        String signature = computeJwtSignature(claims);
        return JWT_HEADER + "." + claims + "." + signature;
    }


}
