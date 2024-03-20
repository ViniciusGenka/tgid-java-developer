CREATE TABLE wallets (
    customer_id BINARY(16),
    company_id BINARY(16),
    balance DECIMAL(19, 2) NOT NULL,
    PRIMARY KEY (customer_id, company_id),
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE
);