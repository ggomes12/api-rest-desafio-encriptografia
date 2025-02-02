CREATE TABLE tbl_sensitive_data (
    id SERIAL PRIMARY KEY,
    user_document VARCHAR(255) NOT NULL,
    credit_card_token VARCHAR(255) NOT NULL,
    value_credit NUMERIC(19, 2) NOT NULL
);