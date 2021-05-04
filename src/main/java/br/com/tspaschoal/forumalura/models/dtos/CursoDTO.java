package br.com.tspaschoal.forumalura.models.dtos;

import br.com.tspaschoal.forumalura.models.entities.Curso;
import br.com.tspaschoal.forumalura.support.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class CursoDTO implements Converter<CursoDTO, Curso> {
    private String nome;
    private String categoria;

    public CursoDTO() {}

    public CursoDTO(Curso curso) {
        this.categoria = curso.getCategoria();
        this.nome = curso.getNome();
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public List<CursoDTO> converter(List<Curso> values) {
        return values.stream().map(CursoDTO::new).collect(Collectors.toList());
    }
}
