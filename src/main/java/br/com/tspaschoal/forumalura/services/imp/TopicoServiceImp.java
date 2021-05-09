package br.com.tspaschoal.forumalura.services.imp;

import br.com.tspaschoal.forumalura.models.TopicoForm;
import br.com.tspaschoal.forumalura.models.dtos.DetalheTopicoDTO;
import br.com.tspaschoal.forumalura.models.dtos.TopicoDTO;
import br.com.tspaschoal.forumalura.models.entities.Curso;
import br.com.tspaschoal.forumalura.models.entities.Topico;
import br.com.tspaschoal.forumalura.repositories.CursoRepository;
import br.com.tspaschoal.forumalura.repositories.TopicoRepository;
import br.com.tspaschoal.forumalura.services.TopicoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicoServiceImp implements TopicoService  {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Page<TopicoDTO> findAll(Pageable pageable) {

        final var topicos = this.repository.findAll(pageable);

        if (topicos == null || topicos.getSize() == 0) {
            return null;
        }

        return new TopicoDTO().converter(topicos);
    }

    @Override
    public DetalheTopicoDTO findById(Long id) {
        final var topico = this.repository.getOne(id);

        if (topico == null) {
            return null;
        }

        return new DetalheTopicoDTO(topico);
    }

    @Override
    public TopicoDTO save(TopicoForm form) {

        Optional<Curso> curso = cursoRepository.findByNome(form.getCurso());

        if (!curso.isPresent()) {
            throw new RuntimeException("topic not found");
        }

        final Topico topico = Topico.builder()
                .titulo(form.getTitulo())
                .curso(curso.get())
                .mensagem(form.getMensagem())
                .build();

        var topicoSaved = repository.save(topico);

        return new TopicoDTO(topicoSaved);
    }

    @Override
    public TopicoDTO update(Long id, TopicoForm form) {
        final Topico topico = this.repository.getOne(id);
        if (topico == null) {
            return null;
        }

        topico.setTitulo(form.getTitulo());
        topico.setMensagem(form.getMensagem());

        Topico topicoAlterado = this.repository.saveAndFlush(topico);

        return new TopicoDTO(topicoAlterado);
    }

    @Override
    public ResponseEntity remove(Long id) {
        final Topico topico = this.repository.getOne(id);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }
        this.repository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}
