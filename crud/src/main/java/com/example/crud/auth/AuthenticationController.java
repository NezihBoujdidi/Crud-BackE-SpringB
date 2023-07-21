package com.example.crud.auth;
import com.example.crud.config.logoutService;
import com.example.crud.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/V2/auth/")
public class AuthenticationController {
    private final AuthenticationService service;
    private final JwtService tokenService;

    private final logoutService logoutServ;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationService service, JwtService tokenService, logoutService logoutServ) {
        this.service = service;
        this.tokenService = tokenService;
        this.logoutServ = logoutServ;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest AuthRequest
    ) {
        return ResponseEntity.ok(service.authenticate(AuthRequest));
    }


    @GetMapping("/checkTokenExp")
    public ResponseEntity<String> checkTokenExp(
            HttpServletRequest request
    ){
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        logger.info("authHeader" + authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            Boolean isExpired = tokenService.isTokenExpired(jwt);
            if (isExpired) {
                return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Token is expired!\"}");
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Token is not expired!\"}");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Invalid token!\"}");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Authentication authentication){
        logoutServ.logout(request,response,authentication);
        return ResponseEntity.ok("Logout successful");

    }



}
