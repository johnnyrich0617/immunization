package com.jrichardson.immunization.models;

import java.util.Objects;

public class LatLngLiteral {

    private Double lat;
    private Double lng;

    public LatLngLiteral(Double lat, Double lng){
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LatLngLiteral)) return false;
        LatLngLiteral that = (LatLngLiteral) o;
        return getLat().equals(that.getLat()) && getLng().equals(that.getLng());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLat(), getLng());
    }

    @Override
    public String toString() {
        return "LatLngLiteral{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
