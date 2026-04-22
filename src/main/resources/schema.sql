CREATE TABLE IF NOT EXISTS instance (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    instance_id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    payment_status VARCHAR(255) NOT NULL,
    connection_status VARCHAR(255) NOT NULL
);