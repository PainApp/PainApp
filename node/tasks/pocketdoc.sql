DROP DATABASE pocketdoc;
CREATE DATABASE pocketdoc;
USE pocketdoc;
CREATE TABLE body_regions (id INT, name VARCHAR(255), PRIMARY KEY (id));
CREATE TABLE specific_body_regions (id INT, body_region_id INT, name VARCHAR(255), PRIMARY KEY (id), FOREIGN KEY (body_region_id) REFERENCES body_regions(id));
CREATE TABLE causes (id INT, specific_body_region_id INT, name VARCHAR(255), classification varchar(250), PRIMARY KEY (id), FOREIGN KEY (specific_body_region_id) REFERENCES specific_body_regions(id));