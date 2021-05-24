DROP TABLE IF EXISTS citizens;
CREATE TABLE citizens
--Registered Citizens, --Simulated External Data Source
(
  id BIGINT auto_increment,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  middle_init VARCHAR(1),
  address_1 VARCHAR(100) NOT NULL,
  address_2 VARCHAR(100),
  city VARCHAR(50) NOT NULL,
  state VARCHAR(2) NOT NULL,
  zip_code VARCHAR(5) NOT NULL,
  ssn VARCHAR(9) NOT NULL,
  PRIMARY KEY(id)
);

DROP TABLE IF EXISTS lookup_vaccine_types;
CREATE TABLE lookup_vaccine_types
--Vaccine Metadata
(
    id BIGINT auto_increment,
    cn VARCHAR(50) NOT NULL,
    dn VARCHAR(50) NOT NULL,
    mfg VARCHAR(100) NOT NULL,
    vac_type VARCHAR(25) NOT NULL,
    vac_target VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS lookup_immunization_locations;
CREATE TABLE lookup_immunization_locations
--Vaccination location metadata
(
    id BIGINT auto_increment,
    loc_name VARCHAR(100) NOT NULL,
    loc_addr VARCHAR(100) NOT NULL,
    loc_lng DECIMAL(11,8) NOT NULL,
    loc_lat DECIMAL(10,8) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS vaccine_supply_chain;
CREATE TABLE vaccine_supply_chain
--Vaccine Delivery, Schedule External Data From Source
(
    id BIGINT auto_increment,
    vaccine BIGINT NOT NULL, --id from lookup_vaccine_types
    foreign key (vaccine) references lookup_vaccine_types(id),
    ship_date TIMESTAMP NOT NULL, --The date this delivery was/will be shipped
    location BIGINT NOT NULL, --id from lookup_vaccine_locations
    foreign key (location) references lookup_immunization_locations(id),
    avail_date TIMESTAMP NOT NULL, --The date this delivery will be available for scheduling
    num_units INT NOT NULL, --Number of units in a given delivery
    num_available INT NOT NULL, --Number of units remaining in this delivery
    lot_num VARCHAR(50), -- The lot number for this vaccine, all vaccines are tracked by lot
    des_dose INT DEFAULT 1, --the designation used for shot series e.g. 1st dose, 2nd dose
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS vaccination_apt_sched;
CREATE TABLE vaccination_apt_sched
--Vaccination Schedule
(
    id BIGINT auto_increment,
    citizen BIGINT NOT NULL,
    foreign key (citizen) references citizens(id),
    vac_data BIGINT NOT NULL,
    foreign key (vac_data) references vaccine_supply_chain(id),
    sched_dose_num INT DEFAULT 1,
    appointment_date TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);