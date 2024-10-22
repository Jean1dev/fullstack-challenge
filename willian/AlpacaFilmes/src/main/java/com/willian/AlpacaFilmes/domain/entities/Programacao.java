package com.willian.AlpacaFilmes.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "programacao")
public class Programacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_filme")
    private Filme filme;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sala")
    private Salas sala;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "programacao_horario", joinColumns = {@JoinColumn(name = "id_programacao")},
            inverseJoinColumns = {@JoinColumn(name = "id_horario")})
    private List<Horarios> horarios;

    @Column(name = "data_cadastro")
    private Date dataCadastro;

    public Programacao() {
    }

    public Programacao(Long id, Filme filme, Salas sala, List<Horarios> horarios, Date dacaCadastro) {
        this.id = id;
        this.filme = filme;
        this.sala = sala;
        this.horarios = horarios;
        this.dataCadastro = dacaCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Salas getSala() {
        return sala;
    }

    public void setSala(Salas sala) {
        this.sala = sala;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dacaCadastro) {
        this.dataCadastro = dacaCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programacao that = (Programacao) o;
        return Objects.equals(id, that.id) && Objects.equals(filme, that.filme) && Objects.equals(sala, that.sala) && Objects.equals(horarios, that.horarios) && Objects.equals(dataCadastro, that.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filme, sala, horarios, dataCadastro);
    }
}
