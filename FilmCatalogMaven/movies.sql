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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'musical'),(2,'comedy'),(3,'drama'),(4,'animated'),(5,'fantasy'),(6,'romantic'),(7,'arthouse'),(8,'psychodelic'),(13,'arthouse1'),(14,' psychodelic1 '),(35,'science fiction'),(36,'action');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
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
-- Table structure for table `movies_genres`
--

DROP TABLE IF EXISTS `movies_genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies_genres` (
  `movieSet_id` int NOT NULL,
  `genreSet_id` int NOT NULL,
  PRIMARY KEY (`movieSet_id`,`genreSet_id`),
  KEY `FKe1gub2tc3029nk75ya6w7jed3` (`genreSet_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies_genres`
--

LOCK TABLES `movies_genres` WRITE;
/*!40000 ALTER TABLE `movies_genres` DISABLE KEYS */;
INSERT INTO `movies_genres` VALUES (1,3),(1,5),(2,2),(2,3),(3,1),(3,4),(3,5),(4,2),(4,3),(5,2),(5,3);
/*!40000 ALTER TABLE `movies_genres` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviestaff`
--

LOCK TABLES `moviestaff` WRITE;
/*!40000 ALTER TABLE `moviestaff` DISABLE KEYS */;
INSERT INTO `moviestaff` VALUES (5,2,1),(6,2,5),(7,2,6),(8,3,7),(9,3,8),(10,3,9),(12,2,11),(13,3,12),(14,4,13),(15,4,14),(16,4,15),(17,4,16),(18,4,17),(71,5,13),(72,5,17),(73,5,18),(74,5,19),(75,1,10),(76,1,1),(77,1,2),(78,1,3),(79,1,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Tom Hanks','actor'),(2,'Michael Clarke Duncan','actor'),(3,'David Morse','actor'),(4,'Bonnie Hunt','actor'),(5,'Robin Wright','actor'),(6,'Mykelti Williamson','actor'),(7,'Jodi Benson','actor'),(8,'Pat Carroll','actor'),(9,'Rene Auberjonois','actor'),(10,'Frank Darabont','director'),(11,'Robert Zemeckis','director'),(12,'John Musker','director'),(13,'Wes Anderson','director'),(14,'Ralph Fiennes','actor'),(15,'Tony Revolori','actor'),(16,'Adrien Brody','actor'),(17,'Edward Norton','actor'),(18,'Jared Gilman','actor'),(19,'Kara Hayward','actor'),(20,'adfslkj','director'),(21,'Yuri Chulyukin','director'),(22,'Nadezhda Rumyantseva','actor'),(23,'Nikolai Rybnikov','actor'),(25,'Svetlana Baskova','director'),(26,'Sergey Pakhomov','actor'),(27,'Vladimir Epifantsev','actor'),(29,'Svetlana Baskova1','director'),(30,'Sergey Pakhomov1','actor'),(31,' Vladimir Epifantsev1 ','actor'),(110,'The Wachowkis','director'),(111,'Keanu Reeves','actor'),(112,'Laurence Fishburne','actor'),(113,'Carrie-Anne Moss','actor');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'movies'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-11 16:55:47
