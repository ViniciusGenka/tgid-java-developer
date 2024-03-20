CREATE TABLE fees (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value DECIMAL(19, 2) NOT NULL,
    company_id BINARY(16),
    transaction_type VARCHAR(255),
    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE
);