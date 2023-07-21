package com.example.crud.auth;

import com.example.crud.config.JwtService;
import com.example.crud.loginCredentials.Credentials;
import com.example.crud.loginCredentials.CredentialsRepository;
import com.example.crud.token.Token;
import com.example.crud.token.TokenRepository;
import com.example.crud.token.TokenType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final CredentialsRepository repository;

    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationService(CredentialsRepository repository, TokenRepository tokenRepository, AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getUserPass()
                )
        );
        var credentials = repository.findByUserName(request.getUserName())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(credentials);
        revokeAllUserTokens(credentials);
        saveUserToken(credentials, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request){
        var credentials = Credentials.builder()
                .userName(request.getUserName())
                .userPass(passwordEncoder.encode(request.getUserPass()))
                .build();
        repository.save(credentials);
        var savedUser = repository.save(credentials);
        var jwtToken=jwtService.generateToken(credentials);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(Credentials credentials){

        var validUserTokens = tokenRepository.findAllValidTokensByUser(credentials.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t-> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(Credentials user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

}
