DROP DATABASE IF EXISTS sneaker_management;

CREATE DATABASE `sneaker_management`
CHARACTER SET utf8
COLLATE utf8_general_ci;

USE `sneaker_management`;

CREATE TABLE `position`(
                           `id` INT PRIMARY KEY AUTO_INCREMENT,
                           `name` VARCHAR(50) NOT NULL,
                           `status` INT NOT NULL DEFAULT 1,
                           `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `staff` (
                         `id` INT PRIMARY KEY AUTO_INCREMENT,
                         `id_position` INT NOT NULL,
                         `username` VARCHAR(50) NOT NULL UNIQUE,
                         `password` VARCHAR(100) NOT NULL,
                         `fullname` VARCHAR(50) NOT NULL,
                         `address` VARCHAR(100) NOT NULL,
                         `email` VARCHAR(50) NOT NULL UNIQUE,
                         `phone_number` VARCHAR(25) NOT NULL UNIQUE,
                         `cccd` VARCHAR(25) NOT NULL UNIQUE,
                         `gender` VARCHAR(25) NOT NULL,
                         `status` INT NOT NULL DEFAULT 1,
                         `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `customer` (
                            `id` INT PRIMARY KEY AUTO_INCREMENT,
                            `fullname` VARCHAR(50) NOT NULL,
                            `address` VARCHAR(100) NOT NULL,
                            `email` VARCHAR(50) NOT NULL UNIQUE,
                            `phone_number` VARCHAR(25) NOT NULL UNIQUE,
                            `gender` VARCHAR(25) NOT NULL,
                            `cccd` VARCHAR(25) NOT NULL UNIQUE,
                            `status` INT NOT NULL DEFAULT 1,
                            `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `invoice`(
                          `id` INT PRIMARY KEY AUTO_INCREMENT,
                          `id_staff` INT NOT NULL,
                          `id_customer` INT NOT NULL,
                          `money_total` DOUBLE NOT NULL DEFAULT 0,
                          `delivery_address` VARCHAR(100) NOT NULL,
                          `delivery_phone_number` VARCHAR(25) NOT NULL,
                          `status` INT NOT NULL DEFAULT 1,
                          `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `invoice_detail` (
                                  `id` INT PRIMARY KEY AUTO_INCREMENT,
                                  `id_invoice` INT NOT NULL,
                                  `id_product` INT NOT NULL,
                                  `product_quantity` INT NOT NULL DEFAULT 1,
                                  `money_total` DOUBLE NOT NULL,
                                  `status` INT NOT NULL DEFAULT 1,
                                  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `supplier` (
                            `id` INT PRIMARY KEY AUTO_INCREMENT,
                            `address` VARCHAR(100) NOT NULL,
                            `phone_number` VARCHAR(25) NOT NULL UNIQUE,
                            `product_quantity` INT NOT NULL DEFAULT 1,
                            `status` INT NOT NULL DEFAULT 1,
                            `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `category`(
                           `id` INT PRIMARY KEY AUTO_INCREMENT,
                           `name` VARCHAR(50) NOT NULL,
                           `status` INT NOT NULL DEFAULT 1,
                           `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `product` (
                           `id` INT PRIMARY KEY AUTO_INCREMENT,
                           `id_supplier` INT NOT NULL,
                           `id_category` INT NOT NULL,
                           `name` VARCHAR(50) NOT NULL,
                           `size` DOUBLE NOT NULL,
                           `desc` VARCHAR(100) NOT NULL DEFAULT "",
                           `price` DOUBLE NOT NULL DEFAULT 0,
                           `avatar` VARCHAR(100) NOT NULL,
                           `status` INT NOT NULL DEFAULT 1,
                           `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `import_goods` (
                                `id` INT PRIMARY KEY AUTO_INCREMENT,
                                `id_staff` INT NOT NULL,
                                `quantity` INT NOT NULL DEFAULT 1,
                                `money_total` DOUBLE NOT NULL DEFAULT 0,
                                `status` INT NOT NULL DEFAULT 1,
                                `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `import_goods_detail` (
                                       `id` INT PRIMARY KEY AUTO_INCREMENT,
                                       `id_import_goods` INT NOT NULL,
                                       `id_product` INT NOT NULL,
                                       `quantity` INT NOT NULL DEFAULT 1,
                                       `unit_price` DOUBLE NOT NULL DEFAULT 0,
                                       `money_total` DOUBLE NOT NULL DEFAULT 0,
                                       `status` INT NOT NULL DEFAULT 1,
                                       `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `discount`(
                           `id` INT PRIMARY KEY AUTO_INCREMENT,
                           `name` VARCHAR(50) NOT NULL,
                           `pecent` DOUBLE NOT NULL DEFAULT 0,
                           `remaining` INT NOT NULL DEFAULT 0,
                           `time_expiry` DATE,
                           `status` INT NOT NULL DEFAULT 1,
                           `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);





