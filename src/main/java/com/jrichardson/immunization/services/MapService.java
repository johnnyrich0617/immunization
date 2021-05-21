package com.jrichardson.immunization.services;

import com.jrichardson.immunization.config.ApplicationConfig;
import com.jrichardson.immunization.entities.ImmunizationLocation;
import com.jrichardson.immunization.models.LatLngLiteral;
import com.jrichardson.immunization.models.MapLocationData;
import com.jrichardson.immunization.models.MapMarkers;
import com.jrichardson.immunization.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapService {

    @Autowired
    LocationRepository locationRepo;

    @Autowired
    ApplicationConfig appConfig;

    public MapLocationData getMapLocations()throws Exception{
       List<ImmunizationLocation> locations = locationRepo.findAll();
        MapLocationData mapData = new MapLocationData(
                                new LatLngLiteral(appConfig.map_center_lat, appConfig.map_center_lng),
                                new MapMarkers(locations)
        );
        return mapData;
    }

    public Optional<ImmunizationLocation> getLocation(Long locationId) throws Exception{
        Optional<ImmunizationLocation> loc = locationRepo.findById(locationId);
        if(loc.isPresent()) {
            return loc;
        }
        throw new Exception("Requested Location Does Not Exist");
    }
}
