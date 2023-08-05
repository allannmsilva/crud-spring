package com.allan.videolocadora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codMovie;

    @Column(length = 200, nullable = false)        
    private String title;

    @Column(length = 200, nullable = false)
    private String genre;

    public Movie() {
    }

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public Long getCodMovie() {
        return codMovie;
    }

    public void setCodMovie(Long codMovie) {
        this.codMovie = codMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codMovie == null) ? 0 : codMovie.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (codMovie == null) {
            if (other.codMovie != null)
                return false;
        } else if (!codMovie.equals(other.codMovie))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Movie [codMovie=" + codMovie + ", title=" + title + ", genre=" + genre + "]";
    }

}
