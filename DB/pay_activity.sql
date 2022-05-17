/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - pay_activity
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `pay_activity`;

/*Table structure for table `session_file` */

CREATE TABLE `session_file` (
  `session_id` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_bonus` */

CREATE TABLE `t_bonus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon` varchar(50) DEFAULT NULL,
  `valid` int(4) DEFAULT NULL,
  `valid_time_from` varchar(20) DEFAULT NULL,
  `valid_time_to` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_activity` */

CREATE TABLE `t_pay_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` varchar(200) DEFAULT NULL COMMENT '内容',
  `people_number_limit` int(11) DEFAULT NULL COMMENT '人数限制',
  `enterprise_limit` text COMMENT '企业限制（;）隔开',
  `limit_regist_time` bigint(14) DEFAULT NULL COMMENT '注册时间限制',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `actual_participant_number` int(11) DEFAULT NULL COMMENT '实际参与人数',
  `start_time` bigint(14) DEFAULT NULL COMMENT '开始时间',
  `end_time` bigint(14) DEFAULT NULL COMMENT '结束时间',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '更新时间',
  `image_url` varchar(200) DEFAULT NULL COMMENT '活动图片路径',
  `home_url` varchar(200) DEFAULT NULL COMMENT '活动首页地址',
  `rules` text NOT NULL COMMENT '规则',
  `amount` decimal(14,4) DEFAULT '0.0000' COMMENT '金额',
  `order_no` varchar(500) DEFAULT NULL COMMENT '订单',
  `gift_quantity` int(11) DEFAULT NULL COMMENT '礼品数量',
  `bonus_id` int(11) DEFAULT NULL,
  `bonus_name` varchar(50) DEFAULT NULL,
  `template_id` int(11) DEFAULT NULL COMMENT '模版消息ID',
  `luckmoney_id` varchar(500) DEFAULT NULL COMMENT '红包ID',
  `rely_on_activity_id` int(11) DEFAULT NULL COMMENT '依赖活动ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_activity_application` */

CREATE TABLE `t_pay_activity_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `key` varchar(200) NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_activity_bonus` */

CREATE TABLE `t_pay_activity_bonus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '礼品名称',
  `amount` decimal(14,4) NOT NULL COMMENT '金额',
  `type` varchar(4) NOT NULL COMMENT '代金卷、红包',
  `provider` varchar(100) DEFAULT NULL COMMENT '提供者',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_activity_bonus_record` */

CREATE TABLE `t_pay_activity_bonus_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fs_user_id` int(11) NOT NULL COMMENT '用户ID',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `user_name` varchar(20) DEFAULT NULL,
  `coupon` varchar(100) NOT NULL COMMENT '优惠卷',
  `pickup_time` bigint(14) NOT NULL COMMENT '领取时间',
  `expire_time` bigint(14) DEFAULT NULL COMMENT '失效时间',
  `activity_id` int(11) NOT NULL COMMENT '活动ID',
  `phone` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`fs_user_id`) USING BTREE,
  KEY `index_enteprise_account` (`enterprise_account`) USING BTREE,
  KEY `index_activity_id` (`activity_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_activity_msg_template` */

CREATE TABLE `t_pay_activity_msg_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COMMENT '文本消息 内容',
  `image_url` text COMMENT '图文消息 图片路径',
  `url` text,
  `app_id` varchar(100) DEFAULT '',
  `top_color` varchar(100) DEFAULT '' COMMENT '背景',
  `type` int(2) NOT NULL COMMENT '1图文消息，2文本消息',
  `template_id` varchar(100) DEFAULT '' COMMENT '模版ID',
  `store` int(2) NOT NULL DEFAULT '0' COMMENT '是否存储：0否 1是',
  `features` varchar(200) DEFAULT '{}',
  `create_time` bigint(14) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_activity_new_msg` */

CREATE TABLE `t_pay_activity_new_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fs_user_id` int(11) NOT NULL COMMENT '用户ID',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `user_name` varchar(20) DEFAULT NULL,
  `version` varchar(20) DEFAULT '' COMMENT '版本',
  `send_time` bigint(14) NOT NULL COMMENT '发送时间',
  `activity_id` int(11) NOT NULL COMMENT '活动ID',
  PRIMARY KEY (`id`),
  KEY `fs_user_id` (`fs_user_id`,`enterprise_account`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=407 DEFAULT CHARSET=utf8;

/*Table structure for table `user_session` */

CREATE TABLE `user_session` (
  `user_id` varchar(50) NOT NULL,
  `session_id` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
