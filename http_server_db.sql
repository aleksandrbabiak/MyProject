CREATE DATABASE  IF NOT EXISTS `http_server_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `http_server_db`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: http_server_db
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `global_statistic`
--

DROP TABLE IF EXISTS `global_statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `global_statistic` (
  `idglobal_statistic` int(11) NOT NULL AUTO_INCREMENT,
  `src_ip` varchar(45) NOT NULL,
  `uri` varchar(45) NOT NULL,
  `time_stemp` varchar(45) NOT NULL,
  `sent_bytes` int(11) NOT NULL,
  `received_bytes` int(11) NOT NULL,
  `speed_transmission` int(11) NOT NULL,
  PRIMARY KEY (`idglobal_statistic`)
) ENGINE=InnoDB AUTO_INCREMENT=273 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_statistic`
--

LOCK TABLES `global_statistic` WRITE;
/*!40000 ALTER TABLE `global_statistic` DISABLE KEYS */;
INSERT INTO `global_statistic` VALUES (255,'0:0:0:0:0:0:0:1','/status','Tue Sep 24 12:31:44 EEST 2013',1199,397,1),(256,'0:0:0:0:0:0:0:1','/redirect?url=football.ua','Tue Sep 24 12:32:11 EEST 2013',142,389,1),(257,'0:0:0:0:0:0:0:1','/status','Tue Sep 24 12:32:17 EEST 2013',1478,371,1),(258,'0:0:0:0:0:0:0:1','/redirect?url=google.ru','Tue Sep 24 12:32:52 EEST 2013',138,387,1),(259,'0:0:0:0:0:0:0:1','/status','Tue Sep 24 12:32:58 EEST 2013',1755,371,1),(260,'0:0:0:0:0:0:0:1','/redirect?url=habrahabr.ru','Tue Sep 24 12:33:43 EEST 2013',144,390,1),(261,'0:0:0:0:0:0:0:1','/status','Tue Sep 24 12:33:50 EEST 2013',2088,371,1),(262,'0:0:0:0:0:0:0:1','/hello','Tue Sep 24 12:34:12 EEST 2013',120,370,1),(263,'0:0:0:0:0:0:0:1','/redirect?url=www.google.com.ua','Tue Sep 24 12:34:34 EEST 2013',154,395,1),(264,'0:0:0:0:0:0:0:1','/redirect?url=football.ua','Tue Sep 24 12:34:56 EEST 2013',142,389,1),(265,'0:0:0:0:0:0:0:1','/status','Tue Sep 24 12:37:02 EEST 2013',2652,371,1),(266,'0:0:0:0:0:0:0:1','/redirect?url=www.google.com.ua','Tue Sep 24 12:37:11 EEST 2013',154,395,1),(267,'0:0:0:0:0:0:0:1','/redirect?url=www.google.com.ua','Tue Sep 24 12:37:28 EEST 2013',154,421,1),(268,'0:0:0:0:0:0:0:1','/status','Tue Sep 24 12:38:03 EEST 2013',3091,397,1),(269,'0:0:0:0:0:0:0:1','/hello','Tue Sep 24 12:38:19 EEST 2013',120,370,1),(270,'0:0:0:0:0:0:0:1','/hello','Tue Sep 24 12:38:29 EEST 2013',120,370,1),(271,'0:0:0:0:0:0:0:1','/status','Tue Sep 24 12:38:36 EEST 2013',3480,371,1),(272,'0:0:0:0:0:0:0:1','/redirect?url=google.ru','Tue Sep 24 12:38:43 EEST 2013',138,387,1);
/*!40000 ALTER TABLE `global_statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_count`
--

DROP TABLE IF EXISTS `request_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_count` (
  `ip_id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(45) NOT NULL,
  `ip_count` int(11) NOT NULL,
  `last_query_time` varchar(45) NOT NULL,
  PRIMARY KEY (`ip_id`),
  UNIQUE KEY `ip_UNIQUE` (`ip`),
  UNIQUE KEY `ip_id_UNIQUE` (`ip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_count`
--

LOCK TABLES `request_count` WRITE;
/*!40000 ALTER TABLE `request_count` DISABLE KEYS */;
INSERT INTO `request_count` VALUES (10,'0:0:0:0:0:0:0:1',18,'Tue Sep 24 12:38:43 EEST 2013');
/*!40000 ALTER TABLE `request_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `url_redirect`
--

DROP TABLE IF EXISTS `url_redirect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `url_redirect` (
  `url_id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `url_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`url_id`),
  UNIQUE KEY `url_UNIQUE` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `url_redirect`
--

LOCK TABLES `url_redirect` WRITE;
/*!40000 ALTER TABLE `url_redirect` DISABLE KEYS */;
INSERT INTO `url_redirect` VALUES (3,'http://football.ua',4),(4,'http://google.ru',5),(5,'http://%3cwww.google.com.ua%3e',1),(6,'http://www.google.com.ua',6),(7,'http://habrahabr.ru/',1),(8,'http://habrahabr.ru',1);
/*!40000 ALTER TABLE `url_redirect` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-24 12:41:09
