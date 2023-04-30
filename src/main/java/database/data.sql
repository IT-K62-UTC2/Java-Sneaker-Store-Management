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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'sneaker vip',0,'2023-04-13 07:44:09','2023-04-29 06:24:39'),(2,'sneaker vip 10',1,'2023-04-13 07:44:09','2023-04-27 03:29:50'),(3,'weqwe',0,'2023-04-13 07:44:09','2023-04-25 15:24:27'),(4,'Nguyen dang quy',1,'2023-04-14 07:25:47','2023-04-14 07:25:47'),(5,'dangquy',1,'2023-04-14 07:26:33','2023-04-14 07:26:33'),(6,'asdsad',0,'2023-04-14 07:27:45','2023-04-29 06:24:42'),(7,'qweqwe',1,'2023-04-14 07:32:37','2023-04-14 07:32:37'),(8,'asdaw',1,'2023-04-14 07:34:53','2023-04-14 07:34:53'),(9,'phu huy',1,'2023-04-14 07:35:06','2023-04-14 07:35:06'),(10,'dangquy',1,'2023-04-14 07:35:28','2023-04-14 07:35:28'),(11,'popipqowiepoqw',1,'2023-04-14 07:39:42','2023-04-14 07:39:42'),(12,'test',1,'2023-04-14 07:40:06','2023-04-14 07:40:06'),(13,'12312',1,'2023-04-14 07:43:12','2023-04-14 07:43:12'),(14,'12312',1,'2023-04-14 07:43:35','2023-04-14 07:43:35'),(15,'12312',1,'2023-04-14 07:43:37','2023-04-14 07:43:37'),(16,'buonngu',1,'2023-04-14 07:46:13','2023-04-14 07:46:13'),(17,'tttttttt',1,'2023-04-14 07:46:51','2023-04-14 07:46:51'),(18,'kjong',1,'2023-04-14 07:52:11','2023-04-14 07:52:11'),(19,'iiiiiii',1,'2023-04-14 07:54:34','2023-04-14 07:54:34'),(20,'ads',1,'2023-04-14 07:56:47','2023-04-14 07:56:47'),(21,'asdad',1,'2023-04-14 07:57:43','2023-04-14 07:57:43'),(22,'qweqwe',1,'2023-04-14 08:02:49','2023-04-14 08:02:49'),(23,'eqwewqeqweqweqw',1,'2023-04-14 08:15:00','2023-04-14 08:15:00'),(24,'tess 1000',1,'2023-04-14 08:20:22','2023-04-14 08:20:22'),(25,'dasdaskdjkash',1,'2023-04-14 08:21:52','2023-04-14 08:21:52'),(26,'000000000000',1,'2023-04-14 08:23:03','2023-04-14 08:23:03'),(27,'111111111111111111111',1,'2023-04-14 08:23:56','2023-04-14 08:23:56'),(28,'asdasd',1,'2023-04-14 08:27:11','2023-04-14 08:27:11'),(29,'aspoduqwoidjoqwdqwdq',1,'2023-04-14 08:52:11','2023-04-14 08:52:11'),(30,'asdasd',1,'2023-04-14 09:01:40','2023-04-14 09:01:40'),(31,'asdsadasd',1,'2023-04-14 09:10:44','2023-04-14 09:10:44'),(32,'adkajslasd',1,'2023-04-14 09:20:08','2023-04-14 09:20:08'),(33,'as;dlkas;odksa',1,'2023-04-14 09:21:09','2023-04-14 09:21:09'),(34,'Dangquy123',1,'2023-04-14 09:23:00','2023-04-14 09:23:00'),(35,'nguyendangquy',1,'2023-04-14 09:24:11','2023-04-14 09:24:11'),(36,'',1,'2023-04-14 09:31:39','2023-04-14 09:31:39'),(37,'test quan li',1,'2023-04-14 13:56:14','2023-04-14 13:56:14'),(38,'quy',1,'2023-04-25 09:07:24','2023-04-25 09:07:24'),(39,'h',1,'2023-04-25 09:08:12','2023-04-25 09:08:12'),(40,'a',1,'2023-04-25 09:08:37','2023-04-25 09:08:37'),(41,'qiu',1,'2023-04-25 09:10:07','2023-04-25 09:10:07'),(42,'quiii',1,'2023-04-25 09:10:26','2023-04-25 09:10:26'),(43,'quiii',1,'2023-04-25 09:11:47','2023-04-25 09:11:47'),(44,',l',1,'2023-04-25 09:13:45','2023-04-25 09:13:45'),(45,'tessssssssssssssssssssssssss',1,'2023-04-25 09:14:02','2023-04-25 09:14:02'),(46,'qqeqwe',1,'2023-04-25 09:17:46','2023-04-25 09:17:46'),(47,'huy',1,'2023-04-25 14:53:25','2023-04-25 14:53:25'),(48,'hoa',1,'2023-04-25 14:53:56','2023-04-25 14:53:56'),(49,'999999999',1,'2023-04-25 15:17:28','2023-04-25 15:17:28'),(50,'',0,'2023-04-25 15:19:17','2023-04-25 15:24:37'),(51,'',0,'2023-04-25 15:24:51','2023-04-25 15:24:58'),(52,'333333',1,'2023-04-25 15:26:52','2023-04-25 15:26:52');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (0,'Anonymus user','Here','NULL','0000000000','Femle','NULL',0,'2023-04-29 05:09:22','2023-04-30 13:04:10'),(1,'Nguyen Dang Quy','Hue','dangquyit@gmail,com','0869960852','Male','123456789123',1,'2023-04-27 04:15:36','2023-04-29 08:34:42'),(2,'Do Duc Phong','Nam Dinh','ducphongit@gmail.com','0869525525','Male','147258369963',0,'2023-04-27 04:15:36','2023-04-29 08:36:05');
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
                                       `unit_price` double NOT NULL DEFAULT '0',
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
                           `id_customer` int(11) NOT NULL,
                           `total_quantity` int(11) NOT NULL DEFAULT '0',
                           `money_total` double NOT NULL DEFAULT '0',
                           `delivery_address` varchar(100) NOT NULL,
                           `delivery_phone_number` varchar(25) NOT NULL,
                           `status` int(11) NOT NULL DEFAULT '1',
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,2,2,0,984,'','',1,'2023-04-28 10:35:34','2023-04-28 10:35:34'),(2,2,2,0,984,'fsadssd','dasdadasa',1,'2023-04-28 10:35:47','2023-04-28 10:35:47'),(6,2,1,0,477,'','',1,'2023-04-28 11:04:49','2023-04-28 11:04:49'),(7,2,2,0,6606560,'asdkjh','asdasm',1,'2023-04-28 11:25:21','2023-04-28 11:25:21'),(8,2,1,0,265,'Huế','Huế',1,'2023-04-28 14:48:07','2023-04-28 14:48:07'),(9,2,2,0,212,'adsa','aad',1,'2023-04-28 14:54:35','2023-04-28 14:54:35'),(10,2,1,0,615,'asda','adsa',1,'2023-04-28 14:56:42','2023-04-28 14:56:42'),(11,2,1,0,7927872,'Huế','0869960852',1,'2023-04-28 14:59:26','2023-04-28 14:59:26'),(12,2,1,0,6607175,'Huế','08666660852',1,'2023-04-28 15:00:42','2023-04-28 15:00:42'),(13,2,2,0,4737,'Nam Định','08552258458',1,'2023-04-28 15:04:02','2023-04-28 15:04:02'),(14,2,0,0,0,'','',1,'2023-04-29 05:07:15','2023-04-29 05:07:15'),(15,2,0,0,5285248,'','',1,'2023-04-29 05:20:06','2023-04-29 05:20:06'),(16,2,0,0,7927872,'','',1,'2023-04-29 05:20:46','2023-04-29 05:20:46'),(17,2,0,0,5287708,'','',1,'2023-04-29 05:21:08','2023-04-29 05:21:08'),(18,2,0,0,2642624,'','',1,'2023-04-29 06:04:07','2023-04-29 06:04:07'),(19,2,0,0,3963936,'','',1,'2023-04-29 06:04:53','2023-04-29 06:04:53'),(20,2,0,0,3963936,'','',1,'2023-04-29 06:05:27','2023-04-29 06:05:27'),(21,2,0,0,5285248,'','',1,'2023-04-29 06:12:22','2023-04-29 06:12:22'),(22,2,0,0,100419712,'','',1,'2023-04-29 06:12:52','2023-04-29 06:12:52'),(23,2,0,0,52063,'','',1,'2023-04-29 06:14:09','2023-04-29 06:14:09'),(24,2,0,9,2352,'','',1,'2023-04-29 06:18:29','2023-04-29 06:18:29'),(25,2,1,32,1176,'Hue, Thừa Thiên Huế','0869960852',1,'2023-04-29 08:09:49','2023-04-29 08:09:49'),(26,2,0,1,33,'','',1,'2023-04-30 07:46:57','2023-04-30 07:46:57');
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_detail`
--

LOCK TABLES `invoice_detail` WRITE;
/*!40000 ALTER TABLE `invoice_detail` DISABLE KEYS */;
INSERT INTO `invoice_detail` VALUES (1,1,2,4,212,1,'2023-04-28 14:54:36','2023-04-29 08:00:28'),(2,1,2,5,615,1,'2023-04-28 14:56:43','2023-04-29 08:04:55'),(3,1,1,6,7927872,1,'2023-04-28 14:59:27','2023-04-29 08:04:55'),(4,12,1,5,6606560,1,'2023-04-28 15:00:43','2023-04-28 15:00:43'),(5,12,2,5,615,1,'2023-04-28 15:00:43','2023-04-28 15:00:43'),(6,13,13,10,4440,1,'2023-04-28 15:04:03','2023-04-28 15:04:03'),(7,13,14,9,297,1,'2023-04-28 15:04:03','2023-04-28 15:04:03'),(8,15,1,4,5285248,1,'2023-04-29 05:20:07','2023-04-29 05:20:07'),(9,16,1,6,7927872,1,'2023-04-29 05:20:46','2023-04-29 05:20:46'),(10,17,1,4,5285248,1,'2023-04-29 05:21:08','2023-04-29 05:21:08'),(11,17,2,20,2460,1,'2023-04-29 05:21:08','2023-04-29 05:21:08'),(12,18,1,2,2642624,1,'2023-04-29 06:04:07','2023-04-29 06:04:07'),(13,19,1,3,3963936,1,'2023-04-29 06:04:53','2023-04-29 06:04:53'),(14,20,1,3,3963936,1,'2023-04-29 06:05:27','2023-04-29 06:05:27'),(15,21,1,4,5285248,1,'2023-04-29 06:12:22','2023-04-29 06:12:22'),(16,22,1,76,100419712,1,'2023-04-29 06:12:52','2023-04-29 06:12:52'),(17,23,2,20,2460,1,'2023-04-29 06:14:09','2023-04-29 06:14:09'),(18,23,3,9,477,1,'2023-04-29 06:14:09','2023-04-29 06:14:09'),(19,23,4,22,49126,1,'2023-04-29 06:14:09','2023-04-29 06:14:09'),(20,24,13,5,2220,1,'2023-04-29 06:18:29','2023-04-29 06:18:29'),(21,24,14,4,132,1,'2023-04-29 06:18:29','2023-04-29 06:18:29'),(22,25,16,12,396,1,'2023-04-29 08:09:49','2023-04-29 08:09:49'),(23,25,17,10,450,1,'2023-04-29 08:09:49','2023-04-29 08:09:49'),(24,25,15,10,330,1,'2023-04-29 08:09:49','2023-04-29 08:09:49'),(25,26,14,1,33,1,'2023-04-30 07:46:57','2023-04-30 07:46:57');
/*!40000 ALTER TABLE `invoice_detail` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Admin',1,'2023-04-09 03:09:47','2023-04-09 03:09:47'),(2,'Employee',1,'2023-04-09 03:09:47','2023-04-09 03:09:47');
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
                           `avatar` varchar(100) NOT NULL,
                           `status` int(11) NOT NULL DEFAULT '1',
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,1,'qwdw',0,'asdsad',1321312,'D:\\FileUpload\\1.png',1,'2023-04-13 07:56:21','2023-04-29 06:12:52'),(2,1,2,'dfdsdsc',0,'aslkdjalsk',123,'D:\\FileUpload\\1.png',1,'2023-04-13 07:56:21','2023-04-29 06:14:09'),(3,4,1,'asdasd',0,'asdas',53,'D:\\FileUpload\\1.png',1,'2023-04-13 07:56:21','2023-04-29 06:14:09'),(4,2,1,'áđasa',0,'222a',2233,'D:\\FileUpload\\1.png',1,'2023-04-27 13:32:13','2023-04-29 06:14:09'),(13,2,1,'dangquy',5,'23',444,'D:\\FileUpload\\1.png',1,'2023-04-27 13:50:46','2023-04-29 06:18:29'),(14,3,3,'sd',95,'',33,'D:\\FileUpload\\1.png',1,'2023-04-27 13:50:46','2023-04-30 07:46:57'),(15,1,3,'g',190,'',33,'D:\\FileUpload\\1.png',1,'2023-04-27 13:50:46','2023-04-29 08:09:49'),(16,1,4,'g',288,'',33,'D:\\FileUpload\\1.png',1,'2023-04-27 13:50:46','2023-04-29 08:09:49'),(17,1,5,'h',290,'',45,'D:\\FileUpload\\1.png',1,'2023-04-27 13:50:46','2023-04-29 08:09:49');
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,2,'D:\\FileUpload\\1662969078950_6-buoc-don-gian-chan-doan-loi-he-thong-mang-2.jpg','admin','t0Uj/CF3SHJk6Z5VJcDTzCiUP26xsiYeEQ5AFW2fydxdy+Tiw41UVP7EzDsLmmKU','ADMIN','khong','admin','123456789','987654321','Female',1,'2023-04-09 03:10:54','2023-04-14 05:10:01'),(2,1,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Nickola','RbsGvw9wlMu','Nickola Petrowsky','Suite 81','npetrowsky0@pagesperso-orange.fr','5357411091','68.105.17.67','Male',1,'2023-04-11 04:30:57','2023-04-13 11:32:07'),(3,2,'D:\\Image\\992651.png','Delora','2sGe7HCDudeJ','Delora Francom','15th Floor','dfrancom1@prlog.org','7425082436','5.171.21.11','Female',1,'2023-04-11 04:30:57','2023-04-13 11:49:57'),(4,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Akim','DE2x8BvWDa','Akim Ruoff','Room 374','aruoff2@loc.gov','1165533250','148.30.239.212','Male',1,'2023-04-11 04:30:57','2023-04-13 11:32:07'),(5,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Richmound','3jt8B3Z','Richmound Du Fray','Room 1859','rdu3@mail.ru','4522059003','236.169.234.174','Female',1,'2023-04-11 04:30:57','2023-04-13 11:32:07'),(6,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Gae','obLVEBoFAe','Gae Cruces','PO Box 97765','gcruces4@state.gov','1887997944','112.174.213.26','Female',1,'2023-04-11 04:30:57','2023-04-13 14:58:05'),(7,1,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Ophelie','jjKMFJX','Ophelie Sartin','Room 1970','osartin5@trellian.com','2678944496','169.117.124.181','Male',1,'2023-04-11 04:30:57','2023-04-13 11:32:07'),(8,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Ronnica','lzK4ds6S','Ronnica Fante','Apt 1060','rfante6@gizmodo.com','2689968464','0','Female',1,'2023-04-11 04:30:57','2023-04-13 11:32:07'),(9,1,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Marina','QEuSn9J','Marina Gullick','Room 38','mgullick7@chicagotribune.com','1876776759','115.137.47.6','Male',1,'2023-04-11 04:30:57','2023-04-13 14:58:05'),(10,1,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Sadella','5xLDdD2YFj','Sadella Weightman','Room 1809','sweightman8@uiuc.edu','9089011956','','Female',1,'2023-04-11 04:30:57','2023-04-14 14:01:22'),(11,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','Gussy','oeyH76o','Gussy Coster','Room 151','gcoster9@earthlink.net','8339562974','25.84.173.105','Female',1,'2023-04-11 04:30:57','2023-04-14 04:25:33'),(17,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','nguyendag','vhMiwOmCp8GZ5pEJKSxnhkAvT/GzDO5Ki2DqvEZNxOD8eqs6vvEc13GGlIQ+ALQi','NGuyen Dang Quy','hue','dangquy0@gmail.com','0869960852','123456789123','Male',1,'2023-04-13 14:29:23','2023-04-13 14:58:05'),(18,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','dangquyeee','huVAi9Ps+AWl+zKYc/x6SdyrzbAFadnbG1eG2HiCSo7SZiQmWTqKM0vMq93/BAx7','dangquy','da','Daangquy@gmail.com','0869960853','046203010918','Female',1,'2023-04-13 14:33:27','2023-04-13 14:56:51'),(19,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','dangquyoi','1PHUKmoeumytp9NK1As2wY1QCta6sRhUyjA9T6Vs1qo4ekzxeIkcHEgAHALumW3B','oiiiii','oiiiii','qqqq@gmail.com','0852085096','085288852212','Female',1,'2023-04-13 14:37:04','2023-04-13 14:56:51'),(21,2,'D:\\FileUpload\\1661934921999_iphone_13_pro_max_gold.png','testtesttest','rNWcWiCZJmKYBJ0asOKqoAYAvod++Tnwo/WdhhdEH0QKK1+F5PDC/5Q+EJFPqMAg','test','test','quy09122@gmail.com','0868888888','085208520852','Female',1,'2023-04-13 14:53:18','2023-04-13 14:57:00'),(22,2,'D:\\FileUpload\\1662966128898_cach-chong-buon-ngu-3.jpg','testestsdsads','X+LTOIIUWWUI0R8XtIJks/H2uPyLBdvGSdYzsCNpsSmtJRLFJ5h6Xjd2SOZ2KJNw','testtttttttttt','test','dangdquy@gmail.com','0817417415','963852741147','Female',1,'2023-04-13 14:56:20','2023-04-25 09:03:21'),(23,2,'D:\\FileUpload\\1674176927591_22385075_1965579760389186_892385645_n.png','ngocnhatngoc','gKAsK+wIAL00AsEH35uWbCXiZJSPYT/TSRcAHv3COawt/YvLhkcFd4wyz8sq/IqW','ngocnhat','ngoc','nngoc@gmail.com','0756085074','085456516516','Female',0,'2023-04-13 15:06:39','2023-04-14 06:12:35'),(26,1,'D:\\FileUpload\\1661937615624_iphone_11_black.png','dangquyitt12oeowp','arxaWzPrE81sWwBQPaKL7RNUIyt1iZ+tBEiXc+ZArPhtZeUCB3PldpaWUtaaW5Y5','akjdshkladjh','admgklajs','dangquyit@gmail.com','0869960855','123456789122','Male',1,'2023-04-14 07:39:22','2023-04-14 08:13:09');
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
INSERT INTO `supplier` VALUES (1,'HCM','12312098','Ho','ho',1,'2023-04-13 08:31:52','2023-04-13 08:31:52');
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

-- Dump completed on 2023-04-30 21:50:04
