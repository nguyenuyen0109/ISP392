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
CREATE SCHEMA IF NOT EXISTS `IS1704_ISP391_G6_1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `IS1704_ISP391_G6_1` ;

-- -----------------------------------------------------
-- Table `IS1704_ISP391_G6_1`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`role` (
  `idrole` INT NOT NULL,
  `display_name` VARCHAR(45) NULL DEFAULT NULL,
  `createAt` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP,
  `updateAt` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `IS1704_ISP391_G6_1`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`account` (
  `idAccount` INT NOT NULL,
  `Name` VARCHAR(500) NOT NULL,
  `Mobile_number` VARCHAR(500) NULL DEFAULT NULL,
  `Email_address` VARCHAR(500) NOT NULL,
  `Password` VARCHAR(500) NOT NULL,
  `Address` VARCHAR(500) NULL DEFAULT NULL,
  `IsActive` BIT(1) NULL DEFAULT NULL,
  `UpdateAt` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP,
  `CreateAt` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP,
  `username` VARCHAR(45) NOT NULL,
  `Amount` DOUBLE NULL DEFAULT NULL,
  `avatar_Url` VARCHAR(500) NULL DEFAULT NULL,
  `gender` BIT(1) NULL DEFAULT NULL,
  `role_idrole` INT NOT NULL,
  PRIMARY KEY (`idAccount`),
  UNIQUE INDEX `idAccount_UNIQUE` (`idAccount` ASC) ,
  INDEX `fk_account_role1_idx` (`role_idrole` ASC) ,
  CONSTRAINT `fk_account_role1`
    FOREIGN KEY (`role_idrole`)
    REFERENCES `IS1704_ISP391_G6_1`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `IS1704_ISP391_G6_1`.`history_payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`history_payment` (
  `idHistory_Payment` INT NOT NULL,
  `Amount` DOUBLE NULL DEFAULT NULL,
  `Payment_date` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP,
  PRIMARY KEY (`idHistory_Payment`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `IS1704_ISP391_G6_1`.`debtor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`debtor` (
  `IdDebtor` INT NOT NULL,
  `name` VARCHAR(500) NULL DEFAULT NULL,
  `address` VARCHAR(500) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(500) NULL DEFAULT NULL,
  `totalDebt` DOUBLE NULL DEFAULT NULL,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `account_idAccount` INT NULL DEFAULT NULL,
  `history_payment_idHistory_Payment` INT NOT NULL,
  PRIMARY KEY (`IdDebtor`, `history_payment_idHistory_Payment`),
  INDEX `fk_debt_account1_idx` (`account_idAccount` ASC) ,
  INDEX `fk_debtor_history_payment1_idx` (`history_payment_idHistory_Payment` ASC) ,
  CONSTRAINT `fk_debt_account1`
    FOREIGN KEY (`account_idAccount`)
    REFERENCES `IS1704_ISP391_G6_1`.`account` (`idAccount`),
  CONSTRAINT `fk_debtor_history_payment1`
    FOREIGN KEY (`history_payment_idHistory_Payment`)
    REFERENCES `IS1704_ISP391_G6_1`.`history_payment` (`idHistory_Payment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `IS1704_ISP391_G6_1`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`notification` (
  `idnotification` INT NOT NULL,
  `notificationr_date` DATETIME NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `discription` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`idnotification`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `IS1704_ISP391_G6_1`.`interest_rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`interest_rate` (
  `id` INT NOT NULL,
  `id_debtDetails` INT NULL DEFAULT NULL,
  `Interest_rate` DOUBLE NULL DEFAULT NULL,
  `date_of_application` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `IS1704_ISP391_G6_1`.`debtdetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`debtdetails` (
  `idDebtDetails` INT NOT NULL,
  `DebtId` INT NULL DEFAULT NULL,
  `Note` VARCHAR(500) NULL DEFAULT NULL,
  `DebtType` BIT(1) NULL DEFAULT NULL,
  `Amount` DOUBLE NULL DEFAULT NULL,
  `Image` VARCHAR(500) NULL DEFAULT NULL,
  `DebtCreatedAt` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP,
  `debtor_IdDebtor` INT NOT NULL,
  `notification_idnotification` INT NOT NULL,
  `interest_rate_id` INT NOT NULL,
  PRIMARY KEY (`idDebtDetails`),
  UNIQUE INDEX `idDebtDetails_UNIQUE` (`idDebtDetails` ASC) ,
  INDEX `fk_debtdetails_debtor1_idx` (`debtor_IdDebtor` ASC) ,
  INDEX `fk_debtdetails_notification1_idx` (`notification_idnotification` ASC) ,
  INDEX `fk_debtdetails_interest_rate1_idx` (`interest_rate_id` ASC) ,
  CONSTRAINT `fk_debtdetails_debtor1`
    FOREIGN KEY (`debtor_IdDebtor`)
    REFERENCES `IS1704_ISP391_G6_1`.`debtor` (`IdDebtor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debtdetails_notification1`
    FOREIGN KEY (`notification_idnotification`)
    REFERENCES `IS1704_ISP391_G6_1`.`notification` (`idnotification`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debtdetails_interest_rate1`
    FOREIGN KEY (`interest_rate_id`)
    REFERENCES `IS1704_ISP391_G6_1`.`interest_rate` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `IS1704_ISP391_G6_1`.`payment_method`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`payment_method` (
  `idPayment_Method` INT NOT NULL,
  `Method_name` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`idPayment_Method`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `IS1704_ISP391_G6_1`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`payment` (
  `idpayment` INT NOT NULL,
  `Amount` DOUBLE NULL DEFAULT NULL,
  `Payment_Deadline` DATETIME NULL DEFAULT NULL,
  `account_idAccount` INT NOT NULL,
  `payment_method_idPayment_Method` INT NOT NULL,
  PRIMARY KEY (`idpayment`),
  INDEX `fk_payment_account1_idx` (`account_idAccount` ASC) ,
  INDEX `fk_payment_payment_method1_idx` (`payment_method_idPayment_Method` ASC) ,
  CONSTRAINT `fk_payment_account1`
    FOREIGN KEY (`account_idAccount`)
    REFERENCES `IS1704_ISP391_G6_1`.`account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_payment_method1`
    FOREIGN KEY (`payment_method_idPayment_Method`)
    REFERENCES `IS1704_ISP391_G6_1`.`payment_method` (`idPayment_Method`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `isp391`.`emailconfirmcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`emailconfirmcode` (
  `account_idAccount` INT NOT NULL,
  `email` VARCHAR(255) NULL,
  `token` VARCHAR(45) NOT NULL,
  `isConfirmed` BIT NULL DEFAULT 0,
  `creatAt` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP,
  PRIMARY KEY (`account_idAccount`, `token`),
  INDEX `fk_emailconfirmcode_account1_idx` (`account_idAccount` ASC) VISIBLE,
  CONSTRAINT `fk_emailconfirmcode_account1`
    FOREIGN KEY (`account_idAccount`)
    REFERENCES `IS1704_ISP391_G6_1`.`account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `isp391`.`capcha`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IS1704_ISP391_G6_1`.`capcha` (
  `account_idAccount` INT NOT NULL,
  `capchaCode` VARCHAR(45) NOT NULL,
  `createAt` DATETIME NULL DEFAULT  CURRENT_TIMESTAMP,
  `isConfirmed` BIT NULL DEFAULT 0,
  PRIMARY KEY (`account_idAccount`, `capchaCode`),
  INDEX `fk_capcha_account1_idx` (`account_idAccount` ASC) VISIBLE,
  CONSTRAINT `fk_capcha_account1`
    FOREIGN KEY (`account_idAccount`)
    REFERENCES `IS1704_ISP391_G6_1`.`account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



