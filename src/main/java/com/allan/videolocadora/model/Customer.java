package com.allan.videolocadora.model;

import com.allan.videolocadora.enumeration.ESex;
import com.allan.videolocadora.enumeration.EStatus;
import com.allan.videolocadora.enumeration.converter.ESexConverter;
import com.allan.videolocadora.enumeration.converter.EStatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer implements Serializable {

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
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NotNull
    @Convert(converter = ESexConverter.class)
    @Column(nullable = false)
    private ESex sex;

    @NotNull
    @Convert(converter = EStatusConverter.class)
    @Column(nullable = false)
    private EStatus status;

    public Customer() {
    }

    public Customer(String name, Date birthDate, ESex sex, EStatus status) {
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.status = status;
    }

    public Customer(Long id, String name, Date birthDate, ESex sex, EStatus status) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ESex getSex() {
        return sex;
    }

    public void setSex(ESex sex) {
        this.sex = sex;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    private ESexConverter getConverter() {
        return new ESexConverter();
    }

    @Override
    public String toString() {
        return "Customer [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", status=" + status +
                ']';
    }
}
