package com.allan.videolocadora.model;

import com.allan.videolocadora.enumeration.ESex;
import com.allan.videolocadora.enumeration.EStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Objects;

@Entity
public class Partner extends Customer {

    @NotNull
    @NotBlank
    @Length(min = 20, max = 200)
    @Column(length = 200, nullable = false, unique = true)
    private String address;

    @NotNull
    @NotBlank
    @Length(min = 9, max = 9)
    @Column(length = 9, nullable = false, unique = true)
    private String phone;

    @NotNull
    @NotBlank
    @Length(min = 11, max = 11)
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    public Partner() {
    }

    public Partner(String name, Date birthDate, ESex sex, EStatus status, String address, String phone, String cpf) {
        super(name, birthDate, sex, status);
        this.address = address;
        this.phone = phone;
        this.cpf = cpf;
    }

    public Partner(Long id, String name, Date birthDate, ESex sex, EStatus status, String address, String phone, String cpf) {
        super(id, name, birthDate, sex, status);
        this.address = address;
        this.phone = phone;
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner partner)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCpf(), partner.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCpf());
    }

    @Override
    public String toString() {
        return "Partner[" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", cpf='" + cpf + '\'' +
                ']';
    }
}
