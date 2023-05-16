DELIMITER $$
CREATE PROCEDURE insert_invoice_detail(IN id_invoice INT, IN id_product INT, IN product_quantity INT, IN money_total DOUBLE)
BEGIN

UPDATE product
SET quantity = quantity - product_quantity
WHERE id = id_product;

INSERT INTO invoice_detail (id_invoice, id_product, product_quantity, money_total)
VALUES (id_invoice, id_product, product_quantity, money_total);
END $$

DELIMITER ;