/**
* just run over database with original schema;
* for new databases, running create script is enough
**/
 
ALTER TABLE `invent`.`item` 
DROP COLUMN `code`,
CHANGE COLUMN `idcategory` `idcategory` SMALLINT(5) UNSIGNED NOT NULL AFTER `id`,
CHANGE COLUMN `price` `rating` DECIMAL(4,2) UNSIGNED NULL DEFAULT NULL ,
ADD COLUMN `provider` VARCHAR(45) NULL AFTER `description`,
ADD COLUMN `url_snapshot` VARCHAR(254) NULL AFTER `rating`,
ADD COLUMN `url_image` VARCHAR(254) NULL AFTER `url_snapshot`,
ADD COLUMN `sys_stamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `url_image`,
ADD COLUMN `idsys_user` INT NOT NULL AFTER `sys_stamp`,
ADD INDEX `itemprovider` (`provider` ASC),
DROP INDEX `code` ;
 
ALTER TABLE `invent`.`storage_movement` CHANGE COLUMN `sys_stamp` `sys_stamp` Timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE `invent`.`order` CHANGE COLUMN `sys_stamp` `sys_stamp` Timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE `invent`.`item` CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `invent`.`inventory` CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `invent`.`inventory` CHANGE COLUMN `code` `code` VARCHAR(10) NOT NULL;
