package br.com.tspaschoal.forumalura.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@AllArgsConstructor
public class CursoForm {

    @NotEmpty @NotNull @Length(max = 50)
    private String nome;

    @NotEmpty @NotNull @Length(max = 20)
    private String categoria;

}
