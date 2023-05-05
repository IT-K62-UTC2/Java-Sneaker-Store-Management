
-- Staff
DROP EVENT IF EXISTS delete_staff_affter_30day_status_0;
CREATE EVENT delete_staff_affter_30day_status_0
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
DELETE FROM staff WHERE status = 0 AND updated_at <= (NOW() - INTERVAL 30 DAY);

-- Customer
DROP EVENT IF EXISTS delete_customer_affter_30day_status_0;
CREATE EVENT delete_customer_affter_30day_status_0
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
DELETE FROM customer WHERE status = 0 AND updated_at <= (NOW() - INTERVAL 30 DAY);

-- Product
DROP EVENT IF EXISTS delete_product_affter_30day_status_0;
CREATE EVENT delete_product_affter_30day_status_0
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
DELETE FROM product WHERE status = 0 AND updated_at <= (NOW() - INTERVAL 30 DAY);

-- Supplier
DROP EVENT IF EXISTS delete_supplier_affter_30day_status_0;
CREATE EVENT delete_supplier_affter_30day_status_0
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
DELETE FROM supplier WHERE status = 0 AND updated_at <= (NOW() - INTERVAL 30 DAY);

-- Category
DROP EVENT IF EXISTS delete_category_affter_30day_status_0;
CREATE EVENT delete_category_affter_30day_status_0
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
DELETE FROM category WHERE status = 0 AND updated_at <= (NOW() - INTERVAL 30 DAY);

-- Category
DROP EVENT IF EXISTS delete_invoice_affter_30day;
CREATE EVENT delete_invoice_affter_30day
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
DELETE FROM category WHERE updated_at <= (NOW() - INTERVAL 30 DAY);

-- Import goods
DROP EVENT IF EXISTS delete_import_goods_affter_30day;
CREATE EVENT delete_import_goods_affter_30day
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
DELETE FROM category WHERE updated_at <= (NOW() - INTERVAL 30 DAY);






