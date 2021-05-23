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
@Table(name="lookup_vaccine_types")
public class VaccineType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Common Name (CN) cannot be Empty")
    @NotNull(message = "Common Name (CN) cannot be NULL")
    String cn;

    @NotEmpty(message = "Drug Name (DN) cannot be Empty")
    @NotNull(message = "Drug Name (DN) cannot be NULL")
    String dn;

    @NotEmpty(message = "Manufacturer cannot be Empty")
    @NotNull(message = "Manufacturer cannot be NULL")
    String mfg;

    @NotEmpty(message = "Vaccine Type cannot be Empty")
    @NotNull(message = "Vaccine Type cannot be NULL")
    String vac_type;

    @NotEmpty(message = "Vaccine Target cannot be Empty")
    @NotNull(message = "Vaccine Target cannot be NULL")
    String vac_target;

    public VaccineType() {
    }

    public VaccineType(String cn, String dn, String mfg, String vac_type, String vac_target) {
        this.cn = cn;
        this.dn = dn;
        this.mfg = mfg;
        this.vac_type = vac_type;
        this.vac_target = vac_target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getMfg() {
        return mfg;
    }

    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    public String getVac_type() {
        return vac_type;
    }

    public void setVac_type(String vac_type) {
        this.vac_type = vac_type;
    }

    public String getVac_target() {
        return vac_target;
    }

    public void setVac_target(String vac_target) {
        this.vac_target = vac_target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VaccineType)) return false;
        VaccineType that = (VaccineType) o;
        return getId().equals(that.getId())
                && getCn().equals(that.getCn())
                && getDn().equals(that.getDn())
                && getMfg().equals(that.getMfg())
                && getVac_type().equals(that.getVac_type())
                && getVac_target().equals(that.getVac_target());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCn(), getDn(), getMfg(), getVac_type(), getVac_target());
    }
}
