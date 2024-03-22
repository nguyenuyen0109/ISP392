-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema is1704_isp391_g6_1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema is1704_isp391_g6_1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `is1704_isp391_g6_1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `is1704_isp391_g6_1` ;

-- -----------------------------------------------------
-- Table `is1704_isp391_g6_1`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `is1704_isp391_g6_1`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `createAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `deleteAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `isDeleted` BIT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `is1704_isp391_g6_1`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `is1704_isp391_g6_1`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `gender` BIT(1) NULL DEFAULT NULL,
  `role_id` INT NOT NULL,
  `token` VARCHAR(450) NULL,
  `expiretime` DATE NULL,
  `isDeleted` BIT NULL,
  PRIMARY KEY (`id`, `role_id`),
  UNIQUE INDEX `idAccount_UNIQUE` (`id` ASC)  ,
  INDEX `fk_account_role_idx` (`role_id` ASC)  ,
  CONSTRAINT `fk_account_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `is1704_isp391_g6_1`.`role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `is1704_isp391_g6_1`.`debtor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `is1704_isp391_g6_1`.`debtor` (
  `id` INT NOT NULL,
  `name` VARCHAR(450) NULL,
  `address` VARCHAR(450) NULL,
  `email` VARCHAR(450) NULL,
  `totalDebt` DOUBLE NULL,
  `createAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `isDeleted` BIT NULL,
  `phone` VARCHAR(450) NULL,
  `updateAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`, `account_id`),
  INDEX `fk_debtor_account1_idx` (`account_id` ASC)  ,
  CONSTRAINT `fk_debtor_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `is1704_isp391_g6_1`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `is1704_isp391_g6_1`.`debttype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `is1704_isp391_g6_1`.`debttype` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `createAt` DATETIME NULL,
  `updateAt` DATETIME NULL,
  `isDelete` BIT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `is1704_isp391_g6_1`.`debtdetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `is1704_isp391_g6_1`.`debtdetails` (
  `id` INT NOT NULL,
  `description` VARCHAR(450) NULL,
  `image` VARCHAR(450) NULL,
  `createAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `interestRate` DOUBLE NULL,
  `status` VARCHAR(45) NULL,
  `due` DOUBLE NULL,
  `deleteAt` DATETIME NULL,
  `isDeleted` BIT NULL,
  `debtissuance` DATETIME NULL,
  `totalAmount` DOUBLE NULL,
  `debtor_id` INT NOT NULL,
  `debtor_account_id` INT NOT NULL,
  `debttype_id` INT NOT NULL,
  PRIMARY KEY (`id`, `debtor_id`, `debtor_account_id`, `debttype_id`),
  INDEX `fk_debtdetails_debtor1_idx` (`debtor_id` ASC, `debtor_account_id` ASC)  ,
  INDEX `fk_debtdetails_debttype1_idx` (`debttype_id` ASC) VISIBLE,
  CONSTRAINT `fk_debtdetails_debtor1`
    FOREIGN KEY (`debtor_id` , `debtor_account_id`)
    REFERENCES `is1704_isp391_g6_1`.`debtor` (`id` , `account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debtdetails_debttype1`
    FOREIGN KEY (`debttype_id`)
    REFERENCES `is1704_isp391_g6_1`.`debttype` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `is1704_isp391_g6_1`.`feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `is1704_isp391_g6_1`.`feedback` (
  `id` INT NOT NULL,
  `rate` DOUBLE NULL,
  `feedback` VARCHAR(450) NULL,
  `img` VARCHAR(450) NULL,
  `deleteAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `createAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `isDelete` BIT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`, `account_id`),
  INDEX `fk_feedback_account1_idx` (`account_id` ASC)  ,
  CONSTRAINT `fk_feedback_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `is1704_isp391_g6_1`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
