package br.com.tspaschoal.forumalura.controllers;

import br.com.tspaschoal.forumalura.models.dtos.CursoDTO;
import br.com.tspaschoal.forumalura.models.entities.Curso;
import br.com.tspaschoal.forumalura.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll() {
        final var cursos = repository.findAll();
        return ResponseEntity.ok(new CursoDTO().converter(cursos));
    }

}
