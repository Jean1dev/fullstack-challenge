package com.willian.AlpacaFilmes.domain.dto.themoviedb;

import java.util.List;

public class TheMovieDbResponse {
    private Dates dates;
    private int page;
    private List<Movie> results;
    private int totalPages;
    private int totalResults;

    public TheMovieDbResponse() {
    }

    public TheMovieDbResponse(Dates dates, int page, List<Movie> results, int totalPages, int totalResults) {
        this.dates = dates;
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
