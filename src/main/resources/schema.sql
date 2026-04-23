CREATE TABLE IF NOT EXISTS instance (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    instance_id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    created DATE NOT NULL,
    payment_status VARCHAR(255) NOT NULL,
    connection_status VARCHAR(255) NOT NULL,
    auto_read_message BOOL NOT NULL,
    call_reject_auto BOOL NOT NULL,
    received_callback_url VARCHAR(255) NOT NULL
);