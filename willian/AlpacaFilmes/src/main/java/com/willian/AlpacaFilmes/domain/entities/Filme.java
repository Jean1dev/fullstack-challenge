package com.willian.AlpacaFilmes.domain.entities;

import com.willian.AlpacaFilmes.common.events.FilmeEvent;
import com.willian.AlpacaFilmes.infra.publisher.EventPublisher;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "filmes")
public class Filme implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(length = 300)
    private String title;

    @Column(name = "original_title", length = 300)
    private String originalTitle;

    @Column(name = "release_date", length = 15)
    private String releaseDate;

    @Column(name = "poster_path", length = 100)
    private String posterPath;

    @Column(length = 1000)
    private String overview;

    @Column(name = "data_cadastro")
    private Date dataCadastro;

    public Filme() {
    }

    public Filme(Long id, String title, String originalTitle, String releaseDate, String posterPath, String overview,  Date dataCadastro) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.overview = overview;
        this.dataCadastro = dataCadastro;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id) && Objects.equals(title, filme.title) && Objects.equals(originalTitle, filme.originalTitle) && Objects.equals(releaseDate, filme.releaseDate) && Objects.equals(posterPath, filme.posterPath) && Objects.equals(overview, filme.overview) && Objects.equals(dataCadastro, filme.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, originalTitle, releaseDate, posterPath, overview, dataCadastro);
    }

    @PostPersist
    public void novoFilmeADicionado() {
        EventPublisher.publishEvent(new FilmeEvent(this));
    }
}
