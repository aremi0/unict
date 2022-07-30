package tsdw.autorelibri.autorelibri;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "libro", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "autore_id"})})
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Autore autore;

    @NotBlank
    private String nome;

    @NotNull
    private int pagine;


    public Libro(Autore autore, String nome, int pagine) {
        this.autore = autore;
        this.nome = nome;
        this.pagine = pagine;
    }

    public Autore getAutore() {
        return this.autore;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPagine() {
        return this.pagine;
    }

    public void setPagine(int pagine) {
        this.pagine = pagine;
    }

    @Override
    public String toString() {
        return "{" +
            " autore='" + autore + "'" +
            ", nome='" + nome + "'" +
            ", pagine='" + pagine + "'" +
            "}";
    }


    public Libro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}