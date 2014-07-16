-- general data
INSERT INTO `invent`.`category` (`description`) VALUES ('seed');
INSERT INTO `invent`.`category` (`description`) VALUES ('tube');
INSERT INTO `invent`.`category` (`description`) VALUES ('leaf bag');

INSERT INTO `invent`.`order_workflow` (`id`, `description`) VALUES (1, 'inventory disposal');
INSERT INTO `invent`.`order_workflow` (`id`, `description`) VALUES (2, 'internal buy');
INSERT INTO `invent`.`order_workflow` (`id`, `description`) VALUES (3, 'external buy');
INSERT INTO `invent`.`order_workflow` (`id`, `description`) VALUES (4, 'inventory transfer');

INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (1, 'created', 3, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (2, 'validated', 3, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (3, 'dispatched', 3, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (4, 'sended', 3, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (5, 'finished', 3, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (6, 'requested', 1, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (7, 'authorized', 1, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (8, 'finished', 1, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (9, 'requested', 2, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (10, 'authorized', 2, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (11, 'dispatched', 2, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (12, 'sended', 2, 'S');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (13, 'completed', 2, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (14, 'requested', 4, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (15, 'authorized', 4, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (16, 'transfered', 4, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (17, 'cancelled', 1, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (18, 'cancelled', 2, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (19, 'cancelled', 3, 'A');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (20, 'closed', 2, 'D');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`, `status`) VALUES (21, 'closed', 3, 'D');
INSERT INTO `invent`.`order_status` (`id`, `description`, `idworkflow`) VALUES (22, 'cancelled', 4);

INSERT INTO `invent`.`order_type` (`description`, `status`) VALUES ('internal', 'A');
INSERT INTO `invent`.`order_type` (`description`, `status`) VALUES ('external', 'D');
INSERT INTO `invent`.`order_type` (`description`, `status`) VALUES ('temporal', 'S');
INSERT INTO `invent`.`order_type` (`description`, `status`) VALUES ('external', 'A');

INSERT INTO `invent`.`customer_type` (`id`, `description`) VALUES (1, 'Company');
INSERT INTO `invent`.`customer_type` (`id`, `description`) VALUES (2, 'Person');
INSERT INTO `invent`.`customer_type` (`id`, `description`) VALUES (3, 'Government');
INSERT INTO `invent`.`customer_type` (`id`, `description`) VALUES (4, 'ONG');

INSERT INTO `invent`.`customer_status` (`id`, `description`, `status`) VALUES (1, 'New', 'A');
INSERT INTO `invent`.`customer_status` (`id`, `description`, `status`) VALUES (2, 'Authorized', 'D');
INSERT INTO `invent`.`customer_status` (`id`, `description`, `status`) VALUES (3, 'No Authorized', 'D');
INSERT INTO `invent`.`customer_status` (`id`, `description`, `status`) VALUES (4, 'Active', 'A');
INSERT INTO `invent`.`customer_status` (`id`, `description`, `status`) VALUES (5, 'Blocked', 'A');
INSERT INTO `invent`.`customer_status` (`id`, `description`, `status`) VALUES (6, 'Deleted', 'A');
INSERT INTO `invent`.`customer_status` (`id`, `description`, `status`) VALUES (7, 'Pending authorization', 'S');

INSERT INTO `invent`.`unit` (`id`, `name`, `description`, `data_type`) VALUES (1, 'grams', 'metric grams', 'F');
INSERT INTO `invent`.`unit` (`id`, `name`, `description`, `data_type`) VALUES (2, 'kilograms', 'metric kilograms', 'F');
INSERT INTO `invent`.`unit` (`id`, `name`, `description`, `data_type`) VALUES (3, 'tons', 'metric tons', 'F');

-- secondary data
INSERT INTO `invent`.`storage` (`code`, `description`, `status`) VALUES ('A123', 'deck1', 'A');
INSERT INTO `invent`.`storage` (`code`, `description`, `status`) VALUES ('A124', 'deck2', 'A');
INSERT INTO `invent`.`storage` (`code`, `description`, `status`) VALUES ('A125', 'desck3', 'D');
INSERT INTO `invent`.`storage` (`code`, `description`, `status`) VALUES ('F1', 'freezer1', 'A');
INSERT INTO `invent`.`storage` (`code`, `description`) VALUES ('F2', 'freezer2');

INSERT INTO `invent`.`customer` (`code`, `name`, `idtype`, `idstatus`, `description`) VALUES ('cus1', 'mycompany1', 1, 4, 'a national company');
INSERT INTO `invent`.`customer` (`code`, `name`, `idtype`, `idstatus`, `description`) VALUES ('cus2', 'acompany', 1, 4, 'international company');
INSERT INTO `invent`.`customer` (`code`, `name`, `idtype`, `idstatus`, `description`) VALUES ('cus3', 'mybusiness', 1, 4, 'a local company');
INSERT INTO `invent`.`customer` (`code`, `name`, `idtype`, `idstatus`, `description`) VALUES ('cus4', 'super', 2, 4, 'simple person');
INSERT INTO `invent`.`customer` (`code`, `name`, `idtype`, `idstatus`, `description`) VALUES ('custom5', 'anonymous', 2, 5, 'suspicious person');
INSERT INTO `invent`.`customer` (`code`, `name`, `idtype`, `idstatus`) VALUES ('ab1', 'north country', 3, 6);
INSERT INTO `invent`.`customer` (`code`, `name`, `idtype`, `idstatus`) VALUES ('bc2', 'new north country', 3, 4);
INSERT INTO `invent`.`customer` (`code`, `name`, `idtype`, `idstatus`) VALUES ('abc1', 'save the sun', 4, 4);
INSERT INTO `invent`.`customer` (`code`, `name`, `idtype`, `idstatus`) VALUES ('abc2', 'green war', 4, 4);

-- access
INSERT INTO `invent`.`sys_user` (`id`, `name`, `password`, `enabled`, `status`) VALUES (1, 'guest', 'g', 1, 'A');
INSERT INTO `invent`.`sys_user` (`id`, `name`, `password`, `enabled`, `status`) VALUES (2, 'guest2', 'gg', 0, 'B');
INSERT INTO `invent`.`sys_user` (`id`, `name`, `password`, `enabled`) VALUES (3, 'admin', 'a', 1);

INSERT INTO `invent`.`sys_group` (`id`, `name`) VALUES (1, 'manager');
INSERT INTO `invent`.`sys_group` (`id`, `name`) VALUES (2, 'assistant');
INSERT INTO `invent`.`sys_group` (`id`, `name`) VALUES (3, 'guest');

INSERT INTO `invent`.`sys_authority` (`id`, `name`) VALUES (1, 'manager');
INSERT INTO `invent`.`sys_authority` (`id`, `name`) VALUES (2, 'operator');
INSERT INTO `invent`.`sys_authority` (`id`, `name`) VALUES (3, 'auditor');
INSERT INTO `invent`.`sys_authority` (`id`, `name`) VALUES (4, 'limited');

INSERT INTO `invent`.`sys_user_group` (`iduser`, `idgroup`) VALUES (1, 3);
INSERT INTO `invent`.`sys_user_group` (`iduser`, `idgroup`) VALUES (2, 3);
INSERT INTO `invent`.`sys_user_group` (`iduser`, `idgroup`) VALUES (3, 1);

INSERT INTO `invent`.`sys_group_authority` (`idgroup`, `idauthority`) VALUES (1, 1);
INSERT INTO `invent`.`sys_group_authority` (`idgroup`, `idauthority`) VALUES (2, 2);
INSERT INTO `invent`.`sys_group_authority` (`idgroup`, `idauthority`) VALUES (3, 4);
INSERT INTO `invent`.`sys_group_authority` (`idgroup`, `idauthority`) VALUES (1, 3);

-- core bisiness
INSERT INTO `invent`.`item` (`id`, `code`, `name`, `idcategory`, `description`, `price`) VALUES (1, 'item1', 'rice', 1, 'white and small', 5);
INSERT INTO `invent`.`item` (`id`, `code`, `name`, `idcategory`, `description`, `price`) VALUES (2, 'item2', 'wheat', 1, 'brownish and small', 5);
INSERT INTO `invent`.`item` (`id`, `code`, `name`, `idcategory`, `description`, `price`) VALUES (3, 'item3', 'maize', 1, 'yellow and medium', 5);
INSERT INTO `invent`.`item` (`id`, `code`, `name`, `idcategory`) VALUES (4, 'item4', 'potato', 3);
INSERT INTO `invent`.`item` (`id`, `code`, `name`, `idcategory`, `description`) VALUES (5, 'item4.1', 'potato', 1, 'big pale brown');
