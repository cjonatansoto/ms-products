-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.33 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for products
CREATE DATABASE IF NOT EXISTS `products` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `products`;

-- Dumping structure for table products.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table products.products: ~47 rows (approximately)
INSERT INTO `products` (`id`, `name`, `price`, `created_at`, `updated_at`, `deleted_at`) VALUES
	(1, 'Teclado', 75.25, '2024-02-27 15:50:28', '2024-02-27 15:50:28', NULL),
	(2, 'Mouse', 150.00, '2024-02-27 15:50:42', '2024-02-27 15:50:42', NULL),
	(3, 'Monitor', 150.00, '2024-02-27 15:50:48', '2024-02-27 15:50:48', NULL),
	(4, 'Audífonos', 50.00, '2024-02-27 15:50:48', '2024-02-27 15:50:48', NULL),
	(5, 'Laptop', 1000.00, '2024-02-27 15:50:49', '2024-02-27 15:50:49', NULL),
	(6, 'Smartphone', 800.00, '2024-02-27 15:50:58', '2024-02-27 15:50:58', NULL),
	(7, 'Tablet', 300.00, '2024-02-27 15:51:03', '2024-02-27 15:51:03', NULL),
	(8, 'Impresora', 200.00, '2024-02-27 15:51:08', '2024-02-27 15:51:08', NULL),
	(9, 'Altavoces', 150.00, '2024-02-27 15:51:13', '2024-02-27 15:51:13', NULL),
	(10, 'Cámara', 400.00, '2024-02-27 15:51:18', '2024-02-27 15:51:18', NULL),
	(11, 'Televisor', 700.00, '2024-02-27 15:51:23', '2024-02-27 15:51:23', NULL),
	(12, 'Router', 80.00, '2024-02-27 15:51:28', '2024-02-27 15:51:28', NULL),
	(13, 'Reproductor Blu-ray', 180.00, '2024-02-27 15:51:33', '2024-02-27 15:51:33', NULL),
	(14, 'Teclado inalámbrico', 60.00, '2024-02-27 15:51:38', '2024-02-27 15:51:38', NULL),
	(15, 'Mouse inalámbrico', 80.00, '2024-02-27 15:51:43', '2024-02-27 15:51:43', NULL),
	(16, 'Webcam', 70.00, '2024-02-27 15:51:48', '2024-02-27 15:51:48', NULL),
	(17, 'Tarjeta de video', 250.00, '2024-02-27 15:51:52', '2024-02-27 15:51:52', NULL),
	(18, 'Memoria RAM', 120.00, '2024-02-27 15:51:57', '2024-02-27 15:51:57', NULL),
	(19, 'Disco duro externo', 150.00, '2024-02-27 15:52:02', '2024-02-27 15:52:02', NULL),
	(20, 'Tarjeta madre', 350.00, '2024-02-27 15:52:07', '2024-02-27 15:52:07', NULL),
	(21, 'Procesador', 300.00, '2024-02-27 15:52:12', '2024-02-27 15:52:12', NULL),
	(22, 'Gabinete para PC', 80.00, '2024-02-27 15:52:17', '2024-02-27 15:52:17', NULL),
	(23, 'Fuente de poder', 100.00, '2024-02-27 15:52:22', '2024-02-27 15:52:22', NULL),
	(24, 'Router inalámbrico', 50.00, '2024-02-27 15:52:27', '2024-02-27 15:52:27', NULL),
	(25, 'Adaptador Wi-Fi USB', 30.00, '2024-02-27 15:52:32', '2024-02-27 15:52:32', NULL),
	(26, 'Cargador portátil', 40.00, '2024-02-27 15:52:37', '2024-02-27 15:52:37', NULL),
	(27, 'Batería de repuesto', 50.00, '2024-02-27 15:52:42', '2024-02-27 15:52:42', NULL),
	(28, 'Mochila para laptop', 40.00, '2024-02-27 15:52:47', '2024-02-27 15:52:47', NULL),
	(29, 'Estuche para tablet', 20.00, '2024-02-27 15:52:51', '2024-02-27 15:52:51', NULL),
	(30, 'Cable HDMI', 10.00, '2024-02-27 15:52:56', '2024-02-27 15:52:56', NULL),
	(31, 'Adaptador de corriente', 20.00, '2024-02-27 15:53:01', '2024-02-27 15:53:01', NULL),
	(32, 'Soporte para monitor', 30.00, '2024-02-27 15:53:06', '2024-02-27 15:53:06', NULL),
	(33, 'Base para laptop', 25.00, '2024-02-27 15:53:11', '2024-02-27 15:53:11', NULL),
	(34, 'Teclado numérico', 15.00, '2024-02-27 15:53:16', '2024-02-27 15:53:16', NULL),
	(35, 'Mouse ergonómico', 35.00, '2024-02-27 15:53:21', '2024-02-27 15:53:21', NULL),
	(36, 'Auriculares con micrófono', 50.00, '2024-02-27 15:53:26', '2024-02-27 15:53:26', NULL),
	(37, 'Control remoto universal', 20.00, '2024-02-27 15:53:31', '2024-02-27 15:53:31', NULL),
	(38, 'Base para smartphone', 15.00, '2024-02-27 15:53:36', '2024-02-27 15:53:36', NULL),
	(39, 'Adaptador de audio Bluetooth', 25.00, '2024-02-27 15:53:41', '2024-02-27 15:53:41', NULL),
	(40, 'Lector de tarjetas de memoria', 15.00, '2024-02-27 15:53:46', '2024-02-27 15:53:46', NULL),
	(41, 'Cable USB-C', 10.00, '2024-02-27 15:53:51', '2024-02-27 15:53:51', NULL),
	(42, 'Cable Lightning', 10.00, '2024-02-27 15:53:55', '2024-02-27 15:53:55', NULL),
	(43, 'Cable VGA', 10.00, '2024-02-27 15:54:00', '2024-02-27 15:54:00', NULL),
	(44, 'Cable DisplayPort', 10.00, '2024-02-27 15:54:05', '2024-02-27 15:54:05', NULL),
	(45, 'Cable de red Ethernet', 10.00, '2024-02-27 15:54:10', '2024-02-27 15:54:10', NULL),
	(46, 'Bolsa para laptop', 25.00, '2024-02-27 15:54:15', '2024-02-27 15:54:15', NULL),
	(47, 'Almohadilla para mouse', 15.00, '2024-02-27 15:54:20', '2024-02-27 15:54:20', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
