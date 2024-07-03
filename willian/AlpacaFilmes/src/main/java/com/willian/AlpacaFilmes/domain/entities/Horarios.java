package com.willian.AlpacaFilmes.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "horarios")
public class Horarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "hora_inicio", length = 5, nullable = false)
    private String horaInicio;
    @Column(name = "hora_fim", length = 5, nullable = false)
    private String horaFim;

    public Horarios() {
    }

    public Horarios(Long id, String horaInicio, String horaFim) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horarios horarios = (Horarios) o;
        return Objects.equals(id, horarios.id) && Objects.equals(horaInicio, horarios.horaInicio) && Objects.equals(horaFim, horarios.horaFim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, horaInicio, horaFim);
    }
}
