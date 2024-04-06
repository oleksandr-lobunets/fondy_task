-- CREATE SCHEMA IF NOT EXISTS change;

CREATE TABLE change_result
(
    external_id     uuid PRIMARY KEY,
    pence_submitted INT NOT NULL,
    pounds          VARCHAR(300),
    pence           VARCHAR(300),
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP
);

