package com.jrichardson.immunization.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix="app-props")
public class ApplicationConfig {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    public Double map_center_lng;
    public Double map_center_lat;

    public void setMap_Center_Lng(final String map_center_lng ){
        this.map_center_lng = Double.valueOf(map_center_lng);
    }

    public void setMap_Center_Lat(final String map_center_lat){
        this.map_center_lat = Double.valueOf(map_center_lat);
    }


    @PostConstruct
    void printValues(){
        logger.info("Finished Loading Application Runtime Properties....................");
        logger.info("app-props.map_center_lat = " + this.map_center_lat);
        logger.info("app-props.map_center_lng = " + this.map_center_lng);
    }
}
