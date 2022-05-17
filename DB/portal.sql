/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - portal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `portal`;

/*Table structure for table `access_log` */

CREATE TABLE `access_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `employee_name` varchar(20) NOT NULL,
  `system_id` int(11) NOT NULL,
  `url` varchar(100) NOT NULL COMMENT '访问路径',
  `param_info` varchar(200) DEFAULT '' COMMENT '参数',
  `create_time` datetime NOT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38711 DEFAULT CHARSET=utf8;

/*Table structure for table `business` */

CREATE TABLE `business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_key` int(11) NOT NULL,
  `business_name` varchar(100) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Table structure for table `business_switch` */

CREATE TABLE `business_switch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `busi_name` varchar(50) NOT NULL COMMENT '业务名称',
  `action_name` varchar(50) NOT NULL COMMENT '类名',
  `method_name` varchar(50) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '1开启，0关闭',
  `system_id` int(11) NOT NULL COMMENT '所属系统',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `remark` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `ip_white_list` */

CREATE TABLE `ip_white_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(50) NOT NULL,
  `class_name` varchar(50) DEFAULT '',
  `method_name` varchar(50) DEFAULT '',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '1启用，0不启用',
  `ip_list` text,
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `remark` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `module` */

CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sort_index` int(11) DEFAULT '0' COMMENT '排序规则',
  `parent_id` int(11) DEFAULT '-1' COMMENT '父模块ID',
  `create_time` datetime NOT NULL,
  `system_id` int(11) NOT NULL COMMENT '所属系统',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

/*Table structure for table `permission` */

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `url` varchar(100) DEFAULT '' COMMENT '菜单地址',
  `system_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT '-1' COMMENT '所属模块',
  `menu` int(1) DEFAULT '0' COMMENT '0非菜单，1是菜单',
  `sort_index` int(11) NOT NULL DEFAULT '0' COMMENT '排序规则',
  `create_time` datetime NOT NULL,
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=425 DEFAULT CHARSET=utf8;

/*Table structure for table `role` */

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `role_permission` */

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15685 DEFAULT CHARSET=utf8;

/*Table structure for table `system` */

CREATE TABLE `system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '系统名字',
  `url` varchar(100) NOT NULL COMMENT '系统路径',
  `type` smallint(1) DEFAULT '0',
  `create_time` datetime NOT NULL,
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `system_gray` */

CREATE TABLE `system_gray` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '灰度名称',
  `white_list` text COMMENT '白名单企业',
  `black_list` text COMMENT '黑名单企业',
  `department` text COMMENT '部门',
  `id_list` text COMMENT '员工',
  `percent` int(4) DEFAULT NULL COMMENT '百分比（0-100）',
  `system_id` int(11) NOT NULL,
  `interval_time` int(11) NOT NULL DEFAULT '0',
  `status` int(2) NOT NULL COMMENT '0不启用，1启用',
  `create_time` datetime NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL COMMENT 'id',
  `employee_name` varchar(20) NOT NULL COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '二次密码校验',
  `error_num` int(5) DEFAULT '0' COMMENT '密码错误次数',
  `phone` varchar(16) NOT NULL COMMENT '电话',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '密码输入错误次数',
  `create_time` datetime NOT NULL,
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `last_login_time` datetime NOT NULL,
  PRIMARY KEY (`id`,`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

/*Table structure for table `user_role` */

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
