
--Citizen table data
INSERT INTO citizens(first_name, last_name, middle_init, address_1, city, state, zip_code, ssn) VALUES
('Mary', 'Jones', 'C', '1440 Leonardtown Rd', 'Hollywood', 'MD', '22636','222114576');

--Vaccine Type lookup table data
--Data Retrieved From https://www.cdc.gov/coronavirus/2019-ncov/vaccines/different-vaccines/janssen.html
INSERT INTO lookup_vaccine_types(cn, dn, mfg, vac_type, vac_target) VALUES
(
 'Pfizer-BioNTech', 'BNT162b2',
 'Pfizer Inc., BioNTech',
 'mRNA',
 'COVID-19'
),
(
 'Moderna',
 'mRNA-1273',
 'ModernaTX Inc.',
 'mRNA',
 'COVID-19'
),
(
 'Johnson & Johnson’s Janssen',
 'JNJ-78436735',
 'Janssen Pharmaceuticals Companies of Johnson & Johnson',
 'Viral Vector',
 'COVID-19'
);

--immunization lookup table data
INSERT INTO lookup_immunization_locations(loc_name, loc_addr, loc_lng, loc_lat) VALUES
(
 'Hollywood Fire/Rescue Base',
 '24801 MD-235, Hollywood, MD 20636',
 '-76.57640615931808',
 '38.34892047654282'
 ),
(
 'St Marys MedStar Hospital',
 '25500 Point Lookout Rd, Leonardtown, MD 20650',
 '-76.63821440164648',
 '38.302297347233754'
 ),
(
 'Second District Volunteer Fire Department and Rescue Squad',
 '45245 Drayden Rd, Valley Lee, MD 20692',
 '-76.52363776367109',
 '38.21437864074401'
 ),
(
 'Huntingtown Volunteer Fire',
 '4030 Old Town Rd, Huntingtown, MD 20639',
 '-76.61702155550078',
 '38.6446960637469'
 ),
(
 'CalvertHealth Medical Center',
 '100 Hospital Rd, Prince Frederick, MD 20678',
 '-76.60328984309601',
 '38.58179687130898'
 ),
(
 'UM Charles Regional Medical Center',
 '5 Garrett Ave, La Plata, MD 20646',
 '-76.9617188087953',
 '38.553879741634304'
 );

--current vaccine supply chain data --Simulated External Data
INSERT INTO vaccine_supply_chain(vaccine,ship_date,location,avail_date,num_units,num_available,lot_num,des_dose) VALUES
(1,PARSEDATETIME('01-15-2021', 'MM-dd-yyyy'),2,PARSEDATETIME('01-18-2021', 'MM-dd-yyyy'),800,0,'AW00340',1),
(1,PARSEDATETIME('02-07-2021', 'MM-dd-yyyy'),2,PARSEDATETIME('02-10-2021', 'MM-dd-yyyy'),800,0,'AW01030', 2),
(1,PARSEDATETIME('03-15-2021', 'MM-dd-yyyy'),2,PARSEDATETIME('03-18-2021', 'MM-dd-yyyy'),2000,100,'AW05900',1),
(3,PARSEDATETIME('02-02-2021', 'MM-dd-yyyy'),2,PARSEDATETIME('02-03-2021', 'MM-dd-yyyy'),400,10,'JJ00030',1),
(1,PARSEDATETIME('04-10-2021', 'MM-dd-yyyy'),2,PARSEDATETIME('04-13-2021', 'MM-dd-yyyy'),2000,100,'AW00921',2),
(2,PARSEDATETIME('02-25-2021', 'MM-dd-yyyy'),2,PARSEDATETIME('03-01-2021', 'MM-dd-yyyy'),2000,900,'MD00031',1),
(2,PARSEDATETIME('03-15-2021', 'MM-dd-yyyy'),2,PARSEDATETIME('03-21-2021', 'MM-dd-yyyy'),2000,900,'MD00159',2),
(1,PARSEDATETIME('03-15-2021', 'MM-dd-yyyy'),1,PARSEDATETIME('03-18-2021', 'MM-dd-yyyy'),2000,200,'AW05900',1),
(1,PARSEDATETIME('04-10-2021', 'MM-dd-yyyy'),1,PARSEDATETIME('04-13-2021', 'MM-dd-yyyy'),2000,200,'AW05900',2),
(1,PARSEDATETIME('04-10-2021', 'MM-dd-yyyy'),1,PARSEDATETIME('04-13-2021', 'MM-dd-yyyy'),2000,200,'AW05900',2),
(3,PARSEDATETIME('04-29-2021', 'MM-dd-yyyy'),3,PARSEDATETIME('05-01-2021', 'MM-dd-yyyy'),1000,900,'JJ00430',1),
(2,PARSEDATETIME('05-15-2021', 'MM-dd-yyyy'),4,PARSEDATETIME('05-18-2021', 'MM-dd-yyyy'),1000,900,'JJ00930',1),
(1,PARSEDATETIME('05-20-2021', 'MM-dd-yyyy'),5,PARSEDATETIME('05-21-2021', 'MM-dd-yyyy'),2000,2000,'AW01030',1),
(1,PARSEDATETIME('06-09-2021', 'MM-dd-yyyy'),5,PARSEDATETIME('06-15-2021', 'MM-dd-yyyy'),2000,2000,'AW02030',2),
(1,PARSEDATETIME('01-15-2021', 'MM-dd-yyyy'),6,PARSEDATETIME('01-18-2021', 'MM-dd-yyyy'),800,10,'AW00340',1),
(1,PARSEDATETIME('02-07-2021', 'MM-dd-yyyy'),6,PARSEDATETIME('02-10-2021', 'MM-dd-yyyy'),800,10,'AW01030', 2),
(1,PARSEDATETIME('03-15-2021', 'MM-dd-yyyy'),6,PARSEDATETIME('03-18-2021', 'MM-dd-yyyy'),3000,50,'AW05900',1),
(3,PARSEDATETIME('07-02-2021', 'MM-dd-yyyy'),6,PARSEDATETIME('07-07-2021', 'MM-dd-yyyy'),2000,2000,'JJ00030',1),
(1,PARSEDATETIME('04-10-2021', 'MM-dd-yyyy'),6,PARSEDATETIME('04-13-2021', 'MM-dd-yyyy'),3000,50,'AW00921',2),
(2,PARSEDATETIME('08-25-2021', 'MM-dd-yyyy'),6,PARSEDATETIME('09-01-2021', 'MM-dd-yyyy'),4000,4000,'MD00031',1),
(2,PARSEDATETIME('09-15-2021', 'MM-dd-yyyy'),6,PARSEDATETIME('09-21-2021', 'MM-dd-yyyy'),4000,4000,'MD00159',2);

--Appointment Test Data
INSERT INTO vaccination_apt_sched(citizen, vac_data, sched_dose_num, appointment_date) VALUES
( 1, 1, 1, PARSEDATETIME('01-25-2021', 'MM-dd-yyyy') ),
( 1, 2, 2, PARSEDATETIME('02-18-2021', 'MM-dd-yyyy'));
