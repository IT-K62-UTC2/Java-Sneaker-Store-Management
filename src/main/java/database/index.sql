ALTER TABLE staff ADD INDEX idx_username (username);
ALTER TABLE product ADD INDEX idx_name (`name`);
ALTER TABLE invoice ADD INDEX idx_invoice (created_at);
