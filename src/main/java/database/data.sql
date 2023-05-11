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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'sneaker vip',0,'2023-04-13 07:44:09','2023-04-29 06:24:39'),(2,'sneaker vip 100',0,'2023-04-13 07:44:09','2023-05-05 17:25:24'),(3,'weqwe',0,'2023-04-13 07:44:09','2023-04-25 15:24:27'),(4,'d',0,'2023-04-14 07:25:47','2023-05-05 17:29:57'),(5,'dangquy100',1,'2023-04-14 07:26:33','2023-05-01 10:11:44'),(6,'asdsad',0,'2023-04-14 07:27:45','2023-04-29 06:24:42'),(7,'qweqwe',1,'2023-04-14 07:32:37','2023-04-14 07:32:37'),(8,'asdaw',0,'2023-04-14 07:34:53','2023-05-01 10:12:24'),(9,'phu huy',1,'2023-04-14 07:35:06','2023-04-14 07:35:06'),(10,'dangquy',1,'2023-04-14 07:35:28','2023-04-14 07:35:28'),(11,'popipqowiepoqw',1,'2023-04-14 07:39:42','2023-04-14 07:39:42'),(12,'test',1,'2023-04-14 07:40:06','2023-04-14 07:40:06'),(13,'12312',1,'2023-04-14 07:43:12','2023-04-14 07:43:12'),(14,'12312',1,'2023-04-14 07:43:35','2023-04-14 07:43:35'),(15,'12312',0,'2023-04-14 07:43:37','2023-05-01 10:12:17'),(16,'buonngu',1,'2023-04-14 07:46:13','2023-04-14 07:46:13'),(17,'tttttttt',1,'2023-04-14 07:46:51','2023-04-14 07:46:51'),(18,'kjong',1,'2023-04-14 07:52:11','2023-04-14 07:52:11'),(19,'iiiiiii',1,'2023-04-14 07:54:34','2023-04-14 07:54:34'),(20,'ads',1,'2023-04-14 07:56:47','2023-04-14 07:56:47'),(21,'asdad',1,'2023-04-14 07:57:43','2023-04-14 07:57:43'),(22,'qweqwe',1,'2023-04-14 08:02:49','2023-04-14 08:02:49'),(23,'eqwewqeqweqweqw',1,'2023-04-14 08:15:00','2023-04-14 08:15:00'),(24,'tess 1000',1,'2023-04-14 08:20:22','2023-04-14 08:20:22'),(25,'dasdaskdjkash',1,'2023-04-14 08:21:52','2023-04-14 08:21:52'),(26,'000000000000',1,'2023-04-14 08:23:03','2023-04-14 08:23:03'),(27,'111111111111111111111',1,'2023-04-14 08:23:56','2023-04-14 08:23:56'),(28,'asdasd',1,'2023-04-14 08:27:11','2023-04-14 08:27:11'),(29,'aspoduqwoidjoqwdqwdq',0,'2023-04-14 08:52:11','2023-05-01 10:12:11'),(30,'asdasd',1,'2023-04-14 09:01:40','2023-04-14 09:01:40'),(31,'asdsadasd',1,'2023-04-14 09:10:44','2023-04-14 09:10:44'),(32,'adkajslasd',1,'2023-04-14 09:20:08','2023-04-14 09:20:08'),(33,'as;dlkas;odksa',1,'2023-04-14 09:21:09','2023-04-14 09:21:09'),(34,'Dangquy123',1,'2023-04-14 09:23:00','2023-04-14 09:23:00'),(35,'nguyendangquy',1,'2023-04-14 09:24:11','2023-04-14 09:24:11'),(36,'',1,'2023-04-14 09:31:39','2023-04-14 09:31:39'),(37,'test quan li',1,'2023-04-14 13:56:14','2023-04-14 13:56:14'),(38,'quy',1,'2023-04-25 09:07:24','2023-04-25 09:07:24'),(39,'h',1,'2023-04-25 09:08:12','2023-04-25 09:08:12'),(40,'a',1,'2023-04-25 09:08:37','2023-04-25 09:08:37'),(41,'qiu',1,'2023-04-25 09:10:07','2023-04-25 09:10:07'),(42,'quiii',1,'2023-04-25 09:10:26','2023-04-25 09:10:26'),(43,'quiii',1,'2023-04-25 09:11:47','2023-04-25 09:11:47'),(44,',l',1,'2023-04-25 09:13:45','2023-04-25 09:13:45'),(45,'tessssssssssssssssssssssssss',1,'2023-04-25 09:14:02','2023-04-25 09:14:02'),(46,'qqeqwe',1,'2023-04-25 09:17:46','2023-04-25 09:17:46'),(47,'huy',1,'2023-04-25 14:53:25','2023-04-25 14:53:25'),(48,'hoa',1,'2023-04-25 14:53:56','2023-04-25 14:53:56'),(49,'999999999',1,'2023-04-25 15:17:28','2023-04-25 15:17:28'),(50,'',0,'2023-04-25 15:19:17','2023-04-25 15:24:37'),(51,'',0,'2023-04-25 15:24:51','2023-04-25 15:24:58'),(52,'333333',1,'2023-04-25 15:26:52','2023-04-25 15:26:52'),(53,'asdsad',1,'2023-05-01 10:11:55','2023-05-01 10:11:55'),(54,'444',0,'2023-05-01 11:25:53','2023-05-05 17:25:50'),(55,'ư',0,'2023-05-01 11:26:02','2023-05-05 17:25:44');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (0,'Anonymus user','Here','NULL','0000000000','Femle','NULL',1,'2023-04-29 05:09:22','2023-05-01 11:04:21'),(1,'Nguyen Dang Quy','Hue','6251071076@st.utc2.edu.vn','0869960852','Male','123456789123',1,'2023-04-27 04:15:36','2023-05-03 09:35:28'),(2,'Do Duc Phong','Nam Dinh','doducphong21062003@gmail.com','0869525525','Male','147258369963',1,'2023-04-27 04:15:36','2023-05-03 16:12:10'),(4,'dasdsa','asdasd','asd@gmail.com','0852258258','Male','123456789124',0,'2023-05-01 11:10:15','2023-05-01 11:13:23'),(5,'Võ tuấn hưng','12 Cà mau','tuanhung@gmail.com','0852852852','Male','123412344231',1,'2023-05-01 11:11:40','2023-05-01 11:11:40'),(6,'Phan Tuyển','kjhkjahskdh','6051071139@st.utc2.edu.vn','0825852852','Male','123456789987',1,'2023-05-04 13:26:30','2023-05-04 13:26:30'),(7,'Toan','Tang Nhon Phu','6051071124@st.utc2.edu.vn','0852258147','Male','123654789987',1,'2023-05-07 02:47:51','2023-05-07 02:49:38'),(8,'Văn Vĩ','Thủ Đức','hocquazalo@gmail.com','0852258854','Male','123654852258',1,'2023-05-07 15:41:43','2023-05-07 15:41:43');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_goods`
--

LOCK TABLES `import_goods` WRITE;
/*!40000 ALTER TABLE `import_goods` DISABLE KEYS */;
INSERT INTO `import_goods` VALUES (1,1,12,120000,1,'2023-05-06 08:12:09','2023-05-06 08:12:09'),(2,1,33,330000000,1,'2023-05-06 08:15:01','2023-05-06 08:15:01'),(3,1,1000,10000000,1,'2023-05-06 09:04:03','2023-05-06 09:04:03'),(4,1,1200,12000000,1,'2023-05-06 09:04:20','2023-05-06 09:04:20'),(5,1,242,2300120000,1,'2023-05-06 09:14:16','2023-05-06 09:14:16'),(6,1,50,500000000,1,'2023-05-06 09:33:59','2023-05-06 09:33:59'),(7,1,50,750000000,1,'2023-05-06 12:03:23','2023-05-06 12:03:23'),(8,1,1,10000,1,'2023-05-07 13:18:30','2023-05-07 13:18:30');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_goods_detail`
--

