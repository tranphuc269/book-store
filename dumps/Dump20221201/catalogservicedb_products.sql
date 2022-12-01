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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `available_item_count` int DEFAULT NULL,
  `product_description` text,
  `images` text,
  `price` double NOT NULL,
  `product_name` text NOT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  `producer_id` varchar(255) DEFAULT NULL,
  `author` text,
  `dimension` text,
  `page_count` int DEFAULT NULL,
  `weight` text,
  PRIMARY KEY (`product_id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FKponvuhms7f447e47sn69rs5gf` (`producer_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKponvuhms7f447e47sn69rs5gf` FOREIGN KEY (`producer_id`) REFERENCES `producers` (`producer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('80e01f06-347e-4f86-a913-1b4a770d5b85','2022-11-29 07:09:33',NULL,'2022-11-29 07:09:33',12,'<div id=\"product_tabs_description_contents\">\n		<div id=\"desc_content\" class=\"std\">\n		    <p style=\"text-align: justify;\">Ủa? GenZ???</p>\n<p style=\"text-align: justify;\">- Cuốn sách này dành cho thế hệ GenZ</p>\n<p style=\"text-align: justify;\">- Vẽ ra chân dung chân thực nhất về cá tính GenZ trong thời đại công nghệ số</p>\n<p style=\"text-align: justify;\">- Giải mã ngôn ngữ GenZ, khám phá những hot trend hàng đầu và kết nối những con người tiên phong tạo nên xu hướng&nbsp;</p>\n<p style=\"text-align: justify;\">&nbsp;</p>\n<p style=\"text-align: justify;\">Ủa / động từ/&nbsp;</p>\n<p style=\"text-align: justify;\">Ủa theo nghĩa đen là cách nói thể hiện sự bất ngờ, ngỡ ngàng, không tin vào mắt mình.</p>\n<p style=\"text-align: justify;\">Còn đối với GenZ, nó đôi khi chỉ là một cách để chào hỏi.</p>\n<p style=\"text-align: justify;\">Nói tóm lại “Ủa? GenZ???” là cuốn sách như một lời chào, mời bạn đến với thế giới có tất tần tật những hành động cộp mác GenZ như:</p>\n<p style=\"text-align: justify;\">- Khi GenZ gặp bế tắc thường không tìm cách giải quyết bế tắc đó mà họ lựa chọn ngồi 18 tiếng đồng hồ để viết status, đăng story, làm meme về việc bế tắc các vấn đề đang bế tắc.</p>\n<p style=\"text-align: justify;\">- Nhờ bạn thân quay story tạo 7749 kiểu canh góc mặt nhưng đăng lên lại kèm cap quay lén ngại ghê.</p>\n<p style=\"text-align: justify;\">- Khi mưa có thể nghỉ làm, nhưng vẫn có thể đi bộ xa nhà 100m nhận đơn shopee để khum lộ số tiền đã bỏ ra.</p>\n<p style=\"text-align: justify;\">- Không đọ lương mà đọ số người xem story.</p>\n<p style=\"text-align: justify;\">- Chấm công có thể quên nhưng luôn nhớ chấm tinh dầu mụn….</p>\n<p style=\"text-align: justify;\">Gen Z đang là thế lực trẻ trung, năng động với những năng lượng khác biệt. Với bộ não sáng tạo không ngừng nghỉ, các bạn liên tục cho ra đời ra những điều mới lạ mang đậm dấu ấn.</p>\n<p style=\"text-align: justify;\">Nào là hệ ngôn ngữ riêng mà nếu không tra từ điển Gen Z chắc chắn các thế hệ \"người lớn\" sẽ đau đầu chóng mặt ngay. Hay đến những thói quen kỳ lạ cộp mác Gen Z khiến thế hệ Gen khác không thể nào hiểu nổi. Tất cả những điều trên đã tạo thành một cuốn sách khắc họa thế hệ trẻ vô cùng cá tính và thú vị. Nếu bạn có vô số những dấu hỏi chấm về GenZ? Với “Ủa? GenZ???” bạn sẽ không còn cảm thấy lạ lẫm hay rơi vào những tình huống dở khóc dở cười khi tiếp cận với một GenZ đầy khác biệt.&nbsp;</p>\n<p style=\"text-align: justify;\">Đặc biệt “Ủa? Genz???” được viết bởi admin fanpage Đài tiếng nói GenZ nơi chuyên chia sẻ những câu chuyện đời thường, đầy hài hước của các bạn trẻ gây bão mạng xã hội, mà bất kì dân chơi hệ “hóng hớt” nào nhất định không nên bỏ qua cuốn sách này.&nbsp;</p>		    <div class=\"clear\"></div>\n		</div>\n	    </div>','[https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669705771441_ua_genz_1_2022_08_04_16_14_55.jpg, https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669705772384_z2574251009417_69568fbabaa15d7fe326fb959daaf02e.jpg]',63000,'Ủa? GenZ???','16b9a263-55b2-4e83-8680-be8db5879fbb','dbeb29bc-1ad3-4d36-a0a9-8869c4866b4e','Đài Tiếng Nói GenZ',NULL,120,NULL),('87a444a2-cfa7-418b-ad4e-46996b0bde0d','2022-11-29 08:00:08',NULL,'2022-11-29 08:00:08',20,'<div id=\"desc_content\" class=\"std\">\n		    <p style=\"text-align: justify;\">Cuộc đời ngày ngày nói yêu mình.</p>\n\n<p style=\"text-align: justify;\">Xong cuộc đời lại đủ thứ phức tạp và bất công với mình.<br>\nVậy là cuộc đời ghét mình rồi!</p>\n\n<p style=\"text-align: justify;\">Thôi nào!</p>\n\n<p style=\"text-align: justify;\">Thả lỏng và tận hưởng sự vui vẻ đi. Vì chẳng phải cuộc đời đang ghét bạn đâu, mà chính bạn bạn đang loay hoay với những mệt nhọc ở trên đời. Ví dụ như nay đã là deadline mà bỗng bị rớt mạng, sáng nay đi làm quên đem theo ví, hay đồng nghiệp ở công ty nói xấu mình,...</p>\n\n<p style=\"text-align: justify;\">Nếu kể ra thì sẽ có ti tỉ thứ không theo ý mình mỗi ngày. Không nói đến những chuyện bực tức khác, những nỗi buồn chúng ta không làm chủ được, những điều vô tình đến khiến ta quạu cọ và xấu xí. Rồi khi chúng ta cứ để chúng trong lòng sự phiền muộn không biết kéo dài đến bao giờ mới kết thúc.</p>\n\n<p style=\"text-align: justify;\">Cho nên, thay vì để tâm đến những chuyện khiến mình không vui, hãy lựa chọn vui vẻ, bạn sẽ thấy cuộc đời trở nên tuyệt vời hơn rất nhiều. Và để cân bằng những cảm xúc ấy “Vui vẻ không quạu nha” chính là một món quà để lan tỏa và nhân lên niềm vui, một cuốn sách mà ai cũng cần phải có để thêm yêu những điều nhỏ xíu đáng yêu xung quanh mình.</p>\n\n<p style=\"text-align: justify;\">Vui vẻ không quạu nha - Cuốn sách không chỉ mặn mà đúng như tên gọi của nó mà còn bắt kịp các xu hướng được quan tâm từ fanpage nổi tiếng “Ở đây zui nè” sẽ giúp bạn có những tràng cười vui vẻ không ngớt, có cái nhìn khoan dung cởi mở hơn, nhìn nhận những xui xẻo vấp phải bỗng trở nên nhỏ bé dưới góc độ dí dỏm và hài hước.</p>\n\n<p style=\"text-align: justify;\">Vui vẻ không quạu nha - xin được gửi đến những bạn trẻ đang dễ giận dữ, cau có ngoài thế giới kia, những ai đang buồn phiền về rắc rối nào đó, “trái tim” nhỏ bé này còn phải dành cho niềm vui, đừng để bực bội, dỗi hờn từ những điều không đáng chiếm hết chỗ.</p>\n\n<p style=\"text-align: justify;\">Và hãy hét to với Thế giới rằng “Mình là một người tràn đầy năng lượng,” luôn sẵn sàng để hạnh phúc hơn.</p>		    <div class=\"clear\"></div>\n		</div>','[https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669708807032_image_195509_1_33312_2.jpg]',55000,'Vui Vẻ Không Quạu Nha (Tái Bản 2021)','16b9a263-55b2-4e83-8680-be8db5879fbb','805a1bda-e470-45cc-8dc9-38e7ea0c23eb','NVCS','12 x 10 cm',280,'300(gr)'),('b6665ebe-0308-4a41-9665-0e8308e9395b','2022-11-29 07:10:54',NULL,'2022-11-29 07:10:54',20,'<div id=\"desc_content\" class=\"std\">\n		    <p style=\"text-align: justify;\"><strong>Lì Quá Để Nói Quài</strong></p>\n\n<p style=\"text-align: justify;\">Cuộc sống mà, ở đâu cũng có những đứa lì lợm thích làm theo ý mình.</p>\n\n<p style=\"text-align: justify;\"><em>7h đi chơi mà 9h nằng nặc đòi về.</em></p>\n\n<p style=\"text-align: justify;\"><em>Đợi nó cúng story rồi mới được ăn.</em></p>\n\n<p style=\"text-align: justify;\">Và ti tỉ những hành động khác khiến ta quạu cọ, khó ở.</p>\n\n<p style=\"text-align: justify;\">Thay vì dồn nén, hãy mở ngay \"Lì Quá Để Nói Quài\" - cuốn sách nhỏ xinh định nghĩa những điều không giống ai nhưng đọc xong lại khiến mấy đứa lì cảm thấy “nhột nhột”.</p>\n\n<p style=\"text-align: justify;\"><em>Bận rộn (tính từ): Tôi có vài đơn hàng, tuy không nhiều nhưng xuống lấy cũng mất cả ngày.</em></p>\n\n<p style=\"text-align: justify;\"><em>Não cá vàng (danh từ): Mua đồ 6 triệu mới nhớ lương 5 triệu.</em></p>\n\n<p style=\"text-align: justify;\"><em>Người chơi hệ cao su (cụm danh từ): Đợi xíu sắp tới là khi nào tới?</em></p>\n\n<p style=\"text-align: justify;\">Có những định nghĩa đọc xong phải chờ một lúc mới “nhảy số” rồi tủm tỉm cười.</p>\n\n<p style=\"text-align: justify;\">Có những định nghĩa đọc xong lại thấy “tưng tức cái ngực”.</p>\n\n<p style=\"text-align: justify;\">Nếu bạn đang không định nghĩa nổi những hành động của mình và những người xung quanh, cuốn sách này chính là dành cho bạn.</p>\n\n<p style=\"text-align: justify;\">Nếu bạn có đứa bạn nhắc mãi nó vẫn cứ “lì” ra, cuốn sách này dành cho bạn của bạn.</p>\n\n<p style=\"text-align: justify;\">Và bạn yên tâm, \"Lì Quá Để Nói Quài\" luôn sẵn sàng nói hộ tiếng lòng của bạn về những vấn đề “khó đỡ” trong cuộc sống.</p>		    <div class=\"clear\"></div>\n		</div>','[https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669705853037_9786043556797.jpg]',62000,'Lì Quá Để Nói Quài','16b9a263-55b2-4e83-8680-be8db5879fbb','dbeb29bc-1ad3-4d36-a0a9-8869c4866b4e','Anh Cầm Fact',NULL,130,NULL),('f43504c0-d2cc-4dac-98e8-8efb4cb2411d','2022-11-29 07:13:14',NULL,'2022-11-29 07:13:14',20,'<div id=\"product_tabs_description_contents\">\n		<div id=\"desc_content\" class=\"std\">\n		    <p style=\"text-align: justify;\"><strong>Đời Có Mấy Tý, Sao Phải Nghĩ (Tái Bản 2022)</strong></p>\n<p style=\"text-align: justify;\">Nhà Văn Cục Súc được biết tới là một blogger sở hữu những <span>bài post nghìn like</span> cùng nhiều câu nói hot trend có phần cục súc, hài hước và thú vị được đăng tải trên fanpage cùng tên với hơn <span>1 triệu lượt theo dõi.</span></p>\n<p style=\"text-align: justify;\">Lấy phong cách hài “mặn” làm chủ đạo, nội dung của Nhà Văn Cục Súc chủ yếu hướng đến giới trẻ và những sự kiện nổi bật thu hút sự quan tâm lớn từ cộng đồng mạng.</p>\n<p style=\"text-align: justify;\"><em>“Lá cây cần quang hợp</em></p>\n<p style=\"text-align: justify;\"><em>còn mình thì cần quan tâm” </em></p>\n<p style=\"text-align: justify;\">Cuộc sống mà, lúc nào cũng đầy rẫy những chuyện phức tạp không ngờ đến. Một vấn đề tưởng như nhỏ xíu cũng có thể khiến bạn trở nên quạu cọ, cục súc.</p>\n<p style=\"text-align: justify;\">Cục súc không phải vì tỏ tình bị từ chối, mà vì đành bái bị thúi heo cơ.</p>\n<p style=\"text-align: justify;\">Cục súc không phải vì phải chờ đợi một ai, mà vì chờ một chiếc điện thoại mới.</p>\n<p style=\"text-align: justify;\">Thay vì dồn nén, hãy tìm cách giải tỏa bằng “Đời có mấy tý, sao phải nghĩ” - cuốn sách nhỏ với những trích dẫn hot trend hài hước, “mặn mà” tạo thành xu hướng trên mạng xã hội.</p>\n<p style=\"text-align: justify;\">Vì đôi lúc cục súc một chút cũng có sao, cục súc để cuộc đời bớt lao đao thôi mà.</p>		    <div class=\"clear\"></div>\n		</div>\n	    </div>','[https://bookstore-service.s3.ap-southeast-1.amazonaws.com/1669705992782_9786043593181_1.jpg]',63000,'Đời Có Mấy Tý, Sao Phải Nghĩ (Tái Bản 2022)','16b9a263-55b2-4e83-8680-be8db5879fbb','805a1bda-e470-45cc-8dc9-38e7ea0c23eb','NVCS',NULL,180,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
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
