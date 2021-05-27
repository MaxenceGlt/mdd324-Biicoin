package com.ipiecoles.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Anime {

    /**
     * Attributs de l'object
     */
    private Long id;
    private String url;
    private String image_url;
    private String title;
    private String synopsis;
    private Integer episodes;
    private Double score;
    private String start_date;
    private String end_date;

    public Anime() {
    }

    public Anime(Long id, String url, String image_url, String title, String synopsis, Integer episodes, Double score, String start_date, String end_date) {
        this.id = id;
        this.url = url;
        this.image_url = image_url;
        this.title = title;
        this.synopsis = synopsis;
        this.episodes = episodes;
        this.score = score;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }




}
