package statocitta.statocitta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CittaService {

    @Autowired
    private CittaRepository repository;

    public Citta addCitta(Citta e) {
        return repository.save(e);
    }

    public Optional<Citta> getCitta(Long id) {
        return repository.findById(id);
    }

    public List<Citta> getAllCittas() {
        List<Citta> output = new ArrayList<Citta>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Citta updateCitta(Citta e) {
        return repository.save(e);
    }

    public void deleteCitta(Citta e) {
        repository.delete(e);
    }

    public void deleteCitta(Long id) {
        repository.deleteById(id);
    }

    public List<Citta> getStatoCitta(Long statoId) {
        List<Citta> citta = new ArrayList<>();
        repository.findByStatoId(statoId).forEach(citta::add);
        return citta;
    }

    public List<Citta> getNomeCitta(String nome) { //Restituisce il nome di tutti i libri di un autore
        List<Citta> citta = new ArrayList<>();
        repository.findByNome(nome).forEach(citta::add);
        return citta;
    }
}