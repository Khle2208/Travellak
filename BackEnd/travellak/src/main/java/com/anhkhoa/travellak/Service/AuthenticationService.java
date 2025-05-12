package com.anhkhoa.travellak.Service;

import com.anhkhoa.travellak.Entity.InvalidatedToken;
import com.anhkhoa.travellak.Entity.Users;
import com.anhkhoa.travellak.Repository.InvalidatedTokenRepository;
import com.anhkhoa.travellak.Repository.UsersRepository;
import com.anhkhoa.travellak.dto.Request.Users.AuthenticationRequest;
import com.anhkhoa.travellak.dto.Request.Users.IntrospectRequest;
import com.anhkhoa.travellak.dto.Request.Users.LogoutRequest;
import com.anhkhoa.travellak.dto.Response.AuthenticationResponse;
import com.anhkhoa.travellak.dto.Response.IntrospectResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;
    InvalidatedTokenRepository invalidatedTokenRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = usersRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email không tồn tại"));
        boolean result = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!result) {
            throw new RuntimeException("User not found");
        }
        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authentication(true)
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean valid = true;
        try {
            verifyToken(token);
        } catch (RuntimeException e) {
            valid = false;
        }
        return IntrospectResponse.builder()
                .valid(valid)
                .build();
    }

    public String generateToken(Users user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("leanhkhoa")
                .issueTime(new Date())
                .jwtID(UUID.randomUUID().toString())
                .claim("role", user.getRoles())
                .claim("scope", buildScope(user))
                .claim("userId", user.getUserId())
                .expirationTime(
                        new Date(
                                Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                        )
                )
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        var signToken = verifyToken(request.getToken());

        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expTime = signToken.getJWTClaimsSet().getExpirationTime();

        invalidatedTokenRepository.save(new InvalidatedToken(jit, expTime));

    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        // Tạo đối tượng verifier dùng để kiểm tra chữ ký
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        // Phân tích token thành đối tương signedJWT
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        // kiểm tra chữ ký nếu hợp lệ thì trả về true
        var verified = signedJWT.verify(verifier);
        if (!(verified && expTime.after(new Date()))) {
            throw new RuntimeException("Invalid token");
        }

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new RuntimeException("Invalid token");
        }

        return signedJWT;

    }

    private String buildScope(Users user) {
        StringJoiner scope = new StringJoiner(" ");
        if (!user.getRoles().isEmpty())
            user.getRoles().forEach(role -> {
                scope.add("ROLE_" + role.getRoleName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> scope.add(permission.getPermissionName()));
            });
        return scope.toString();
    }
}
