package br.com.tspaschoal.forumalura.units;

import br.com.tspaschoal.forumalura.UnitTesting;
import br.com.tspaschoal.forumalura.models.dtos.DetalheTopicoDTO;
import br.com.tspaschoal.forumalura.models.entities.Curso;
import br.com.tspaschoal.forumalura.models.entities.Topico;
import br.com.tspaschoal.forumalura.models.enums.StatusTopico;
import br.com.tspaschoal.forumalura.repositories.CursoRepository;
import br.com.tspaschoal.forumalura.repositories.TopicoRepository;
import br.com.tspaschoal.forumalura.services.imp.TopicoServiceImp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

public class TopicoServiceTest extends UnitTesting {

    @Mock
    private TopicoRepository topicoRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private TopicoServiceImp topicoService;

    @Test
    public void shouldReturnOneTopicWhenIdIsPassed() {

        final var expectedDate = LocalDateTime.of(2021, Month.MAY, 25, 12, 50);

        var topico = Topico.builder()
                .curso(Curso.builder()
                        .nome("Spring Boot")
                        .categoria("Programação")
                        .build())
                .mensagem("Erro ao criar projeto")
                .dataCriacao(expectedDate)
                .status(StatusTopico.NAO_RESPONDIDO)
                .titulo("Dúvida")
                .build();

        var detalheTopicoDTOExpected = DetalheTopicoDTO.builder()
                .curso("Spring Boot")
                .dataCriacao(expectedDate)
                .mensagem("Erro ao criar projeto")
                .status("NAO_RESPONDIDO")
                .titulo("Dúvida")
                .build();

        when(topicoRepository.getOne(1L)).thenReturn(topico);
        DetalheTopicoDTO result = topicoService.findById(1L);

        verify(topicoRepository).getOne(1L);
        verifyZeroInteractions(cursoRepository);
        assertEquals(detalheTopicoDTOExpected, result);

    }

    @Test
    public void shouldReturnNullWhenTopicIdIsInvalid() {
        when(topicoRepository.getOne(999L)).thenReturn(null);
        DetalheTopicoDTO result = topicoService.findById(999L);
        assertNull(result);
    }

    @Test
    public void shouldRemoveOneTopic() {

    }

}
