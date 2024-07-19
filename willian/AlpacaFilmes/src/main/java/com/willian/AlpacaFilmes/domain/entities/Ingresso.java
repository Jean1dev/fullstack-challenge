package com.willian.AlpacaFilmes.domain.entities;

import com.willian.AlpacaFilmes.domain.enums.StatusIngresso;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ingressos")
public class Ingresso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ingresso_combo", joinColumns = {@JoinColumn(name = "id_ingresso")},
            inverseJoinColumns = {@JoinColumn(name = "id_combo")})
    private List<Combo> comboList;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_filme")
    private Filme filme;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sala")
    private Salas salas;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_horario")
    private Horarios horarios;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_ingresso")
    private TipoIngresso tipoIngresso;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cadeira")
    private Cadeiras cadeiras;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 11)
    private String documento;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusIngresso status = StatusIngresso.ATIVO;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public Ingresso() {
    }

    public Ingresso(Long id, List<Combo> comboList, Filme filme, Salas salas, Horarios horarios, TipoIngresso tipoIngresso, Cadeiras cadeiras, String nome, String documento, StatusIngresso status) {
        this.id = id;
        this.comboList = comboList;
        this.filme = filme;
        this.salas = salas;
        this.horarios = horarios;
        this.tipoIngresso = tipoIngresso;
        this.cadeiras = cadeiras;
        this.nome = nome;
        this.documento = documento;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Combo> getComboList() {
        return comboList;
    }

    public void setComboList(List<Combo> comboList) {
        this.comboList = comboList;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Salas getSalas() {
        return salas;
    }

    public void setSalas(Salas salas) {
        this.salas = salas;
    }

    public Horarios getHorarios() {
        return horarios;
    }

    public void setHorarios(Horarios horarios) {
        this.horarios = horarios;
    }

    public TipoIngresso getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(TipoIngresso tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public Cadeiras getCadeiras() {
        return cadeiras;
    }

    public void setCadeiras(Cadeiras cadeiras) {
        this.cadeiras = cadeiras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public StatusIngresso getStatus() {
        return status;
    }

    public void setStatus(StatusIngresso status) {
        this.status = status;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return Objects.equals(id, ingresso.id) && tipoIngresso == ingresso.tipoIngresso && Objects.equals(nome, ingresso.nome) && Objects.equals(documento, ingresso.documento) && status == ingresso.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoIngresso, nome, documento, status);
    }
}