LOCK TABLES `import_goods_detail` WRITE;
/*!40000 ALTER TABLE `import_goods_detail` DISABLE KEYS */;
INSERT INTO `import_goods_detail` VALUES (1,3,1,10,200000,1,'2023-05-03 14:58:59','2023-05-03 14:58:59'),(2,50,50,10,10,1,'2023-05-03 15:06:13','2023-05-03 15:06:13'),(3,1,1,20,200000,1,'2023-05-03 15:06:42','2023-05-03 15:06:42'),(4,0,23,12,120000,1,'2023-05-06 08:12:09','2023-05-06 08:12:09'),(5,2,20,12,120000000,1,'2023-05-06 08:15:01','2023-05-06 08:15:01'),(6,2,21,21,210000000,1,'2023-05-06 08:15:01','2023-05-06 08:15:01'),(7,3,23,1000,10000000,1,'2023-05-06 09:04:03','2023-05-06 09:04:03'),(8,4,23,1200,12000000,1,'2023-05-06 09:04:20','2023-05-06 09:04:20'),(9,5,23,12,120000,1,'2023-05-06 09:14:16','2023-05-06 09:14:16'),(10,5,21,230,2300000000,1,'2023-05-06 09:14:16','2023-05-06 09:14:16'),(11,6,20,50,500000000,1,'2023-05-06 09:33:59','2023-05-06 09:33:59'),(12,7,22,50,750000000,1,'2023-05-06 12:03:23','2023-05-06 12:03:23'),(13,8,23,1,10000,1,'2023-05-07 13:18:30','2023-05-07 13:18:30');
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,2,2,6,32052678,'Nam Dinh','0869525525',1,'2023-04-02 10:11:54','2023-05-07 03:13:22'),(3,1,1,7,86186184,'Hue','0869960852',1,'2023-05-03 08:54:57','2023-05-03 08:54:57'),(4,1,0,4,49249248,'','',1,'2023-05-03 09:02:40','2023-05-03 09:02:40'),(5,1,0,1,5342113,'','',1,'2023-05-03 09:06:59','2023-05-03 09:06:59'),(10,1,0,9,110810808,'','',1,'2023-05-03 09:25:25','2023-05-03 09:25:25'),(11,1,0,10,123123120,'','',1,'2023-05-03 09:31:03','2023-05-03 09:31:03'),(13,1,1,16,16026840,'Hue','0869960852',1,'2023-05-03 09:36:06','2023-05-03 09:36:06'),(14,1,1,10,123123120,'Hue','0869960852',1,'2023-05-03 09:38:37','2023-05-03 09:38:37'),(15,1,0,3,36936936,'','',1,'2023-05-03 09:53:16','2023-05-03 09:53:16'),(16,1,0,6,73873872,'','',1,'2023-05-03 09:55:07','2023-05-03 09:55:07'),(17,1,1,5,61561560,'Hue','0869960852',1,'2023-05-03 09:55:25','2023-05-03 09:55:25'),(19,1,2,13,118238862,'Test','0852258852',1,'2023-05-03 16:12:52','2023-05-03 16:12:52'),(20,1,6,7,40651053,'kjhkjahskdh','0825852852',1,'2023-05-04 13:26:48','2023-05-04 13:26:48'),(21,1,1,23,263020936,'Hue','0869960852',1,'2023-05-06 12:03:59','2023-05-06 12:03:59'),(22,1,7,6,69308850,'Tang Nhon Phu','0852258147',1,'2023-05-07 02:48:20','2023-05-07 02:48:20'),(23,1,7,17,255561560,'Tang Nhon Phu','0852258147',1,'2023-05-07 02:50:10','2023-05-07 02:50:10'),(24,1,0,95,1140000,'Here','0000000000',1,'2023-05-07 12:26:11','2023-05-07 12:26:11'),(25,1,0,363,4356000,'Here','0000000000',1,'2023-05-07 13:04:29','2023-05-07 13:04:29'),(26,1,0,1,12000,'Here','0000000000',1,'2023-05-07 14:04:20','2023-05-07 14:04:20'),(27,4,8,13,156060000,'Thủ Đức','0852258854',1,'2023-05-07 15:42:04','2023-05-07 15:42:04'),(28,1,8,17,42095,'','',1,'2023-05-10 04:57:21','2023-05-10 04:57:21'),(29,1,8,12,125084000,'','',1,'2023-05-11 03:30:04','2023-05-11 03:30:04'),(30,1,0,3,75000000,NULL,NULL,1,'2023-05-11 03:39:42','2023-05-11 03:39:42'),(31,1,0,10,100072000,NULL,NULL,1,'2023-05-11 03:49:02','2023-05-11 03:49:02');
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_detail`
--

LOCK TABLES `invoice_detail` WRITE;
/*!40000 ALTER TABLE `invoice_detail` DISABLE KEYS */;
INSERT INTO `invoice_detail` VALUES (1,1,18,6,32052678,1,'2023-05-02 10:11:54','2023-05-02 10:11:54'),(5,3,20,7,86186184,1,'2023-05-03 08:54:57','2023-05-03 08:54:57'),(6,4,20,4,49249248,1,'2023-05-03 09:02:40','2023-05-03 09:02:40'),(7,5,18,1,5342113,1,'2023-05-03 09:06:59','2023-05-03 09:06:59'),(8,10,20,9,110810808,1,'2023-05-03 09:25:25','2023-05-03 09:25:25'),(9,11,20,10,123123120,1,'2023-05-03 09:31:03','2023-05-03 09:31:03'),(13,13,18,3,16026339,1,'2023-05-03 09:36:06','2023-05-03 09:36:06'),(14,13,17,6,270,1,'2023-05-03 09:36:06','2023-05-03 09:36:06'),(15,13,16,7,231,1,'2023-05-03 09:36:06','2023-05-03 09:36:06'),(16,14,20,10,123123120,1,'2023-05-03 09:38:37','2023-05-03 09:38:37'),(17,15,20,3,36936936,1,'2023-05-03 09:53:16','2023-05-03 09:53:16'),(19,17,20,5,61561560,1,'2023-05-03 09:55:25','2023-05-03 09:55:25'),(20,18,16,7,231,1,'2023-05-03 09:56:59','2023-05-03 09:56:59'),(21,18,20,1,12312312,1,'2023-05-03 09:56:59','2023-05-03 09:56:59'),(22,18,18,1,5342113,1,'2023-05-03 09:56:59','2023-05-03 09:56:59'),(23,19,20,7,86186184,1,'2023-05-03 16:12:52','2023-05-03 16:12:52'),(24,19,18,6,32052678,1,'2023-05-03 16:12:52','2023-05-03 16:12:52'),(25,20,18,3,16026339,1,'2023-05-04 13:26:48','2023-05-04 13:26:48'),(26,20,20,2,24624624,1,'2023-05-04 13:26:48','2023-05-04 13:26:48'),(27,20,17,2,90,1,'2023-05-04 13:26:48','2023-05-04 13:26:48'),(28,21,22,7,154000000,1,'2023-05-06 12:03:59','2023-05-06 12:03:59'),(29,21,23,7,84000,1,'2023-05-06 12:03:59','2023-05-06 12:03:59'),(30,21,21,6,72000000,1,'2023-05-06 12:03:59','2023-05-06 12:03:59'),(31,21,20,3,36936936,1,'2023-05-06 12:03:59','2023-05-06 12:03:59'),(32,22,22,1,22000000,1,'2023-05-07 02:48:20','2023-05-07 02:48:20'),(33,22,21,1,12000000,1,'2023-05-07 02:48:20','2023-05-07 02:48:20'),(34,22,20,2,24624624,1,'2023-05-07 02:48:20','2023-05-07 02:48:20'),(35,22,18,2,10684226,1,'2023-05-07 02:48:20','2023-05-07 02:48:20'),(36,23,22,5,110000000,1,'2023-05-07 02:50:10','2023-05-07 02:50:10'),(37,23,21,7,84000000,1,'2023-05-07 02:50:10','2023-05-07 02:50:10'),(38,23,20,5,61561560,1,'2023-05-07 02:50:10','2023-05-07 02:50:10'),(39,24,23,95,1140000,1,'2023-05-07 12:26:11','2023-05-07 12:26:11'),(40,25,23,363,4356000,1,'2023-05-07 13:04:29','2023-05-07 13:04:29'),(41,26,23,1,12000,1,'2023-05-07 14:04:20','2023-05-07 14:04:20'),(42,27,23,5,60000,1,'2023-05-07 15:42:04','2023-05-07 15:42:04'),(43,27,22,6,132000000,1,'2023-05-07 15:42:04','2023-05-07 15:42:04'),(44,27,21,2,24000000,1,'2023-05-07 15:42:04','2023-05-07 15:42:04'),(45,28,4,8,17864,1,'2023-05-10 04:57:21','2023-05-10 04:57:21'),(46,28,16,7,231,1,'2023-05-10 04:57:21','2023-05-10 04:57:21'),(47,28,23,2,24000,1,'2023-05-10 04:57:21','2023-05-10 04:57:21'),(48,29,23,7,84000,1,'2023-05-11 03:30:04','2023-05-11 03:30:04'),(49,29,22,5,125000000,1,'2023-05-11 03:30:04','2023-05-11 03:30:04'),(50,30,22,3,75000000,1,'2023-05-11 03:39:42','2023-05-11 03:39:42'),(51,31,23,6,72000,1,'2023-05-11 03:49:02','2023-05-11 03:49:02'),(52,31,22,4,100000000,1,'2023-05-11 03:49:02','2023-05-11 03:49:02');
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
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,2,7,'qwdw',30,'asdsad',1321312,NULL,'a',1,'2023-04-13 07:56:21','2023-05-07 10:41:13'),(2,1,7,'dfdsdsc',0,'aslkdjalsk',123,NULL,'D:\\FileUpload\\1.png',1,'2023-04-13 07:56:21','2023-05-01 14:49:18'),(3,1,12,'asdasd',0,'asdas',53,NULL,'D:\\FileUpload\\1.png',1,'2023-04-13 07:56:21','2023-05-01 14:49:28'),(4,1,5,'áđasa',69,'222a',2233,NULL,'D:\\FileUpload\\1.png',1,'2023-04-27 13:32:13','2023-05-10 04:57:21'),(13,1,12,'dangquy',95,'',444,NULL,'D:\\FileUpload\\1.png',1,'2023-04-27 13:50:46','2023-05-05 17:49:03'),(14,1,3,'sd',95,'',33,NULL,'D:\\FileUpload\\1.png',1,'2023-04-27 13:50:46','2023-05-01 14:49:36'),(15,1,5,'g',190,'',33,NULL,'D:\\FileUpload\\1.png',0,'2023-04-27 13:50:46','2023-05-01 15:16:06'),(16,1,10,'g',235,'',33,NULL,'D:\\FileUpload\\1.png',1,'2023-04-27 13:50:46','2023-05-10 04:57:21'),(17,1,5,'h',261,'',45,NULL,'D:\\FileUpload\\1.png',1,'2023-04-27 13:50:46','2023-05-04 13:26:48'),(18,1,2,'Iphone',10,'',5342113,NULL,'D:\\FileUpload\\1661934932511_iphone_11_black.png',1,'2023-05-01 14:01:58','2023-05-07 02:48:20'),(19,1,11,'Iphone 11 Promax',0,'',5600000,NULL,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png',0,'2023-05-01 14:06:24','2023-05-01 15:16:23'),(20,1,5,'dsađoá',86,'	',12312312,10000000,'D:\\FileUpload\\1661934902387_iphone_11_black.png',1,'2023-05-01 14:09:17','2023-05-07 02:50:10'),(21,2,53,'Androi',235,'',12000000,10000000,'D:\\FileUpload\\1661934884064_xiaomi_redmi_note_11.png',1,'2023-05-06 06:47:23','2023-05-07 15:42:04'),(22,2,53,'No',19,'',25000000,15000000,'D:\\FileUpload\\t.png',1,'2023-05-06 06:49:14','2023-05-11 03:49:02'),(23,2,53,'test',1739,'',12000,10000,'D:\\FileUpload\\1661934942391_iphone_13_pro_max_gold.png',1,'2023-05-06 06:51:55','2023-05-11 03:49:02');
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
                         UNIQUE KEY `cccd` (`cccd`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,1,'D:\\FileUpload\\1662969078950_6-buoc-don-gian-chan-doan-loi-he-thong-mang-2.jpg','admin','dENd3ZHRQ0zPZv0JI1AeV/MRbTO/MSxeSIL06VwvnDrO73XaB+kou2//jJvkYMed','ADMIN','khong','admin','123456789','987654321','Female',1,'2023-04-09 03:10:54','2023-05-07 07:46:24'),(4,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','dangquy','t0Uj/CF3SHJk6Z5VJcDTzCiUP26xsiYeEQ5AFW2fydxdy+Tiw41UVP7EzDsLmmKU','Akim Ruoff','Room 374','aruoff2@loc.gov','1165533250','148.30.239.212','Male',1,'2023-04-11 04:30:57','2023-05-07 05:36:05'),(5,3,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','ducphong','t0Uj/CF3SHJk6Z5VJcDTzCiUP26xsiYeEQ5AFW2fydxdy+Tiw41UVP7EzDsLmmKU','Richmound Du Fray','Room 1859','rdu3@mail.ru','4522059003','236.169.234.174','Female',1,'2023-04-11 04:30:57','2023-05-07 06:06:58'),(7,1,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Ophelie','jjKMFJX','Ophelie Sartin','Room 1970','osartin5@trellian.com','2678944496','169.117.124.181','Male',1,'2023-04-11 04:30:57','2023-04-13 11:32:07'),(8,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Ronnica','lzK4ds6S','Ronnica Fante','Apt 1060','rfante6@gizmodo.com','2689968464','0','Female',1,'2023-04-11 04:30:57','2023-04-13 11:32:07'),(10,1,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Sadella','5xLDdD2YFj','Sadella Weightman','Room 1809','sweightman8@uiuc.edu','9089011956','','Female',1,'2023-04-11 04:30:57','2023-04-14 14:01:22'),(11,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Gussy','oeyH76o','Gussy Coster','Room 151','gcoster9@earthlink.net','8339562974','25.84.173.105','Female',1,'2023-04-11 04:30:57','2023-04-14 04:25:33'),(17,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','nguyendag','vhMiwOmCp8GZ5pEJKSxnhkAvT/GzDO5Ki2DqvEZNxOD8eqs6vvEc13GGlIQ+ALQi','NGuyen Dang Quy','hue','dangquy0@gmail.com','0869960852','123456789123','Male',1,'2023-04-13 14:29:23','2023-04-13 14:58:05'),(18,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','dangquyeee','huVAi9Ps+AWl+zKYc/x6SdyrzbAFadnbG1eG2HiCSo7SZiQmWTqKM0vMq93/BAx7','dangquy','da','Daangquy@gmail.com','0869960853','046203010918','Female',1,'2023-04-13 14:33:27','2023-04-13 14:56:51'),(19,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','dangquyoi','1PHUKmoeumytp9NK1As2wY1QCta6sRhUyjA9T6Vs1qo4ekzxeIkcHEgAHALumW3B','oiiiii','oiiiii','qqqq@gmail.com','0852085096','085288852212','Female',1,'2023-04-13 14:37:04','2023-04-13 14:56:51'),(21,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','testtesttest','rNWcWiCZJmKYBJ0asOKqoAYAvod++Tnwo/WdhhdEH0QKK1+F5PDC/5Q+EJFPqMAg','test','test','quy09122@gmail.com','0868888888','085208520852','Female',1,'2023-04-13 14:53:18','2023-04-13 14:57:00'),(22,2,'D:\\FileUpload\\1662966128898_cach-chong-buon-ngu-3.jpg','testestsdsads','X+LTOIIUWWUI0R8XtIJks/H2uPyLBdvGSdYzsCNpsSmtJRLFJ5h6Xjd2SOZ2KJNw','testtttttttttt','test','dangdquy@gmail.com','0817417415','963852741147','Female',1,'2023-04-13 14:56:20','2023-04-25 09:03:21'),(26,1,'D:\\FileUpload\\1661937323424_iphone_12_red.png','dangquyitt12oeowp','arxaWzPrE81sWwBQPaKL7RNUIyt1iZ+tBEiXc+ZArPhtZeUCB3PldpaWUtaaW5Y5','akjdshkladjh','admgklajs','dangquyit@gmail.com','0869960855','123456789122','Male',1,'2023-04-14 07:39:22','2023-05-01 10:16:28'),(32,1,'D:\\FileUpload\\1661937323424_iphone_12_red.png','Dangquyalskdjlsajd','bEPBfokHzGzj9prW+xx8p0K5EJDzb9Y8HTvjU6FZV+BUzXaeLw/3F5zNiCK3N/hE','Nguyen Dang Quy','a','dangquyitt@gmail.com','0866608522','258258258258','Male',1,'2023-05-01 10:10:13','2023-05-01 10:10:13');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'HCM','0852252252','Iphone','iphone@gmail.com',1,'2023-04-13 08:31:52','2023-05-02 06:49:20'),(2,'Hue','0351525252','Kho tân bình','ndqsuppplier@gmail.com',1,'2023-05-02 06:04:24','2023-05-02 06:49:45'),(3,'hue','0356654654','test','test@gmail.com',0,'2023-05-02 06:59:32','2023-05-02 06:59:40');
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

-- Dump completed on 2023-05-11 10:51:11
