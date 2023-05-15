/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for sneaker_manager
DROP DATABASE IF EXISTS `sneaker_manager`;
CREATE DATABASE IF NOT EXISTS `sneaker_manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `sneaker_manager`;

-- Dumping structure for table sneaker_manager.auth
DROP TABLE IF EXISTS `auth`;
CREATE TABLE IF NOT EXISTS `auth` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_position` int(11) NOT NULL,
    `id_menu` int(11) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.auth: ~24 rows (approximately)
INSERT INTO `auth` (`id`, `id_position`, `id_menu`) VALUES
                                                        (1, 1, 1),
                                                        (2, 1, 2),
                                                        (3, 1, 3),
                                                        (4, 1, 4),
                                                        (5, 1, 5),
                                                        (6, 1, 6),
                                                        (7, 1, 7),
                                                        (8, 1, 8),
                                                        (9, 1, 9),
                                                        (10, 2, 1),
                                                        (11, 2, 7),
                                                        (12, 2, 9),
                                                        (13, 2, 3),
                                                        (14, 3, 1),
                                                        (15, 3, 9),
                                                        (16, 3, 3),
                                                        (17, 3, 4),
                                                        (18, 3, 5),
                                                        (19, 3, 6),
                                                        (20, 3, 7),
                                                        (21, 3, 8),
                                                        (22, 1, 10),
                                                        (23, 2, 10),
                                                        (24, 3, 10);

-- Dumping structure for table sneaker_manager.category
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.category: ~5 rows (approximately)
INSERT INTO `category` (`id`, `name`, `status`, `created_at`, `updated_at`) VALUES
                                                                                (1, 'Other', 1, '2023-05-11 04:23:45', '2023-05-11 04:23:45'),
                                                                                (2, 'Nước Uống Tăng Lực', 0, '2023-05-11 05:22:10', '2023-05-11 07:01:57'),
                                                                                (3, 'Điện Thoại', 1, '2023-05-11 05:22:21', '2023-05-11 05:22:21'),
                                                                                (4, 'Giày sneaker', 1, '2023-05-11 05:22:34', '2023-05-11 06:37:51'),
                                                                                (5, 'Áo Adidas', 1, '2023-05-11 05:22:55', '2023-05-11 05:22:55');

