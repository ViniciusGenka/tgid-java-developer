CREATE TABLE transactions (
    id BINARY(16) PRIMARY KEY,
    customer_id BINARY(16),
    company_id BINARY(16),
    type VARCHAR(255) NOT NULL,
    value DECIMAL(19, 2) NOT NULL,
    FOREIGN KEY (customer_id, company_id) REFERENCES wallets(customer_id, company_id)
);
