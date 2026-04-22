
CREATE DATABASE federation_collectivities_agricultural;
\c federation_collectivities_agricultural;


CREATE TABLE collectivities (
    id VARCHAR(255) PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    federation_approval BOOLEAN,
    number VARCHAR(255) UNIQUE,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE members (
    id VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    gender VARCHAR(10),
    address TEXT,
    profession VARCHAR(255),
    phone_number VARCHAR(20),
    email VARCHAR(255) UNIQUE,
    occupation VARCHAR(50),
    registration_fee_paid BOOLEAN,
    membership_dues_paid BOOLEAN,
    collectivity_id VARCHAR(255),
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id)
);


CREATE TABLE member_referees (
    member_id VARCHAR(255),
    referee_id VARCHAR(255),
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
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id),
    FOREIGN KEY (president_id) REFERENCES members(id),
    FOREIGN KEY (vice_president_id) REFERENCES members(id),
    FOREIGN KEY (treasurer_id) REFERENCES members(id),
    FOREIGN KEY (secretary_id) REFERENCES members(id)
);

CREATE TABLE membership_fees (
    id VARCHAR(255) PRIMARY KEY,
    collectivity_id VARCHAR(255) NOT NULL,
    eligible_from DATE NOT NULL,
    frequency VARCHAR(20) CHECK (frequency IN ('WEEKLY', 'MONTHLY', 'ANNUALLY', 'PUNCTUALLY')),
    amount DECIMAL(15,2) CHECK (amount >= 0),
    label VARCHAR(255),
    status VARCHAR(20) DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id)
);


CREATE TABLE financial_accounts (
    id VARCHAR(255) PRIMARY KEY,
    type VARCHAR(30) NOT NULL CHECK (type IN ('CASH', 'MOBILE_BANKING', 'BANK')),
    amount DECIMAL(15,2) DEFAULT 0 CHECK (amount >= 0)
);

CREATE TABLE cash_accounts (
    id VARCHAR(255) PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES financial_accounts(id)
);

CREATE TABLE mobile_banking_accounts (
    id VARCHAR(255) PRIMARY KEY,
    holder_name VARCHAR(255) NOT NULL,
    mobile_banking_service VARCHAR(20) CHECK (mobile_banking_service IN ('AIRTEL_MONEY', 'MVOLA', 'ORANGE_MONEY')),
    mobile_number VARCHAR(20),
    FOREIGN KEY (id) REFERENCES financial_accounts(id)
);

CREATE TABLE bank_accounts (
    id VARCHAR(255) PRIMARY KEY,
    holder_name VARCHAR(255),
    bank_name VARCHAR(50) CHECK (bank_name IN ('BRED', 'MCB', 'BMOI', 'BOA', 'BGFI', 'AFG', 'ACCES_BAQUE', 'BAOBAB', 'SIPEM')),
    bank_code VARCHAR(20),
    bank_branch_code VARCHAR(20),
    bank_account_number VARCHAR(50),
    bank_account_key VARCHAR(20),
    FOREIGN KEY (id) REFERENCES financial_accounts(id)
);


CREATE TABLE member_payments (
    id VARCHAR(255) PRIMARY KEY,
    member_id VARCHAR(255) NOT NULL,
    amount DECIMAL(15,2) CHECK (amount > 0),
    payment_mode VARCHAR(20) CHECK (payment_mode IN ('CASH', 'MOBILE_BANKING', 'BANK_TRANSFER')),
    account_credited_id VARCHAR(255) NOT NULL,
    creation_date DATE NOT NULL,
    membership_fee_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members(id),
    FOREIGN KEY (account_credited_id) REFERENCES financial_accounts(id),
    FOREIGN KEY (membership_fee_id) REFERENCES membership_fees(id)
);


CREATE TABLE collectivity_transactions (
    id VARCHAR(255) PRIMARY KEY,
    collectivity_id VARCHAR(255) NOT NULL,
    creation_date DATE NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    payment_mode VARCHAR(20) CHECK (payment_mode IN ('CASH', 'MOBILE_BANKING', 'BANK_TRANSFER')),
    account_credited_id VARCHAR(255) NOT NULL,
    member_debited_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id),
    FOREIGN KEY (account_credited_id) REFERENCES financial_accounts(id),
    FOREIGN KEY (member_debited_id) REFERENCES members(id)
);