-- Dumping structure for table sneaker_manager.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `fullname` varchar(50) NOT NULL,
    `address` varchar(100) NOT NULL,
    `email` varchar(50) NOT NULL,
    `phone_number` varchar(25) NOT NULL,
    `gender` varchar(25) NOT NULL,
    `cccd` varchar(25) NOT NULL,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`),
    UNIQUE KEY `phone_number` (`phone_number`),
    UNIQUE KEY `cccd` (`cccd`)
    ) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.customer: ~12 rows (approximately)
INSERT INTO `customer` (`id`, `fullname`, `address`, `email`, `phone_number`, `gender`, `cccd`, `status`, `created_at`, `updated_at`) VALUES
                                                                                                                                          (1, 'Anonymus User', 'Anonymus User', 'Anonymus User', 'Anonymus User', 'Anonymus User', 'Anonymus User', 1, '2023-05-11 04:24:35', '2023-05-11 04:24:35'),
                                                                                                                                          (2, 'Nguyễn Tòn Xòn', 'Lâm Đồng', 'tonxon@gmail.com', '0378132456', 'Female', '123456789134', 1, '2023-05-11 05:05:11', '2023-05-11 05:05:11'),
                                                                                                                                          (3, 'Châu Tinh Trì', 'Thượng Hải', 'anhtinh123@gmail.com', '0378456789', 'Female', '123456789156', 1, '2023-05-11 05:07:25', '2023-05-11 05:07:25'),
                                                                                                                                          (4, 'Châu Nhuận Phát', 'Hồng Kông', 'thanbai@gmail.com', '0378123456', 'Female', '123456789178', 1, '2023-05-11 05:08:10', '2023-05-11 05:08:10'),
                                                                                                                                          (5, 'Hứa Minh Đạt', 'Lào Cai', 'hohua@gmail.com', '0378654312', 'Female', '123456789189', 1, '2023-05-11 05:09:04', '2023-05-11 05:09:04'),
                                                                                                                                          (6, 'Tôn Ngộ Không', 'Hoa Quả Sơn', 'daithanh123@gmail.com', '0378123987', 'Female', '123456789136', 1, '2023-05-11 05:09:49', '2023-05-11 05:09:49'),
                                                                                                                                          (7, 'Đường Tăng Tạng', 'Châu Thổ Đại Đường', 'duongtangcamak@gmail.com', '0378369258', 'Female', '123456789131', 1, '2023-05-11 05:11:33', '2023-05-11 05:11:33'),
                                                                                                                                          (8, 'Jonh Wick', 'New York', 'jonhwick@gmail.com', '0378147258', 'Female', '123456789139', 1, '2023-05-11 05:12:36', '2023-05-11 05:12:36'),
                                                                                                                                          (9, 'Nguyễn Thanh Tùng', 'Quận 9', 'tungdepzai@gmail.com', '0378963852', 'Female', '123456789138', 1, '2023-05-11 05:13:38', '2023-05-11 05:13:38'),
                                                                                                                                          (10, 'Huỳnh Trấn Thành', 'Hàn Quốc', 'thanhcrry@gmail.com', '0378147741', 'Female', '123456789133', 1, '2023-05-11 05:14:55', '2023-05-11 05:14:55'),
                                                                                                                                          (11, 'Võ Tuấn Hưng', 'Thủ Đức', 'votuanhungit2008@gmail.com', '0943532632', 'Female', '147741147741', 1, '2023-05-11 06:43:54', '2023-05-11 06:43:54'),
                                                                                                                                          (12, 'Nguyễn Đăng Quý', 'Bình Thạnh', '6251071076@st.utc2.edu.vn', '0869960852', 'Female', '147741148528', 1, '2023-05-11 06:46:26', '2023-05-11 06:46:26');

-- Dumping structure for table sneaker_manager.discount
DROP TABLE IF EXISTS `discount`;
CREATE TABLE IF NOT EXISTS `discount` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `pecent` double NOT NULL DEFAULT 0,
    `remaining` int(11) NOT NULL DEFAULT 0,
    `time_expiry` date DEFAULT NULL,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.discount: ~0 rows (approximately)

-- Dumping structure for table sneaker_manager.import_goods
DROP TABLE IF EXISTS `import_goods`;
CREATE TABLE IF NOT EXISTS `import_goods` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_staff` int(11) NOT NULL,
    `quantity` int(11) NOT NULL DEFAULT 1,
    `money_total` double NOT NULL DEFAULT 0,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.import_goods: ~3 rows (approximately)
INSERT INTO `import_goods` (`id`, `id_staff`, `quantity`, `money_total`, `status`, `created_at`, `updated_at`) VALUES
                                                                                                                   (1, 1, 50, 25000000, 1, '2023-05-11 06:39:15', '2023-05-11 06:39:15'),
                                                                                                                   (2, 1, 90, 5170000, 1, '2023-05-11 08:00:08', '2023-05-11 08:00:08'),
                                                                                                                   (3, 3, 104, 59100000, 1, '2023-05-11 08:22:39', '2023-05-11 08:22:39');

