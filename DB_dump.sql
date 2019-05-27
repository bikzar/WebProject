CREATE DATABASE  IF NOT EXISTS `payment_system` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `payment_system`;
-- MySQL dump 10.13  Distrib 8.0.16, for Linux (x86_64)
--
-- Host: localhost    Database: payment_system
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank_account` (
  `bank_account_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_block` tinyint(4) NOT NULL,
  `acc_money` double NOT NULL,
  `currency_type` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`bank_account_id`),
  UNIQUE KEY `account_id_UNIQUE` (`bank_account_id`),
  KEY `fk_user_id_idx` (`user_id`),
  KEY `fk_bank_account_currency_type_idx` (`currency_type`),
  CONSTRAINT `fk_acc_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_bank_account_currency_type` FOREIGN KEY (`currency_type`) REFERENCES `currency_type` (`currency_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (1,0,4000,1,23),(2,0,760,2,23),(3,0,19988.27,4,23),(4,0,1566,1,2),(5,0,5600,2,3),(6,0,50600,3,3),(7,0,105000,2,23),(8,0,899998,1,23),(9,0,100000,1,1),(10,0,100200,2,1),(11,0,100000,3,1),(12,0,100158.06,4,1);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `credit_card` (
  `credit_card_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_block` tinyint(4) NOT NULL,
  `currency_type` int(11) NOT NULL,
  `bank_account_id` int(11) NOT NULL,
  PRIMARY KEY (`credit_card_id`),
  KEY `fk_credit_card_currency_tupe_idx` (`currency_type`),
  KEY `fk_credit_card_bank_account_idx` (`bank_account_id`),
  CONSTRAINT `fk_credit_card_bank_account` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`bank_account_id`),
  CONSTRAINT `fk_credit_card_currency_tupe` FOREIGN KEY (`currency_type`) REFERENCES `currency_type` (`currency_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (1,0,1,1),(2,0,2,2),(3,0,4,3),(4,0,1,4),(5,0,2,5),(6,0,3,6),(7,0,2,7),(11,0,1,8),(12,0,2,2);
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency_type`
--

DROP TABLE IF EXISTS `currency_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `currency_type` (
  `currency_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `currency_abbreviation` varchar(45) NOT NULL,
  PRIMARY KEY (`currency_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency_type`
--

LOCK TABLES `currency_type` WRITE;
/*!40000 ALTER TABLE `currency_type` DISABLE KEYS */;
INSERT INTO `currency_type` VALUES (1,'USD'),(2,'EUR'),(3,'RUB'),(4,'BYN');
/*!40000 ALTER TABLE `currency_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation_type`
--

DROP TABLE IF EXISTS `operation_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operation_type` (
  `operation_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`operation_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation_type`
--

LOCK TABLES `operation_type` WRITE;
/*!40000 ALTER TABLE `operation_type` DISABLE KEYS */;
INSERT INTO `operation_type` VALUES (1,'Pay'),(2,'Replenishment'),(3,'Commision');
/*!40000 ALTER TABLE `operation_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `transaction` (
  `trans_id` int(11) NOT NULL AUTO_INCREMENT,
  `resurs_id` int(11) NOT NULL,
  `destination_id` int(11) NOT NULL,
  `operation_type` int(11) NOT NULL,
  `credit_card_id` int(11) DEFAULT NULL,
  `operation_sum` double NOT NULL,
  `trasaction_currency_type` varchar(45) NOT NULL,
  `t_date` varchar(45) NOT NULL,
  PRIMARY KEY (`trans_id`),
  KEY `fk_transaction_resurs_id_idx` (`resurs_id`),
  KEY `fk_transaction_operat_type_idx` (`operation_type`),
  CONSTRAINT `fk_transaction_operat_type` FOREIGN KEY (`operation_type`) REFERENCES `operation_type` (`operation_type_id`),
  CONSTRAINT `fk_transaction_resurs_id` FOREIGN KEY (`resurs_id`) REFERENCES `bank_account` (`bank_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (45,2,12,3,8,58.36,'4','2019-05-21 21:42:08'),(46,2,12,2,8,11669.99,'4','2019-05-21 21:42:08'),(47,12,7,2,0,11670,'4','2019-05-21 21:42:08'),(48,2,7,2,8,5019.35,'2','2019-05-21 21:42:08'),(49,3,12,3,3,0.06,'4','2019-05-21 22:15:30'),(50,3,12,2,3,11.67,'4','2019-05-21 22:15:30'),(51,12,2,2,0,11.67,'4','2019-05-21 22:15:30'),(52,3,2,2,3,11.67,'4','2019-05-21 22:15:30'),(53,2,11111,1,2,100,'2','2019-05-24 21:38:01'),(54,2,10,1,2,100,'2','2019-05-24 21:38:01'),(55,2,11111,1,2,100,'2','2019-05-24 21:38:10'),(56,2,10,1,2,100,'2','2019-05-24 21:38:10');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `second_name` varchar(45) NOT NULL,
  `birth_date` date NOT NULL,
  `is_admin` tinyint(4) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'MainBank','MainBank','1990-04-25',1,'MainBank','123'),(2,'Сергей','Старостенко','1990-01-21',0,'starStn','456'),(3,'Анастасия','Науменко','1861-08-20',0,'anasBi','789'),(4,'Петр','Петров','1945-09-01',1,'admin','admin'),(5,'Иван','Иванов','1990-04-16',0,'stirt','ght123'),(16,'Николай','Николаев','1994-03-02',0,'pup','asd'),(17,'Пётр','Бубешкин','1986-01-14',0,'LotД','123'),(21,'Николюша','Верский','2019-05-02',0,'niko','qwerty'),(23,'Сергей','Войтенков','1990-04-25',0,'bikzar','123'),(24,'Сергей','Войтенков','1989-02-15',0,'yu','yu'),(26,'Сергей','Войтенков','2019-05-21',0,'oi','oi');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-27 12:59:01
