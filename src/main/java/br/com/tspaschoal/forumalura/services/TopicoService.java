package br.com.tspaschoal.forumalura.services;

import br.com.tspaschoal.forumalura.models.TopicoForm;
import br.com.tspaschoal.forumalura.models.dtos.DetalheTopicoDTO;
import br.com.tspaschoal.forumalura.models.dtos.TopicoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TopicoService {

    public ResponseEntity<List<TopicoDTO>> findAll();

    public ResponseEntity<DetalheTopicoDTO> findById(Long id);

    public ResponseEntity<TopicoDTO> save(TopicoForm form);

    public ResponseEntity<TopicoDTO> update(Long id, TopicoForm form);

    public ResponseEntity remove(Long id);

}
