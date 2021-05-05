package br.com.tspaschoal.forumalura.services;

import br.com.tspaschoal.forumalura.models.dtos.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface TokenService {

    public ResponseEntity<TokenDTO> createToken(Authentication authentication);

    public Boolean isValidToken(String token);

    public Long getUserIdFromToken(String token);
}
