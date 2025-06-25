package com.anhkhoa.travellak.Controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhkhoa.travellak.Service.AuthenticationService;
import com.anhkhoa.travellak.dto.Request.Users.AuthenticationRequest;
import com.anhkhoa.travellak.dto.Request.Users.IntrospectRequest;
import com.anhkhoa.travellak.dto.Request.Users.LogoutRequest;
import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.AuthenticationResponse;
import com.anhkhoa.travellak.dto.Response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("Authentication")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/Login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse authenticate = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticate)
                .build();
    }

    @PostMapping("/Introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        IntrospectResponse result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/Logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }
}
