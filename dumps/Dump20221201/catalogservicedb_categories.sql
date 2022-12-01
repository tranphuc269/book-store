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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` varchar(255) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `categoryId` varchar(255) DEFAULT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES ('12e5d636-388f-4d87-bd8e-b4db43bf5685','2022-11-29 04:57:56','ACTIVE','2022-11-29 04:57:56','12e5d636-388f-4d87-bd8e-b4db43bf5685','Truyện trinh thám','Truyện trinh thám hay tiểu thuyết trinh thám là một tiểu loại của tiểu thuyết phiêu lưu. Bản thân tên gọi thể loại đã làm nổi bật một vài đặc điểm riêng của nó. Nó nói lên nghề nghiệp của nhân vật chính.','https://img.freepik.com/free-vector/detective-story-tour-banner-with-woman-sleuth_107791-6528.jpg?w=2000'),('16b9a263-55b2-4e83-8680-be8db5879fbb','2022-11-29 04:46:47','ACTIVE','2022-11-29 04:46:47','16b9a263-55b2-4e83-8680-be8db5879fbb','Thế hệ GENZ','Trái với suy nghĩ của nhiều người về một thế hệ Gen Z \"mãi không chịu lớn\", Gen Z là những cô cậu hiện diện trong cộng đồng của chúng ta mỗi ...','https://jobsgo.vn/blog/wp-content/uploads/2022/04/genz-la-gi-2.jpg'),('8c05d57c-ae2f-4cf7-9399-d502bd970971','2022-11-29 04:38:35','ACTIVE','2022-11-29 04:38:35','8c05d57c-ae2f-4cf7-9399-d502bd970971','Truyện tình cảm','Tiểu thuyết lãng mạn hay tiểu thuyết tình cảm là một thuật ngữ ...','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669696712607_love-story-template-banner-poster-holiday-lettering-love-story-template-banner-poster-holiday-lettering-greeting-108805261.jpg'),('d676bdcc-3109-42c2-973a-00f5619326b0','2022-11-29 04:46:06','ACTIVE','2022-11-29 04:46:06','d676bdcc-3109-42c2-973a-00f5619326b0','Sách lịch sử','Lịch sử Việt Nam qua nhiều năm dựng xây và giữ gìn bờ cõi đã được các nhà Sử học tái hiện qua những cuốn sách về lịch sử hay','https://i.ytimg.com/vi/-AgWXOh9Jpg/maxresdefault.jpg');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
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
