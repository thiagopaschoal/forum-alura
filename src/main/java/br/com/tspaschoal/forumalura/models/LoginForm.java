package br.com.tspaschoal.forumalura.models;

import br.com.tspaschoal.forumalura.support.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@AllArgsConstructor
public class LoginForm {

    @NotNull @NotEmpty @Email
    private String email;

    @NotNull @NotEmpty
    private String senha;

    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken() {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(this.email, this.senha);
        return usernamePasswordAuthenticationToken;
    }

}
