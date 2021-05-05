package br.com.tspaschoal.forumalura.services.imp;

import br.com.tspaschoal.forumalura.models.dtos.TokenDTO;
import br.com.tspaschoal.forumalura.models.entities.Usuario;
import br.com.tspaschoal.forumalura.services.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImp implements TokenService {

    @Value("${forum.jwt.expiration}")
    private String expirationDate;

    @Value("${forum.jwt.secret}")
    private String secret;

    @Override
    public ResponseEntity<TokenDTO> createToken(Authentication authentication) {
        var usuario = (Usuario) authentication.getPrincipal();
        Date currentDate = new Date();
        Date expirationDt = new Date(currentDate.getTime() + Long.parseLong(expirationDate));
        String tokenJWT = Jwts.builder()
                .setIssuer("alura forum API")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(currentDate)
                .setExpiration(expirationDt)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

    @Override
    public Boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long getUserIdFromToken(String token) {
        String subject = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        return Long.parseLong(subject);
    }
}
