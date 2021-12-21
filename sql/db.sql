CREATE DATABASE  IF NOT EXISTS `vacflix` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `remote_api_manager`;

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

--
-- Table structure for table `youtube_video_info`
--

DROP TABLE IF EXISTS `youtube_video_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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

