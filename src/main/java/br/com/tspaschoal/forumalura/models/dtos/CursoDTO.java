package br.com.tspaschoal.forumalura.models.dtos;

import br.com.tspaschoal.forumalura.models.entities.Curso;
import br.com.tspaschoal.forumalura.support.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Getter
@NoArgsConstructor
public class CursoDTO implements Converter<CursoDTO, Curso> {

    private String nome;
    private String categoria;

    public CursoDTO(Curso curso) {
        this.categoria = curso.getCategoria();
        this.nome = curso.getNome();
    }

    @Override
    public Page<CursoDTO> converter(Page<Curso> values) {
        return values.map(CursoDTO::new);
    }
}
