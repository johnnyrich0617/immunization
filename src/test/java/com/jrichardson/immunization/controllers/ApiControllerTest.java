package com.jrichardson.immunization.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrichardson.immunization.entities.Citizen;
import com.jrichardson.immunization.entities.ImmunizationLocation;
import com.jrichardson.immunization.entities.VaccineSupplySchedule;
import com.jrichardson.immunization.entities.VaccineType;
import com.jrichardson.immunization.models.MapLocationData;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate = null;

    private static Logger logger;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
        logger = LoggerFactory.getLogger(ApiControllerTest.class);
    }

    @BeforeEach
    void setUp() {
        baseUrl = baseUrl.concat(":").concat(port+ "").concat("/api");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCitizens() {
        try {
            logger.info("Testing getCitizens()..............................");
            logger.info("Sending request to URL..... " +baseUrl.concat("/v1/citizens"));
            ResponseEntity<String> citizens = restTemplate.getForEntity(baseUrl.concat("/v1/citizens"), String.class);
            MatcherAssert.assertThat(citizens.getStatusCode(), is(equalTo(HttpStatus.OK)));

            ObjectMapper mapper = new ObjectMapper();
            List<Citizen> citizenList = mapper.readValue(citizens.getBody(), new TypeReference<>() {});
            assertFalse(citizenList.isEmpty());
            assertTrue(citizenList.size() == 1);
            logger.info("Completed all Assertions for getCitizens()..............................");
        }catch (Exception e ){
            logger.info("Exception Thrown ..............................." + e.getMessage());
        }
    }

    @Test
    void getCitizensById() {
        logger.info("Testing getCitizensById()..............................");
        logger.info("Sending request to URL..... " +baseUrl.concat("/v1/citizens/{id}"));
        Citizen citizen = restTemplate.getForObject(baseUrl.concat("/v1/citizens/{id}"), Citizen.class, 1);
        assertAll(
                () -> assertTrue(citizen.getId() == 1L),
                () -> assertTrue(citizen.getFirst_name().equalsIgnoreCase("mary")),
                () -> assertFalse(citizen.getAppointments().isEmpty()),
                () -> assertTrue(citizen.getAppointments().size() == 2)
        );
        logger.info("Completed all Assertions for getCitizensById()..............................");
    }

    @Test
    void getVaccineTypes() {
        try {
            logger.info("Testing getVaccineTypes()..............................");
            logger.info("Sending request to URL..... " +baseUrl.concat("/v1/vaccinetypes"));
            ResponseEntity<String> citizens = restTemplate.getForEntity(baseUrl.concat("/v1/vaccinetypes"), String.class);
            MatcherAssert.assertThat(citizens.getStatusCode(), is(equalTo(HttpStatus.OK)));

            ObjectMapper mapper = new ObjectMapper();
            List<VaccineType> vaccinetypes = mapper.readValue(citizens.getBody(), new TypeReference<>() {});
            assertFalse(vaccinetypes.isEmpty());
            assertTrue(vaccinetypes.size() == 3);
            logger.info("Completed all Assertions for getVaccineTypes()..............................");
        }catch (Exception e ){
            logger.info("Exception Thrown ..............................." + e.getMessage());
        }
    }

    @Test
    void getVaccineType() {
        logger.info("Testing getVaccineType()..............................");
        logger.info("Sending request to URL..... " +baseUrl.concat("/v1/vaccinetypes/{id}"));
        VaccineType vaccinetype = restTemplate.getForObject(baseUrl.concat("/v1/vaccinetypes/{id}"),
                VaccineType.class, 2);
        assertAll(
                () -> assertTrue(vaccinetype.getId() == 2L),
                () -> assertTrue(vaccinetype.getCn().equalsIgnoreCase("Moderna")),
                () -> assertTrue(vaccinetype.getVac_type().equalsIgnoreCase("mRNA")),
                () -> assertTrue(vaccinetype.getVac_target().equalsIgnoreCase("COVID-19"))
        );
        logger.info("Completed all Assertions for getVaccineType()..............................");
    }

    @Test
    void getMapLocations() {
        try {
            logger.info("Testing getMapLocations()..............................");
            logger.info("Sending request to URL..... " +baseUrl.concat("/v1/map"));
            ResponseEntity<String> citizens = restTemplate.getForEntity(baseUrl.concat("/v1/map"), String.class);
            MatcherAssert.assertThat(citizens.getStatusCode(), is(equalTo(HttpStatus.OK)));

            ObjectMapper mapper = new ObjectMapper();
            MapLocationData mapdata = mapper.readValue(citizens.getBody(), MapLocationData.class);
            assertFalse(mapdata.getMapMarkers().getLocations().isEmpty());
            assertTrue(mapdata.getCenter().getLat().doubleValue() == 38.39741362559147);
            assertTrue(mapdata.getCenter().getLng().doubleValue() == -76.65794130637283);
            assertTrue(mapdata.getMapMarkers().getLocations()
                    .get(0)
                    .getLocName()
                    .equalsIgnoreCase("Hollywood Fire/Rescue Base")
            );

            logger.info("Completed all Assertions for getMapLocations()..............................");
        }catch (Exception e ){
            logger.info("Exception Thrown ..............................." + e.getMessage());
        }
    }

    @Test
    void getMapLocation() {
        logger.info("Testing getMapLocation()..............................");
        logger.info("Sending request to URL..... " +baseUrl.concat("/v1/map/{id}"));
        ImmunizationLocation locdata = restTemplate.getForObject(baseUrl.concat("/v1/map/{id}"),
                ImmunizationLocation.class, 2);
        assertAll(
                () -> assertTrue(locdata.getId() == 2L),
                () -> assertTrue(locdata.getLocName().equalsIgnoreCase("St Marys MedStar Hospital")),
                () -> assertTrue(locdata.getLocAddr()
                        .equalsIgnoreCase("25500 Point Lookout Rd, Leonardtown, MD 20650"))
        );
        logger.info("Completed all Assertions for getMapLocation()..............................");
    }

    @Test
    void getSchedulesForAll() {
        try {
            logger.info("Testing getSchedulesForAll()..............................");
            logger.info("Sending request to URL..... " +baseUrl.concat("/v1/schedules"));
            ResponseEntity<String> citizens = restTemplate.getForEntity(baseUrl.concat("/v1/schedules"), String.class);
            MatcherAssert.assertThat(citizens.getStatusCode(), is(equalTo(HttpStatus.OK)));

            ObjectMapper mapper = new ObjectMapper();
            List<VaccineSupplySchedule> schedules = mapper.readValue(citizens.getBody(), new TypeReference<>() {});
            assertFalse(schedules.isEmpty());
            assertTrue(schedules.size() == 21);
            logger.info("Completed all Assertions for getSchedulesForAll()..............................");
        }catch (Exception e ){
            logger.info("Exception Thrown ..............................." + e.getMessage());
        }
    }

    @Test
    void getSchedulesForVaccine() {
        try {
            logger.info("Testing getSchedulesForVaccine()..............................");
            logger.info("Sending request to URL..... " +baseUrl.concat("/v1/schedules/vaccine/{vaccineid}"));
            ResponseEntity<String> citizens = restTemplate.getForEntity(baseUrl
                    .concat("/v1/schedules/vaccine/{vaccineId}"), String.class, 1);

            MatcherAssert.assertThat(citizens.getStatusCode(), is(equalTo(HttpStatus.OK)));

            ObjectMapper mapper = new ObjectMapper();
            List<VaccineSupplySchedule> schedules = mapper.readValue(citizens.getBody(), new TypeReference<>() {});
            assertFalse(schedules.isEmpty());
            assertTrue(schedules.size() == 13);
            logger.info("Completed all Assertions for getSchedulesForVaccine()..............................");
        }catch (Exception e ){
            logger.info("Exception Thrown ..............................." + e.getMessage());
        }
    }

    @Test
    void getSchedulesForLocation() {
        try {
            logger.info("Testing getSchedulesForLocation()..............................");
            logger.info("Sending request to URL..... " +baseUrl.concat("/v1/schedules/location/{locationId}"));
            ResponseEntity<String> citizens = restTemplate.getForEntity(baseUrl
                    .concat("/v1/schedules/location/{locationId}"), String.class, 1);

            MatcherAssert.assertThat(citizens.getStatusCode(), is(equalTo(HttpStatus.OK)));

            ObjectMapper mapper = new ObjectMapper();
            List<VaccineSupplySchedule> schedules = mapper.readValue(citizens.getBody(), new TypeReference<>() {});
            assertFalse(schedules.isEmpty());
            assertTrue(schedules.size() == 3);
            logger.info("Completed all Assertions for getSchedulesForLocation()..............................");
        }catch (Exception e ){
            logger.info("Exception Thrown ..............................." + e.getMessage());
        }
    }
}