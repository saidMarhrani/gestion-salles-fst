package org.mql.gestionsalle.backend.controllers;

import org.mql.gestionsalle.backend.authenticationmodels.AuthenticationRequest;
import org.mql.gestionsalle.backend.authenticationmodels.AuthenticationResponse;
import org.mql.gestionsalle.backend.security.MyUserDetailService;
import org.mql.gestionsalle.backend.services.StudentService;
import org.mql.gestionsalle.backend.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    private AuthenticationManager authenticationManager;
    private MyUserDetailService userDetailService;
    private JwtUtil jwtUtil;

    public AuthenticateController(AuthenticationManager authenticationManager, MyUserDetailService userDetailService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body("Bad credentials");
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(jwt).getJwt());
    }

}
