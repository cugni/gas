SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `GAS` ;
CREATE SCHEMA IF NOT EXISTS `GAS` DEFAULT CHARACTER SET latin1 ;
USE `GAS` ;

-- -----------------------------------------------------
-- Table `GAS`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`user` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(32) NOT NULL ,
  `role` INT NOT NULL ,
  `name` VARCHAR(20) NOT NULL ,
  `surname` VARCHAR(20) NOT NULL ,
  `birth_date` DATE NOT NULL ,
  `approved` TINYINT(1) NULL DEFAULT false ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`product` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(30) NOT NULL ,
  `cost` FLOAT NOT NULL ,
  `units` VARCHAR(20) NOT NULL ,
  `quantity` FLOAT NOT NULL ,
  `producer` INT NOT NULL ,
  `description` VARCHAR(50) NOT NULL ,
  `dimensions` VARCHAR(10) NOT NULL ,
  `transport_cost` FLOAT NOT NULL ,
  `stock_quantity` FLOAT NOT NULL ,
  `min_to_buy_order` FLOAT NULL ,
  `min_to_buy_user` FLOAT NOT NULL ,
  `max_to_buy_user` FLOAT NOT NULL ,
  `available_from` DATE NULL ,
  `available_to` DATE NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FKC42BD16457C99462` (`producer` ASC) ,
  INDEX `FKC42BD164D298B611` (`producer` ASC) ,
  INDEX `FKC42BD164B4A9FE64` (`producer` ASC) ,
  INDEX `fk_products_users_idx` (`producer` ASC) ,
  CONSTRAINT `fk_products_users`
    FOREIGN KEY (`producer` )
    REFERENCES `GAS`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`proposal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`proposal` ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `GAS`.`delivery_withdrawal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`delivery_withdrawal` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`delivery_withdrawal` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `order` INT(11) NOT NULL ,
  `delivery_date` DATE NULL DEFAULT NULL ,
  `withdrawal_date` DATE NULL DEFAULT NULL ,
  `collector` INT NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FKB14A75E060C1FB5F` (`collector` ASC) ,
  INDEX `order_idx` (`order` ASC) ,
  INDEX `collector_idx` (`collector` ASC) ,
  CONSTRAINT `order`
    FOREIGN KEY (`order` )
    REFERENCES `GAS`.`proposal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `collector`
    FOREIGN KEY (`collector` )
    REFERENCES `GAS`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`producer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`producer` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`producer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `company_name` VARCHAR(40) NULL DEFAULT NULL ,
  `description` VARCHAR(40) NULL DEFAULT NULL ,
  `contact` VARCHAR(20) NULL DEFAULT NULL ,
  `address` VARCHAR(40) NULL DEFAULT NULL ,
  `phone_number` VARCHAR(15) NULL DEFAULT NULL ,
  `fax_number` VARCHAR(15) NULL DEFAULT NULL ,
  `email` VARCHAR(30) NULL DEFAULT NULL ,
  `delegate` INT NOT NULL ,
  `payment_mode` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK586D393B3F42C404` (`delegate` ASC) ,
  INDEX `FK586D393B21540C57` (`delegate` ASC) ,
  INDEX `fk_producer_users1_idx` (`delegate` ASC) ,
  CONSTRAINT `fk_producer_info_users1`
    FOREIGN KEY (`delegate` )
    REFERENCES `GAS`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `GAS`.`purchase_request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`purchase_request` ;


CREATE TABLE `purchase_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proposal` int(11) NOT NULL,
  `acquirer` int(11) NOT NULL,
  `quantity` float NOT NULL,
  `received` tinyint(1) NOT NULL DEFAULT '0',
  `completed` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC307E7E3894EE92E` (`acquirer`),
  KEY `fk_purchase_orders_orders1_idx` (`proposal`),
  KEY `diobono_idx` (`acquirer`),
  CONSTRAINT `diamine` FOREIGN KEY (`acquirer`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase_orders_orders1` FOREIGN KEY (`proposal`) REFERENCES `proposal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `purchase_request_part`;

CREATE TABLE `purchase_request_part` (
  `id` int(11) NOT NULL,
  `quantity` float NOT NULL,
  `acquirer` int(11) NOT NULL,
  `purchaseRequest` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prurchasepart_purchase_idx` (`purchaseRequest`),
  KEY `fk_purchasepart_user_idx` (`acquirer`),
  CONSTRAINT `fk_prurchasepart_purchase` FOREIGN KEY (`purchaseRequest`) REFERENCES `purchase_request` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_purchasepart_user` FOREIGN KEY (`acquirer`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- -----------------------------------------------------
-- Table `GAS`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`message` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `date` DATE NOT NULL ,
  `user` INT(20) NOT NULL ,
  `order` INT(11) NOT NULL ,
  `text` VARCHAR(255) NULL ,
  INDEX `fk_user_idx` (`user` ASC) ,
  INDEX `fk_order_idx` (`order` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_user`
    FOREIGN KEY (`user` )
    REFERENCES `GAS`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order`
    FOREIGN KEY (`order` )
    REFERENCES `GAS`.`proposal` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GAS`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`event` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `date` DATETIME NOT NULL ,
  `type` INT NOT NULL ,
  `user` INT NULL ,
  `proposal` INT(11) NULL ,
  `delivery_withdrawal` INT(11) NULL ,
  `message` INT NULL ,
  `product` INT(11) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_event_user1_idx` (`user` ASC) ,
  INDEX `fk_event_proposal1_idx` (`proposal` ASC) ,
  INDEX `fk_event_delivery_withdrawal1_idx` (`delivery_withdrawal` ASC) ,
  INDEX `fk_event_message1_idx` (`message` ASC) ,
  INDEX `fk_event_product1_idx` (`product` ASC) ,
  CONSTRAINT `fk_event_user1`
    FOREIGN KEY (`user` )
    REFERENCES `GAS`.`user` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_event_proposal1`
    FOREIGN KEY (`proposal` )
    REFERENCES `GAS`.`proposal` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_event_delivery_withdrawal1`
    FOREIGN KEY (`delivery_withdrawal` )
    REFERENCES `GAS`.`delivery_withdrawal` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_event_message1`
    FOREIGN KEY (`message` )
    REFERENCES `GAS`.`message` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_event_product1`
    FOREIGN KEY (`product` )
    REFERENCES `GAS`.`product` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GAS`.`notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GAS`.`notification` ;

CREATE  TABLE IF NOT EXISTS `GAS`.`notification` (
  `event_id` INT NOT NULL ,
  `user_id` INT NOT NULL ,
  INDEX `fk_notification_event1_idx` (`event_id` ASC) ,
  INDEX `fk_notification_user1_idx` (`user_id` ASC) ,
  PRIMARY KEY (`event_id`, `user_id`) ,
  CONSTRAINT `fk_notification_event1`
    FOREIGN KEY (`event_id` )
    REFERENCES `GAS`.`event` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `GAS`.`user` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
