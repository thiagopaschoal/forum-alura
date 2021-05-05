package br.com.tspaschoal.forumalura.models.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@ToString
public class TokenDTO {

    private String token;

    private String authenticationType;

    public TokenDTO(String token) {
        this.token = token;
        this.authenticationType = "Bearer";
    }
}
