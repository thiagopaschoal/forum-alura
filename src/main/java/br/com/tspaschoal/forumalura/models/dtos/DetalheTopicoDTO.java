package br.com.tspaschoal.forumalura.models.dtos;

import br.com.tspaschoal.forumalura.models.entities.Topico;

import java.time.LocalDateTime;

public class DetalheTopicoDTO {

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String status;
    private String curso;

    public DetalheTopicoDTO(Topico topico) {
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus().name();
        this.curso = topico.getCurso().getNome();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public String getCurso() {
        return curso;
    }
}
