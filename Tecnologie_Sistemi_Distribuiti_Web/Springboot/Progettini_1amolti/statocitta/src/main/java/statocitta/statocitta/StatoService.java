package statocitta.statocitta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatoService {

    @Autowired
    private StatoRepository repository;

    @Autowired
    private CittaRepository cRepo;

    public Stato addStato(Stato e) {
        return repository.save(e);
    }

    public Optional<Stato> getStato(Long id) {
        return repository.findById(id);
    }

    public List<Stato> getAllStatos() {
        List<Stato> output = new ArrayList<Stato>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Stato updateStato(Stato e) {
        return repository.save(e);
    }

    public void deleteStato(Stato e) {
        repository.delete(e);
    }

    public void deleteStato(Long id) {
        repository.deleteById(id);
    }
}