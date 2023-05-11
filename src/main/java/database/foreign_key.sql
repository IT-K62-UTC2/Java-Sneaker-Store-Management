ALTER TABLE staff
    ADD CONSTRAINT staff_posision_fk
        FOREIGN KEY (id_position)
            REFERENCES position (id);

ALTER TABLE product
    ADD CONSTRAINT product_supplier_fk
        FOREIGN KEY (id_supplier)
            REFERENCES supplier(id);

ALTER TABLE product
    ADD CONSTRAINT product_category_fk
        FOREIGN KEY (id_category)
            REFERENCES category(id);

ALTER TABLE invoice_detail
    ADD CONSTRAINT invoice_detail_invoice_fk
        FOREIGN KEY (id_invoice)
            REFERENCES invoice(id);

ALTER TABLE invoice_detail
    ADD CONSTRAINT invoice_detail_pfoduct_fk
        FOREIGN KEY (id_product)
            REFERENCES product(id);

ALTER TABLE invoice
    ADD CONSTRAINT invoice_staff_fk
        FOREIGN KEY (id_staff)
            REFERENCES staff(id);

ALTER TABLE invoice
    ADD CONSTRAINT invoice_customer
        FOREIGN KEY (id_customer)
            REFERENCES customer(id);

ALTER TABLE import_goods_detail
    ADD CONSTRAINT import_goods_detail_import_goods_fk
        FOREIGN KEY (id_import_goods)
            REFERENCES import_goods(id);

ALTER TABLE import_goods_detail
    ADD CONSTRAINT import_goods_detail_product_fk
        FOREIGN KEY (id_product)
            REFERENCES product(id);

ALTER TABLE import_goods
    ADD CONSTRAINT import_goods_staff_fk
        FOREIGN KEY (id_staff)
            REFERENCES staff(id);

ALTER TABLE auth
    ADD CONSTRAINT auth_menu
        FOREIGN KEY (id_menu)
            REFERENCES menu(id);

ALTER TABLE auth
    ADD CONSTRAINT auth_position
        FOREIGN KEY (id_position)
            REFERENCES position (id);




