DROP TRIGGER IF EXISTS after_create_import_goods_detail;
DELIMITER $$
CREATE TRIGGER after_create_import_goods_detail
    AFTER INSERT ON `import_goods_detail`
    FOR EACH ROW
BEGIN
    UPDATE product
    SET quantity = quantity + NEW.quantity, updated_at = CURRENT_TIMESTAMP() WHERE id = NEW.id_product;
    END$$
    DELIMITER ;
