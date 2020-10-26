CREATE DATABASE  IF NOT EXISTS `movies` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `movies`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: movies
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'musical'),(2,'comedy'),(3,'drama'),(4,'animated'),(5,'fantasy');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moviegenres`
--

DROP TABLE IF EXISTS `moviegenres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moviegenres` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Genres_id` int NOT NULL,
  `Movies_id` int NOT NULL,
  PRIMARY KEY (`id`,`Genres_id`,`Movies_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_MovieGenres_Genres_idx` (`Genres_id`),
  KEY `fk_MovieGenres_Movies1_idx` (`Movies_id`),
  CONSTRAINT `fk_MovieGenres_Genres` FOREIGN KEY (`Genres_id`) REFERENCES `genres` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_MovieGenres_Movies1` FOREIGN KEY (`Movies_id`) REFERENCES `movies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviegenres`
--

LOCK TABLES `moviegenres` WRITE;
/*!40000 ALTER TABLE `moviegenres` DISABLE KEYS */;
INSERT INTO `moviegenres` VALUES (1,3,1),(2,5,1),(3,2,2),(4,3,2),(5,1,3),(6,4,3),(7,5,3),(8,2,4),(9,3,4),(10,2,5),(11,3,5);
/*!40000 ALTER TABLE `moviegenres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `year` year DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'The Green Mile',1999),(2,'Forrest Gump',1994),(3,'The Little Mermaid',1994),(4,'Moonlight Kingdom',2012),(5,'The Grand Budapest Hotel',2014);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moviestaff`
--

DROP TABLE IF EXISTS `moviestaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moviestaff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Movies_id` int NOT NULL,
  `Staff_id` int NOT NULL,
  PRIMARY KEY (`id`,`Movies_id`,`Staff_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_MovieStaff_Movies1_idx` (`Movies_id`),
  KEY `fk_MovieStaff_Staff1_idx` (`Staff_id`),
  CONSTRAINT `fk_MovieStaff_Movies1` FOREIGN KEY (`Movies_id`) REFERENCES `movies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_MovieStaff_Staff1` FOREIGN KEY (`Staff_id`) REFERENCES `staff` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviestaff`
--

LOCK TABLES `moviestaff` WRITE;
/*!40000 ALTER TABLE `moviestaff` DISABLE KEYS */;
INSERT INTO `moviestaff` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,2,5),(7,2,6),(8,3,7),(9,3,8),(10,3,9),(11,1,10),(12,2,11),(13,3,12),(14,4,13),(15,4,14),(16,4,15),(17,4,16),(18,4,17),(19,5,13),(20,5,17),(21,5,18),(22,5,19);
/*!40000 ALTER TABLE `moviestaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `position` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Tom Hanks','actor'),(2,'Michael Clarke Duncan','actor'),(3,'David Morse','actor'),(4,'Bonnie Hunt','actor'),(5,'Robin Wright','actor'),(6,'Mykelti Williamson','actor'),(7,'Jodi Benson','actor'),(8,'Pat Carroll','actor'),(9,'Rene Auberjonois','actor'),(10,'Frank Darabont','director'),(11,'Robert Zemeckis','director'),(12,'John Musker','director'),(13,'Wes Anderson','director'),(14,'Ralph Fiennes','actor'),(15,'Tony Revolori','actor'),(16,'Adrien Brody','actor'),(17,'Edward Norton','actor'),(18,'Jared Gilman','actor'),(19,'Kara Hayward','actor');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-15 16:46:15
