-- File: ddl-script-v2.sql

DROP TABLE IF EXISTS vehicles;

CREATE TABLE vehicles (
      vehicle_id BIGSERIAL PRIMARY KEY,
      brand VARCHAR(255),
      model VARCHAR(255),
      year BIGINT,
      license_plate VARCHAR(255),
      created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
      updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
      vehicle_owner_id BIGINT NOT NULL,
      CONSTRAINT fk_vehicle_owner
          FOREIGN KEY (vehicle_owner_id)
              REFERENCES vehicle_owners (vehicle_owner_id)
              ON DELETE CASCADE
);
