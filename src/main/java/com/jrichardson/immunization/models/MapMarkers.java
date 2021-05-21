package com.jrichardson.immunization.models;

import com.jrichardson.immunization.entities.ImmunizationLocation;

import java.util.List;
import java.util.Objects;

public class MapMarkers {

    private List<ImmunizationLocation> locations;

    public MapMarkers(List<ImmunizationLocation> locations){
        this.locations = locations;
    }

    public List<ImmunizationLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<ImmunizationLocation> locations) {
        this.locations = locations;
    }

    public void addLocation(ImmunizationLocation location){
        this.locations.add(location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapMarkers)) return false;
        MapMarkers that = (MapMarkers) o;
        return Objects.equals(getLocations(), that.getLocations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocations());
    }

    @Override
    public String toString() {
        return "MapMarkers{" +
                "locations=" + locations +
                '}';
    }
}
