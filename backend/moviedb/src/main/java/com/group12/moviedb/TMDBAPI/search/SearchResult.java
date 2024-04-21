package com.group12.moviedb.TMDBAPI.search;

import java.util.Objects;
import java.util.List;

public class SearchResult {
  private boolean adult;
  private String backdrop_path;
  private List<Integer> genre_ids;
  private int id;
  private String original_language;
  private String original_title;
  private String overview;
  private double popularity;
  private String poster_path;
  private String release_date;
  private String first_air_date;
  private String title;
  private String name;
  private boolean video;
  private double vote_average;
  private int vote_count;
  private String media_type;

  public SearchResult() {
  }

  public SearchResult(boolean adult, String backdrop_path, List<Integer> genre_ids, int id, String original_language, String original_title, String overview, double popularity, String poster_path, String release_date, String first_air_date, String title, String name, boolean video, double vote_average, int vote_count, String media_type) {
    this.adult = adult;
    this.backdrop_path = backdrop_path;
    this.genre_ids = genre_ids;
    this.id = id;
    this.original_language = original_language;
    this.original_title = original_title;
    this.overview = overview;
    this.popularity = popularity;
    this.poster_path = poster_path;
    this.release_date = release_date;
    this.first_air_date = first_air_date;
    this.title = title;
    this.name = name;
    this.video = video;
    this.vote_average = vote_average;
    this.vote_count = vote_count;
    this.media_type = media_type;
  }

  public boolean isAdult() {
    return this.adult;
  }

  public boolean getAdult() {
    return this.adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public String getBackdrop_path() {
    return this.backdrop_path;
  }

  public void setBackdrop_path(String backdrop_path) {
    this.backdrop_path = backdrop_path;
  }

  public List<Integer> getGenre_ids() {
    return this.genre_ids;
  }

  public void setGenre_ids(List<Integer> genre_ids) {
    this.genre_ids = genre_ids;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOriginal_language() {
    return this.original_language;
  }

  public void setOriginal_language(String original_language) {
    this.original_language = original_language;
  }

  public String getOriginal_title() {
    return this.original_title;
  }

  public void setOriginal_title(String original_title) {
    this.original_title = original_title;
  }

  public String getOverview() {
    return this.overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public double getPopularity() {
    return this.popularity;
  }

  public void setPopularity(double popularity) {
    this.popularity = popularity;
  }

  public String getPoster_path() {
    return this.poster_path;
  }

  public void setPoster_path(String poster_path) {
    this.poster_path = poster_path;
  }

  public String getRelease_date() {
    return this.release_date;
  }

  public void setRelease_date(String release_date) {
    this.release_date = release_date;
  }

  public String getFirst_air_date() {
    return this.first_air_date;
  }

  public void setFirst_air_date(String first_air_date) {
    this.first_air_date = first_air_date;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isVideo() {
    return this.video;
  }

  public boolean getVideo() {
    return this.video;
  }

  public void setVideo(boolean video) {
    this.video = video;
  }

  public double getVote_average() {
    return this.vote_average;
  }

  public void setVote_average(double vote_average) {
    this.vote_average = vote_average;
  }

  public int getVote_count() {
    return this.vote_count;
  }

  public void setVote_count(int vote_count) {
    this.vote_count = vote_count;
  }

  public String getMedia_type() {
    return this.media_type;
  }

  public void setMedia_type(String media_type) {
    this.media_type = media_type;
  }

  public SearchResult adult(boolean adult) {
    setAdult(adult);
    return this;
  }

  public SearchResult backdrop_path(String backdrop_path) {
    setBackdrop_path(backdrop_path);
    return this;
  }

  public SearchResult genre_ids(List<Integer> genre_ids) {
    setGenre_ids(genre_ids);
    return this;
  }

  public SearchResult id(int id) {
    setId(id);
    return this;
  }

  public SearchResult original_language(String original_language) {
    setOriginal_language(original_language);
    return this;
  }

  public SearchResult original_title(String original_title) {
    setOriginal_title(original_title);
    return this;
  }

  public SearchResult overview(String overview) {
    setOverview(overview);
    return this;
  }

  public SearchResult popularity(double popularity) {
    setPopularity(popularity);
    return this;
  }

  public SearchResult poster_path(String poster_path) {
    setPoster_path(poster_path);
    return this;
  }

  public SearchResult release_date(String release_date) {
    setRelease_date(release_date);
    return this;
  }

  public SearchResult first_air_date(String first_air_date) {
    setFirst_air_date(first_air_date);
    return this;
  }

  public SearchResult title(String title) {
    setTitle(title);
    return this;
  }

  public SearchResult name(String name) {
    setName(name);
    return this;
  }

  public SearchResult video(boolean video) {
    setVideo(video);
    return this;
  }

  public SearchResult vote_average(double vote_average) {
    setVote_average(vote_average);
    return this;
  }

  public SearchResult vote_count(int vote_count) {
    setVote_count(vote_count);
    return this;
  }

  public SearchResult media_type(String media_type) {
    setMedia_type(media_type);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SearchResult)) {
            return false;
        }
        SearchResult searchResult = (SearchResult) o;
        return adult == searchResult.adult && Objects.equals(backdrop_path, searchResult.backdrop_path) && Objects.equals(genre_ids, searchResult.genre_ids) && id == searchResult.id && Objects.equals(original_language, searchResult.original_language) && Objects.equals(original_title, searchResult.original_title) && Objects.equals(overview, searchResult.overview) && popularity == searchResult.popularity && Objects.equals(poster_path, searchResult.poster_path) && Objects.equals(release_date, searchResult.release_date) && Objects.equals(first_air_date, searchResult.first_air_date) && Objects.equals(title, searchResult.title) && Objects.equals(name, searchResult.name) && video == searchResult.video && vote_average == searchResult.vote_average && vote_count == searchResult.vote_count && Objects.equals(media_type, searchResult.media_type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adult, backdrop_path, genre_ids, id, original_language, original_title, overview, popularity, poster_path, release_date, first_air_date, title, name, video, vote_average, vote_count, media_type);
  }

  @Override
  public String toString() {
    return "{" +
      " adult='" + isAdult() + "'" +
      ", backdrop_path='" + getBackdrop_path() + "'" +
      ", genre_ids='" + getGenre_ids() + "'" +
      ", id='" + getId() + "'" +
      ", original_language='" + getOriginal_language() + "'" +
      ", original_title='" + getOriginal_title() + "'" +
      ", overview='" + getOverview() + "'" +
      ", popularity='" + getPopularity() + "'" +
      ", poster_path='" + getPoster_path() + "'" +
      ", release_date='" + getRelease_date() + "'" +
      ", first_air_date='" + getFirst_air_date() + "'" +
      ", title='" + getTitle() + "'" +
      ", name='" + getName() + "'" +
      ", video='" + isVideo() + "'" +
      ", vote_average='" + getVote_average() + "'" +
      ", vote_count='" + getVote_count() + "'" +
      ", media_type='" + getMedia_type() + "'" +
      "}";
  }
}
