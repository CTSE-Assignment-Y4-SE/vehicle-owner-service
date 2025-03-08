-- File: ddl-script-v1.sql

DROP TABLE IF EXISTS vehicle_owners;

CREATE TABLE vehicle_owners (
    vehicle_owner_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone_number VARCHAR(15) UNIQUE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);
