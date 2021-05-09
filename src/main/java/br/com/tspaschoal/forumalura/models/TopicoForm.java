package br.com.tspaschoal.forumalura.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@AllArgsConstructor
@Builder
public class TopicoForm {

    @NotNull @NotEmpty @Length(max = 50)
    private String titulo;

    @NotNull @NotEmpty
    private String mensagem;

    @NotNull @NotEmpty
    private String curso;

}
