CREATE TABLE IF NOT EXISTS instance (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    instance_id VARCHAR(255),
    name VARCHAR(255),
    created Date ,
    payment_status VARCHAR(255),
    connection_status BOOL,
    auto_read_message BOOL,
    call_reject_auto BOOL,
    received_callback_url VARCHAR(255)
);