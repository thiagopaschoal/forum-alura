package br.com.tspaschoal.forumalura.controllers;

import br.com.tspaschoal.forumalura.models.TopicoForm;
import br.com.tspaschoal.forumalura.models.dtos.DetalheTopicoDTO;
import br.com.tspaschoal.forumalura.models.dtos.TopicoDTO;
import br.com.tspaschoal.forumalura.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<TopicoDTO>> findAll(Pageable page) {
        return topicoService.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheTopicoDTO> findById(@PathVariable Long id) {
        return topicoService.findById(id);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid TopicoForm form) {
        return topicoService.save(form);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody @Valid TopicoForm form) {
        return this.topicoService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        return this.topicoService.remove(id);
    }
}
