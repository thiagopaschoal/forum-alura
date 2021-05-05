package br.com.tspaschoal.forumalura.support;

import br.com.tspaschoal.forumalura.models.LoginForm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TokenUtils {

    private TokenUtils() {}
    public static UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(LoginForm form) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getSenha());
        return usernamePasswordAuthenticationToken;
    }
}
