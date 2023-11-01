package com.allan.videolocadora.model;

import com.allan.videolocadora.enumeration.EItemType;
import com.allan.videolocadora.enumeration.converter.EItemTypeConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 2, max = 100)
    @Column(length = 100, nullable = false, unique = true)
    private String title;

    @NotNull
    @Column(nullable = false)
    private int serialNumber;

    @NotNull
    @Convert(converter = EItemTypeConverter.class)
    @Column(nullable = false)
    private EItemType itemType;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date acquisitionDate;

    public Item() {
    }

    public Item(Long id, String title, int serialNumber, String itemType, Date acquisitionDate) {
        this.id = id;
        this.title = title;
        this.serialNumber = serialNumber;
        this.itemType = getConverter().convertToEntityAttribute(itemType);
        this.acquisitionDate = acquisitionDate;
    }

    public Item(String title, int serialNumber, String itemType, Date acquisitionDate) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.itemType = getConverter().convertToEntityAttribute(itemType);
        this.acquisitionDate = acquisitionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public EItemType getItemType() {
        return itemType;
    }

    public void setItemType(@NotNull @NotBlank EItemType itemType) {
        this.itemType = itemType;
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
        if (title == null) {
            return other.title == null;
        } else return title.equals(other.title);
    }

    private EItemTypeConverter getConverter() {
        return new EItemTypeConverter();
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + title + "]";
    }

}
