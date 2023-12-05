package com.allan.videolocadora.model;

import com.allan.videolocadora.enumeration.EPaid;
import com.allan.videolocadora.enumeration.converter.EPaidConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column
    private double worth;

    @Column
    private double fine;

    @Column
    @Temporal(TemporalType.DATE)
    private Date estimatedDevolutionDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date devolutionDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date locationDate;

    @NotNull
    @Convert(converter = EPaidConverter.class)
    @Column(nullable = false)
    private EPaid paid;

    public Location() {
    }

    public Location(Item item, Customer customer, double worth, double fine, Date estimatedDevolutionDate, Date devolutionDate, Date locationDate, EPaid paid) {
        this.item = item;
        this.customer = customer;
        this.worth = worth;
        this.fine = fine;
        this.estimatedDevolutionDate = estimatedDevolutionDate;
        this.devolutionDate = devolutionDate;
        this.locationDate = locationDate;
        this.paid = paid;
    }

    public Location(Long id, Item item, Customer customer, double worth, double fine, Date estimatedDevolutionDate, Date devolutionDate, Date locationDate, EPaid paid) {
        this.id = id;
        this.item = item;
        this.customer = customer;
        this.worth = worth;
        this.fine = fine;
        this.estimatedDevolutionDate = estimatedDevolutionDate;
        this.devolutionDate = devolutionDate;
        this.locationDate = locationDate;
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public Date getEstimatedDevolutionDate() {
        return estimatedDevolutionDate;
    }

    public void setEstimatedDevolutionDate(Date estimatedDevolutionDate) {
        this.estimatedDevolutionDate = estimatedDevolutionDate;
    }

    public Date getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public Date getLocationDate() {
        return locationDate;
    }

    public void setLocationDate(Date locationDate) {
        this.locationDate = locationDate;
    }

    public EPaid getPaid() {
        return paid;
    }

    public void setPaid(EPaid paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return Objects.equals(getId(), location.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Location[" +
                "id=" + id +
                ", item=" + item +
                ", customer=" + customer +
                ", worth=" + worth +
                ", devolutionDate=" + devolutionDate +
                ']';
    }
}
