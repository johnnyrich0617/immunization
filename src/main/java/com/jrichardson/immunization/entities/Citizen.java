package com.jrichardson.immunization.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="CITIZENS")
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "first_name cannot be null")
    @NotEmpty(message = "first_name cannot be empty")
    String first_name;

    @NotNull(message = "last_name cannot be null")
    @NotEmpty(message = "last_name cannot be empty")
    String last_name;

    String middle_init;

    @NotNull(message = "address_1 cannot be null")
    @NotEmpty(message = "address_1 cannot be empty")
    String address_1;

    String address_2;

    @NotNull(message = "city cannot be null")
    @NotEmpty(message = "city cannot be empty")
    String city;

    @NotNull(message = "state cannot be null")
    @NotEmpty(message = "state cannot be empty")
    String state;

    @NotNull(message = "zip_code cannot be null")
    @NotEmpty(message = "zip_code cannot be empty")
    String zip_code;

    @NotNull(message = "ssn cannot be null")
    @NotEmpty(message = "ssn cannot be empty")
    String ssn;

    /*
        @JasonManagedReference is used to prevent circular
        dependency and Stack Overflow when json marshalling occurs.
        This will be what actually gets marshalled by json parser.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "citizen")
    List<VaccinationAppointment> appointments;

    public Citizen() {
    }

    public Citizen(String first_name, String last_name,
                   String middle_init, String address_1, String address_2,
                   String city, String state, String zip_code, String ssn)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_init = middle_init;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
        this.ssn = ssn;
    }

    public Citizen(String first_name, String last_name, String middle_init, String address_1,
                   String city, String state, String zip_code, String ssn)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_init = middle_init;
        this.address_1 = address_1;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
        this.ssn = ssn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_init() {
        return middle_init;
    }

    public void setMiddle_init(String middle_init) {
        this.middle_init = middle_init;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<VaccinationAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<VaccinationAppointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citizen)) return false;
        Citizen citizen = (Citizen) o;
        return getId().equals(citizen.getId())
                && getFirst_name().equals(citizen.getFirst_name())
                && getLast_name().equals(citizen.getLast_name())
                && Objects.equals(getMiddle_init(), citizen.getMiddle_init())
                && getAddress_1().equals(citizen.getAddress_1())
                && Objects.equals(getAddress_2(), citizen.getAddress_2())
                && getCity().equals(citizen.getCity())
                && getState().equals(citizen.getState())
                && getZip_code().equals(citizen.getZip_code())
                && getSsn().equals(citizen.getSsn())
                && getAppointments().equals(citizen.getAppointments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirst_name(), getLast_name(),
                getMiddle_init(), getAddress_1(), getAddress_2(), getCity(),
                getState(), getZip_code(), getSsn(), getAppointments());
    }

//    @Override
//    public String toString() {
//        return "Citizen{" +
//                "id=" + id +
//                ", first_name='" + first_name + '\'' +
//                ", last_name='" + last_name + '\'' +
//                ", middle_init='" + middle_init + '\'' +
//                ", address_1='" + address_1 + '\'' +
//                ", address_2='" + address_2 + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", zip_code='" + zip_code + '\'' +
//                ", ssn='" + ssn + '\'' +
//                ", appointments=" + appointments +
//                '}';
//    }
}
