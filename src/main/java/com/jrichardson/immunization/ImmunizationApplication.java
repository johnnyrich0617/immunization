package com.jrichardson.immunization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.jrichardson.immunization")
public class ImmunizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmunizationApplication.class, args);
    }

}
