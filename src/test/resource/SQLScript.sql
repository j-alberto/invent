SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `liberal`.`USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liberal`.`USER` ;

CREATE TABLE IF NOT EXISTS `liberal`.`USER` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(50) NULL,
  `enabled` BIT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liberal`.`AUTHORITY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liberal`.`AUTHORITY` ;

CREATE TABLE IF NOT EXISTS `liberal`.`AUTHORITY` (
  `authority_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `authority_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`authority_id`),
  UNIQUE INDEX `authority_name_UNIQUE` (`authority_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liberal`.`GROUP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liberal`.`GROUP` ;

CREATE TABLE IF NOT EXISTS `liberal`.`GROUP` (
  `group_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE INDEX `group_name_UNIQUE` (`group_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liberal`.`GROUP_AUTHORITY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liberal`.`GROUP_AUTHORITY` ;

CREATE TABLE IF NOT EXISTS `liberal`.`GROUP_AUTHORITY` (
  `group_authority_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `group_fk` INT UNSIGNED NOT NULL,
  `authority_fk` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`group_authority_id`),
  INDEX `GROUP_AUTHORITY_GROUP_idx` (`group_fk` ASC),
  INDEX `GROUP_AUTHORITY_AUTHORITY_idx` (`authority_fk` ASC),
  UNIQUE INDEX `GROUP_AUTHORITY_idx` (`group_fk` ASC, `authority_fk` ASC),
  CONSTRAINT `fk_GROUP_AUTHORITY_GROUP`
    FOREIGN KEY (`group_fk`)
    REFERENCES `liberal`.`GROUP` (`group_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_GROUP_AUTHORITY_AUTHORITY1`
    FOREIGN KEY (`authority_fk`)
    REFERENCES `liberal`.`AUTHORITY` (`authority_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liberal`.`GROUP_MEMBER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liberal`.`GROUP_MEMBER` ;

CREATE TABLE IF NOT EXISTS `liberal`.`GROUP_MEMBER` (
  `group_member_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `group_fk` INT UNSIGNED NOT NULL,
  `user_fk` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`group_member_id`),
  INDEX `GROUP_MEMBER_GROUP_idx` (`group_fk` ASC),
  INDEX `GROUP_MEMBER_USER_idx` (`user_fk` ASC),
  INDEX `GROUP_MEMBER_idx` (`group_fk` ASC, `user_fk` ASC),
  CONSTRAINT `fk_GROUP_MEMBER_GROUP1`
    FOREIGN KEY (`group_fk`)
    REFERENCES `liberal`.`GROUP` (`group_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_GROUP_MEMBER_USER1`
    FOREIGN KEY (`user_fk`)
    REFERENCES `liberal`.`USER` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `liberal`.`CLASS_GROUP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liberal`.`CLASS_GROUP` ;

CREATE TABLE IF NOT EXISTS `liberal`.`CLASS_GROUP` (
  `class_group_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `class_group_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`class_group_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liberal`.`STUDENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liberal`.`STUDENT` ;

CREATE TABLE IF NOT EXISTS `liberal`.`STUDENT` (
  `student_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_code` VARCHAR(8) NOT NULL,
  `student_name` VARCHAR(45) NOT NULL,
  `student_lname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`student_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liberal`.`GROUP_STUDENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liberal`.`GROUP_STUDENT` ;

CREATE TABLE IF NOT EXISTS `liberal`.`GROUP_STUDENT` (
  `class_group_fk` INT UNSIGNED NOT NULL,
  `student_fk` INT UNSIGNED NOT NULL,
  INDEX `fk_GROUP_STUDENT_CLASS_GROUP1_idx` (`class_group_fk` ASC),
  INDEX `fk_GROUP_STUDENT_STUDENT1_idx` (`student_fk` ASC),
  INDEX `GROUP_STUDENT_idx` (`class_group_fk` ASC, `student_fk` ASC),
  PRIMARY KEY (`class_group_fk`, `student_fk`),
  CONSTRAINT `CLASS_GROUP1`
    FOREIGN KEY (`class_group_fk`)
    REFERENCES `liberal`.`CLASS_GROUP` (`class_group_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `STUDENT1`
    FOREIGN KEY (`student_fk`)
    REFERENCES `liberal`.`STUDENT` (`student_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `liberal`.`USER` (`user_id`,`user_name`) VALUES (1,'admin');
INSERT INTO `liberal`.`USER` (`user_id`,`user_name`) VALUES (2,'guest');

INSERT INTO `liberal`.`GROUP` (`group_id`,`group_name`) VALUES (1,'root');
INSERT INTO `liberal`.`GROUP` (`group_id`,`group_name`) VALUES (2,'guest');

INSERT INTO `liberal`.`AUTHORITY` (`authority_id`,`authority_name`) VALUES (1,'ADMIN_ROLE');
INSERT INTO `liberal`.`AUTHORITY` (`authority_id`,`authority_name`) VALUES (2,'GUEST_ROLE');

INSERT INTO `liberal`.`GROUP_AUTHORITY` (`group_fk`, `authority_fk`) VALUES ('1', '1');
INSERT INTO `liberal`.`GROUP_AUTHORITY` (`group_fk`, `authority_fk`) VALUES ('2', '2');

INSERT INTO `liberal`.`GROUP_MEMBER` (`group_fk`, `user_fk`) VALUES ('1', '1');
INSERT INTO `liberal`.`GROUP_MEMBER` (`group_fk`, `user_fk`) VALUES ('2', '2');

INSERT INTO `liberal`.`CLASS_GROUP` (`class_group_id`,`class_group_name`) VALUES (1,'GRUPO 1');
INSERT INTO `liberal`.`CLASS_GROUP` (`class_group_id`,`class_group_name`) VALUES (2,'grupo 2');
INSERT INTO `liberal`.`CLASS_GROUP` (`class_group_id`,`class_group_name`) VALUES (3,'grupo 3');

INSERT INTO `liberal`.`STUDENT` (`student_id`,`student_code`, `student_name`, `student_lname`) VALUES (1,'a1234567', 'alumno', 'primero');
INSERT INTO `liberal`.`STUDENT` (`student_id`,`student_code`, `student_name`, `student_lname`) VALUES (2,'a1122334', 'alumno', 'apellido');
INSERT INTO `liberal`.`STUDENT` (`student_id`,`student_code`, `student_name`, `student_lname`) VALUES (3,'x3456789', 'nombre', 'apellido');
INSERT INTO `liberal`.`STUDENT` (`student_id`,`student_code`, `student_name`, `student_lname`) VALUES (4,'12345678', 'nombre1', 'app2');
INSERT INTO `liberal`.`STUDENT` (`student_id`,`student_code`, `student_name`, `student_lname`) VALUES (5,'55998865', 'otro', 'alvarez');
INSERT INTO `liberal`.`STUDENT` (`student_id`,`student_code`, `student_name`, `student_lname`) VALUES (6,'65425648', 'blanca', 'barragán');
INSERT INTO `liberal`.`STUDENT` (`student_id`,`student_code`, `student_name`, `student_lname`) VALUES (7,'ccccc234', 'Carlos', 'Castro');
INSERT INTO `liberal`.`STUDENT` (`student_id`,`student_code`, `student_name`, `student_lname`) VALUES (8,'f8885522', 'Daniel', 'Duran');


INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('1', '1');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('1', '2');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('1', '3');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('1', '4');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('1', '5');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('1', '6');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('2', '7');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('2', '8');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('3', '4');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('3', '6');
INSERT INTO `liberal`.`GROUP_STUDENT` (`class_group_fk`, `student_fk`) VALUES ('3', '8');
