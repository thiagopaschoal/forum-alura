package br.com.tspaschoal.forumalura.services;

import br.com.tspaschoal.forumalura.models.TopicoForm;
import br.com.tspaschoal.forumalura.models.dtos.DetalheTopicoDTO;
import br.com.tspaschoal.forumalura.models.dtos.TopicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TopicoService {

    public Page<TopicoDTO> findAll(Pageable pageable);

    public DetalheTopicoDTO findById(Long id);

    public TopicoDTO save(TopicoForm form);

    public TopicoDTO update(Long id, TopicoForm form);

    public ResponseEntity remove(Long id);

}
