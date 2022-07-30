package tsdw.autorelibri.autorelibri;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    public Libro addLibro(Libro e) {
        return repository.save(e);
    }

    public Optional<Libro> getLibro(Long id) {
        return repository.findById(id);
    }

    public List<Libro> getAllLibros() {
        List<Libro> output = new ArrayList<Libro>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Libro updateLibro(Libro e) {
        return repository.save(e);
    }

    public void deleteLibro(Libro e) {
        repository.delete(e);
    }

    public void deleteLibro(Long id) {
        repository.deleteById(id);
    }

    public List<Libro> getAutoreLibri(Long autoreId) {
		List<Libro> libri = new ArrayList<>();
		repository.findByAutoreId(autoreId).forEach(libri::add);
		return libri;
	}

	public List<Libro> getNomeLibri(String nome) { //Restituisce il nome di tutti i libri di un autore
		List<Libro> libri = new ArrayList<>();
		repository.findByNome(nome).forEach(libri::add);
		return libri;
	}
}