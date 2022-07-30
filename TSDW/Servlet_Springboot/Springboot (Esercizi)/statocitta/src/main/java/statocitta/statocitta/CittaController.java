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
public class CittaController {

    @Autowired
    private CittaService service;

    @GetMapping("/citta")
    public ResponseEntity<List<Citta>> getAllCittas() {
        List<Citta> entityList = service.getAllCittas();
        return ResponseEntity.ok(entityList);
    }

    @GetMapping("/citta/{id}")
    public ResponseEntity<Citta> getCitta(@PathVariable long id) {
        Optional<Citta> entity = service.getCitta(id);
        if(entity.isPresent())
            return ResponseEntity.ok(entity.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/citta")
    public ResponseEntity<Citta> addCitta(@Valid @RequestBody Citta e) throws URISyntaxException {
        if (e.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Citta entity = service.addCitta(e);
        return ResponseEntity.created(new URI("/api/citta" + entity.getId())).body(entity);
    }

    @PutMapping("/citta")
    public ResponseEntity<Citta> updateCitta(@Valid @RequestBody Citta e) {
        if (e.getId() == null)
            return ResponseEntity.notFound().build();
        Citta entity = service.updateCitta(e);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/citta/{id}")
    public ResponseEntity<Void> deleteCitta(@PathVariable long id) {
        if (service.getCitta(id).isEmpty())
            return ResponseEntity.notFound().build();

        service.deleteCitta(id);
        return ResponseEntity.ok().build();
    }
}