package com.allan.videolocadora.model;

import com.allan.videolocadora.enumeration.ECategory;
import com.allan.videolocadora.enumeration.converter.ECategoryConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 2, max = 100)
    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(length = 4, nullable = false)
    private int year;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String synopsis;

    @NotNull
    @Convert(converter = ECategoryConverter.class)
    @Column(nullable = false)
    private ECategory category;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class c;

    @ManyToMany
    @JoinTable(name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> cast = new ArrayList<>();

    public Movie() {
    }

    public Movie(Long id, String name, int year, String synopsis, String category, Director director, Class c, List<Actor> cast) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.synopsis = synopsis;
        this.category = getConverter().convertToEntityAttribute(category);
        this.director = director;
        this.c = c;
        this.cast = cast;
    }

    public Movie(String name, int year, String synopsis, String category, Director director, Class c, List<Actor> cast) {
        this.name = name;
        this.year = year;
        this.synopsis = synopsis;
        this.category = getConverter().convertToEntityAttribute(category);
        this.director = director;
        this.c = c;
        this.cast = cast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public ECategory getCategory() {
        return category;
    }

    public void setCategory(@NotNull @NotBlank ECategory category) {
        this.category = category;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            return other.name == null;
        } else return name.equals(other.name);
    }

    private ECategoryConverter getConverter() {
        return new ECategoryConverter();
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + "]";
    }

}