-- Dumping structure for table sneaker_manager.import_goods_detail
DROP TABLE IF EXISTS `import_goods_detail`;
CREATE TABLE IF NOT EXISTS `import_goods_detail` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_import_goods` int(11) NOT NULL,
    `id_product` int(11) NOT NULL,
    `quantity` int(11) NOT NULL DEFAULT 1,
    `money_total` double NOT NULL DEFAULT 0,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.import_goods_detail: ~6 rows (approximately)
INSERT INTO `import_goods_detail` (`id`, `id_import_goods`, `id_product`, `quantity`, `money_total`, `status`, `created_at`, `updated_at`) VALUES
                                                                                                                                               (1, 1, 1, 50, 25000000, 1, '2023-05-11 06:39:15', '2023-05-11 06:39:15'),
                                                                                                                                               (2, 2, 4, 67, 4020000, 1, '2023-05-11 08:00:08', '2023-05-11 08:00:08'),
                                                                                                                                               (3, 2, 3, 23, 1150000, 1, '2023-05-11 08:00:08', '2023-05-11 08:00:08'),
                                                                                                                                               (4, 3, 8, 52, 52000000, 1, '2023-05-11 08:22:39', '2023-05-11 08:22:39'),
                                                                                                                                               (5, 3, 3, 42, 2100000, 1, '2023-05-11 08:22:39', '2023-05-11 08:22:39'),
                                                                                                                                               (6, 3, 1, 10, 5000000, 1, '2023-05-11 08:22:39', '2023-05-11 08:22:39');

-- Dumping structure for table sneaker_manager.invoice
DROP TABLE IF EXISTS `invoice`;
CREATE TABLE IF NOT EXISTS `invoice` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_staff` int(11) NOT NULL,
    `id_customer` int(11) NOT NULL DEFAULT 0,
    `total_quantity` int(11) NOT NULL DEFAULT 0,
    `money_total` double NOT NULL DEFAULT 0,
    `delivery_address` varchar(100) DEFAULT NULL,
    `delivery_phone_number` varchar(25) DEFAULT NULL,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.invoice: ~8 rows (approximately)
INSERT INTO `invoice` (`id`, `id_staff`, `id_customer`, `total_quantity`, `money_total`, `delivery_address`, `delivery_phone_number`, `status`, `created_at`, `updated_at`) VALUES
                                                                                                                                                                                (1, 1, 11, 5, 5500000, 'Thủ Đức', '0943532632', 1, '2023-05-06 06:44:10', '2023-05-11 07:46:01'),
                                                                                                                                                                                (2, 1, 12, 9, 1248000, 'Bình Thạnh', '0869960852', 1, '2023-05-03 06:54:35', '2023-05-11 07:46:07'),
                                                                                                                                                                                (3, 1, 1, 7, 49390000, 'Anonymus User', 'Anonymus User', 1, '2023-05-11 07:47:05', '2023-05-11 07:47:05'),
                                                                                                                                                                                (4, 2, 1, 4, 800000, 'Anonymus User', 'Anonymus User', 1, '2023-05-11 07:57:00', '2023-05-11 07:57:00'),
                                                                                                                                                                                (5, 3, 12, 6, 43090000, 'Bình Thạnh', '0869960852', 1, '2023-05-11 08:18:15', '2023-05-11 08:18:15'),
                                                                                                                                                                                (6, 3, 11, 11, 2700000, 'Thủ Đức', '0943532632', 1, '2023-05-11 08:24:09', '2023-05-11 08:24:09'),
                                                                                                                                                                                (7, 2, 1, 2, 400000, 'Anonymus User', 'Anonymus User', 1, '2023-05-11 08:25:30', '2023-05-11 08:25:30'),
                                                                                                                                                                                (12, 1, 1, 10, 31600000, 'Anonymus User', 'Anonymus User', 1, '2023-05-15 18:19:47', '2023-05-15 18:19:47');

