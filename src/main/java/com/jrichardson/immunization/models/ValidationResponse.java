package com.jrichardson.immunization.models;

import java.util.Objects;

public class ValidationResponse {
    long validatedCitizen;

    public ValidationResponse(){}

    public ValidationResponse(Long validatedCitizen){
        this.validatedCitizen = validatedCitizen;
    }

    public long getValidatedCitizen() {
        return validatedCitizen;
    }

    public void setValidatedCitizen(long validatedCitizen) {
        this.validatedCitizen = validatedCitizen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationResponse)) return false;
        ValidationResponse that = (ValidationResponse) o;
        return getValidatedCitizen() == that.getValidatedCitizen();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValidatedCitizen());
    }

    @Override
    public String toString() {
        return "ValidationResponse{" +
                "validatedCitizen=" + validatedCitizen +
                '}';
    }
}
