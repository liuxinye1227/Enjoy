/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - pay_notice
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `pay_notice`;

/*Table structure for table `alarm_log` */

CREATE TABLE `alarm_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '错误级别 （WRAN,ERROR）',
  `trans_type` varchar(10) NOT NULL DEFAULT '' COMMENT '交易类型',
  `error_msg` varchar(100) NOT NULL,
  `request_info` text NOT NULL,
  `create_time` bigint(15) NOT NULL,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '处理状态，  默认0  1 已处理  ',
  `update_time` bigint(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=534926 DEFAULT CHARSET=utf8;

/*Table structure for table `operate_log` */

CREATE TABLE `operate_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `busi_no` int(10) NOT NULL COMMENT '业务类型',
  `operate_type` int(10) NOT NULL,
  `trans_type` int(10) NOT NULL COMMENT '交易类型',
  `request_info` text NOT NULL COMMENT '请求参数',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