-- Dumping structure for table sneaker_manager.invoice_detail
DROP TABLE IF EXISTS `invoice_detail`;
CREATE TABLE IF NOT EXISTS `invoice_detail` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_invoice` int(11) NOT NULL,
    `id_product` int(11) NOT NULL,
    `product_quantity` int(11) NOT NULL DEFAULT 1,
    `money_total` double NOT NULL,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.invoice_detail: ~17 rows (approximately)
INSERT INTO `invoice_detail` (`id`, `id_invoice`, `id_product`, `product_quantity`, `money_total`, `status`, `created_at`, `updated_at`) VALUES
                                                                                                                                             (1, 1, 1, 5, 5500000, 1, '2023-05-11 06:44:10', '2023-05-11 06:44:10'),
                                                                                                                                             (2, 2, 6, 2, 36000, 1, '2023-05-11 06:54:35', '2023-05-11 06:54:35'),
                                                                                                                                             (3, 2, 5, 1, 12000, 1, '2023-05-11 06:54:35', '2023-05-11 06:54:35'),
                                                                                                                                             (4, 2, 4, 6, 1200000, 1, '2023-05-11 06:54:35', '2023-05-11 06:54:35'),
                                                                                                                                             (5, 3, 7, 2, 6600000, 1, '2023-05-11 07:47:05', '2023-05-11 07:47:05'),
                                                                                                                                             (6, 3, 4, 2, 400000, 1, '2023-05-11 07:47:05', '2023-05-11 07:47:05'),
                                                                                                                                             (7, 3, 3, 2, 600000, 1, '2023-05-11 07:47:05', '2023-05-11 07:47:05'),
                                                                                                                                             (8, 3, 9, 1, 41790000, 1, '2023-05-11 07:47:05', '2023-05-11 07:47:05'),
                                                                                                                                             (9, 4, 4, 4, 800000, 1, '2023-05-11 07:57:00', '2023-05-11 07:57:00'),
                                                                                                                                             (10, 5, 4, 2, 400000, 1, '2023-05-11 08:18:15', '2023-05-11 08:18:15'),
                                                                                                                                             (11, 5, 3, 3, 900000, 1, '2023-05-11 08:18:15', '2023-05-11 08:18:15'),
                                                                                                                                             (12, 5, 9, 1, 41790000, 1, '2023-05-11 08:18:15', '2023-05-11 08:18:15'),
                                                                                                                                             (13, 6, 4, 6, 1200000, 1, '2023-05-11 08:24:09', '2023-05-11 08:24:09'),
                                                                                                                                             (14, 6, 3, 5, 1500000, 1, '2023-05-11 08:24:09', '2023-05-11 08:24:09'),
                                                                                                                                             (15, 7, 4, 2, 400000, 1, '2023-05-11 08:25:30', '2023-05-11 08:25:30'),
                                                                                                                                             (20, 12, 8, 2, 5200000, 1, '2023-05-15 18:19:47', '2023-05-15 18:19:47'),
                                                                                                                                             (21, 12, 7, 8, 26400000, 1, '2023-05-15 18:19:47', '2023-05-15 18:19:47');

-- Dumping structure for table sneaker_manager.menu
DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `path` varchar(255) NOT NULL,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.menu: ~10 rows (approximately)
INSERT INTO `menu` (`id`, `path`, `name`) VALUES
                                              (1, 'views/dashboard.fxml', 'Home'),
                                              (2, 'views/staff.fxml', 'Staff'),
                                              (3, 'views/customer.fxml', 'Customer'),
                                              (4, 'views/category.fxml', 'Category'),
                                              (5, 'views/product.fxml', 'Product'),
                                              (6, 'views/import-goods.fxml', 'Import Goods'),
                                              (7, 'views/history-invoice.fxml', 'History'),
                                              (8, 'views/supplier.fxml', 'Supplier'),
                                              (9, 'views/sell.fxml', 'Sell'),
                                              (10, 'views/change-password.fxml', 'Change Password');

-- Dumping structure for table sneaker_manager.position
DROP TABLE IF EXISTS `position`;
CREATE TABLE IF NOT EXISTS `position` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.position: ~3 rows (approximately)
INSERT INTO `position` (`id`, `name`, `status`, `created_at`, `updated_at`) VALUES
                                                                                (1, 'Admin', 1, '2023-04-09 03:09:47', '2023-04-09 03:09:47'),
                                                                                (2, 'Employee', 1, '2023-04-09 03:09:47', '2023-04-09 03:09:47'),
                                                                                (3, 'Manager', 1, '2023-05-07 06:04:55', '2023-05-07 06:04:55');

-- Dumping structure for table sneaker_manager.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `id_supplier` int(11) NOT NULL,
    `id_category` int(11) NOT NULL,
    `name` varchar(50) NOT NULL,
    `quantity` int(11) NOT NULL DEFAULT 0,
    `desc` varchar(100) NOT NULL DEFAULT '',
    `price` double NOT NULL DEFAULT 0,
    `import_price` double DEFAULT NULL,
    `avatar` varchar(255) NOT NULL,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`),
    KEY `product_supplier_fk` (`id_supplier`),
    KEY `product_category_fk` (`id_category`),
    CONSTRAINT `product_category_fk` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
    CONSTRAINT `product_supplier_fk` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.product: ~9 rows (approximately)
