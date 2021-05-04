package br.com.tspaschoal.forumalura.controllers;

import br.com.tspaschoal.forumalura.models.CursoForm;
import br.com.tspaschoal.forumalura.models.dtos.CursoDTO;
import br.com.tspaschoal.forumalura.models.entities.Curso;
import br.com.tspaschoal.forumalura.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<Page<CursoDTO>> findAll(Pageable page) {
        final var cursos = repository.findAll(page);
        return ResponseEntity.ok(new CursoDTO().converter(cursos));
    }

    @PostMapping
    public ResponseEntity<CursoDTO> save(@RequestBody @Valid CursoForm cursoForm) {

        Curso curso = new Curso();
        curso.setNome(cursoForm.getNome());
        curso.setCategoria(cursoForm.getCategoria());

        Curso cursoSaved = repository.save(curso);

        return ResponseEntity.ok(new CursoDTO(cursoSaved));
    }

}
