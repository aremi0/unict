package tsdw.autorelibri.autorelibri;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    List<Libro> findByAutoreId(Long id);
    List<Libro> findByNome(String nome);
}