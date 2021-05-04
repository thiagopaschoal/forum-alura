package br.com.tspaschoal.forumalura.repositories;

import br.com.tspaschoal.forumalura.models.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Optional<Curso> findByNome(String nome);

}
