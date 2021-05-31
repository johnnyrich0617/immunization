package com.jrichardson.immunization.models;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

public class AppointmentUpdateRequest {

    @NotNull
    Long appointmentId;

    @NotNull
    Long citizenId;

    Long vaccineDataId;

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
    Date appointmentDate;

    Boolean isCompleted;

    public AppointmentUpdateRequest() {
    }

    public AppointmentUpdateRequest(Long appointmentId, Long citizenId,
                                    Long vaccineDataId, Date appointmentDate,
                                    Boolean isCompleted) {
        this.appointmentId = appointmentId;
        this.citizenId = citizenId;
        this.vaccineDataId = vaccineDataId;
        this.appointmentDate = appointmentDate;
        this.isCompleted = isCompleted;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public Long getVaccineDataId() {
        return vaccineDataId;
    }

    public void setVaccineDataId(Long vaccineDataId) {
        this.vaccineDataId = vaccineDataId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppointmentUpdateRequest)) return false;
        AppointmentUpdateRequest that = (AppointmentUpdateRequest) o;
        return isCompleted() == that.isCompleted()
                && getAppointmentId().equals(that.getAppointmentId())
                && getCitizenId().equals(that.getCitizenId())
                && Objects.equals(getVaccineDataId(), that.getVaccineDataId())
                && Objects.equals(getAppointmentDate(), that.getAppointmentDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAppointmentId(),
                getCitizenId(), getVaccineDataId(),
                getAppointmentDate(), isCompleted());
    }
}
