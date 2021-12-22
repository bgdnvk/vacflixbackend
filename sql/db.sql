CREATE DATABASE  IF NOT EXISTS `vacflix` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `vacflix`;

-- Host: localhost    Database: vacflix

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP TABLE IF EXISTS `youtube_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `youtube_channel` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `created` datetime DEFAULT NULL,
                                   `updated` datetime DEFAULT NULL,
                                   `created_by` varchar(45) DEFAULT NULL,
                                   `updated_by` varchar(45) DEFAULT NULL,
                                   `name` varchar(255) DEFAULT NULL,
                                   `channel_id` varchar(255) NOT NULL,
                                   `subscription_count` int(11) DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `channel_id_UNIQUE` (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=299 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `youtube_video_info`
--

DROP TABLE IF EXISTS `youtube_video_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `youtube_video_info` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `created` datetime DEFAULT NULL,
                                      `updated` datetime DEFAULT NULL,
                                      `created_by` varchar(45) DEFAULT NULL,
                                      `updated_by` varchar(45) DEFAULT NULL,
                                      `video_id` varchar(45) NOT NULL,
                                      `title` text DEFAULT NULL,
                                      `thumbnail_url` varchar(255) DEFAULT NULL,
                                      `description` text DEFAULT NULL,
                                      `published_date` datetime DEFAULT NULL,
                                      `definition` varchar(45) DEFAULT NULL,
                                      `duration` varchar(45) DEFAULT NULL,
                                      `caption` varchar(45) DEFAULT NULL,
                                      `projection` varchar(45) DEFAULT NULL,
                                      `channel_id` varchar(255) NOT NULL,
                                      `video_stat_id` varchar(255) NOT NULL,
                                      `country_restricted` varchar(255) DEFAULT NULL,
                                      `keyword` varchar(255) DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `video_id_UNIQUE` (`video_id`)
) ENGINE=InnoDB AUTO_INCREMENT=564 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `youtube_video_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `youtube_video_stat` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `created` datetime DEFAULT NULL,
                                      `updated` datetime DEFAULT NULL,
                                      `created_by` varchar(45) DEFAULT NULL,
                                      `updated_by` varchar(45) DEFAULT NULL,
                                      `like_count` int(11) DEFAULT NULL,
                                      `dislike_count` int(11) DEFAULT NULL,
                                      `view_count` int(11) DEFAULT NULL,
                                      `favourite_count` int(11) DEFAULT NULL,
                                      `comment_count` int(11) DEFAULT NULL,
                                      `video_id` varchar(45) NOT NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `video_id_UNIQUE` (`video_id`)
) ENGINE=InnoDB AUTO_INCREMENT=564 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

--
-- Table structure for table `youtube_video_stat`
--

DROP TABLE IF EXISTS `youtube_video_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `youtube_video_stat` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `created` datetime DEFAULT NULL,
                                      `updated` datetime DEFAULT NULL,
                                      `created_by` varchar(45) DEFAULT NULL,
                                      `updated_by` varchar(45) DEFAULT NULL,
                                      `like_count` int(11) DEFAULT NULL,
                                      `dislike_count` int(11) DEFAULT NULL,
                                      `view_count` int(11) DEFAULT NULL,
                                      `favourite_count` int(11) DEFAULT NULL,
                                      `comment_count` int(11) DEFAULT NULL,
                                      `video_id` varchar(45) NOT NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `video_id_UNIQUE` (`video_id`)
) ENGINE=InnoDB AUTO_INCREMENT=564 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
