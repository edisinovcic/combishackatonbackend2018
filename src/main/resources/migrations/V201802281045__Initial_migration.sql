CREATE SCHEMA IF NOT EXISTS codeapp;

DROP TABLE IF EXISTS exception_logs;
DROP TABLE IF EXISTS social_connection;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
  id SERIAL,
  username VARCHAR(500),
  email VARCHAR(100),
  phone VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE exception_logs(
  id SERIAL,
  exception_name VARCHAR(100),
  timestamp TIMESTAMP,
  stack_trace VARCHAR(65536),
  user_id VARCHAR(100) REFERENCES users (id),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
)

CREATE TABLE social_connection(
  social_id SERIAL,
  social_type VARCHAR(100),
  user_id VARCHAR(100) REFERENCES users (id)
  PRIMARY KEY (social_id)
)
