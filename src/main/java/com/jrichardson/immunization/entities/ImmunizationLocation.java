package com.jrichardson.immunization.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="LOOKUP_IMMUNIZATION_LOCATIONS")
public class ImmunizationLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "loc_name cannot be null")
    @NotEmpty(message = "loc_name is a Required Field")
    private String loc_name;

    @NotNull(message = "loc_addr cannot be null")
    @NotEmpty(message = "loc_addr is a Required Field")
    private String loc_addr;

    @NotNull(message = "loc_lng cannot be null")
    private Double loc_lng;

    @NotNull(message = "loc_lat cannot be null")
    private Double loc_lat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocName() {
        return loc_name;
    }

    public void setLocName(String locName) {
        this.loc_name = locName;
    }

    public String getLocAddr() {
        return loc_addr;
    }

    public void setLocAddr(String locAddr) {
        this.loc_addr = locAddr;
    }

    public Double getLocLat() {
        return loc_lat;
    }

    public void setLocLat(Double locLat) {
        this.loc_lat = locLat;
    }

    public Double getLocLng() {
        return loc_lng;
    }

    public void setLocLng(Double locLng) {
        this.loc_lng = locLng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmunizationLocation)) return false;
        ImmunizationLocation that = (ImmunizationLocation) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getLocName(),
                that.getLocName()) && Objects.equals(getLocAddr(),
                that.getLocAddr()) && Objects.equals(getLocLng(),
                that.getLocLng()) && Objects.equals(getLocLat(),
                that.getLocLat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLocName(), getLocAddr(), getLocLng(), getLocLat());
    }

    @Override
    public String toString() {
        return "ImmunizationLocation{" +
                "id=" + id +
                ", locName='" + loc_name + '\'' +
                ", locAddr='" + loc_addr + '\'' +
                ", locLng=" + loc_lng +
                ", locLat=" + loc_lat +
                '}';
    }
}
