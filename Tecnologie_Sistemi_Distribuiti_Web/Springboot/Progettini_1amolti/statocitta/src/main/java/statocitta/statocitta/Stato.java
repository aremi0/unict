package statocitta.statocitta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Stato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private int popolazione;

    public Stato() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Stato(String nome, int popolazione) {
        this.nome = nome;
        this.popolazione = popolazione;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPopolazione() {
        return this.popolazione;
    }

    public void setPopolazione(int popolazione) {
        this.popolazione = popolazione;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + nome + "'" +
            ", popolazione='" + popolazione + "'" +
            "}";
    }

}