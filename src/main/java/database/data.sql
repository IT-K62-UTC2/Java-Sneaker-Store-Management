-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: sneaker_management
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth`
--

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `id_position` int(11) NOT NULL,
                        `id_menu` int(11) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
INSERT INTO `auth` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,2,1),(11,2,7),(12,2,9),(13,2,3),(14,3,1),(15,3,9),(16,3,3),(17,3,4),(18,3,5),(19,3,6),(20,3,7),(21,3,8),(22,1,10),(23,2,10),(24,3,10);
/*!40000 ALTER TABLE `auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(50) NOT NULL,
                            `status` int(11) NOT NULL DEFAULT '1',
                            `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Other',1,'2023-05-11 04:23:45','2023-05-11 04:23:45');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `fullname` varchar(50) NOT NULL,
                            `address` varchar(100) NOT NULL,
                            `email` varchar(50) NOT NULL,
                            `phone_number` varchar(25) NOT NULL,
                            `gender` varchar(25) NOT NULL,
                            `cccd` varchar(25) NOT NULL,
                            `status` int(11) NOT NULL DEFAULT '1',
                            `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `email` (`email`),
                            UNIQUE KEY `phone_number` (`phone_number`),
                            UNIQUE KEY `cccd` (`cccd`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Anonymus User','Anonymus User','Anonymus User','Anonymus User','Anonymus User','Anonymus User',1,'2023-05-11 04:24:35','2023-05-11 04:24:35');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(50) NOT NULL,
                            `pecent` double NOT NULL DEFAULT '0',
                            `remaining` int(11) NOT NULL DEFAULT '0',
                            `time_expiry` date DEFAULT NULL,
                            `status` int(11) NOT NULL DEFAULT '1',
                            `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_goods`
--

DROP TABLE IF EXISTS `import_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `import_goods` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `id_staff` int(11) NOT NULL,
                                `quantity` int(11) NOT NULL DEFAULT '1',
                                `money_total` double NOT NULL DEFAULT '0',
                                `status` int(11) NOT NULL DEFAULT '1',
                                `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_goods`
--

LOCK TABLES `import_goods` WRITE;
/*!40000 ALTER TABLE `import_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `import_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_goods_detail`
--

DROP TABLE IF EXISTS `import_goods_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `import_goods_detail` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `id_import_goods` int(11) NOT NULL,
                                       `id_product` int(11) NOT NULL,
                                       `quantity` int(11) NOT NULL DEFAULT '1',
                                       `money_total` double NOT NULL DEFAULT '0',
                                       `status` int(11) NOT NULL DEFAULT '1',
                                       `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                       `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_goods_detail`
--

LOCK TABLES `import_goods_detail` WRITE;
/*!40000 ALTER TABLE `import_goods_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `import_goods_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `id_staff` int(11) NOT NULL,
                           `id_customer` int(11) NOT NULL DEFAULT '0',
                           `total_quantity` int(11) NOT NULL DEFAULT '0',
                           `money_total` double NOT NULL DEFAULT '0',
                           `delivery_address` varchar(100) DEFAULT NULL,
                           `delivery_phone_number` varchar(25) DEFAULT NULL,
                           `status` int(11) NOT NULL DEFAULT '1',
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_detail`
--

DROP TABLE IF EXISTS `invoice_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_detail` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `id_invoice` int(11) NOT NULL,
                                  `id_product` int(11) NOT NULL,
                                  `product_quantity` int(11) NOT NULL DEFAULT '1',
                                  `money_total` double NOT NULL,
                                  `status` int(11) NOT NULL DEFAULT '1',
                                  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_detail`
--

LOCK TABLES `invoice_detail` WRITE;
/*!40000 ALTER TABLE `invoice_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `path` varchar(255) NOT NULL,
                        `name` varchar(100) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'views/dashboard.fxml','Home'),(2,'views/staff.fxml','Staff'),(3,'views/customer.fxml','Customer'),(4,'views/category.fxml','Category'),(5,'views/product.fxml','Product'),(6,'views/import-goods.fxml','Import Goods'),(7,'views/history-invoice.fxml','History'),(8,'views/supplier.fxml','Supplier'),(9,'views/sell.fxml','Sell'),(10,'views/change-password.fxml','Change Password');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(50) NOT NULL,
                            `status` int(11) NOT NULL DEFAULT '1',
                            `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Admin',1,'2023-04-09 03:09:47','2023-04-09 03:09:47'),(2,'Employee',1,'2023-04-09 03:09:47','2023-04-09 03:09:47'),(3,'Manager',1,'2023-05-07 06:04:55','2023-05-07 06:04:55');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `id_supplier` int(11) NOT NULL,
                           `id_category` int(11) NOT NULL,
                           `name` varchar(50) NOT NULL,
                           `quantity` int(11) NOT NULL DEFAULT '0',
                           `desc` varchar(100) NOT NULL DEFAULT '',
                           `price` double NOT NULL DEFAULT '0',
                           `import_price` double DEFAULT NULL,
                           `avatar` varchar(100) NOT NULL,
                           `status` int(11) NOT NULL DEFAULT '1',
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `product_supplier_fk` (`id_supplier`),
                           KEY `product_category_fk` (`id_category`),
                           CONSTRAINT `product_category_fk` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
                           CONSTRAINT `product_supplier_fk` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `id_position` int(11) NOT NULL,
                         `avatar` varchar(255) DEFAULT NULL,
                         `username` varchar(50) NOT NULL,
                         `password` varchar(100) NOT NULL,
                         `fullname` varchar(50) NOT NULL,
                         `address` varchar(100) NOT NULL,
                         `email` varchar(50) NOT NULL,
                         `phone_number` varchar(25) NOT NULL,
                         `cccd` varchar(25) NOT NULL,
                         `gender` varchar(25) NOT NULL,
                         `status` int(11) NOT NULL DEFAULT '1',
                         `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `username` (`username`),
                         UNIQUE KEY `email` (`email`),
                         UNIQUE KEY `phone_number` (`phone_number`),
                         UNIQUE KEY `cccd` (`cccd`),
                         KEY `staff_posision_fk` (`id_position`),
                         CONSTRAINT `staff_posision_fk` FOREIGN KEY (`id_position`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,1,'Admin','Admin','C1ft1LEx2Q7upS9B/u2bJkjVBkj9viQoLzSbGoae/78u3qgKp41oZmozuZuQ8pAm','Admin','Admin','Admin','Admin','Admin','Admin',1,'2023-05-11 04:26:15','2023-05-11 04:26:15');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `address` varchar(100) NOT NULL,
                            `phone_number` varchar(45) DEFAULT NULL,
                            `name` varchar(45) DEFAULT NULL,
                            `email` varchar(25) NOT NULL,
                            `status` int(11) NOT NULL DEFAULT '1',
                            `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `phone_number` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Anonymus Supplier','Anonymus Supplier','Anonymus Supplier','Anonymus Supplier',1,'2023-05-11 04:25:32','2023-05-11 04:25:32');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sneaker_management'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-11 11:27:46
