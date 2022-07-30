package statocitta.statocitta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CittaRepository extends JpaRepository<Citta, Long>{
    List<Citta> findByStatoId(Long id);
    List<Citta> findByNome(String nome);
}