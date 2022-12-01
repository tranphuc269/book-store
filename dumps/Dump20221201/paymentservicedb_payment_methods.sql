-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: paymentservicedb
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `payment_methods`
--

DROP TABLE IF EXISTS `payment_methods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_methods` (
  `payment_id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `payment_description` text,
  `payment_name` text NOT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_methods`
--

LOCK TABLES `payment_methods` WRITE;
/*!40000 ALTER TABLE `payment_methods` DISABLE KEYS */;
INSERT INTO `payment_methods` VALUES ('3e9bb631-4333-4a5c-9302-a92ca35c331b',NULL,'COD - Cash On Delivery, nghĩa là Thanh toán khi nhận hàng. Khi chọn phương thức thanh toán này, quý khách hàng sẽ trả tiền mặt cho nhân viên giao hàng ngay khi nhận được đơn hàng của mình.','Thanh toán khi nhận hàng(COD)','COD'),('6d7ea05b-cd52-441a-9ffa-d5ee28a539fb',NULL,'Cổng thanh toán VNPAY-QR là cổng trung gian kết nối các đơn vị kinh doanh với ngân hàng, cho phép khách hàng sử dụng thẻ/tài khoản ngân hàng, công nghệ thanh toán bằng mã QR (QR Code) trên ứng dụng Mobile Banking để thanh toán các giao dịch.','Thanh toán bằng VNPay','VNPAY'),('f58237a1-0613-4618-8beb-e6b740822cea',NULL,'Thanh toán bằng Chuyển khoản ngân hàng: Là hình thức thanh toán cho phép Người mua thanh toán bằng thẻ ATM nội địa hoặc chuyển khoản trực tiếp tại ngân hàng. Chỉ áp dụng cho một số sản phẩm nhất định','Thanh toán chuyển khoản ngân hàng','BANKING');
/*!40000 ALTER TABLE `payment_methods` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-01 21:42:11
