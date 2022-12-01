-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: userservicedb
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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` varchar(255) NOT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('145ade0c-80a3-4a1f-9e26-1115853c3462','2022-10-04 03:53:47','2022-10-04 04:04:56','phuc260900@gmail.com','Trần Vănn','Phúc','$2a$10$GYR/Q.0YAaUIr2zirMw/WeHwurmsJNtqAU9EkIWiqwomKZfo2QNb6','tugjabg1',0),('asdasdsa-6727-4229-a4ab-zxczxcxczxcc',NULL,NULL,'john.doe@gmail.com','Cores','DevD','$2a$10$2XWkMz42.EApOBnx7nJaSupInwvBfPCGb5HZwWM.2RsC92joeAQzq','devd.cores',0),('c96a4ac9-b126-4c8c-b30b-315e44c8fbc7','2022-09-25 12:02:50','2022-09-25 12:02:50','phuc260900@gmail.com','Tran','Phuc','$2a$10$LfcbhC2/UXseb8MqX0pbsO5ULDOe4HlFXTdvKprKEX.cma3f5OZ7m','tugjabg2',0),('cvbserte-6727-4229-a4ab-vbnbvvnvbnvb',NULL,NULL,'rale.reddy@gmail.com','Cores','DevD','$2a$10$2XWkMz42.EApOBnx7nJaSupInwvBfPCGb5HZwWM.2RsC92joeAQzq','rale.reddy',0),('cvcvbcvb-ba5d-4b92-85be-fggfgtrytyty',NULL,NULL,'reddy.devaraj@gmail.com','Admin','Admin','$2a$10$br7HrUzeQQ0ddR2ogg7L1.aRQ1sGC1rud.mL8VQBEKaMkx1G5zXR6','reddy.devaraj',0),('ddfgdfgh-6727-4229-a4ab-ertdfgfdgdfg',NULL,NULL,'reddy.rale@gmail.com','Cores','DevD','$2a$10$2XWkMz42.EApOBnx7nJaSupInwvBfPCGb5HZwWM.2RsC92joeAQzq','reddy.rale',0),('dfgdfgdf-ba5d-4b92-85be-vbvbvbnvbnjb',NULL,NULL,'devd.bro@gmail.com','Admin','Admin','$2a$10$br7HrUzeQQ0ddR2ogg7L1.aRQ1sGC1rud.mL8VQBEKaMkx1G5zXR6','devd.bro',0),('febaacee-8e3a-4763-a9fd-58af29bc3124','2022-09-25 04:42:37','2022-09-26 04:52:20','meliodasss2000@gmail.com','Trần','Phúc','$2a$10$shL0QR4YHzZm8jspTru9BukGUR/60o4QExaWtFkXhmVxUNAdqdjB2','tugjabg',0),('rertertr-6727-4229-a4ab-erererererer',NULL,NULL,'devaraj.reddy@gmail.com','Cores','DevD','$2a$10$2XWkMz42.EApOBnx7nJaSupInwvBfPCGb5HZwWM.2RsC92joeAQzq','devaraj.reddy',0),('xcvcvbvv-ba5d-4b92-85be-dfgdfgdfgdfg',NULL,NULL,'admin@gmail.com','Admin','Admin','$2a$10$br7HrUzeQQ0ddR2ogg7L1.aRQ1sGC1rud.mL8VQBEKaMkx1G5zXR6','admin.admin',0),('xcvxvcgv-ba5d-4b92-85be-fghfghtryfgh',NULL,NULL,'devd.reddy@gmail.com','Admin','Admin','$2a$10$br7HrUzeQQ0ddR2ogg7L1.aRQ1sGC1rud.mL8VQBEKaMkx1G5zXR6','devd.reddy',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
