CREATE TABLE customers (
    id BINARY(16) PRIMARY KEY,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);