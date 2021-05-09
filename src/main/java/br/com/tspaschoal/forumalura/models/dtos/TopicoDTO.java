package br.com.tspaschoal.forumalura.models.dtos;

import br.com.tspaschoal.forumalura.models.entities.Topico;
import br.com.tspaschoal.forumalura.support.Converter;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TopicoDTO implements Converter<TopicoDTO, Topico> {

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

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
    public Page<TopicoDTO> converter(Page<Topico> values) {
        return values.map(TopicoDTO::new);
    }
}
