/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - pay_risk_info
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `pay_risk_info`;

/*Table structure for table `enterprise_activity` */

CREATE TABLE `enterprise_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` bigint(14) NOT NULL DEFAULT '0',
  `activity_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ENTERPRISE_ID` (`enterprise_id`),
  KEY `INDEX_ENTERPRISE_ID` (`enterprise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
