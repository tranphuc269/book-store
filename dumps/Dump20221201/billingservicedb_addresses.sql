-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: billingservicedb
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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `address_id` varchar(255) NOT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `district` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `province` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES ('5b67abe2-6bd6-463b-8a28-bf4aaaabf476','2022-10-04 04:33:08','2022-10-04 04:33:08','Hà Nội','147 Tân Mai','Hoàng Mai','0337200712','Tân Mai','145ade0c-80a3-4a1f-9e26-1115853c3462','Lê Thị Nhài'),('6dab7c38-3a04-42be-b88e-8a87eafffa7d','2022-09-26 10:32:47','2022-09-26 10:32:47','Hà Nội','147 Tân Mai','Hoàng Mai','0383916526','Tân Mai','febaacee-8e3a-4763-a9fd-58af29bc3124','Trần Văn Phúc'),('74c2b8ec-1641-4178-9ee9-ca8b230b7a6d','2022-09-26 10:32:47','2022-10-04 04:39:40','Hà Nội','147 Tân Mai','Hoàng Mai','0383916526','Tân Mai','145ade0c-80a3-4a1f-9e26-1115853c3462','Trần Văn Phúc'),('888b5fb4-acc4-4ec2-8a71-884fe910bb4b','2022-09-26 10:26:15','2022-09-26 10:26:15','Hà Nội','147 Tân Mai','Hoàng Mai','0383916526','Tân Mai','febaacee-8e3a-4763-a9fd-58af29bc3124','Trần Văn Phúc');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-01 21:42:09
