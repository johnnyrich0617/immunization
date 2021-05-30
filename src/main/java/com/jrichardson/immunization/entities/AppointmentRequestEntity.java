package com.jrichardson.immunization.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "vaccination_apt_sched")
public class AppointmentRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name = "citizen")
    Long citizenId;

    @NotNull
    @Column(name = "vac_data")
    Long vaccinationDataId;

    @NotNull
    @Column(name = "sched_dose_num")
    int doseNumber;

    @NotNull
    @Column(name = "appointment_date")
    Date appointmentDate;

    @NotNull
    @Column(name = "completed", columnDefinition = "boolean default false")
    boolean completed = false;

    public AppointmentRequestEntity() {
    }

    public AppointmentRequestEntity(Long citizenId, Long vaccinationDataId,
                                    int doseNumber, Date appointmentDate,
                                    boolean completed) {
        this.citizenId = citizenId;
        this.vaccinationDataId = vaccinationDataId;
        this.doseNumber = doseNumber;
        this.appointmentDate = appointmentDate;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public Long getVaccinationDataId() {
        return vaccinationDataId;
    }

    public void setVaccinationDataId(Long vaccinationDataId) {
        this.vaccinationDataId = vaccinationDataId;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public void setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppointmentRequestEntity)) return false;
        AppointmentRequestEntity that = (AppointmentRequestEntity) o;
        return getDoseNumber() == that.getDoseNumber() && isCompleted() == that.isCompleted()
                && getId().equals(that.getId()) && getCitizenId().equals(that.getCitizenId())
                && getVaccinationDataId().equals(that.getVaccinationDataId())
                && getAppointmentDate().equals(that.getAppointmentDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCitizenId(),
                getVaccinationDataId(), getDoseNumber(),
                getAppointmentDate(), isCompleted());
    }

    @Override
    public String toString() {
        return "AppointmentRequestEntity{" +
                "id=" + id +
                ", citizenId=" + citizenId +
                ", vaccinationDataId=" + vaccinationDataId +
                ", doseNumber=" + doseNumber +
                ", appointmentDate=" + appointmentDate +
                ", completed=" + completed +
                '}';
    }
}
