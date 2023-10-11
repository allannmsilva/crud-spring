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

import java.util.ArrayList;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE DIRECTOR SET STATUS = 'Inactive' WHERE ID = ?")
@Where(clause = "status = 'Active'")
public class Director {
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
    @Column(length = 10, nullable = false)
    @Convert(converter = EStatusConverter.class)
    private EStatus status = EStatus.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "director")
    List<Movie> movies = new ArrayList<>();

    public Director() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Director(String name) {
        this.name = name;
    }

    public Director(Long id, String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
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
        Director other = (Director) obj;
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
        return "Director [id=" + id + ", name=" + name + "]";
    }
}