INSERT INTO `product` (`id`, `id_supplier`, `id_category`, `name`, `quantity`, `desc`, `price`, `import_price`, `avatar`, `status`, `created_at`, `updated_at`) VALUES
                                                                                                                                                                    (1, 6, 5, 'INDIGO HERZ FUR TEE', 155, '', 1100000, 500000, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\ProductImages\\images.jpg', 1, '2023-05-11 06:32:46', '2023-05-11 08:22:39'),
                                                                                                                                                                    (2, 6, 5, 'Jacket Adidas Black', 1000, '', 280000, 50000, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\ProductImages\\331427448_695166989067410_1112695238816411055_n.jpg', 1, '2023-05-11 06:48:41', '2023-05-11 06:48:41'),
                                                                                                                                                                    (3, 6, 5, 'Jacket Adidas Greyblack', 530, '', 300000, 50000, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\ProductImages\\332702635_1259548951295628_8354857388070785097_n.jpg', 1, '2023-05-11 06:49:37', '2023-05-15 18:03:42'),
                                                                                                                                                                    (4, 6, 5, 'T-shirt Adidas Black', 340, '', 200000, 60000, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\ProductImages\\336678662_241258024966227_6977079659847370747_n.jpg', 1, '2023-05-11 06:50:23', '2023-05-15 18:03:42'),
                                                                                                                                                                    (5, 6, 2, 'Sting', 999, '', 12000, 5000, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\ProductImages\\ImageName.jpg', 0, '2023-05-11 06:52:07', '2023-05-11 07:01:50'),
                                                                                                                                                                    (6, 6, 2, 'Red Bull', 498, '', 18000, 7000, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\ProductImages\\tải xuống (2).jpg', 0, '2023-05-11 06:53:27', '2023-05-11 07:01:36'),
                                                                                                                                                                    (7, 6, 4, 'SAMBA OG', 90, '', 3300000, 1500000, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\ProductImages\\adidas-samba_1.png', 1, '2023-05-11 06:58:57', '2023-05-15 18:19:47'),
                                                                                                                                                                    (8, 6, 4, 'STAN SMITH', 150, '', 2600000, 1000000, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\ProductImages\\d36f9163c2cdb77a116acd25ba66c4a7.png', 1, '2023-05-11 07:01:15', '2023-05-15 18:19:47'),
                                                                                                                                                                    (9, 6, 3, 'Iphone 14 Pro Max', 198, '', 41790000, 20000000, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\ProductImages\\tải xuống (3).jpg', 1, '2023-05-11 07:32:11', '2023-05-11 08:18:15');

-- Dumping structure for table sneaker_manager.staff
DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
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
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `email` (`email`),
    UNIQUE KEY `phone_number` (`phone_number`),
    UNIQUE KEY `cccd` (`cccd`),
    KEY `staff_posision_fk` (`id_position`),
    CONSTRAINT `staff_posision_fk` FOREIGN KEY (`id_position`) REFERENCES `position` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.staff: ~5 rows (approximately)
INSERT INTO `staff` (`id`, `id_position`, `avatar`, `username`, `password`, `fullname`, `address`, `email`, `phone_number`, `cccd`, `gender`, `status`, `created_at`, `updated_at`) VALUES
                                                                                                                                                                                        (1, 1, 'Admin', 'Admin', 'C1ft1LEx2Q7upS9B/u2bJkjVBkj9viQoLzSbGoae/78u3qgKp41oZmozuZuQ8pAm', 'Admin', 'Admin', 'Admin', 'Admin', 'Admin', 'Admin', 1, '2023-05-11 04:26:15', '2023-05-11 04:26:15'),
                                                                                                                                                                                        (2, 2, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\NhanVienImages\\1663302511146.png', 'nguyendangquy', 'EZUdXdgKDsaXy4hOXYjkcwv7kcuzVSHaHjPp7R3QKUQKPlPOoLE3yR2g2EVQ8upm', 'Nguyễn Đăng Quý', 'Thủ Đức', '62510721076@st.utc2.edu.vn', '0869960852', '147741147741', 'Male', 1, '2023-05-11 04:53:59', '2023-05-11 04:53:59'),
                                                                                                                                                                                        (3, 3, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\NhanVienImages\\1663302522811.jpg', 'doducphong', 'kEMKa+C+KlCiOD57pabn6Wv1wI2Oca4+633EYGRsxZB6X1rUgnmA0VGf4DKaJBIn', 'Đỗ Đức Phong', 'Đồng Nai', '6251071072@st.utc2.edu.vn', '0378436733', '036203014789', 'Male', 1, '2023-05-11 04:56:07', '2023-05-11 04:56:07'),
                                                                                                                                                                                        (4, 2, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\NhanVienImages\\1663302538663.jpg', 'votuanhung', 'D7XI4Gz0Jz6P3PG7PTNXE6f5GdFzeJt4qTt+ll1ArxkweKK5/8yCsJ+MrqaJJE3O', 'Võ Tuấn Hưng', 'Bình Thạnh', '6251071041@st.utc2.edu.vn', '0869960855', '369963369963', 'Male', 1, '2023-05-11 04:59:34', '2023-05-11 04:59:34'),
                                                                                                                                                                                        (5, 3, 'D:\\BTL-JAVA-Intellij\\JavaFX-Store\\src\\main\\resources\\utc2\\itk62\\store\\images\\NhanVienImages\\1663302558588.jpg', 'nguyentronghuy', '0eUiM63CvnAv3nkiuW1tu4xnleIY0k3KmjS1/6eznWYn1tg7cvPRr6dbyowHc9pp', 'Nguyễn Trọng Huy', 'Tân Bình', '6251071040@st.tuc2.edu.vn', '0376423569', '147897456123', 'Female', 1, '2023-05-11 05:01:57', '2023-05-15 17:32:24');

-- Dumping structure for table sneaker_manager.supplier
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE IF NOT EXISTS `supplier` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `address` varchar(100) NOT NULL,
    `phone_number` varchar(45) DEFAULT NULL,
    `name` varchar(45) DEFAULT NULL,
    `email` varchar(25) NOT NULL,
    `status` int(11) NOT NULL DEFAULT 1,
    `created_at` timestamp NULL DEFAULT current_timestamp(),
    `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `phone_number` (`email`)
    ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table sneaker_manager.supplier: ~6 rows (approximately)
INSERT INTO `supplier` (`id`, `address`, `phone_number`, `name`, `email`, `status`, `created_at`, `updated_at`) VALUES
                                                                                                                    (1, 'Anonymus Supplier', 'Anonymus Supplier', 'Anonymus Supplier', 'Anonymus Supplier', 1, '2023-05-11 04:25:32', '2023-05-11 04:25:32'),
                                                                                                                    (2, 'Tân Bình', '0378436733', 'Kho Tân Bình ', 'khohangtanbinh@gmail.com', 1, '2023-05-11 05:16:35', '2023-05-11 05:16:35'),
                                                                                                                    (3, 'Thủ Đức', '0378963258', 'Kho Thủ Đức', 'khothuduc@gmail.com', 1, '2023-05-11 05:17:44', '2023-05-11 05:17:44'),
                                                                                                                    (4, 'Bình Dương', '0378741369', 'Kho Bình Dương', 'khobinhduong@gmail.com', 1, '2023-05-11 05:19:48', '2023-05-11 05:19:48'),
                                                                                                                    (5, 'Hà Nội', '0378852369', 'Kho Hà Nội', 'khohanoi@gmail.com', 1, '2023-05-11 05:21:01', '2023-05-11 05:21:01'),
                                                                                                                    (6, 'Lào Cai', '0378789456', 'Kho Lào Cai', 'kholaocai@gmail.com', 1, '2023-05-11 05:21:28', '2023-05-11 05:21:28');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
