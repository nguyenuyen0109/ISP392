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
  `displayName` VARCHAR(45) NULL DEFAULT NULL,
  `createAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`salt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`salt` (
  `id` INT NOT NULL,
  `salt` VARCHAR(45) NULL,
  `createAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isp391`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`account` (
  `id` INT NOT NULL,
  `name` VARCHAR(500) NOT NULL,
  `Mobile_number` VARCHAR(500) NULL DEFAULT NULL,
  `emailAddress` VARCHAR(500) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `Address` VARCHAR(500) NULL DEFAULT NULL,
  `isActive` BIT(1) NULL DEFAULT NULL,
  `updateAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `CreateAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `username` VARCHAR(45) NOT NULL,
  `Amount` DOUBLE NULL DEFAULT NULL,
  `avatarUrl` VARCHAR(500) NULL DEFAULT NULL,
  `gender` BIT(1) NULL DEFAULT NULL,
  `role_id` INT NOT NULL,
  `salt_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idAccount_UNIQUE` (`id` ASC) ,
  INDEX `fk_account_role_idx` (`role_id` ASC) ,
  INDEX `fk_account_salt1_idx` (`salt_id` ASC) ,
  CONSTRAINT `fk_account_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `isp391`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_salt1`
    FOREIGN KEY (`salt_id`)
    REFERENCES `isp391`.`salt` (`id`)
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
  `Amount` DOUBLE NULL DEFAULT NULL,
  `paymentDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`debtor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`debtor` (
  `IdDebtor` INT NOT NULL,
  `name` VARCHAR(500) NULL DEFAULT NULL,
  `address` VARCHAR(500) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(500) NULL DEFAULT NULL,
  `totalDebt` DOUBLE NULL DEFAULT NULL,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `account_id` INT NOT NULL,
  `history_payment_id` INT NOT NULL,
  PRIMARY KEY (`IdDebtor`),
  INDEX `fk_debtor_account1_idx` (`account_id` ASC) ,
  INDEX `fk_debtor_history_payment1_idx` (`history_payment_id` ASC) ,
  CONSTRAINT `fk_debtor_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `isp391`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debtor_history_payment1`
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
  `id_debtDetails` INT NULL DEFAULT NULL,
  `Interest_rate` DOUBLE NULL DEFAULT NULL,
  `date_of_application` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`debtdetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `isp391`.`debtdetails` (
  `id` INT NOT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `debtType` BIT(1) NULL DEFAULT NULL,
  `amount` DOUBLE NULL DEFAULT NULL,
  `Image` VARCHAR(500) NULL DEFAULT NULL,
  `creatAT` DATETIME NULL DEFAULT NULL,
  `qr` VARCHAR(500) NULL DEFAULT 'CURRENT_TIMESTAMP',
  `debtor_IdDebtor` INT NOT NULL,
  `interest_rate_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idDebtDetails_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_debtdetails_debtor1_idx` (`debtor_IdDebtor` ASC) ,
  INDEX `fk_debtdetails_interest_rate1_idx` (`interest_rate_id` ASC) ,
  CONSTRAINT `fk_debtdetails_debtor1`
    FOREIGN KEY (`debtor_IdDebtor`)
    REFERENCES `isp391`.`debtor` (`IdDebtor`)
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
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
