package com.squad.auth.controller;

import com.squad.auth.model.*;
import com.squad.auth.service.CognitoAuthenticationService;
import com.squad.auth.util.AWSConstants;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CognitoAuthenticationService authService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager , CognitoAuthenticationService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @SuppressWarnings("unchecked")
    @CrossOrigin
    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticationRequest(@RequestBody @NotNull AuthenticationRequest authenticationRequest) {
        String expiresIn = null, token = null, accessToken = null;
        AuthenticationResponse authenticationResponse = null;

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        String newPassword = authenticationRequest.getNewPassword();

        Map<String, String> credentials = new HashMap<>();
        credentials.put(AWSConstants.PASS_WORD_KEY , password);
        credentials.put(AWSConstants.NEW_PASS_WORD_KEY , newPassword);

        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username , credentials));

        Map<String, String> authenticatedCredentials = (Map<String, String>) authentication.getCredentials();
        token = authenticatedCredentials.get(AWSConstants.ID_TOKEN_KEY);
        expiresIn = authenticatedCredentials.get(AWSConstants.EXPIRES_IN_KEY);
        accessToken = authenticatedCredentials.get(AWSConstants.ACCESS_TOKEN_KEY);

        UserResponse userResponse = authService.getUserInfo(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        authenticationResponse = new AuthenticationResponse(token , expiresIn , accessToken , userResponse);
        authenticationResponse.setAccessToken(token);
        authenticationResponse.setExpiresIn(expiresIn);
        authenticationResponse.setSessionToken(accessToken);
        authenticationResponse.setUserResponse(userResponse);

        return ResponseEntity.ok(new AuthenticationResponse(token , expiresIn , accessToken , userResponse));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<UserSignUpResponse> signUpRequest(@RequestBody UserSignUpRequest signUpRequest) {
        UserSignUpResponse user = null;
        user = authService.signUp(signUpRequest);
        authService.signUpConfirmation(signUpRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/reset")
    public ResponseEntity<PasswordResponse> resetPassword(@RequestBody PasswordRequest resetPasswordRequest) {
        PasswordResponse response = authService.resetPassword(resetPasswordRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/confirm")
    public ResponseEntity<PasswordResponse> confirmResetPassword(@RequestBody PasswordRequest resetPasswordRequest) {
        PasswordResponse response = authService.confirmResetPassword(resetPasswordRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/signout")
    public ResponseEntity<AuthenticationResponse> signOut(@RequestBody @NotNull AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setMessage(authService.signOut(authenticationRequest.getAccessToken() , authenticationRequest.getUsername()));
        response.setUsername(authenticationRequest.getUsername());
        return ResponseEntity.ok(response);
    }


    @DeleteMapping
    public ResponseEntity<AuthenticationResponse> deleteUser(@RequestBody @NotNull UserRequest userRequest) {
        AuthenticationResponse response = new AuthenticationResponse();
        authService.deleteUser(userRequest.getUsername());
        response.setMessage("User Deleted");
        response.setUsername(userRequest.getUsername());
        return ResponseEntity.ok(response);
    }
}
