package br.com.tspaschoal.forumalura.units;

import br.com.tspaschoal.forumalura.UnitTesting;
import br.com.tspaschoal.forumalura.models.TopicoForm;
import br.com.tspaschoal.forumalura.models.dtos.DetalheTopicoDTO;
import br.com.tspaschoal.forumalura.models.dtos.TopicoDTO;
import br.com.tspaschoal.forumalura.models.entities.Curso;
import br.com.tspaschoal.forumalura.models.entities.Topico;
import br.com.tspaschoal.forumalura.models.enums.StatusTopico;
import br.com.tspaschoal.forumalura.repositories.CursoRepository;
import br.com.tspaschoal.forumalura.repositories.TopicoRepository;
import br.com.tspaschoal.forumalura.services.imp.TopicoServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    public void shouldReturnAllTopicsCorrect() {
        List<Topico> topicos = getTopicos();
        final var pageable = PageRequest.of(0, topicos.size());
        when(topicoRepository.findAll()).thenReturn(topicos);
        List<TopicoDTO> topicosDTOs = topicoService.findAll(pageable).getContent();
        verify(topicoRepository).findAll(pageable);
        assertNotNull(topicosDTOs);
    }

    @Test
    public void shouldRemoveOneTopic() {
        final var curso = Curso.builder().nome("Spring").categoria("Programação").build();
        final var expectedDate = LocalDateTime.of(2021, Month.MAY, 25, 12, 50);
        var topico = Topico.builder()
                .id(2L)
                .curso(curso)
                .mensagem("Erro ao criar projeto spring mvc")
                .dataCriacao(expectedDate)
                .status(StatusTopico.NAO_RESPONDIDO)
                .titulo("Dúvida")
                .build();

        when(topicoRepository.getOne(2L)).thenReturn(topico);
        final var responseEntity = topicoService.remove(2L);
        verify(topicoRepository, times(1)).delete(topico);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void shouldReturnNotFoundAsStatusCodeWhenIdIsInvalid() {
        when(topicoRepository.getOne(999L)).thenReturn(null);
        final var responseEntity = topicoService.remove(999L);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void shouldCreateAnTopicWithSuccessfully() {

        final var expectedDate = LocalDateTime.of(2021, Month.MAY, 25, 12, 50);
        final var form = TopicoForm.builder()
                .titulo("Duvida 3")
                .mensagem("descricao")
                .curso("Spring Boot")
                .build();

        final var curso = Curso.builder().nome("Spring Boot").categoria("Programação").build();
        var topico = Topico.builder()
                .curso(curso)
                .titulo(form.getTitulo())
                .mensagem(form.getMensagem())
                .build();

        var topicoMock = Topico.builder()
                .curso(curso)
                .mensagem(form.getMensagem())
                .titulo(form.getTitulo())
                .dataCriacao(expectedDate)
                .status(StatusTopico.NAO_RESPONDIDO)
                .respostas(Arrays.asList())
                .build();

        when(cursoRepository.findByNome(form.getCurso())).thenReturn(Optional.of(curso));
        when(topicoRepository.save(topico)).thenReturn(topicoMock);

        TopicoDTO topicSaved = topicoService.save(form);

        verify(topicoRepository, times(1)).save(topico);
        Assertions.assertEquals(topicoMock.getTitulo(), topicSaved.getTitulo());
        Assertions.assertEquals(topicoMock.getMensagem(), topicSaved.getMensagem());
    }

    @Test
    public void shouldNotCreateATopicWithoutACourse() {
        when(cursoRepository.findByNome("Java")).thenReturn(Optional.empty());
        final var form = TopicoForm.builder()
                .titulo("Duvida 3")
                .mensagem("descricao")
                .curso("Java")
                .build();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> topicoService.save(form));
        Assertions.assertEquals("topic not found", exception.getMessage());
    }

    private List<Topico> getTopicos() {

        final var curso = Curso.builder().nome("Spring").categoria("Programação").build();
        final var expectedDate = LocalDateTime.of(2021, Month.MAY, 25, 12, 50);

        var topico1 = Topico.builder()
                .id(2L)
                .curso(curso)
                .mensagem("Erro ao criar projeto spring mvc")
                .dataCriacao(expectedDate)
                .status(StatusTopico.NAO_RESPONDIDO)
                .titulo("A")
                .build();

        var topico2 = Topico.builder()
                .id(1L)
                .curso(curso)
                .mensagem("Erro ao criar projeto spring boot")
                .dataCriacao(expectedDate)
                .status(StatusTopico.NAO_RESPONDIDO)
                .titulo("C")
                .build();

        var topico3 = Topico.builder()
                .id(3L)
                .curso(curso)
                .mensagem("Erro ao criar projeto spring security")
                .dataCriacao(expectedDate)
                .status(StatusTopico.NAO_RESPONDIDO)
                .titulo("B")
                .build();

        return Arrays.asList(topico1, topico2, topico3);
    }

}
