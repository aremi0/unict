package statocitta.statocitta;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatoController {

    @Autowired
    private StatoService service;

    @GetMapping("/stato")
    public ResponseEntity<List<Stato>> getAllStatos() {
        List<Stato> entityList = service.getAllStatos();
        return ResponseEntity.ok(entityList);
    }

    @GetMapping("/stato/{id}")
    public ResponseEntity<Stato> getStato(@PathVariable long id) {
        Optional<Stato> entity = service.getStato(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/stato")
    public ResponseEntity<Stato> addStato(@Valid @RequestBody Stato e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Stato entity = service.addStato(e);
        return ResponseEntity.created(new URI("/api/stato" + entity.getId())).body(entity);
    }

    @PutMapping("/stato")
    public ResponseEntity<Stato> updateStato(@Valid @RequestBody Stato e) {
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        Stato entity = service.updateStato(e);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/stato/{id}")
    public ResponseEntity<Void> deleteStato(@PathVariable long id) {
        if (service.getStato(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteStato(id);
        return ResponseEntity.ok().build();
    }
}