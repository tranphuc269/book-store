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
-- Table structure for table `producers`
--

DROP TABLE IF EXISTS `producers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producers` (
  `producer_id` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` text NOT NULL,
  `img_url` text,
  `producer_name` text NOT NULL,
  PRIMARY KEY (`producer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producers`
--

LOCK TABLES `producers` WRITE;
/*!40000 ALTER TABLE `producers` DISABLE KEYS */;
INSERT INTO `producers` VALUES ('7fc3714e-5753-4172-be8f-67f7887ef6b4','2022-11-29 07:02:37','ACTIVE','2022-11-29 07:02:37','Nhà xuất bản Kim Đồng là nhà xuất bản chuyên về sách văn học thiếu nhi của Việt Nam được thành lập vào ngày 15 tháng 6 năm 1956 tại Hà Nội','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669705356771_kimdong.png','Nhà xuất bản Kim Đồng'),('805a1bda-e470-45cc-8dc9-38e7ea0c23eb','2022-11-29 06:54:55','ACTIVE','2022-11-29 06:54:55','Nhà xuất bản Trẻ là một đơn vị chuyên xuất bản và phát hành sách nhiều thể loại có trụ sở chính tại Thành phố Hồ Chí Minh','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669704893945_1632678447297-Thiết kế không tên - 2021-09-27T004706.026.png','Nhà xuất bản trẻ'),('94fbc252-afee-486f-89bd-085e7b98518e','2022-11-29 06:58:00','ACTIVE','2022-11-29 06:58:00','Công ty Cổ phần Văn hóa và Truyền thông Nhã Nam, thường gọi tắt là Nhã Nam là doanh nghiệp hoạt động trong lĩnh vực kinh doanh dịch vụ văn hóa và xuất bản phẩm tại Việt Nam','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669705078422_1582344898530-1582276635522-1561977250696-Nhã Nam.jpg','Nhà xuất bản Nhã Nam'),('d6292ab5-fad5-4516-8a83-693e1c80f20d','2022-11-29 06:59:40','ACTIVE','2022-11-29 06:59:40','Nhà xuất bản Giáo dục Việt Nam là một nhà xuất bản trực thuộc Bộ Giáo dục và Đào tạo. Nhà xuất bản Giáo dục Việt Nam có nhiều chi nhánh và công ty con trên toàn quốc','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669705179166_images.png','Nhà xuất bản giáo dục'),('dbeb29bc-1ad3-4d36-a0a9-8869c4866b4e','2022-11-29 07:05:12','ACTIVE','2022-11-29 07:05:12','Là một Nhà xuất bản trẻ, nhưng Dân Trí đã nhanh chóng tham gia thị trường xuất bản, với hàng nghìn đầu sách được ấn hành hàng năm. Nhà xuất bản Dân Trí đã đầu tư vào chất lượng sản phẩm, nâng cao sự phong phú cho các mảng sách khác nhau (sách Pháp luật, sách Thiếu nhi, sách Tham khảo chính trị- văn hóa, sách Đời sống, sách Kỹ năng, sách cho khối Mầm non, khối Tiểu học, Trung học sơ sở, Trung học phổ thông,…. Ngoài hoạt động chuyên môn chính,','https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669705511664_logo_footer.png','Nhà xuất bản Dân Trí');
/*!40000 ALTER TABLE `producers` ENABLE KEYS */;
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
