package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository <Medico, Long> {



    Page<Medico> findAllByAtivoTrue(Pageable paginacao);


    @Query("SELECT m FROM Medico m WHERE m.ativo = true AND m.especialidade = :especialidade AND NOT EXISTS (" +
            "SELECT c FROM Consulta c WHERE c.medico = m AND c.data = :data) ORDER BY function('RAND') OFFSET 0"
    )
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);


    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    Boolean findAtivoById(Long id);
}
