package br.com.tspaschoal.forumalura.controllers;

import br.com.tspaschoal.forumalura.models.LoginForm;
import br.com.tspaschoal.forumalura.models.dtos.TokenDTO;
import br.com.tspaschoal.forumalura.services.TokenService;
import br.com.tspaschoal.forumalura.support.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile("prod")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody @Valid LoginForm loginForm) {
        var usernamePasswordAuthenticationToken = TokenUtils.getUsernamePasswordAuthenticationToken(loginForm);
        try {
            var authenticated = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return tokenService.createToken(authenticated);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
