package br.com.tspaschoal.forumalura.models.dtos;

import br.com.tspaschoal.forumalura.models.entities.Topico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
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

}
