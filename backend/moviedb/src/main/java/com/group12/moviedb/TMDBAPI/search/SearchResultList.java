package com.group12.moviedb.TMDBAPI.search;

import java.util.Objects;
import java.util.List;

public class SearchResultList {
  private int page;
  private List<SearchResult> results;
  private int total_pages;
  private int total_results;


  public SearchResultList() {
  }

  public SearchResultList(int page, List<SearchResult> results, int total_pages, int total_results) {
    this.page = page;
    this.results = results;
    this.total_pages = total_pages;
    this.total_results = total_results;
  }

  public int getPage() {
    return this.page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public List<SearchResult> getResults() {
    return this.results;
  }

  public void setResults(List<SearchResult> results) {
    this.results = results;
  }

  public int getTotal_pages() {
    return this.total_pages;
  }

  public void setTotal_pages(int total_pages) {
    this.total_pages = total_pages;
  }

  public int getTotal_results() {
    return this.total_results;
  }

  public void setTotal_results(int total_results) {
    this.total_results = total_results;
  }

  public SearchResultList page(int page) {
    setPage(page);
    return this;
  }

  public SearchResultList results(List<SearchResult> results) {
    setResults(results);
    return this;
  }

  public SearchResultList total_pages(int total_pages) {
    setTotal_pages(total_pages);
    return this;
  }

  public SearchResultList total_results(int total_results) {
    setTotal_results(total_results);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SearchResultList)) {
            return false;
        }
        SearchResultList searchResultList = (SearchResultList) o;
        return page == searchResultList.page && Objects.equals(results, searchResultList.results) && total_pages == searchResultList.total_pages && total_results == searchResultList.total_results;
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, results, total_pages, total_results);
  }

  @Override
  public String toString() {
    return "{" +
      " page='" + getPage() + "'" +
      ", results='" + getResults() + "'" +
      ", total_pages='" + getTotal_pages() + "'" +
      ", total_results='" + getTotal_results() + "'" +
      "}";
  }

}