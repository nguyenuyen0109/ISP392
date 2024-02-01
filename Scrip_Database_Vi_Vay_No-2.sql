-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema isp391
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema isp391
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `isp391` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `isp391` ;

-- -----------------------------------------------------
-- Table `isp391`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`role` (
  `id` INT NOT NULL,
  `displayname` VARCHAR(45) NULL DEFAULT NULL,
  `createAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`account` (
  `id` INT NOT NULL,
  `name` VARCHAR(500) NOT NULL,
  `mobileNumber` VARCHAR(500) NULL DEFAULT NULL,
  `emailAddress` VARCHAR(500) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `address` VARCHAR(500) NULL DEFAULT NULL,
  `isActive` BIT(1) NULL DEFAULT NULL,
  `updateAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `createAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `username` VARCHAR(45) NOT NULL,
  `avatarUrl` VARCHAR(500) NULL DEFAULT NULL,
  `gender` BIT(1) NOT NULL DEFAULT 1,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idAccount_UNIQUE` (`id` ASC) ,
  INDEX `fk_account_role1_idx` (`role_id` ASC) ,
  CONSTRAINT `fk_account_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `isp391`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`history_payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`history_payment` (
  `id` INT NOT NULL,
  `amount` DOUBLE NULL DEFAULT NULL,
  `paymentDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`guest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`guest` (
  `id` INT NOT NULL,
  `name` VARCHAR(500) NULL DEFAULT NULL,
  `address` VARCHAR(500) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(500) NULL DEFAULT NULL,
  `totalDebt` DOUBLE NULL DEFAULT NULL,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `account_id` INT NOT NULL,
  `history_payment_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_guest_account1_idx` (`account_id` ASC) ,
  INDEX `fk_guest_history_payment1_idx` (`history_payment_id` ASC) ,
  CONSTRAINT `fk_guest_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `isp391`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_guest_history_payment1`
    FOREIGN KEY (`history_payment_id`)
    REFERENCES `isp391`.`history_payment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`interest_rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`interest_rate` (
  `id` INT NOT NULL,
  `interestRate` DOUBLE NULL DEFAULT NULL,
  `dateOfApplication` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`debtdetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`debtdetails` (
  `id` INT NOT NULL,
  `note` VARCHAR(500) NULL DEFAULT NULL,
  `debtType` BIT NULL DEFAULT NULL,
  `amount` DOUBLE NULL DEFAULT NULL,
  `image` VARCHAR(500) NULL DEFAULT NULL,
  `debtCreatedAt` DATETIME NULL DEFAULT NULL,
  `qr` VARCHAR(500) NULL,
  `guest_id` INT NOT NULL,
  `interest_rate_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idDebtDetails_UNIQUE` (`id` ASC) ,
  INDEX `fk_debtdetails_guest1_idx` (`guest_id` ASC) ,
  INDEX `fk_debtdetails_interest_rate1_idx` (`interest_rate_id` ASC) ,
  CONSTRAINT `fk_debtdetails_guest1`
    FOREIGN KEY (`guest_id`)
    REFERENCES `isp391`.`guest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debtdetails_interest_rate1`
    FOREIGN KEY (`interest_rate_id`)
    REFERENCES `isp391`.`interest_rate` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`notification` (
  `id` INT NOT NULL,
  `notificationDate` DATETIME NULL DEFAULT NULL,
  `discription` VARCHAR(500) NULL DEFAULT NULL,
  `debtdetails_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_notification_debtdetails1_idx` (`debtdetails_id` ASC) ,
  CONSTRAINT `fk_notification_debtdetails1`
    FOREIGN KEY (`debtdetails_id`)
    REFERENCES `isp391`.`debtdetails` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`salt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`salt` (
  `account_id` INT NOT NULL,
  `salt` VARCHAR(45) NULL,
  INDEX `fk_salt_account_idx` (`account_id` ASC) ,
  PRIMARY KEY (`account_id`),
  CONSTRAINT `fk_salt_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `isp391`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isp391`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`report` (
  `id` INT NOT NULL,
  `discription` VARCHAR(500) NULL,
  `createAt` VARCHAR(500) NULL,
  `status` VARCHAR(45) NULL,
  `debtdetails_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_report_debtdetails1_idx` (`debtdetails_id` ASC) ,
  CONSTRAINT `fk_report_debtdetails1`
    FOREIGN KEY (`debtdetails_id`)
    REFERENCES `isp391`.`debtdetails` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
