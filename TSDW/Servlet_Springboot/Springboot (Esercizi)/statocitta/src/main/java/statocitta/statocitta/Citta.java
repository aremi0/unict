package statocitta.statocitta;

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
@Table(name = "citta", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "stato_id"})})
public class Citta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private double estensione;
    @ManyToOne
    @NotNull
    private Stato stato;

    public Citta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Citta(String nome, double estensione, Stato stato) {
        this.nome = nome;
        this.estensione = estensione;
        this.stato = stato;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getEstensione() {
        return this.estensione;
    }

    public void setEstensione(double estensione) {
        this.estensione = estensione;
    }

    public Stato getStato() {
        return this.stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + nome + "'" +
            ", estensione='" + estensione + "'" +
            ", stato='" + stato + "'" +
            "}";
    }

}