-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: catalogservicedb
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
-- Table structure for table `banners`
--

DROP TABLE IF EXISTS `banners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banners` (
  `banner_id` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `banner_title` text NOT NULL,
  `img_url` text,
  `navigation` text,
  PRIMARY KEY (`banner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banners`
--

LOCK TABLES `banners` WRITE;
/*!40000 ALTER TABLE `banners` DISABLE KEYS */;
INSERT INTO `banners` VALUES ('4aaddb3f-0cbc-4219-abec-af8093caaebd','2022-11-29 04:27:56','ACTIVE','2022-11-29 04:27:56','Book Festival','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669696074521_5653934.jpg','PRODUCER'),('6ae23d77-8e6d-4401-9968-795f1b4cb339','2022-11-29 03:54:32','ACTIVE','2022-11-29 03:54:32','Love','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669694071143_d35016bd61465740a09d4ca983aac0f236847552.webp','PRODUCER'),('809f923d-0d54-41d7-91a3-cbc3ee89f9fc','2022-11-29 03:54:17','ACTIVE','2022-11-29 03:54:17','Những cuốn sách cổ','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669694055890_book-1659717__480.jpg','PRODUCER'),('86d57360-623f-49ba-875f-651e1d12f317','2022-11-29 03:52:54','ACTIVE','2022-11-29 03:52:54','Blood and Bones','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669693972981_Blood-and-Bones-Pre-order-post-1920x1080.png','PRODUCER');
/*!40000 ALTER TABLE `banners` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-01 21:42:10
