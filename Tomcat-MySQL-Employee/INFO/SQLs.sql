-----Database:

CREATE DATABASE demo_db;

-----Tables:

CREATE TABLE IF NOT EXISTS books
( id INTEGER NOT NULL AUTO_INCREMENT,
  title VARCHAR(128) NOT NULL,
  author VARCHAR(128) NOT NULL,
  price DOUBLE NOT NULL,
  PRIMARY KEY (id)
);