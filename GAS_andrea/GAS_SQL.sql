SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `GAS` ;
CREATE SCHEMA IF NOT EXISTS `GAS` DEFAULT CHARACTER SET latin1 ;
USE `GAS` ;

-- -----------------------------------------------------
-- Table `GAS`.`statistics`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`statistics` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`statistics` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `n_bought` INT(11) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`producer_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`producer_info` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`producer_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `company_name` VARCHAR(40) NULL DEFAULT NULL ,
  `description` VARCHAR(40) NULL DEFAULT NULL ,
  `contact` VARCHAR(20) NULL DEFAULT NULL ,
  `address` VARCHAR(40) NULL DEFAULT NULL ,
  `phone_number` VARCHAR(15) NULL DEFAULT NULL ,
  `fax_number` VARCHAR(15) NULL DEFAULT NULL ,
  `email` VARCHAR(30) NULL DEFAULT NULL ,
  `delegate` VARCHAR(20) NOT NULL ,
  `payment_mode` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK586D393B3F42C404` (`delegate` ASC) ,
  INDEX `FK586D393B21540C57` (`delegate` ASC) ,
  INDEX `fk_producer_info_users1` (`delegate` ASC) )
ENGINE = MyISAM
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`users` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`users` (
  `username` VARCHAR(20) NOT NULL ,
  `password` VARCHAR(32) NOT NULL ,
  `role` INT(11) NOT NULL ,
  `name` VARCHAR(20) NOT NULL ,
  `surname` VARCHAR(20) NOT NULL ,
  `birth_date` DATE NOT NULL ,
  `statistics` INT(11) NOT NULL ,
  `producer_info` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`username`) ,
  INDEX `FK6A68E08CEED85FA` (`statistics` ASC) ,
  INDEX `FK6A68E0899FFCF4F` (`producer_info` ASC) ,
  INDEX `FK6A68E0878E040D` (`statistics` ASC) ,
  INDEX `FK6A68E082C7914A2` (`producer_info` ASC) ,
  INDEX `statistics` (`statistics` ASC) ,
  INDEX `producer info` (`producer_info` ASC) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`approvals`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`approvals` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`approvals` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `user` VARCHAR(20) NOT NULL ,
  `approved` TINYINT(4) NOT NULL DEFAULT '0' ,
  `admin` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK8E076290F6043221` (`admin` ASC) ,
  INDEX `FK8E076290F0B51A9D` (`user` ASC) ,
  INDEX `fk_approvals_users1` (`user` ASC, `admin` ASC) )
ENGINE = MyISAM
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`products` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`products` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(30) NOT NULL ,
  `cost` FLOAT NOT NULL ,
  `units` VARCHAR(20) NOT NULL ,
  `quantity` FLOAT NOT NULL ,
  `producer` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(50) NOT NULL ,
  `dimensions` VARCHAR(10) NOT NULL ,
  `transport_cost` FLOAT NOT NULL ,
  `stock_quantity` FLOAT NOT NULL ,
  `min_to_buy` FLOAT NOT NULL ,
  `max_to_buy` FLOAT NOT NULL ,
  `available` TINYINT(4) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FKC42BD16457C99462` (`producer` ASC) ,
  INDEX `FKC42BD164D298B611` (`producer` ASC) ,
  INDEX `FKC42BD164B4A9FE64` (`producer` ASC) ,
  INDEX `fk_products_users` (`producer` ASC) )
ENGINE = MyISAM
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`orders` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `product` INT(11) NOT NULL ,
  `start_date` DATE NOT NULL ,
  `end_date` DATE NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FKC3DF62E54FECA577` (`product` ASC) ,
  INDEX `fk_orders_products1` (`product` ASC) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`delivery_withdrawal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`delivery_withdrawal` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`delivery_withdrawal` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `order` INT(11) NOT NULL ,
  `delivery_date` DATE NULL DEFAULT NULL ,
  `withdrawal_date` DATE NULL DEFAULT NULL ,
  `collector` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FKB14A75E060C1FB5F` (`collector` ASC) ,
  INDEX `order` (`order` ASC) ,
  INDEX `collector` (`collector` ASC) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`purchase_orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`purchase_orders` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`purchase_orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `order` INT(11) NOT NULL ,
  `acquirer` VARCHAR(20) NOT NULL ,
  `quantity` FLOAT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FKC307E7E3894EE92E` (`acquirer` ASC) ,
  INDEX `fk_purchase_orders_orders1` (`order` ASC) ,
  INDEX `diobono` (`acquirer` ASC) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
