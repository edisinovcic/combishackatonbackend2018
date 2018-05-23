CREATE SCHEMA IF NOT EXISTS codeapp;

DROP TABLE IF EXISTS configuration;
DROP TABLE IF EXISTS log;
DROP TABLE IF EXISTS social_connection;
DROP TABLE IF EXISTS blood_groups;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS user_donations;
DROP TABLE IF EXISTS donation_events;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id VARCHAR(255) DEFAULT gen_random_uuid(),
  first_name VARCHAR(500),
  last_name VARCHAR(500),
  phone_number VARCHAR(500),
  email VARCHAR(100),
  year_of_birth VARCHAR(10),
  gender VARCHAR(10),
  address VARCHAR(200),
  blood_type VARCHAR(50),
  authority_group VARCHAR(200),
  password VARCHAR(200),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(255),
  modified_by VARCHAR(255),
  CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE social_connection (
  social_id   SERIAL PRIMARY KEY,
  social_type VARCHAR(100),
  user_id VARCHAR(255) REFERENCES users (id)
);

CREATE TABLE configuration (
  id          SERIAL PRIMARY KEY ,
  conf_key    VARCHAR(100),
  conf_type   CHAR(10),
  conf_value  VARCHAR(255),
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by  VARCHAR(255),
  modified_by VARCHAR(255)
);

CREATE TABLE log (
  id         SERIAL PRIMARY KEY ,
  action     VARCHAR(50),
  time       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_id    VARCHAR (255),
  device     VARCHAR(512),
  is_success BOOLEAN,
  error      TEXT
);

CREATE TABLE user_donations (
  id VARCHAR(255) DEFAULT gen_random_uuid(),
  user_id   VARCHAR(255) REFERENCES users(id),
  description VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_user_donation PRIMARY KEY (id)
);

CREATE TABLE blood_groups (
  id VARCHAR(255) DEFAULT gen_random_uuid(),
  name      VARCHAR(500),
  accepts   VARCHAR(500),
  gives     VARCHAR(500),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_blood_group PRIMARY KEY (id)
);

CREATE TABLE questions (
  id VARCHAR(255) DEFAULT gen_random_uuid(),
  description VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_question PRIMARY KEY (id)
);

CREATE TABLE answers (
  id VARCHAR(255) DEFAULT gen_random_uuid(),
  user_id   VARCHAR(255) REFERENCES users(id),
  question_id VARCHAR(255) REFERENCES questions(id),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_answer PRIMARY KEY (id)
);


CREATE TABLE invites (
  id VARCHAR(255) DEFAULT gen_random_uuid(),
  user_id   VARCHAR(255) REFERENCES users(id),
  description TEXT,
  answer_type VARCHAR(100),
  CONSTRAINT pk_invite PRIMARY KEY (id)
);

CREATE TABLE donation_events (
  id VARCHAR(255) DEFAULT gen_random_uuid(),
  description VARCHAR(255),
  address     VARCHAR(255),
  latitude    VARCHAR(255),
  longitude   VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_donation_event PRIMARY KEY (id)
);

CREATE TABLE blood_stocks (
  id VARCHAR(255) DEFAULT gen_random_uuid(),
  blood_group VARCHAR(255) REFERENCES blood_groups(id),
  amount FLOAT DEFAULT 0.0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_blood_stock PRIMARY KEY (id)
);
