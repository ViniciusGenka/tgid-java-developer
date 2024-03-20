CREATE TABLE companies (
    id BINARY(16) PRIMARY KEY,
    cnpj VARCHAR(255) NOT NULL UNIQUE,
    balance DECIMAL(19, 2) NOT NULL,
    webhook_url VARCHAR(255) NOT NULL
);