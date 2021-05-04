package br.com.tspaschoal.forumalura.services.imp;

import br.com.tspaschoal.forumalura.models.dtos.DetalheTopicoDTO;
import br.com.tspaschoal.forumalura.models.entities.Curso;
import br.com.tspaschoal.forumalura.models.entities.Topico;
import br.com.tspaschoal.forumalura.models.TopicoForm;
import br.com.tspaschoal.forumalura.models.dtos.TopicoDTO;
import br.com.tspaschoal.forumalura.repositories.CursoRepository;
import br.com.tspaschoal.forumalura.repositories.TopicoRepository;
import br.com.tspaschoal.forumalura.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoServiceImp implements TopicoService  {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public ResponseEntity<List<TopicoDTO>> findAll() {

        final var topicos = this.repository.findAll();

        if (topicos == null || topicos.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new TopicoDTO().converter(topicos));
    }

    @Override
    public ResponseEntity<DetalheTopicoDTO> findById(Long id) {
        final var topico = this.repository.findById(id);

        if (!topico.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DetalheTopicoDTO(topico.get()));
    }

    @Override
    public ResponseEntity<TopicoDTO> save(TopicoForm form) {

        Optional<Curso> curso = cursoRepository.findByNome(form.getCurso());

        if (!curso.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        final Topico topico = new Topico();
        topico.setMensagem(form.getMensagem());
        topico.setTitulo(form.getTitulo());
        topico.setCurso(curso.get());

        var topicoSaved = repository.save(topico);

        return ResponseEntity.ok(new TopicoDTO(topicoSaved));
    }

    @Override
    public ResponseEntity<TopicoDTO> update(Long id, TopicoForm form) {
        final Topico topico = this.repository.getOne(id);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }

        topico.setTitulo(form.getTitulo());
        topico.setMensagem(form.getMensagem());

        Topico topicoAlterado = this.repository.saveAndFlush(topico);

        return ResponseEntity.ok(new TopicoDTO(topicoAlterado));
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
