INSERT INTO users (first_name, last_name, phone_number, email, year_of_birth, gender, address, blood_type, authority_group, password) VALUES ('admin', 'adminko', '+385990000000','admin@eestec-zg.hr', '1950', 'M', 'Unska 3, Zagreb', '','ADMIN','$2a$04$3dj6LGwGdmLAu91GdoV/dezHtbphnlOkcgKHaftctzVGJzkNm4h4S');
INSERT INTO users (first_name, last_name, phone_number, email, year_of_birth, gender, address, blood_type, authority_group, password) VALUES ('edi', 'sinovcic', '+385990000000','edi.sinovcic@gmail.com', '1993', 'M', 'Odranska 3, Zagreb', 'A+','CUSTOMER','$2a$04$3dj6LGwGdmLAu91GdoV/dezHtbphnlOkcgKHaftctzVGJzkNm4h4S');
INSERT INTO users (first_name, last_name, phone_number, email, year_of_birth, gender, address, blood_type, authority_group, password) VALUES ('alen', 'hrga', '+385990000000','ahrga93@gmail.com', '1993', 'M', 'Odranska 3, Zagreb', '0+','CUSTOMER','$2a$04$3dj6LGwGdmLAu91GdoV/dezHtbphnlOkcgKHaftctzVGJzkNm4h4S');
INSERT INTO users (first_name, last_name, phone_number, email, year_of_birth, gender, address, blood_type, authority_group, password) VALUES ('eugen', 'veliki', '+385990000000','eugen.veliki@gmail.com', '1993', 'M', 'Odranska 3, Zagreb', 'AB-','CUSTOMER','$2a$04$3dj6LGwGdmLAu91GdoV/dezHtbphnlOkcgKHaftctzVGJzkNm4h4S');
INSERT INTO users (first_name, last_name, phone_number, email, year_of_birth, gender, address, blood_type, authority_group, password) VALUES ('anton', 'filipovic', '+385990000000','anton.filipovic777@gmail.com', '1995', 'M', 'Odranska 3, Zagreb', 'B-','CUSTOMER','$2a$04$3dj6LGwGdmLAu91GdoV/dezHtbphnlOkcgKHaftctzVGJzkNm4h4S');


INSERT INTO blood_groups (name, accepts, gives) VALUES ('0-', '0-','0-,A-,B-,AB-,0+,A+,B+,AB+');
INSERT INTO blood_groups (name, accepts, gives) VALUES ('A-', '0-,A-', 'A-,AB-,A+,AB+');
INSERT INTO blood_groups (name, accepts, gives) VALUES ('B-', '0-,B-', 'B-,AB-,B+,AB+');
INSERT INTO blood_groups (name, accepts, gives) VALUES ('AB-', '0-,A-,B-,AB-', 'AB-,AB+');
INSERT INTO blood_groups (name, accepts, gives) VALUES ('0+', '0-,0+', '0+,A+,B+,AB+');
INSERT INTO blood_groups (name, accepts, gives) VALUES ('A+', '0-,A-,0+,A+', 'A+,AB+');
INSERT INTO blood_groups (name, accepts, gives) VALUES ('B+', '0-,B-,0+,B+', 'B+,AB+');
INSERT INTO blood_groups (name, accepts, gives) VALUES ('AB+', '0-,A-,B-,AB-,0+,A+,B+,AB+', 'AB+');
