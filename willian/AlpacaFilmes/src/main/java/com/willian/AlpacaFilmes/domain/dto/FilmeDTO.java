package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.willian.AlpacaFilmes.domain.entities.Filme;

import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder({
        "id", "title", "overview", "poster_path", "original_title", "release_date"
})
public class FilmeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String title;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("original_title")
    private String releaseDate;

    @JsonProperty("poster_path")
    private String posterPath;

    private String overview;

    @JsonIgnore
    private Date dataCadastro;

    public FilmeDTO() {
    }

    public FilmeDTO(Long id, String title, String originalTitle, String releaseDate, String posterPath, String overview, Date dataCadastro) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.overview = overview;
        this.dataCadastro = dataCadastro;
    }

    public FilmeDTO(Filme filme) {
        this.id = filme.getId();
        this.title = filme.getTitle();
        this.originalTitle = filme.getOriginalTitle();
        this.releaseDate = filme.getReleaseDate();
        this.posterPath = filme.getPosterPath();
        this.overview = filme.getOverview();
        this.dataCadastro = filme.getDataCadastro();
    }

    public static Filme converter(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        filme.setTitle(filmeDTO.getTitle());
        filme.setOverview(filmeDTO.getOverview());
        filme.setPosterPath(filmeDTO.getPosterPath());
        filme.setDataCadastro(filmeDTO.dataCadastro);
        filme.setReleaseDate(filmeDTO.getReleaseDate());
        filme.setOriginalTitle(filmeDTO.getOriginalTitle());
        return filme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
