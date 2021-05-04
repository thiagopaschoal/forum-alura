package br.com.tspaschoal.forumalura.repositories;

import br.com.tspaschoal.forumalura.models.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    public List<Topico> findByMensagem(String mensagem);


    @Query("select t from Topico t where t.curso.nome=:nomeCurso")
    public List<Topico> findTopicosByCurso(@Param("nomeCurso") String nomeCurso);

}
