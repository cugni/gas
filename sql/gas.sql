DELIMITER ;
DROP SCHEMA IF EXISTS  GAS;
CREATE DATABASE  IF NOT EXISTS GAS ;
USE GAS ;
GRANT ALL PRIVILEGES ON GAS.* TO gas@`%` IDENTIFIED BY 'gas';

-- MySQL dump 10.13  Distrib 5.6.12, for Win64 (x86_64)
--                                                                    ß
-- Host: 127.0.0.1    Database: GAS
-- ------------------------------------------------------
-- Server version	5.6.12

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



DROP TABLE IF EXISTS `delivery_withdrawal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;


CREATE TABLE `delivery_withdrawal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proposal` int(11) NOT NULL,
  `delivery_date` date DEFAULT NULL,
  `withdrawal_date` date DEFAULT NULL,
  `collector` int(11) DEFAULT NULL,
  `address` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB14A75E060C1FB5F` (`collector`),
  KEY `order_idx` (`proposal`),
  KEY `collector_idx` (`collector`),
  CONSTRAINT `collector` FOREIGN KEY (`collector`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order` FOREIGN KEY (`proposal`) REFERENCES `proposal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `type` int(11) NOT NULL,
  `user` int(11) DEFAULT NULL,
  `proposal` int(11) DEFAULT NULL,
  `delivery_withdrawal` int(11) DEFAULT NULL,
  `message` int(11) DEFAULT NULL,
  `product` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_event_user1_idx` (`user`),
  KEY `fk_event_proposal1_idx` (`proposal`),
  KEY `fk_event_delivery_withdrawal1_idx` (`delivery_withdrawal`),
  KEY `fk_event_message1_idx` (`message`),
  KEY `fk_event_product1_idx` (`product`),
  CONSTRAINT `fk_event_delivery_withdrawal1` FOREIGN KEY (`delivery_withdrawal`) REFERENCES `delivery_withdrawal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_event_message1` FOREIGN KEY (`message`) REFERENCES `message` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_event_product1` FOREIGN KEY (`product`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_event_proposal1` FOREIGN KEY (`proposal`) REFERENCES `proposal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_event_user1` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `user` int(20) NOT NULL,
  `proposal` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_idx` (`user`),
  KEY `fk_order_idx` (`proposal`),
  CONSTRAINT `fk_order` FOREIGN KEY (`proposal`) REFERENCES `proposal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`event_id`,`user_id`),
  KEY `fk_notification_event1_idx` (`event_id`),
  KEY `fk_notification_user1_idx` (`user_id`),
  CONSTRAINT `fk_notification_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_notification_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `producer`
--

DROP TABLE IF EXISTS `producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(40) NOT NULL,
  `description` varchar(40) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `fax_number` varchar(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `delegate` int(11) DEFAULT NULL,
  `payment_mode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK586D393B3F42C404` (`delegate`),
  KEY `FK586D393B21540C57` (`delegate`),
  KEY `fk_producer_users1_idx` (`delegate`),
  CONSTRAINT `fk_producer_info_users1` FOREIGN KEY (`delegate`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `cost` float NOT NULL,
  `units` varchar(20) NOT NULL,
  `quantity` float NOT NULL,
  `producer` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `dimensions` varchar(10) DEFAULT NULL,
  `transport_cost` float DEFAULT NULL,
  `stock_quantity` float NOT NULL,
  `min_to_buy_order` float DEFAULT NULL,
  `min_to_buy_user` float DEFAULT NULL,
  `max_to_buy_user` float DEFAULT NULL,
  `available_from` date DEFAULT NULL,
  `available_to` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC42BD16457C99462` (`producer`),
  KEY `FKC42BD164D298B611` (`producer`),
  KEY `FKC42BD164B4A9FE64` (`producer`),
  KEY `fk_products_users_idx` (`producer`),
  CONSTRAINT `fk_products_users` FOREIGN KEY (`producer`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proposal`
--

DROP TABLE IF EXISTS `proposal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proposal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `min_reached` tinyint(1) NOT NULL DEFAULT '0',
  `delegate` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC3DF62E54FECA577` (`product`),
  KEY `fk_orders_products1_idx` (`product`),
  KEY `fk_orders_delegates_idx` (`delegate`),
  CONSTRAINT `fk_orders_delegates` FOREIGN KEY (`delegate`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_products1` FOREIGN KEY (`product`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchase_request`
--

DROP TABLE IF EXISTS `purchase_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proposal` int(11) NOT NULL,
  `acquirer` int(11) NOT NULL,
  `quantity` float NOT NULL,
  `received` tinyint(1) NOT NULL DEFAULT '0',
  `completed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FKC307E7E3894EE92E` (`acquirer`),
  KEY `fk_purchase_orders_orders1_idx` (`proposal`),
  KEY `diobono_idx` (`acquirer`),
  CONSTRAINT `diamine` FOREIGN KEY (`acquirer`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase_orders_orders1` FOREIGN KEY (`proposal`) REFERENCES `proposal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchase_request_part`
--

DROP TABLE IF EXISTS `purchase_request_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `purchase_request_part` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` float NOT NULL,
  `acquirer` int(11) NOT NULL,
  `purchaseRequest` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prurchasepart_purchase_idx` (`purchaseRequest`),
  KEY `fk_purchasepart_user_idx` (`acquirer`),
  CONSTRAINT `fk_prurchasepart_purchase` FOREIGN KEY (`purchaseRequest`) REFERENCES `purchase_request` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_purchasepart_user` FOREIGN KEY (`acquirer`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$



--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `birth_date` date NOT NULL,
  `approved` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
INSERT INTO  `user`(username,password,role,name,surname,birth_date,approved)
  values ('admin','admin',3,'admin','admin','2000/01/01',1);
INSERT INTO  `user`(username,password,role,name,surname,birth_date,approved)
  values ('user','user',0,'user','user','2000/01/01',1);
INSERT INTO  `user`(username,password,role,name,surname,birth_date,approved)
  values ('delegate','delegate',1,'delagate','delegate','2000/01/01',1);
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-11 13:19:47
