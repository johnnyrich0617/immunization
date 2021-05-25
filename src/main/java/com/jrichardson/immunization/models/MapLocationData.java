package com.jrichardson.immunization.models;

import java.util.Objects;

public class MapLocationData {

    private LatLngLiteral center;
    private MapMarkers mapMarkers;

    public MapLocationData() {
    }

    public MapLocationData(LatLngLiteral center, MapMarkers mapMarkers){
        this.center = center;
        this.mapMarkers = mapMarkers;
    }

    public LatLngLiteral getCenter() {
        return center;
    }

    public void setCenter(LatLngLiteral center) {
        this.center = center;
    }

    public MapMarkers getMapMarkers() {
        return mapMarkers;
    }

    public void setMapMarkers(MapMarkers mapMarkers) {
        this.mapMarkers = mapMarkers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapLocationData)) return false;
        MapLocationData that = (MapLocationData) o;
        return getCenter().equals(that.getCenter()) && getMapMarkers().equals(that.getMapMarkers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter(), getMapMarkers());
    }

    @Override
    public String toString() {
        return "MapLocationData{" +
                "center=" + center +
                ", mapMarkers=" + mapMarkers +
                '}';
    }
}
