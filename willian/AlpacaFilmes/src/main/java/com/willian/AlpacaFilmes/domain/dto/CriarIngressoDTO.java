package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@JsonRootName("criar_ingresso")
@JsonPropertyOrder({
        "nome", "documento", "id_filme", "id_tipo_ingresso", "id_sala", "id_cadeira", "id_horario", "combos"
})
public class CriarIngressoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("combos")
    private List<Long> comboList;

    @JsonProperty("id_programcao")
    @NotNull(message = "Id da programação não pode ser nulo")
    @NotBlank(message = "Id da programação é obrigatório!")
    private Long programacao;

    @JsonProperty("id_horario")
    @NotNull(message = "Id do horario não pode ser nulo")
    @NotBlank(message = "Id do horario é obrigatório!")
    private Long horario;

    @JsonProperty("id_tipo_ingresso")
    @NotNull(message = "Id do tipo de ingresso não pode ser nulo")
    @NotBlank(message = "Id do tipo de ingresso é obrigatório!")
    private Long tipoIngresso;

    @JsonProperty("id_cadeira")
    @NotNull(message = "Id do cadeira não pode ser nulo")
    @NotBlank(message = "Id do cadeira é obrigatório!")
    private Long cadeira;

    @Size(min = 3, max = 100, message = "Nome deve conter de 3 a 100 caracteres!")
    private String nome;

    private String documento;

    public CriarIngressoDTO() {
    }

    public CriarIngressoDTO(List<Long> comboList, Long programacao, Long horario, Long tipoIngresso, Long cadeira, String nome, String documento) {
        this.comboList = comboList;
        this.programacao = programacao;
        this.horario = horario;
        this.tipoIngresso = tipoIngresso;
        this.cadeira = cadeira;
        this.nome = nome;
        this.documento = documento;
    }

    public List<Long> getComboList() {
        return comboList;
    }

    public void setComboList(List<Long> comboList) {
        this.comboList = comboList;
    }

    public Long getProgramacao() {
        return programacao;
    }

    public void setProgramacao(Long programacao) {
        this.programacao = programacao;
    }

    public Long getHorario() {
        return horario;
    }

    public void setHorario(Long horario) {
        this.horario = horario;
    }

    public Long getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(Long tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public Long getCadeira() {
        return cadeira;
    }

    public void setCadeira(Long cadeira) {
        this.cadeira = cadeira;
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
}
