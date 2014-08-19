drop database if exists invent;
create database invent;
use invent;
/*
Created: 7/23/2014
Modified: 7/24/2014
Model: MySQL 5.5
Database: MySQL 5.5
*/

-- Create tables section -------------------------------------------------

-- Table invent.item

CREATE TABLE invent.item (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcategory` smallint(5) unsigned NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `provider` varchar(45) DEFAULT NULL,
  `rating` decimal(4,2) unsigned DEFAULT NULL,
  `url_snapshot` varchar(254) DEFAULT NULL,
  `url_image` varchar(254) DEFAULT NULL,
  `sys_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idsys_user` int(11) NOT NULL
) ENGINE=InnoDB;

ALTER TABLE invent.item ADD PRIMARY KEY (id)
;

ALTER TABLE invent.item ADD INDEX itemprovider (provider)
;

-- Table invent.category

CREATE TABLE invent.category
(
  id Smallint UNSIGNED NOT NULL AUTO_INCREMENT,
  description Varchar(30) NOT NULL,
  status int NOT NULL DEFAULT 0
  COMMENT '0=registered,1=active,2=suspended,3=deleted',
 PRIMARY KEY (id)
)
;

-- Table invent.order_type

CREATE TABLE invent.order_type
(
  id Int NOT NULL AUTO_INCREMENT,
  description Varchar(30) NOT NULL,
  status int NOT NULL DEFAULT 0
  COMMENT '0=registered,1=active,2=suspended,3=deleted',
 PRIMARY KEY (id)
)
;

-- Table invent.order_workflow

CREATE TABLE invent.order_workflow
(
  id Int NOT NULL AUTO_INCREMENT,
  description Varchar(30) NOT NULL,
 PRIMARY KEY (id)
)
;

-- Table invent.order_status

CREATE TABLE invent.order_status
(
  id Int NOT NULL AUTO_INCREMENT,
  description Varchar(30) NOT NULL,
  idworkflow Int NOT NULL,
  status int NOT NULL DEFAULT 0
  COMMENT '0=registered,1=active,2=suspended,3=deleted',
 PRIMARY KEY (id)
)
;

-- Table invent.inventory

CREATE TABLE invent.inventory
(
  id Int NOT NULL AUTO_INCREMENT,
  code Varchar(10) NOT NULL,
  iditem Int(11) NOT NULL,
  quantity Decimal(14,6) UNSIGNED NOT NULL DEFAULT 0,
  idunit Int NOT NULL,
  quantity_min Decimal(14,6) NOT NULL DEFAULT 0,
  locked Bit(1) NOT NULL DEFAULT 0
)
;

ALTER TABLE invent.inventory ADD PRIMARY KEY (id)
;

ALTER TABLE invent.inventory ADD UNIQUE code (code)
;

-- Table invent.storage

CREATE TABLE invent.storage
(
  id Int NOT NULL AUTO_INCREMENT,
  code Varchar(8) NOT NULL,
  description Varchar(30) NOT NULL,
  status int NOT NULL DEFAULT 0
  COMMENT '0=registered,1=active,2=suspended,3=deleted',
 PRIMARY KEY (id)
)
;

ALTER TABLE invent.storage ADD UNIQUE code (code)
;

-- Table invent.storage_movement

CREATE TABLE invent.storage_movement
(
  id Int NOT NULL AUTO_INCREMENT,
  idstorage_src Int,
  idstorage_dst Int,
  date_move Datetime NOT NULL,
  type INT NOT NULL DEFAULT 2
  COMMENT '0=Input,1=Output,2=Relocation',
  idinventory_detail Int NOT NULL,
  idsys_user Int,
  sys_stamp Timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (id)
)
;

-- Table invent.unit

CREATE TABLE invent.unit
(
  id Int NOT NULL AUTO_INCREMENT,
  name Varchar(30) NOT NULL,
  description Varchar(50),
  data_type int NOT NULL DEFAULT 4
  COMMENT '0=Date,1=Text,2=Integer,3=Float,4=Other',
 PRIMARY KEY (id)
)
;

ALTER TABLE invent.unit ADD UNIQUE name (name)
;

-- Table invent.inventory_detail

CREATE TABLE invent.inventory_detail
(
  id Int NOT NULL AUTO_INCREMENT,
  idinventory Int NOT NULL,
  idunit Int NOT NULL,
  quantity Decimal(14,6) UNSIGNED NOT NULL DEFAULT 0,
  quantity_std Decimal(14,6) NOT NULL DEFAULT 0,
  date_entry Datetime NOT NULL,
  idstorage Int,
  price_entry Decimal(14,4) DEFAULT 0,
  comment Varchar(50),
  expiration Date,
 PRIMARY KEY (id)
)
;

-- Table invent.customer_type

CREATE TABLE invent.customer_type
(
  id Int NOT NULL AUTO_INCREMENT,
  description Varchar(30) NOT NULL,
 PRIMARY KEY (id)
)
;

-- Table invent.customer

CREATE TABLE invent.customer
(
  id Int NOT NULL AUTO_INCREMENT,
  code Varchar(8) NOT NULL,
  name Varchar(30) NOT NULL,
  idtype Int NOT NULL,
  idstatus Int,
  description Varchar(50),
 PRIMARY KEY (id)
)
;

ALTER TABLE invent.customer ADD UNIQUE code (code)
;

-- Table invent.customer_status

CREATE TABLE invent.customer_status
(
  id Int NOT NULL AUTO_INCREMENT,
  description Varchar(30) NOT NULL,
  status int NOT NULL DEFAULT 0
  COMMENT '0=registered,1=active,2=suspended,3=deleted',
 PRIMARY KEY (id)
)
;

-- Table invent.order

CREATE TABLE invent.order
(
  id Int NOT NULL AUTO_INCREMENT,
  idtype Int NOT NULL,
  idstatus Int NOT NULL,
  idcustomer Int,
  date Datetime NOT NULL,
  num_items Int UNSIGNED NOT NULL DEFAULT 0,
  price_total Decimal(14,4) UNSIGNED NOT NULL DEFAULT 0,
  comments Varchar(50),
  idsys_user Int,
  sys_stamp Timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (id)
)
;

-- Table invent.order_detail

CREATE TABLE invent.order_detail
(
  id Int NOT NULL AUTO_INCREMENT,
  idorder Int NOT NULL,
  iditem Int NOT NULL,
  quantity Decimal(14,6) NOT NULL DEFAULT 0,
  price_item Decimal(14,4) UNSIGNED NOT NULL DEFAULT 0,
  price_subtotal Decimal(14,4) UNSIGNED,
  cancelled Bit(1) NOT NULL DEFAULT 0,
  idstorage_mov Int,
 PRIMARY KEY (id)
)
;

-- Table invent.sys_user

CREATE TABLE invent.sys_user
(
  id Int NOT NULL AUTO_INCREMENT,
  name Varchar(50) NOT NULL,
  password Varchar(50) NOT NULL,
  enabled Bit(1) NOT NULL DEFAULT 0,
  status Enum('R','A','S','B','D') NOT NULL DEFAULT 'R'
  COMMENT 'R=registered,A=Active,S=Suspended,B=Blocked,D=Deleted',
 PRIMARY KEY (id)
)
;

ALTER TABLE invent.sys_user ADD UNIQUE name (name)
;

-- Table invent.sys_authority

CREATE TABLE invent.sys_authority
(
  id Int NOT NULL AUTO_INCREMENT,
  name Varchar(30) NOT NULL,
 PRIMARY KEY (id)
)
;

ALTER TABLE invent.sys_authority ADD UNIQUE name (name)
;

-- Table invent.sys_group

CREATE TABLE invent.sys_group
(
  id Int NOT NULL AUTO_INCREMENT,
  name Varchar(30) NOT NULL,
 PRIMARY KEY (id)
)
;

ALTER TABLE invent.sys_group ADD UNIQUE name (name)
;

-- Table invent.sys_group_authority

CREATE TABLE invent.sys_group_authority
(
  idgroup Int NOT NULL,
  idauthority Int NOT NULL
)
;

ALTER TABLE invent.sys_group_authority ADD PRIMARY KEY (idgroup,idauthority)
;

-- Table invent.sys_user_group

CREATE TABLE invent.sys_user_group
(
  iduser Int NOT NULL,
  idgroup Int NOT NULL
)
;

ALTER TABLE invent.sys_user_group ADD PRIMARY KEY (idgroup,iduser)
;

-- Table invent.sys_user_preferences

CREATE TABLE invent.sys_user_preferences
(
  id Int NOT NULL AUTO_INCREMENT,
  idsys_user Int NOT NULL,
  keyval Varchar(10) NOT NULL DEFAULT '',
  value Varchar(20) DEFAULT '',
 PRIMARY KEY (id)
)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE invent.item ADD CONSTRAINT Relationship1 FOREIGN KEY (idcategory) REFERENCES invent.category (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.order_status ADD CONSTRAINT Relationship4 FOREIGN KEY (idworkflow) REFERENCES invent.order_workflow (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.inventory ADD CONSTRAINT Relationship7 FOREIGN KEY (iditem) REFERENCES invent.item (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.storage_movement ADD CONSTRAINT Relationship11 FOREIGN KEY (idstorage_src) REFERENCES invent.storage (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.storage_movement ADD CONSTRAINT Relationship13 FOREIGN KEY (idstorage_dst) REFERENCES invent.storage (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.inventory ADD CONSTRAINT Relationship15 FOREIGN KEY (idunit) REFERENCES invent.unit (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.inventory_detail ADD CONSTRAINT Relationship17 FOREIGN KEY (idunit) REFERENCES invent.unit (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.inventory_detail ADD CONSTRAINT Relationship19 FOREIGN KEY (idinventory) REFERENCES invent.inventory (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.inventory_detail ADD CONSTRAINT Relationship20 FOREIGN KEY (idstorage) REFERENCES invent.storage (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.customer ADD CONSTRAINT Relationship23 FOREIGN KEY (idtype) REFERENCES invent.customer_type (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.customer ADD CONSTRAINT Relationship24 FOREIGN KEY (idstatus) REFERENCES invent.customer_status (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.order ADD CONSTRAINT Relationship26 FOREIGN KEY (idstatus) REFERENCES invent.order_status (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.order ADD CONSTRAINT Relationship27 FOREIGN KEY (idtype) REFERENCES invent.order_type (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.order ADD CONSTRAINT Relationship28 FOREIGN KEY (idcustomer) REFERENCES invent.customer (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.order_detail ADD CONSTRAINT Relationship29 FOREIGN KEY (idorder) REFERENCES invent.order (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.order_detail ADD CONSTRAINT Relationship30 FOREIGN KEY (idstorage_mov) REFERENCES invent.storage_movement (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.order_detail ADD CONSTRAINT Relationship31 FOREIGN KEY (iditem) REFERENCES invent.inventory_detail (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.sys_group_authority ADD CONSTRAINT Relationship32 FOREIGN KEY (idgroup) REFERENCES invent.sys_group (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.sys_group_authority ADD CONSTRAINT Relationship34 FOREIGN KEY (idauthority) REFERENCES invent.sys_authority (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.sys_user_group ADD CONSTRAINT Relationship35 FOREIGN KEY (idgroup) REFERENCES invent.sys_group (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.sys_user_group ADD CONSTRAINT Relationship36 FOREIGN KEY (iduser) REFERENCES invent.sys_user (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.order ADD CONSTRAINT Relationship37 FOREIGN KEY (idsys_user) REFERENCES invent.sys_user (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.storage_movement ADD CONSTRAINT Relationship38 FOREIGN KEY (idinventory_detail) REFERENCES invent.inventory_detail (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.storage_movement ADD CONSTRAINT Relationship39 FOREIGN KEY (idsys_user) REFERENCES invent.sys_user (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE invent.sys_user_preferences ADD CONSTRAINT Relationship40 FOREIGN KEY (idsys_user) REFERENCES invent.sys_user (id) ON DELETE RESTRICT ON UPDATE RESTRICT
;



