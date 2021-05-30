package com.jrichardson.immunization.models;


import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class AppointmentAddRequest {

    @NotNull(message = "The CitizenId is required to complete the request")
    Long citizenId;

    @NotNull(message = "The VaccinationDataId is required to complete the request")
    Long vaccinationDataId;

    @NotNull(message = "The Dose number is required  to complete the request")
    int doseNumber;

    @NotNull(message = "The appointment Date is required to complete the request")
    Date appointmentDate;


    boolean completed = false;

    public AppointmentAddRequest() {
    }

    public AppointmentAddRequest(Long citizenId, Long vaccinationDataId, int doseNumber, Date appointmentDate, boolean completed) {
        this.citizenId = citizenId;
        this.vaccinationDataId = vaccinationDataId;
        this.doseNumber = doseNumber;
        this.appointmentDate = appointmentDate;
        this.completed = completed;
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
        if (!(o instanceof AppointmentAddRequest)) return false;
        AppointmentAddRequest that = (AppointmentAddRequest) o;
        return getDoseNumber() == that.getDoseNumber()
                && isCompleted() == that.isCompleted()
                && getCitizenId().equals(that.getCitizenId())
                && getVaccinationDataId().equals(that.getVaccinationDataId())
                && getAppointmentDate().equals(that.getAppointmentDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCitizenId(),
                getVaccinationDataId(),
                getDoseNumber(),
                getAppointmentDate(),
                isCompleted());
    }

    @Override
    public String toString() {
        return "AppointmentAddRequest{" +
                "citizenId=" + citizenId +
                ", vaccinationDataId=" + vaccinationDataId +
                ", doseNumber=" + doseNumber +
                ", appointmentDate=" + appointmentDate +
                ", completed=" + completed +
                '}';
    }
}
