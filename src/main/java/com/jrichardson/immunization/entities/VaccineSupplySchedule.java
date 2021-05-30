package com.jrichardson.immunization.entities;

import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "vaccine_supply_chain")
public class VaccineSupplySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "Vaccine must not be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vaccine", referencedColumnName = "id")
    VaccineType vaccine;

    @NotNull(message = "Ship Date Must not be NULL")
    Date ship_date;

    @NotNull(message = "Immunization Location Must not be NULL")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location", referencedColumnName = "id")
    ImmunizationLocation location;

    @NotNull(message = "Available Date Must not be NULL")
    Date avail_date;

    @NotNull(message = "Number of Units Must not be NULL")
    int num_units;

    @NotNull(message = "Number of Available Units Must not be NULL")
    int num_available;

    @NotEmpty(message = "lot Number must not be Empty")
    @NotNull(message = "Lot Number Must not be NULL")
    String lot_num;

    @NotNull(message = "Dose Designation Must not be NULL")
    int des_dose;

    public VaccineSupplySchedule() {
    }

    public VaccineSupplySchedule(VaccineType vaccine, Date ship_date, ImmunizationLocation location,
                                 Date avail_date, int num_units, int num_available,
                                 String lot_num, int des_dose) {
        this.vaccine = vaccine;
        this.ship_date = ship_date;
        this.location = location;
        this.avail_date = avail_date;
        this.num_units = num_units;
        this.num_available = num_available;
        this.lot_num = lot_num;
        this.des_dose = des_dose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VaccineType getVaccine() {
        return vaccine;
    }

    public void setVaccine(VaccineType vaccine) {
        this.vaccine = vaccine;
    }

    public Date getShip_date() {
        return ship_date;
    }

    public void setShip_date(Date ship_date) {
        this.ship_date = ship_date;
    }

    public ImmunizationLocation getLocation() {
        return location;
    }

    public void setLocation(ImmunizationLocation location) {
        this.location = location;
    }

    public Date getAvail_date() {
        return avail_date;
    }

    public void setAvail_date(Date avail_date) {
        this.avail_date = avail_date;
    }

    public int getNum_units() {
        return num_units;
    }

    public void setNum_units(int num_units) {
        this.num_units = num_units;
    }

    public int getNum_available() {
        return num_available;
    }

    public void setNum_available(int num_available) {
        this.num_available = num_available;
    }

    public String getLot_num() {
        return lot_num;
    }

    public void setLot_num(String lot_num) {
        this.lot_num = lot_num;
    }

    public int getDes_dose() {
        return des_dose;
    }

    public void setDes_dose(int des_dose) {
        this.des_dose = des_dose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VaccineSupplySchedule)) return false;
        VaccineSupplySchedule that = (VaccineSupplySchedule) o;
        return getNum_units() == that.getNum_units() && getNum_available() == that.getNum_available()
                && getDes_dose() == that.getDes_dose() && getId().equals(that.getId())
                && getVaccine().equals(that.getVaccine()) && getShip_date().equals(that.getShip_date())
                && getLocation().equals(that.getLocation()) && getAvail_date().equals(that.getAvail_date())
                && getLot_num().equals(that.getLot_num());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVaccine(), getShip_date(), getLocation(),
                getAvail_date(), getNum_units(), getNum_available(), getLot_num(), getDes_dose());
    }

    @Override
    public String toString() {
        return "VaccineSupplySchedule{" +
                "id=" + id +
                ", vaccine=" + vaccine +
                ", ship_date=" + ship_date +
                ", location=" + location +
                ", avail_date=" + avail_date +
                ", num_units=" + num_units +
                ", num_available=" + num_available +
                ", lot_num='" + lot_num + '\'' +
                ", des_dose=" + des_dose +
                '}';
    }
}
