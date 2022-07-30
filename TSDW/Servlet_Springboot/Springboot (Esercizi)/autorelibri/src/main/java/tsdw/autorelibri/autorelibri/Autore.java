package tsdw.autorelibri.autorelibri;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    
    @NotNull
    private Date nascita;


    public Autore(String nome, Date nascita) {
        this.nome = nome;
        this.nascita = nascita;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascita() {
        return this.nascita;
    }

    public void setNascita(Date nascita) {
        this.nascita = nascita;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + nome + "'" +
            ", nascita='" + nascita + "'" +
            "}";
    }


    public Autore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}