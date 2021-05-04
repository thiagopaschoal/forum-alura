package br.com.tspaschoal.forumalura.models.dtos;

import br.com.tspaschoal.forumalura.models.entities.Topico;
import br.com.tspaschoal.forumalura.support.Converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDTO implements Converter<TopicoDTO, Topico> {

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDTO() {}

    public TopicoDTO(Topico topico) {
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public List<TopicoDTO> converter(List<Topico> values) {
        return values.stream().map(TopicoDTO::new).collect(Collectors.toList());
    }
}
