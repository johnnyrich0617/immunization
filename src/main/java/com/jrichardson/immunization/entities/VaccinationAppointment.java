package com.jrichardson.immunization.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="vaccination_apt_sched")
public class VaccinationAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    /*
       @JsonBackReference is used to prevent circular
       dependency and Stack Overflow when json marshalling occurs.
       This will not get marshalled by json parser.
    */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "citizen", referencedColumnName = "id", nullable = false)
    Citizen citizen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vac_data", referencedColumnName = "id", nullable = false)
    VaccineSupplySchedule vac_data;

    @NotNull
    int sched_dose_num;

    @NotNull
    Timestamp appointment_date;

    public VaccinationAppointment() {
    }

    public VaccinationAppointment(Citizen citizen, VaccineSupplySchedule vac_data,
                                  int sched_dose_num, Timestamp appointment_date) {
        this.citizen = citizen;
        this.vac_data = vac_data;
        this.sched_dose_num = sched_dose_num;
        this.appointment_date = appointment_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public VaccineSupplySchedule getVac_data() {
        return vac_data;
    }

    public void setVac_data(VaccineSupplySchedule vac_data) {
        this.vac_data = vac_data;
    }

    public int getSched_dose_num() {
        return sched_dose_num;
    }

    public void setSched_dose_num(int sched_dose_num) {
        this.sched_dose_num = sched_dose_num;
    }

    public Timestamp getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Timestamp appointment_date) {
        this.appointment_date = appointment_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VaccinationAppointment)) return false;
        VaccinationAppointment that = (VaccinationAppointment) o;
        return getSched_dose_num() == that.getSched_dose_num()
                && getId().equals(that.getId()) && getCitizen().equals(that.getCitizen())
                && getVac_data().equals(that.getVac_data())
                && getAppointment_date().equals(that.getAppointment_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCitizen(), getVac_data(), getSched_dose_num(), getAppointment_date());
    }

    @Override
    public String toString() {
        return "VaccinationAppointment{" +
                "id=" + id +
                ", citizen=" + citizen +
                ", vac_data=" + vac_data +
                ", sched_dose_num=" + sched_dose_num +
                ", appointment_date=" + appointment_date +
                '}';
    }
}
