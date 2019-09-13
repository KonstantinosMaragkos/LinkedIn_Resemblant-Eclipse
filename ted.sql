-- MySQL Script generated by MySQL Workbench
-- Sun Sep 30 10:57:58 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema TED
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `TED` ;

-- -----------------------------------------------------
-- Schema TED
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TED` DEFAULT CHARACTER SET utf8 ;
USE `TED` ;

-- -----------------------------------------------------
-- Table `TED`.`Personal_Data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Personal_Data` ;

CREATE TABLE IF NOT EXISTS `TED`.`Personal_Data` (
  `idPersonal_Data` INT NOT NULL,
  `University` VARCHAR(45) NULL,
  `Department` VARCHAR(45) NULL,
  `YearFrom` INT NULL,
  `YearTo` INT NULL,
  `Education` VARCHAR(45) NULL,
  `Title` VARCHAR(45) NULL,
  `Company` VARCHAR(45) NULL,
  `Location` VARCHAR(45) NULL,
  `Finished_Status` INT NULL,
  PRIMARY KEY (`idPersonal_Data`),
  UNIQUE INDEX `idPersonal_Data_UNIQUE` (`idPersonal_Data` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`User` ;

CREATE TABLE IF NOT EXISTS `TED`.`User` (
  `User_id` INT NOT NULL AUTO_INCREMENT,
  `E_Mail` VARCHAR(250) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `First_Name` VARCHAR(250) NULL,
  `Last_Name` VARCHAR(250) NULL,
  `Phone` VARCHAR(20) NULL,
  `Photo` VARCHAR(250) NULL,
  `Personal_Data_id` INT NOT NULL,
  PRIMARY KEY (`User_id`),
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC),
  INDEX `fk_User_Personal_Data1_idx` (`Personal_Data_id` ASC),
  UNIQUE INDEX `Personal_Data_id_UNIQUE` (`Personal_Data_id` ASC),
  CONSTRAINT `fk_User_Personal_Data1`
    FOREIGN KEY (`Personal_Data_id`)
    REFERENCES `TED`.`Personal_Data` (`idPersonal_Data`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`Friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Friends` ;

CREATE TABLE IF NOT EXISTS `TED`.`Friends` (
  `Relationship_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  `Status` INT NOT NULL,
  INDEX `fk_Friends_User1_idx` (`user_id` ASC),
  INDEX `fk_Friends_User2_idx` (`friend_id` ASC),
  PRIMARY KEY (`Relationship_id`),
  UNIQUE INDEX `Relationship_id_UNIQUE` (`Relationship_id` ASC),
  CONSTRAINT `fk_Friends_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Friends_User2`
    FOREIGN KEY (`friend_id`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`Arthro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Arthro` ;

CREATE TABLE IF NOT EXISTS `TED`.`Arthro` (
  `idArthro` INT NOT NULL,
  `User_id` INT NOT NULL,
  `Text` TEXT(10000) NULL,
  `Photo` VARCHAR(200) NULL,
  `Video` VARCHAR(200) NULL,
  PRIMARY KEY (`idArthro`),
  UNIQUE INDEX `idArthro_UNIQUE` (`idArthro` ASC),
  INDEX `fk_Arthro_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Arthro_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`Sxolio_Arthro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Sxolio_Arthro` ;

CREATE TABLE IF NOT EXISTS `TED`.`Sxolio_Arthro` (
  `idSxolio_Arthro` INT NOT NULL,
  `idArthro` INT NOT NULL,
  `User_id` INT NOT NULL,
  `Comment` TEXT(10000) NULL,
  PRIMARY KEY (`idSxolio_Arthro`),
  UNIQUE INDEX `idSxolio_Arthro_UNIQUE` (`idSxolio_Arthro` ASC),
  INDEX `fk_Sxolio_Arthro_Arthro1_idx` (`idArthro` ASC),
  INDEX `fk_Sxolio_Arthro_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Sxolio_Arthro_Arthro1`
    FOREIGN KEY (`idArthro`)
    REFERENCES `TED`.`Arthro` (`idArthro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sxolio_Arthro_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`PD_Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`PD_Status` ;

CREATE TABLE IF NOT EXISTS `TED`.`PD_Status` (
  `idPersonal_Data` INT NOT NULL,
  `University_Status` INT NULL,
  `Department_Status` INT NULL,
  `Education_Status` INT NULL,
  `Title_Status` INT NULL,
  `Company_Status` INT NULL,
  `Location_Status` INT NULL,
  PRIMARY KEY (`idPersonal_Data`),
  UNIQUE INDEX `idPersonal_Data_UNIQUE` (`idPersonal_Data` ASC),
  CONSTRAINT `fk_table1_Personal_Data1`
    FOREIGN KEY (`idPersonal_Data`)
    REFERENCES `TED`.`Personal_Data` (`idPersonal_Data`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`Interest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Interest` ;

CREATE TABLE IF NOT EXISTS `TED`.`Interest` (
  `idInterest` INT NOT NULL,
  `User_id` INT NOT NULL,
  `idArthro` INT NOT NULL,
  INDEX `fk_Interest_User1_idx` (`User_id` ASC),
  INDEX `fk_Interest_Arthro1_idx` (`idArthro` ASC),
  PRIMARY KEY (`idInterest`),
  UNIQUE INDEX `Interestcol_UNIQUE` (`idInterest` ASC),
  CONSTRAINT `fk_Interest_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Interest_Arthro1`
    FOREIGN KEY (`idArthro`)
    REFERENCES `TED`.`Arthro` (`idArthro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`Aggelia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Aggelia` ;

CREATE TABLE IF NOT EXISTS `TED`.`Aggelia` (
  `idAggelia` INT NOT NULL,
  `user_id` INT NOT NULL,
  `Title` VARCHAR(60) NULL,
  `Description` TEXT(2000) NULL,
  `Requirements` TEXT(2000) NULL,
  `Desired` TEXT(2000) NULL,
  `Location` VARCHAR(100) NULL,
  `Workload` VARCHAR(100) NULL,
  UNIQUE INDEX `idAggelia_UNIQUE` (`idAggelia` ASC),
  PRIMARY KEY (`idAggelia`),
  INDEX `fk_Aggelia_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_Aggelia_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`Aitisi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Aitisi` ;

CREATE TABLE IF NOT EXISTS `TED`.`Aitisi` (
  `idAitisi` INT NOT NULL,
  `idAggelia` INT NOT NULL,
  `user_id` INT NOT NULL,
  `Status` VARCHAR(45) NULL,
  PRIMARY KEY (`idAitisi`),
  UNIQUE INDEX `idAitisi_UNIQUE` (`idAitisi` ASC),
  INDEX `fk_Aitisi_User1_idx` (`user_id` ASC),
  INDEX `fk_Aitisi_Aggelia1_idx` (`idAggelia` ASC),
  CONSTRAINT `fk_Aitisi_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aitisi_Aggelia1`
    FOREIGN KEY (`idAggelia`)
    REFERENCES `TED`.`Aggelia` (`idAggelia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`Admin_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Admin_user` ;

CREATE TABLE IF NOT EXISTS `TED`.`Admin_user` (
  `User_id` INT NOT NULL,
  INDEX `fk_Admin_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Admin_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `TED`.`Personal_Data`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`Personal_Data` (`idPersonal_Data`, `University`, `Department`, `YearFrom`, `YearTo`, `Education`, `Title`, `Company`, `Location`, `Finished_Status`) VALUES (1, 'Kapodistrian University of Athens', 'DIT', 2014, 2018, 'Bachelor-Uni', 'CEO Manager', 'Microsoft Hellas', 'Athens, Greece', 0);
INSERT INTO `TED`.`Personal_Data` (`idPersonal_Data`, `University`, `Department`, `YearFrom`, `YearTo`, `Education`, `Title`, `Company`, `Location`, `Finished_Status`) VALUES (2, 'Aristotelio University', 'FPC', 2014, 2018, 'Bachelor-Uni', 'Software Developer', 'CollegeLink', 'Athens, Greece', 0);
INSERT INTO `TED`.`Personal_Data` (`idPersonal_Data`, `University`, `Department`, `YearFrom`, `YearTo`, `Education`, `Title`, `Company`, `Location`, `Finished_Status`) VALUES (3, 'Kapodistrian University of Athens', 'REDF', 2014, 2018, 'Bachelor-Uni', 'Toilet Cleaner', 'DIT Office', 'Athens, Greece', 0);
INSERT INTO `TED`.`Personal_Data` (`idPersonal_Data`, `University`, `Department`, `YearFrom`, `YearTo`, `Education`, `Title`, `Company`, `Location`, `Finished_Status`) VALUES (4, 'Aegean University of Greece', 'OMN', 2014, 2018, 'Bachelor-TEI', 'Helper', 'MyHouse', 'Krete', 0);
INSERT INTO `TED`.`Personal_Data` (`idPersonal_Data`, `University`, `Department`, `YearFrom`, `YearTo`, `Education`, `Title`, `Company`, `Location`, `Finished_Status`) VALUES (5, 'MIT', 'Computer Science', 2008, 2012, 'Bachelor_Uni', 'Administrator', 'MyLinkedIn', 'Athens, Greece', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `TED`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`User` (`User_id`, `E_Mail`, `Password`, `First_Name`, `Last_Name`, `Phone`, `Photo`, `Personal_Data_id`) VALUES (1, 'errikos@hotmail.com', '81DC9BDB52D04DC20036DBD8313ED055', 'Errikos', 'Gutierrez', '69450575', 'DSC_0101.jpg', 1);
INSERT INTO `TED`.`User` (`User_id`, `E_Mail`, `Password`, `First_Name`, `Last_Name`, `Phone`, `Photo`, `Personal_Data_id`) VALUES (2, 'aris@hotmail.com', 'B59C67BF196A4758191E42F76670CEBA', 'Aris', 'Katopodis', '66612121', 'Marco_the_phoenix_HB.jpg', 2);
INSERT INTO `TED`.`User` (`User_id`, `E_Mail`, `Password`, `First_Name`, `Last_Name`, `Phone`, `Photo`, `Personal_Data_id`) VALUES (3, 'kostmara@hotmail.com', '934B535800B1CBA8F96A5D72F72F1611', 'Kostas', 'Maragkos', '21212212', 'untitled.png', 3);
INSERT INTO `TED`.`User` (`User_id`, `E_Mail`, `Password`, `First_Name`, `Last_Name`, `Phone`, `Photo`, `Personal_Data_id`) VALUES (4, 'stelios@hotmail.com', '4A7D1ED414474E4033AC29CCB8653D9B', 'Stelios', 'Syrtagias', '22121212', 'DODOS2.jpg', 4);
INSERT INTO `TED`.`User` (`User_id`, `E_Mail`, `Password`, `First_Name`, `Last_Name`, `Phone`, `Photo`, `Personal_Data_id`) VALUES (5, 'email@hotmail.com', 'A41ACC7EFFE601DE1DC2099A4E2FDD7C', 'Some', 'User', '111', 'admin.jpg', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `TED`.`Friends`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`Friends` (`Relationship_id`, `user_id`, `friend_id`, `Status`) VALUES (1, 1, 2, 0);
INSERT INTO `TED`.`Friends` (`Relationship_id`, `user_id`, `friend_id`, `Status`) VALUES (2, 2, 1, 0);
INSERT INTO `TED`.`Friends` (`Relationship_id`, `user_id`, `friend_id`, `Status`) VALUES (3, 1, 3, 0);
INSERT INTO `TED`.`Friends` (`Relationship_id`, `user_id`, `friend_id`, `Status`) VALUES (4, 3, 1, 0);
INSERT INTO `TED`.`Friends` (`Relationship_id`, `user_id`, `friend_id`, `Status`) VALUES (5, 3, 4, 0);
INSERT INTO `TED`.`Friends` (`Relationship_id`, `user_id`, `friend_id`, `Status`) VALUES (6, 4, 3, 0);
INSERT INTO `TED`.`Friends` (`Relationship_id`, `user_id`, `friend_id`, `Status`) VALUES (7, 1, 4, 0);
INSERT INTO `TED`.`Friends` (`Relationship_id`, `user_id`, `friend_id`, `Status`) VALUES (8, 4, 1, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `TED`.`Arthro`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`Arthro` (`idArthro`, `User_id`, `Text`, `Photo`, `Video`) VALUES (1, 1, 'Hello World!', NULL, NULL);
INSERT INTO `TED`.`Arthro` (`idArthro`, `User_id`, `Text`, `Photo`, `Video`) VALUES (2, 1, 'Hello World - again!', NULL, NULL);
INSERT INTO `TED`.`Arthro` (`idArthro`, `User_id`, `Text`, `Photo`, `Video`) VALUES (3, 1, 'Guys, here i present you some exercises in Analusi1', 'DODOS5.jpg', NULL);
INSERT INTO `TED`.`Arthro` (`idArthro`, `User_id`, `Text`, `Photo`, `Video`) VALUES (4, 2, 'Hello, look here some plays in last nights lcs.. I am errikos96 if you didnt know haha :p', NULL, 'video.mp4');
INSERT INTO `TED`.`Arthro` (`idArthro`, `User_id`, `Text`, `Photo`, `Video`) VALUES (5, 4, 'Look here a beauty! I met her last year in my vacation :*', 'fani_pic.jpg', NULL);
INSERT INTO `TED`.`Arthro` (`idArthro`, `User_id`, `Text`, `Photo`, `Video`) VALUES (6, 1, 'GEiaaaaaa', 'DSC_0101.jpg', NULL);
INSERT INTO `TED`.`Arthro` (`idArthro`, `User_id`, `Text`, `Photo`, `Video`) VALUES (7, 1, 'EDWWWWW', 'olympiakos_gazzetta.jpg', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `TED`.`Sxolio_Arthro`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`Sxolio_Arthro` (`idSxolio_Arthro`, `idArthro`, `User_id`, `Comment`) VALUES (1, 1, 2, 'Haha - lol!');
INSERT INTO `TED`.`Sxolio_Arthro` (`idSxolio_Arthro`, `idArthro`, `User_id`, `Comment`) VALUES (2, 5, 1, 'Wow so jealous!');

COMMIT;


-- -----------------------------------------------------
-- Data for table `TED`.`PD_Status`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`PD_Status` (`idPersonal_Data`, `University_Status`, `Department_Status`, `Education_Status`, `Title_Status`, `Company_Status`, `Location_Status`) VALUES (1, 0, 0, 0, 0, 0, 0);
INSERT INTO `TED`.`PD_Status` (`idPersonal_Data`, `University_Status`, `Department_Status`, `Education_Status`, `Title_Status`, `Company_Status`, `Location_Status`) VALUES (2, 0, 0, 0, 0, 0, 0);
INSERT INTO `TED`.`PD_Status` (`idPersonal_Data`, `University_Status`, `Department_Status`, `Education_Status`, `Title_Status`, `Company_Status`, `Location_Status`) VALUES (3, 0, 0, 0, 0, 0, 0);
INSERT INTO `TED`.`PD_Status` (`idPersonal_Data`, `University_Status`, `Department_Status`, `Education_Status`, `Title_Status`, `Company_Status`, `Location_Status`) VALUES (4, 0, 0, 0, 0, 0, 0);
INSERT INTO `TED`.`PD_Status` (`idPersonal_Data`, `University_Status`, `Department_Status`, `Education_Status`, `Title_Status`, `Company_Status`, `Location_Status`) VALUES (5, 0, 0, 0, 0, 0, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `TED`.`Interest`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`Interest` (`idInterest`, `User_id`, `idArthro`) VALUES (1, 1, 2);
INSERT INTO `TED`.`Interest` (`idInterest`, `User_id`, `idArthro`) VALUES (2, 1, 1);
INSERT INTO `TED`.`Interest` (`idInterest`, `User_id`, `idArthro`) VALUES (3, 4, 6);
INSERT INTO `TED`.`Interest` (`idInterest`, `User_id`, `idArthro`) VALUES (4, 4, 2);
INSERT INTO `TED`.`Interest` (`idInterest`, `User_id`, `idArthro`) VALUES (5, 4, 1);
INSERT INTO `TED`.`Interest` (`idInterest`, `User_id`, `idArthro`) VALUES (6, 4, 5);
INSERT INTO `TED`.`Interest` (`idInterest`, `User_id`, `idArthro`) VALUES (7, 1, 5);
INSERT INTO `TED`.`Interest` (`idInterest`, `User_id`, `idArthro`) VALUES (8, 1, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `TED`.`Aggelia`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`Aggelia` (`idAggelia`, `user_id`, `Title`, `Description`, `Requirements`, `Desired`, `Location`, `Workload`) VALUES (1, 1, 'CEO manager', 'Microsoft Hellas has opened 20 new positions for hardcore programmers! Join the best company now!', 'It is nessecary to know at least 2 programming languages from the following: C, C++, Javascript, Pearl, C#.', 'Every possible programming language and a University Degree with average of >9.', 'Athens, Greece', '8 days a week');
INSERT INTO `TED`.`Aggelia` (`idAggelia`, `user_id`, `Title`, `Description`, `Requirements`, `Desired`, `Location`, `Workload`) VALUES (2, 2, 'Software Engineer', 'We are Evil Corp - the greatest evil company - and we are looking for a Software Engineer.', 'Must be an awsome programmer', 'Must know every programming language ever( even cobol and fortran)', 'Athens, Greece', 'All day');
INSERT INTO `TED`.`Aggelia` (`idAggelia`, `user_id`, `Title`, `Description`, `Requirements`, `Desired`, `Location`, `Workload`) VALUES (3, 3, 'CEO Manager2', 'It is a different CEO Manager this time... Evil corp strikes again', 'Everything is required of course.. hahahah', 'Every knowloedge ever.. +) If you are affiliated with the hacker underground community come and expose them!', 'In yo mama\'s', '24/7');
INSERT INTO `TED`.`Aggelia` (`idAggelia`, `user_id`, `Title`, `Description`, `Requirements`, `Desired`, `Location`, `Workload`) VALUES (4, 4, 'GOD', 'Hahaha thought the requiements couldn\'t be any higher? we are actually not even hiring anyone anymore!', 'Get off man!', 'Everything! Im kidding not even enough', '*blanc*', 'come on...');
INSERT INTO `TED`.`Aggelia` (`idAggelia`, `user_id`, `Title`, `Description`, `Requirements`, `Desired`, `Location`, `Workload`) VALUES (5, 1, 'DIT Professor', 'Just kidding - come work at Evil Corp plz...We are desperate now', 'Ok this time we dont require anything! Ok just betraying your friends and killing your family but you know!', 'Nothing?', 'Ahens', '1 hour per week');

COMMIT;


-- -----------------------------------------------------
-- Data for table `TED`.`Aitisi`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`Aitisi` (`idAitisi`, `idAggelia`, `user_id`, `Status`) VALUES (1, 1, 2, '1');
INSERT INTO `TED`.`Aitisi` (`idAitisi`, `idAggelia`, `user_id`, `Status`) VALUES (2, 3, 1, '1');
INSERT INTO `TED`.`Aitisi` (`idAitisi`, `idAggelia`, `user_id`, `Status`) VALUES (3, 4, 1, '1');
INSERT INTO `TED`.`Aitisi` (`idAitisi`, `idAggelia`, `user_id`, `Status`) VALUES (4, 1, 3, '1');
INSERT INTO `TED`.`Aitisi` (`idAitisi`, `idAggelia`, `user_id`, `Status`) VALUES (5, 5, 3, '1');

COMMIT;


-- -----------------------------------------------------
-- Data for table `TED`.`Admin_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `TED`;
INSERT INTO `TED`.`Admin_user` (`User_id`) VALUES (5);

COMMIT;

-- -----------------------------------------------------
-- Table `TED`.`Chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Chat` ;

CREATE TABLE IF NOT EXISTS `TED`.`Chat` (
  `idChat` INT NOT NULL AUTO_INCREMENT,
  `User1` INT NOT NULL,
  `User2` INT NOT NULL,
  `Status` INT NOT NULL,
  PRIMARY KEY (`idChat`),
  UNIQUE INDEX `idChat_UNIQUE` (`idChat` ASC),
  INDEX `fk_Chat_User1_idx` (`User1` ASC),
  INDEX `fk_Chat_User2_idx` (`User2` ASC),
  CONSTRAINT `fk_Chat_User1`
    FOREIGN KEY (`User1`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Chat_User2`
    FOREIGN KEY (`User2`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`Active`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Active` ;

CREATE TABLE IF NOT EXISTS `TED`.`Active` (
  `idActive` INT NOT NULL AUTO_INCREMENT,
  `User_id` INT NOT NULL,
  `Chat_id` INT NOT NULL,
  PRIMARY KEY (`idActive`),
  UNIQUE INDEX `idActive_UNIQUE` (`idActive` ASC),
  INDEX `fk_Active_User1_idx` (`User_id` ASC),
  INDEX `fk_Active_Chat1_idx` (`Chat_id` ASC),
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC),
  CONSTRAINT `fk_Active_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `TED`.`User` (`User_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Active_Chat1`
    FOREIGN KEY (`Chat_id`)
    REFERENCES `TED`.`Chat` (`idChat`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TED`.`Chat_Data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TED`.`Chat_Data` ;

CREATE TABLE IF NOT EXISTS `TED`.`Chat_Data` (
  `idChat_Data` INT NOT NULL AUTO_INCREMENT,
  `Chat_id` INT NOT NULL,
  `User_id` INT NOT NULL,
  `Text` VARCHAR(100) NOT NULL,
  `Date` VARCHAR(20) NOT NULL,
  `Time` VARCHAR(20) NOT NULL,
  `sr_no` INT NOT NULL,
  PRIMARY KEY (`idChat_Data`),
  UNIQUE INDEX `idchat_data_UNIQUE` (`idChat_Data` ASC),
  INDEX `fk_chat_data_Chat1_idx` (`Chat_id` ASC),
  CONSTRAINT `fk_chat_data_Chat1`
    FOREIGN KEY (`Chat_id`)
    REFERENCES `TED`.`Chat` (`idChat`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

