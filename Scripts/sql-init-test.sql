SELECT 'CONFIG BEGIN' AS '';

SET FOREIGN_KEY_CHECKS = 0;
DROP DATABASE IF EXISTS ProjectHandlerDB;

SET FOREIGN_KEY_CHECKS = 1;
CREATE DATABASE IF NOT EXISTS ProjectHandlerDB CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci';
GRANT ALL PRIVILEGES ON ProjectHandlerDB.* To 'ProjectHandlerUser'@'localhost' IDENTIFIED BY 'ProjectHandlerPassword';

USE `ProjectHandlerDB`;

SET NAMES 'utf8';
SET CHARSET 'utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`CLIENT`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`CLIENT` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(50) DEFAULT NULL,
  `CLIENT_NAME` varchar(50) DEFAULT NULL,
  `PHONE_NUMBER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_js7gatcxtql775a2okcd68h5v` (`EMAIL`),
  UNIQUE KEY `UK_ik5occ35bby2t5io6k325ma4f` (`CLIENT_NAME`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`COMMENT`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`COMMENT` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(255) DEFAULT NULL,
  `POST_DATE` datetime DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `PARENT_COMMENT_ID` bigint(20) DEFAULT NULL,
  `TASK_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc4guf2g3mhm8dd6rvogse4wb0` (`USER_ID`),
  KEY `FKetxtvxcb3ck86tnl4f8dlyqi1` (`PARENT_COMMENT_ID`),
  KEY `FKi6fnw82a12cqh8pi89kbq8w22` (`TASK_ID`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`FORUM`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`FORUM` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `FORUM_TOPIC` varchar(50) DEFAULT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrewdwmetq51yl68n07oinq4mk` (`PROJECT_ID`),
  KEY `FKpw0ii6nmxg5lioijuxi28bbrc` (`USER_ID`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`NOTIFICATION`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`NOTIFICATION` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATION_DATE` datetime DEFAULT NULL,
  `NOTIFICATION_READ` datetime DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `relatedTask_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi4qsgvxj6sej10bi0jujql4rt` (`relatedTask_id`),
  KEY `FK16c2apdkel73r81ed2dpwv0a2` (`user_id`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`POST`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`POST` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(255) DEFAULT NULL,
  `POST_DATE` datetime DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `FORUM_ID` bigint(20) DEFAULT NULL,
  `PARENT_POST_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk57duahs09p84ac63gbqbvx9v` (`USER_ID`),
  KEY `FKpdgos4um8q5tq7s6rls5it0o` (`FORUM_ID`),
  KEY `FKeexi8in63yl6u50qjkiejtg2n` (`PARENT_POST_ID`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`PROJECT`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`PROJECT` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DEADLINE` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `KICKOFF` datetime DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `PROJECT_STATE` varchar(255) DEFAULT NULL,
  `CLIENT_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_91c3xxc5m2dkyclcjpg660bal` (`NAME`),
  KEY `FKiss9q9d6rm705cyttx7fmic2k` (`CLIENT_ID`),
  KEY `FKt6v2qof43obw55i8nd9g8pe7a` (`USER_ID`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`PROJECTS`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`PROJECTS` (
  `USER_ID` bigint(20) NOT NULL,
  `PROJ_ID` bigint(20) NOT NULL,
  KEY `FKiie7o3bgqf53pcl0at8ev30ec` (`PROJ_ID`),
  KEY `FK6fjihsg0qqr4a4xupoqiqyyha` (`USER_ID`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`RECORD`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`RECORD` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `LOGIN_TIME` datetime DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdm8pr1wxend622r3433fc9nkc` (`USER_ID`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`ROLE`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`ROLE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`ROLES`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`ROLES` (
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  KEY `FKk5jytqi28y89s2i84l0k5k83n` (`ROLE_ID`),
  KEY `FK2am0xc3dfwskt28nrijeu8t41` (`USER_ID`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`TASK`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`TASK` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DEADLINE` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `PRIORITY` varchar(50) DEFAULT NULL,
  `TASK_STATUS` varchar(25) DEFAULT NULL,
  `TASK_TYPE` varchar(25) DEFAULT NULL,
  `PARENT_TASK_ID` bigint(20) DEFAULT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tbaloihbh4a7pjhcgjmxfdmji` (`NAME`),
  KEY `FKeod6k320dksd10jvt6vvpek8m` (`PARENT_TASK_ID`),
  KEY `FKqq26ebk96qkkvss8xp8julmuf` (`PROJECT_ID`),
  KEY `FKjuv3v1hakvwr93spvwxd5xeyy` (`USER_ID`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`USER`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`USER` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `BIRTH_DATE` date DEFAULT NULL,
  `FIRST_NAME` varchar(25) DEFAULT NULL,
  `GENDER` varchar(10) DEFAULT NULL,
  `LAST_NAME` varchar(25) DEFAULT NULL,
  `PICTURE` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(200) DEFAULT NULL,
  `POSITION` varchar(50) DEFAULT NULL,
  `USER_NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h029unq4qgmbvesub83df4vok` (`USER_NAME`)
) DEFAULT CHARSET='utf8';

DROP TABLE IF EXISTS `ProjectHandlerDB`.`USER_SETTINGS`;
CREATE TABLE IF NOT EXISTS `ProjectHandlerDB`.`USER_SETTINGS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `PAGING` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK43u49hgqhkjtexv79mv5gjmc9` (`user_id`)
) DEFAULT CHARSET='utf8';

ALTER TABLE `ProjectHandlerDB`.`COMMENT` ADD CONSTRAINT `FKc4guf2g3mhm8dd6rvogse4wb0` FOREIGN KEY (`USER_ID`) REFERENCES `ProjectHandlerDB`.`USER` (`id`);
ALTER TABLE `ProjectHandlerDB`.`COMMENT` ADD CONSTRAINT `FKetxtvxcb3ck86tnl4f8dlyqi1` FOREIGN KEY (`PARENT_COMMENT_ID`) REFERENCES `ProjectHandlerDB`.`COMMENT` (`id`);
ALTER TABLE `ProjectHandlerDB`.`COMMENT` ADD CONSTRAINT `FKi6fnw82a12cqh8pi89kbq8w22` FOREIGN KEY (`TASK_ID`) REFERENCES `ProjectHandlerDB`.`TASK` (`id`);

ALTER TABLE `ProjectHandlerDB`.`FORUM` ADD CONSTRAINT `FKpw0ii6nmxg5lioijuxi28bbrc` FOREIGN KEY (`USER_ID`) REFERENCES `ProjectHandlerDB`.`USER` (`id`);
ALTER TABLE `ProjectHandlerDB`.`FORUM` ADD CONSTRAINT `FKrewdwmetq51yl68n07oinq4mk` FOREIGN KEY (`PROJECT_ID`) REFERENCES `ProjectHandlerDB`.`PROJECT` (`id`);

ALTER TABLE `ProjectHandlerDB`.`NOTIFICATION` ADD CONSTRAINT `FK16c2apdkel73r81ed2dpwv0a2` FOREIGN KEY (`user_id`) REFERENCES `ProjectHandlerDB`.`USER` (`id`);
ALTER TABLE `ProjectHandlerDB`.`NOTIFICATION` ADD CONSTRAINT `FKi4qsgvxj6sej10bi0jujql4rt` FOREIGN KEY (`relatedTask_id`) REFERENCES `ProjectHandlerDB`.`TASK` (`id`);

ALTER TABLE `ProjectHandlerDB`.`POST` ADD CONSTRAINT `FKeexi8in63yl6u50qjkiejtg2n` FOREIGN KEY (`PARENT_POST_ID`) REFERENCES `ProjectHandlerDB`.`POST` (`id`);
ALTER TABLE `ProjectHandlerDB`.`POST` ADD CONSTRAINT `FKk57duahs09p84ac63gbqbvx9v` FOREIGN KEY (`USER_ID`) REFERENCES `ProjectHandlerDB`.`USER` (`id`);
ALTER TABLE `ProjectHandlerDB`.`POST` ADD CONSTRAINT `FKpdgos4um8q5tq7s6rls5it0o` FOREIGN KEY (`FORUM_ID`) REFERENCES `ProjectHandlerDB`.`FORUM` (`id`);

ALTER TABLE `ProjectHandlerDB`.`PROJECT` ADD CONSTRAINT `FKiss9q9d6rm705cyttx7fmic2k` FOREIGN KEY (`CLIENT_ID`) REFERENCES `ProjectHandlerDB`.`CLIENT` (`id`);
ALTER TABLE `ProjectHandlerDB`.`PROJECT` ADD CONSTRAINT `FKt6v2qof43obw55i8nd9g8pe7a` FOREIGN KEY (`USER_ID`) REFERENCES `ProjectHandlerDB`.`USER` (`id`);

ALTER TABLE `ProjectHandlerDB`.`PROJECTS` ADD CONSTRAINT `FK6fjihsg0qqr4a4xupoqiqyyha` FOREIGN KEY (`USER_ID`) REFERENCES `ProjectHandlerDB`.`USER` (`id`);
ALTER TABLE `ProjectHandlerDB`.`PROJECTS` ADD CONSTRAINT `FKiie7o3bgqf53pcl0at8ev30ec` FOREIGN KEY (`PROJ_ID`) REFERENCES `ProjectHandlerDB`.`PROJECT` (`id`);

ALTER TABLE `ProjectHandlerDB`.`RECORD` ADD CONSTRAINT `FKdm8pr1wxend622r3433fc9nkc` FOREIGN KEY (`USER_ID`) REFERENCES `ProjectHandlerDB`.`USER` (`id`);

ALTER TABLE `ProjectHandlerDB`.`ROLES` ADD CONSTRAINT `FK2am0xc3dfwskt28nrijeu8t41` FOREIGN KEY (`USER_ID`) REFERENCES `ProjectHandlerDB`.`USER` (`id`);
ALTER TABLE `ProjectHandlerDB`.`ROLES` ADD CONSTRAINT `FKk5jytqi28y89s2i84l0k5k83n` FOREIGN KEY (`ROLE_ID`) REFERENCES `ProjectHandlerDB`.`ROLE` (`id`);

ALTER TABLE `ProjectHandlerDB`.`TASK` ADD CONSTRAINT `FKeod6k320dksd10jvt6vvpek8m` FOREIGN KEY (`PARENT_TASK_ID`) REFERENCES `ProjectHandlerDB`.`TASK` (`id`);
ALTER TABLE `ProjectHandlerDB`.`TASK` ADD CONSTRAINT `FKjuv3v1hakvwr93spvwxd5xeyy` FOREIGN KEY (`USER_ID`) REFERENCES `ProjectHandlerDB`.`USER` (`id`);
ALTER TABLE `ProjectHandlerDB`.`TASK` ADD CONSTRAINT `FKqq26ebk96qkkvss8xp8julmuf` FOREIGN KEY (`PROJECT_ID`) REFERENCES `ProjectHandlerDB`.`PROJECT` (`id`);

ALTER TABLE `ProjectHandlerDB`.`USER_SETTINGS` ADD CONSTRAINT `FK43u49hgqhkjtexv79mv5gjmc9` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`);

-- INSERT INTO:

-- USER TABLE
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('1','1983-05-06','Szilárd','MALE','Gerlei',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','frontend developer','gerlei');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('2','1994-07-04','János','MALE','Harsányi',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','backend developer','janosharsanyi');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('3','1982-05-31','Roland','MALE','Darvas',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','backend developer','darvasr');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('4','1989-07-31','Balázs','MALE','Horváth',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','frontend developer','balazs630');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('5','1994-05-26','Stacia','FEMALE','Bevill',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','sales clerk','staciaa');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('6','1973-05-28','John','MALE','Schmeling',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','IT admin','john1');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('7','1988-02-07','Millie','FEMALE','Philhower',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','senior java developer','milli64');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('8','1991-07-07','Matthias','MALE','Kuncz',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','frontend developer','matthias.kuncz');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('9','1985-12-01','Lajos','MALE','Pető',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','helpdesk','apeto');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('10','1978-03-14','George','MALE','Sheets',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','backend developer','george.s');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('11','1984-03-02','Anika','FEMALE','Soden',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','accountant','anika13');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('12','1984-07-21','Annice','FEMALE','Antrim',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','frontend developer','antrim');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('13','1989-01-12','Jospeh','MALE','Capito',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','backend developer','capito');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('14','1979-07-24','Ronald','MALE','Friesen',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','senior pyton developer','fritsen.ronald');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('15','1983-05-15','Melinda','FEMALE','Worm',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','senior java developer','melinda66');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('16','1989-11-04','Huey','MALE','Tibbits',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','backend developer','huey_tibbits');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('17','1978-05-19','Clifton','MALE','Whiteman',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','senior java developer','clifton_w');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('18','1974-01-05','Kate','FEMALE','Patch',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','helpdesk ','katee11');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('19','1979-06-23','Tanner','MALE','Dutton',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','frontend developer','tan_dutton');
INSERT INTO ProjectHandlerDB.USER (id, BIRTH_DATE, FIRST_NAME, GENDER, LAST_NAME, PICTURE, PASSWORD, POSITION, USER_NAME) VALUES ('20','1981-03-21','Noble','MALE','Oyama',NULL,'$2a$13$JHOEXTk8H9V7PSNvckORseDwvBK/83dCjjmTTb8XfZQQIbm.US52C','accountant','oyama');


-- USER_SETTINGS TABLE
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('1','35','1');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('2','50','2');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('3','20','3');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('4','10','4');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('5','20','5');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('6','50','6');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('7','10','7');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('8','10','8');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('9','20','9');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('10','50','10');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('11','50','11');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('12','25','12');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('13','20','13');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('14','50','14');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('15','25','15');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('16','200','16');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('17','20','17');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('18','50','18');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('19','100','19');
INSERT INTO ProjectHandlerDB.USER_SETTINGS (id, PAGING, user_id) VALUES ('20','25','20');


-- ROLE TABLE
INSERT INTO ProjectHandlerDB.ROLE (id, ROLE_NAME) VALUES ('1','user');
INSERT INTO ProjectHandlerDB.ROLE (id, ROLE_NAME) VALUES ('2','admin');


-- ROLES TABLE
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('1','2');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('2','2');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('3','2');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('4','2');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('5','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('6','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('7','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('8','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('9','2');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('10','2');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('11','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('12','2');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('13','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('14','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('15','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('16','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('17','2');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('18','1');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('19','2');
INSERT INTO ProjectHandlerDB.ROLES (USER_ID, ROLE_ID) VALUES ('20','1');


-- CLIENT TABLE
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('1','info@westside.com','Westside Code Ltd.','+1 202 555 0174');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('2','progro@gmail.com','Pro Group Kft.','+1 946 568 0175');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('3','info@directmark.com','Direct Marketing Ltd.','+1 613 135 3189');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('4','peter@clicklabs.com','Click Labs','+36 30 317 9791');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('5','technews@technews.com','Tech Plus Kft.','+36 70 269 0178');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('6','crazystuff@cnmagazine.com','Crazy News Magazine','+36 20 681 6876');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('7','mark@codingllc.net','Coding LLC','+1 681 555 6914');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('8','info@codingeart.cn','Earth Coding Ltd.','+1 681 568 0175');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('9','info@musicproductions.hu','Music Productions Kft.','+1 613 681 3189');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('10','it@sinceritytrd.cn','Sincerity Trading ','+36 70 317 6817');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('11','sec@sectec.com','Security Tec Zrt.','+36 30 269 8178');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('12','contact@audio-wise.de','Audio-Visual Wise','+36 20 681 5717');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('13','info@cmotion.cz','Creative-Motion Group','+36 70 269 8188');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('14','alpha@alphaideas.hu','Alpha-ideas Kft.','+36 20 681 2387');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('15','info@futurestudio.net','Future Studio LLC','+1 681 555 8661');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('16','sales@cloud.hu','Cloud Life Rt.','+1 681 568 6818');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('17','info@interkey.hu','Inter-Key Kft.','+1 613 872 8781');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('18','info@nova-tech.com','Nova Technologies Group','+36 70 697 3215');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('19','hr@epic-rocket.de','Epic Rocket Agent','+36 30 121 1321');
INSERT INTO ProjectHandlerDB.CLIENT (id, EMAIL, CLIENT_NAME, PHONE_NUMBER) VALUES ('20','marketing@aero.com','Aero-Concepts LLC','+36 20 681 3918');


-- PROJECT TABLE
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('1','2019-04-19 00.00.00','seamlessly foster revolutionary imperatives','2015-01-20 01.25.10','White Storm','READY','1','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('2','2017-09-07 10.00.00','dynamically pontificate timely alignments','2013-09-25 06.45.14','Dangerous Haystack','PENDING','2','2');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('3','2018-07-22 18.00.00','objectively evolve turnkey systems','2013-10-14 08.11.15','Supersonic Tombstone','PENDING','9','10');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('4','2016-11-04 20.00.00','objectively plagiarize real-time networks','2014-05-31 00.04.32','Eastern Cheerful Rhinestone','CANCELLED','1','10');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('5','2018-04-08 19.00.00','globally engineer visionary information','2016-01-30 08.22.05','Gloomy Eyelid','READY','8','2');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('6','2017-04-01 12.00.00','conveniently morph B2B applications','2015-11-29 21.33.20','Digital Monkey','CANCELLED','1','6');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('7','2017-06-06 21.00.00','globally plagiarize strategic action items','2016-04-09 02.29.33','Teal Subdivision','READY','9','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('8','2017-08-25 21.00.00','dynamically syndicate standardized content','2014-07-25 04.17.43','Dusty Winter','PENDING','8','6');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('9','2017-04-18 00.00.00','professionally pursue ubiquitous ideas','2014-08-18 04.29.11','Crossbow Dirty','CANCELLED','1','2');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('10','2017-05-30 07.00.00','credibly administrate tactical e-markets','2013-12-14 02.09.02','Hungry Aggressive Tiger','READY','9','7');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('11','2017-10-20 19.00.00','efficiently formulate future-proof mindshare','2012-10-02 15.33.08','Dreaded Uranium','CANCELLED','4','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('12','2018-12-30 12.00.00','seamlessly formulate scalable vortals','2014-01-04 05.16.59','Maximum Drill','READY','2','8');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('13','2017-08-20 09.00.00','assertively develop 24/365 total linkage','2012-12-07 12.05.39','Dusty Autumn','READY','1','5');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('14','2019-02-06 07.00.00','proactively create customized outsourcing','2015-12-13 06.50.53','Maroon Fist','PENDING','4','3');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('15','2017-02-23 16.00.00','uniquely revolutionize web-enabled bandwidth','2015-12-29 17.40.33','Skilled Gravel','READY','9','5');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('16','2019-08-26 06.00.00','rapidiously utilize orthogonal e-services','2012-09-07 16.26.42','Rare Compass','READY','2','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('17','2018-09-26 08.00.00','fungibly conceptualize backend mindshare','2015-05-21 18.53.42','Sticky Alpha','CANCELLED','1','8');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('18','2017-05-12 23.00.00','seamlessly enable 24/7 innovation','2013-03-24 21.47.15','Discarded Scissors','READY','5','3');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('19','2018-03-04 01.00.00','quickly expedite high-payoff channels','2012-11-15 19.26.41','Hungry Endless Door','READY','1','5');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('20','2017-01-31 14.00.00','intrinsically innovate top-line experiences','2014-01-19 07.56.31','Dusty Snake','PENDING','4','2');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('21','2019-04-19 00.00.00','seamlessly foster revolutionary imperatives','2015-09-18 17.45.45','Discarded Steel','CANCELLED','1','8');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('22','2017-09-07 10.00.00','dynamically pontificate timely alignments','2015-07-01 03.04.14','Stormy Balcony','READY','2','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('23','2018-07-22 18.00.00','objectively evolve turnkey systems','2013-02-22 17.25.15','Neutron Minimum','CANCELLED','5','2');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('24','2016-11-04 20.00.00','objectively plagiarize real-time networks','2012-10-13 10.19.14','Dirty Hook','PENDING','1','4');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('25','2018-04-08 19.00.00','globally engineer visionary information','2014-09-25 15.10.12','Dangerous Albatross','READY','3','10');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('26','2017-04-01 12.00.00','conveniently morph B2B applications','2015-06-18 13.01.34','Next Meaningful Epsilon','CANCELLED','5','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('27','2017-06-06 21.00.00','globally plagiarize strategic action items','2014-11-03 23.25.17','Roadrunner Rainbow','READY','9','3');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('28','2017-08-25 21.00.00','dynamically syndicate standardized content','2013-06-16 18.35.26','Eternal Flannel','CANCELLED','1','4');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('29','2017-04-18 00.00.00','professionally pursue ubiquitous ideas','2015-06-17 16.03.45','Surreal Albatross','PENDING','6','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('30','2017-05-30 07.00.00','credibly administrate tactical e-markets','2013-12-29 13.52.14','Endless Scorpion','CANCELLED','1','4');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('31','2017-10-20 19.00.00','efficiently formulate future-proof mindshare','2013-01-08 21.54.06','Husky Pineapple','PENDING','6','3');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('32','2018-12-30 12.00.00','seamlessly formulate scalable vortals','2013-12-02 06.25.24','Puppet Forgotten','READY','7','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('33','2017-08-20 09.00.00','assertively develop 24/365 total linkage','2013-08-17 13.35.02','Worthy Railroad','CANCELLED','6','9');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('34','2019-02-06 07.00.00','proactively create customized outsourcing','2014-12-02 02.56.25','Yellow Heart','PENDING','7','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('35','2017-02-23 16.00.00','uniquely revolutionize web-enabled bandwidth','2016-04-27 12.26.18','Dancing Monkey','READY','1','3');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('36','2019-08-26 06.00.00','rapidiously utilize orthogonal e-services','2015-05-31 21.11.02','Strong Vegetable','READY','9','3');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('37','2018-09-26 08.00.00','fungibly conceptualize backend mindshare','2015-10-16 12.25.14','Sliding Tire','CANCELLED','7','9');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('38','2017-05-12 23.00.00','seamlessly enable 24/7 innovation','2013-10-15 00.59.03','Postal Elastic','PENDING','1','1');
INSERT INTO ProjectHandlerDB.PROJECT (id, DEADLINE, DESCRIPTION, KICKOFF, NAME, PROJECT_STATE, CLIENT_ID, USER_ID) VALUES ('39','2018-03-04 01.00.00','quickly expedite high-payoff channels','2012-09-14 07.28.45','Screaming Lantern','READY','6','9');


-- TASK TABLE
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('1','2019-04-19 00.00.00','seamlessly foster revolutionary imperatives','integrate scalable nosql','URGENT','ACTIVE','BUG','1','1','1');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('2','2017-09-07 10.00.00','dynamically pontificate timely alignments','grow error-free clouds','NORMAL','ACTIVE','BUG',NULL,'2','1');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('3','2018-07-22 18.00.00','objectively evolve turnkey systems','drive intermandated channels','URGENT','BLOCKED','TASK',NULL,'3','2');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('4','2016-11-04 20.00.00','objectively plagiarize real-time networks','initiate hyper-scale benefits','NOT_IMPORTANT','ACTIVE','BUG',NULL,'4','3');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('5','2018-04-08 19.00.00','globally engineer visionary information','deploy visionary markets','NORMAL','PASSIVE','BUG',NULL,'1','2');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('6','2017-04-01 12.00.00','conveniently morph B2B applications','build hyper-scale applications','URGENT','BLOCKED','TASK','3','5','3');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('7','2017-06-06 21.00.00','globally plagiarize strategic action items','embrace interdependent action','NORMAL','ACTIVE','TASK',NULL,'3','2');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('8','2017-08-25 21.00.00','dynamically syndicate standardized content','reintermediate adaptive networks','URGENT','PASSIVE','TASK',NULL,'2','1');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('9','2017-04-18 00.00.00','professionally pursue ubiquitous ideas','foster extensible networks','NORMAL','BLOCKED','STORY','6','7','3');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('10','2017-05-30 07.00.00','credibly administrate tactical e-markets','disintermediate wireless products','NORMAL','OPEN','BUG',NULL,'1','3');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('11','2017-10-20 19.00.00','efficiently formulate future-proof mindshare','empower intuitive systems','URGENT','OPEN','TASK','2','4','2');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('12','2018-12-30 12.00.00','seamlessly formulate scalable vortals','reconceptualize customized information','NORMAL','CREATED','TASK',NULL,'3','4');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('13','2017-08-20 09.00.00','assertively develop 24/365 total linkage','repurpose dynamic ROI','URGENT','ACTIVE','TASK',NULL,'6','1');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('14','2019-02-06 07.00.00','proactively create customized outsourcing','supply clicks-and-mortar items','NORMAL','CANCELLED','STORY',NULL,'2','2');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('15','2017-02-23 16.00.00','uniquely revolutionize web-enabled bandwidth','aggregate business catalysts','URGENT','PASSIVE','BUG',NULL,'1','2');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('16','2019-08-26 06.00.00','rapidiously utilize orthogonal e-services','strategize next-generation resources','NORMAL','ACTIVE','TASK',NULL,'2','1');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('17','2018-09-26 08.00.00','fungibly conceptualize backend mindshare','facilitate cloud-centric alignments','NOT_IMPORTANT','CANCELLED','STORY',NULL,'4','2');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('18','2017-05-12 23.00.00','seamlessly enable 24/7 innovation','drive cloud-centric niches','URGENT','PASSIVE','STORY',NULL,'1','4');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('19','2018-03-04 01.00.00','quickly expedite high-payoff channels','deploy distributed data','NORMAL','CREATED','BUG',NULL,'2','2');
INSERT INTO ProjectHandlerDB.TASK (id, DEADLINE, DESCRIPTION, NAME, PRIORITY, TASK_STATUS, TASK_TYPE, PARENT_TASK_ID, PROJECT_ID, USER_ID) VALUES ('20','2017-01-31 14.00.00','intrinsically innovate top-line experiences','maintain goal-oriented human capital','NOT_IMPORTANT','OPEN','STORY',NULL,'3','4');


-- FORUM TABLE
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('1','login issues','1','1');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('2','dev issues','2','2');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('3','frontend','3','8');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('4','frontend','7','5');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('5','backend','6','4');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('6','dev issues','5','6');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('7','backend','4','1');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('8','dev issues','3','2');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('9','frontend','2','2');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('10','frontend','4','1');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('11','general','6','3');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('12','general','1','8');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('13','general','8','3');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('14','backend','3','6');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('15','dev issues','4','4');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('16','random','1','1');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('17','backend','2','2');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('18','backend','1','1');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('19','backend','7','1');
INSERT INTO ProjectHandlerDB.FORUM (id, FORUM_TOPIC, PROJECT_ID, USER_ID) VALUES ('20','questions','1','5');


-- POST TABLE
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('1','Hi! Is there any update?','2016-01-06 16.03.37','1','1',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('2','Nothing yet…','2016-01-08 13.56.32','2','1',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('3','Looks like an annoying bug…','2016-01-13 18.15.45','8','1',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('4','Better take some beers and start debugging','2016-01-17 15.42.47','5','1',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('5','Okay John, keep up the good job!','2016-01-18 17.47.07','4','1',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('6','Looks fine now, check it out!','2016-01-31 23.15.01','6','1',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('7','You forgot to push it to remote branch!!','2016-02-02 00.19.43','1','1',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('8','My bad, pushed it now..','2016-02-17 00.05.52','2','1',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('9','Cool!','2016-02-19 06.04.25','2','1',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('10','Hello everybody, I need some testers for the current project...','2016-02-27 23.34.52','1','2',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('11','We need some more students, too..','2016-03-09 20.15.22','3','2',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('12','Here we go, thanks for the idea!','2016-03-11 13.59.06','8','2',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('13','Good night! B.','2016-03-18 12.26.53','3','2',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('14','Hi, what is the deadline of this project?','2016-03-22 22.27.33','6','3',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('15','20th of Oct I think..','2016-03-24 16.30.52','4','3',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('16','ARE YOU KIDDING ME??!!','2016-03-26 09.43.56','1','3',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('17','Sorry, 20th of November… :)','2016-03-27 12.17.52','2','3',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('18','Hi, is there anybody here?','2016-03-28 03.33.11','1','4',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('19','Haha, nice feature, well done bro!','2016-04-02 15.43.07','1','5',NULL);
INSERT INTO ProjectHandlerDB.POST (id, CONTENT, POST_DATE, USER_ID, FORUM_ID, PARENT_POST_ID) VALUES ('20','Cheers','2016-04-12 23.26.31','5','5',NULL);


-- NOTIFICATION TABLE
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('1','2016-09-01 05.48.18','2016-09-06 14.32.52','TASK_USER_ASSOCIATION_CHANGED',NULL,'1');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('2','2016-09-04 00.42.25','2016-09-09 04.37.57','NEW_TASK_COMMENT',NULL,'2');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('3','2016-09-02 21.12.29','2016-09-07 03.51.29','NEW_TASK_COMMENT',NULL,'8');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('4','2016-09-02 06.19.52','2016-09-07 19.23.34','TASK_USER_ASSOCIATION_CHANGED',NULL,'5');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('5','2016-09-02 22.38.48','2016-09-08 05.14.35','NEW_TASK_COMMENT',NULL,'4');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('6','2016-09-05 00.02.30','2016-09-07 01.38.43','TASK_USER_ASSOCIATION_CHANGED',NULL,'6');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('7','2016-09-01 01.30.10','2016-09-07 01.16.37','NEW_TASK_COMMENT',NULL,'1');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('8','2016-09-01 23.28.15','2016-09-10 21.54.03','NEW_TASK_COMMENT',NULL,'2');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('9','2016-09-03 19.28.13','2016-09-09 01.25.07','NEW_TASK_COMMENT',NULL,'2');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('10','2016-09-05 20.33.12','2016-09-09 12.01.29','TASK_USER_ASSOCIATION_CHANGED',NULL,'1');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('11','2016-09-01 00.23.41','2016-09-09 14.43.21','NEW_TASK_COMMENT',NULL,'3');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('12','2016-09-01 23.36.31','2016-09-10 17.11.06','NEW_TASK_COMMENT',NULL,'8');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('13','2016-09-02 13.43.47','2016-09-10 02.52.31','TASK_USER_ASSOCIATION_CHANGED',NULL,'3');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('14','2016-09-03 20.48.36','2016-09-08 17.10.35','NEW_TASK_COMMENT',NULL,'6');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('15','2016-09-05 21.07.25','2016-09-08 18.22.22','TASK_USER_ASSOCIATION_CHANGED',NULL,'4');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('16','2016-09-04 10.14.25','2016-09-08 02.57.46','NEW_TASK_COMMENT',NULL,'1');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('17','2016-09-04 07.14.28','2016-09-06 14.28.35','NEW_TASK_COMMENT',NULL,'2');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('18','2016-09-03 19.29.25','2016-09-10 09.57.04','TASK_USER_ASSOCIATION_CHANGED',NULL,'1');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('19','2016-09-02 20.52.37','2016-09-06 15.32.44','TASK_USER_ASSOCIATION_CHANGED',NULL,'1');
INSERT INTO ProjectHandlerDB.NOTIFICATION (id, CREATION_DATE, NOTIFICATION_READ, TYPE, relatedTask_id, user_id) VALUES ('20','2016-09-05 15.56.19','2016-09-06 06.40.52','TASK_USER_ASSOCIATION_CHANGED',NULL,'5');


-- COMMENT TABLE
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('1','integrate impactful web-readiness','2015-07-06 12.36.55','1',NULL,'1');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('2','disintermediate collaborative technologies','2014-06-27 07.59.33','2',NULL,'2');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('3','utilize virtual technologies','2014-11-02 16.53.37','8',NULL,'1');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('4','recontextualize leading-edge functionalities','2013-06-28 00.37.39','3',NULL,'2');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('5','exploit killer e-services','2013-05-20 13.32.12','4',NULL,'2');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('6','morph leading-edge technologies','2013-10-12 11.05.50','2',NULL,'3');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('7','transform one-to-one applications','2014-06-27 12.52.22','5',NULL,'3');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('8','recontextualize robust deliverables','2013-12-02 07.01.45','2',NULL,'1');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('9','benchmark customized initiatives','2016-03-21 12.25.08','3',NULL,'1');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('10','incubate cross-platform e-markets','2015-09-29 19.37.01','4',NULL,'1');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('11','facilitate integrated architectures','2016-03-04 14.27.15','1',NULL,'2');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('12','revolutionize 24/365 models','2016-08-29 02.07.03','5',NULL,'3');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('13','expedite killer infomediaries','2012-09-28 14.09.00','6',NULL,'1');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('14','implement cross-platform deliverables','2013-12-25 06.35.03','1',NULL,'1');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('15','reintermediate holistic platforms','2014-05-20 22.10.06','1',NULL,'4');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('16','integrate killer e-tailers','2013-11-10 16.52.06','2',NULL,'2');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('17','productize strategic architectures','2015-01-08 01.31.54','3',NULL,'4');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('18','orchestrate back-end infomediaries','2015-03-29 19.58.21','3',NULL,'3');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('19','target holistic architectures','2012-09-29 10.11.48','8',NULL,'3');
INSERT INTO ProjectHandlerDB.COMMENT (id, CONTENT, POST_DATE, USER_ID, PARENT_COMMENT_ID, TASK_ID) VALUES ('20','redefine transparent communities','2016-05-16 16.38.38','1',NULL,'3');



SELECT 'CONFIG END' AS '';
