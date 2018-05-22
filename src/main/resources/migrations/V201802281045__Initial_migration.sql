CREATE SCHEMA IF NOT EXISTS codeapp;

DROP TABLE IF EXISTS configuration;
DROP TABLE IF EXISTS log;
DROP TABLE IF EXISTS social_connection;
DROP TABLE IF EXISTS users;



CREATE TABLE users (
  id VARCHAR(255) DEFAULT gen_random_uuid(),
  first_name VARCHAR(500),
  last_name VARCHAR(500),
  email VARCHAR(100),
  password VARCHAR(200),
  gender VARCHAR(10),
  address VARCHAR(200),
  authority_group VARCHAR(200),
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
