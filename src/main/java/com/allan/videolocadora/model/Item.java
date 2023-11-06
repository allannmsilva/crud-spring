package com.allan.videolocadora.model;

import com.allan.videolocadora.enumeration.EItemType;
import com.allan.videolocadora.enumeration.converter.EItemTypeConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @NotNull
    @Column(nullable = false)
    private int serialNumber;

    @NotNull
    @Convert(converter = EItemTypeConverter.class)
    @Column(nullable = false)
    private EItemType type;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date acquisitionDate;

    public Item() {
    }

    public Item(Long id, Movie movie, int serialNumber, String type, Date acquisitionDate) {
        this.id = id;
        this.movie = movie;
        this.serialNumber = serialNumber;
        this.type = getConverter().convertToEntityAttribute(type);
        this.acquisitionDate = acquisitionDate;
    }

    public Item(Movie movie, int serialNumber, String type, Date acquisitionDate) {
        this.movie = movie;
        this.serialNumber = serialNumber;
        this.type = getConverter().convertToEntityAttribute(type);
        this.acquisitionDate = acquisitionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public EItemType getType() {
        return type;
    }

    public void setType(@NotNull @NotBlank EItemType type) {
        this.type = type;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
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
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (movie == null) {
            return other.movie == null;
        } else return movie.equals(other.movie);
    }

    private EItemTypeConverter getConverter() {
        return new EItemTypeConverter();
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + movie + "]";
    }

}
