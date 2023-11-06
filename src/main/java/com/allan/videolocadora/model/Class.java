package com.allan.videolocadora.model;

import com.allan.videolocadora.enumeration.EStatus;
import com.allan.videolocadora.enumeration.converter.EStatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Class {

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
    @Column(nullable = false)
    private double worth;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date devolutionDate;

//    @OneToMany(mappedBy = "c")
//    private List<Movie> movies;

    public Class() {
    }

    public Class(String name, double worth, Date devolutionDate) {
        this.name = name;
        this.worth = worth;
        this.devolutionDate = devolutionDate;
    }

    public Class(Long id, String name, double worth, Date devolutionDate) {
        this.id = id;
        this.name = name;
        this.worth = worth;
        this.devolutionDate = devolutionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Class(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public Date getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

//    public List<Movie> getMovies() {
//        return movies;
//    }

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
        Class other = (Class) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            return other.name == null;
        } else return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Class [id=" + id + ", name=" + name + "worth=" + worth + "devolutionDate=" + new SimpleDateFormat("yyyy/MM/dd").format(devolutionDate) + "]";
    }

}
