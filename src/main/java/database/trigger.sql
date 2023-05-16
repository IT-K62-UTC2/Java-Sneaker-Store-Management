SET
SQL_SAFE_UPDATES = 0;
-- Trigger for import goods detail
DROP TRIGGER IF EXISTS after_create_import_goods_detail;
DELIMITER
$$
CREATE TRIGGER after_create_import_goods_detail
    AFTER INSERT
    ON `import_goods_detail`
    FOR EACH ROW
BEGIN
    UPDATE product
    SET quantity   = quantity + NEW.quantity,
        updated_at = CURRENT_TIMESTAMP()
    WHERE id = NEW.id_product;
    END$$
    DELIMITER ;


-- Trigger for import goods details
    DROP TRIGGER IF EXISTS before_delete_import_goods;
    DELIMITER $$
    CREATE TRIGGER before_delete_import_goods
        BEFORE DELETE
        ON `import_goods`
        FOR EACH ROW
    BEGIN
        DELETE FROM import_goods_detail WHERE id_import_goods = OLD.id;
        END$$
        DELIMITER ;

-- Trigger for  invoice detail
        DROP TRIGGER IF EXISTS before_delete_invoice;
        DELIMITER $$
        CREATE TRIGGER before_delete_invoice
            BEFORE DELETE
            ON `invoice`
            FOR EACH ROW
        BEGIN
            DELETE FROM invoice_detail WHERE id_invoice = OLD.id;
            END$$ DELIMITER ;