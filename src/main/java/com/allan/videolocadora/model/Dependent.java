package com.allan.videolocadora.model;

import com.allan.videolocadora.enumeration.ESex;
import com.allan.videolocadora.enumeration.EStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.Objects;

@Entity
public class Dependent extends Customer {

    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    public Dependent() {
    }

    public Dependent(String name, Date birthDate, ESex sex, EStatus status, Partner partner) {
        super(name, birthDate, sex, status);
        this.partner = partner;
    }

    public Dependent(Long id, String name, Date birthDate, ESex sex, EStatus status, Partner partner) {
        super(id, name, birthDate, sex, status);
        this.partner = partner;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependent dependent)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getPartner(), dependent.getPartner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPartner());
    }
}
