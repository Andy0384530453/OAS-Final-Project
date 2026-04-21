-- Active: 1775829249217@@127.0.0.1@5432@federation_collectivities_agricultural
CREATE DATABASE federation_collectivities_agricultural;
\c  federation_collectivities_agricultural

CREATE TABLE collectivities (
    id VARCHAR(255) PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    federation_approval boolean
);

CREATE TABLE members (
    id VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    gender VARCHAR(10),
    address TEXT,
    profession VARCHAR(255),
    phone_number BIGINT,
    email VARCHAR(255) UNIQUE,
    occupation VARCHAR(50),
    registration_fee_paid boolean ,
    membership_dues_paid boolean,
    collectivity_id VARCHAR(255),
    CONSTRAINT fk_collectivity  FOREIGN KEY (collectivity_id) REFERENCES collectivities(id)
);

CREATE TABLE member_referees (
    member_id VARCHAR(255) NOT NULL,
    referee_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (member_id, referee_id),
    FOREIGN KEY (member_id) REFERENCES members(id) ,
    FOREIGN KEY (referee_id) REFERENCES members(id)
);

CREATE TABLE collectivity_structures (
    collectivity_id VARCHAR(255) PRIMARY KEY,
    president_id VARCHAR(255),
    vice_president_id VARCHAR(255),
    treasurer_id VARCHAR(255),
    secretary_id VARCHAR(255),
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id) ,
    FOREIGN KEY (president_id) REFERENCES members(id),
    FOREIGN KEY (vice_president_id) REFERENCES members(id),
    FOREIGN KEY (treasurer_id) REFERENCES members(id),
    FOREIGN KEY (secretary_id) REFERENCES members(id)
);