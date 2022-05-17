/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - luckmoney
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `luckmoney`;

/*Table structure for table `luck_money` */

CREATE TABLE `luck_money` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ea` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fs_user_id` int(11) NOT NULL,
  `fs_user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` tinyint(2) NOT NULL,
  `scope` tinyint(2) NOT NULL,
  `gift_type` tinyint(2) DEFAULT NULL,
  `receive_qixin_id` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `total_amount` int(11) NOT NULL,
  `number` int(11) NOT NULL DEFAULT '0',
  `amount` int(11) DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(2) NOT NULL,
  `expire_time` datetime NOT NULL,
  `order_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `refund_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `refund_amount` int(11) DEFAULT NULL,
  `refund_time` datetime DEFAULT NULL,
  `grab_amount` int(11) DEFAULT '0',
  `grab_number` int(11) DEFAULT '0',
  `redis_status` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `theme_id` bigint(4) DEFAULT NULL COMMENT '主题ID',
  `rules` text COLLATE utf8mb4_unicode_ci COMMENT '红包规则{"金额1":"人数1","金额2":"人数2"}',
  `send_status` tinyint(2) DEFAULT '0' COMMENT '发送状态（1已发送、2未发送）',
  `send_type` tinyint(2) DEFAULT '0' COMMENT '发送类型（1即时发送、2定时发送）',
  `send_time` datetime DEFAULT NULL COMMENT '启动时间',
  `circle_ids` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '部门ID, 形如"[12,23]"',
  `employee_ids` varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '员工ID, 形如"[1222,2333]"',
  `enterprise_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `money_id` (`money_id`,`order_id`) USING BTREE,
  UNIQUE KEY `money_id_2` (`money_id`,`refund_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4667 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `luck_money_bak` */

CREATE TABLE `luck_money_bak` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ea` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fs_user_id` int(11) NOT NULL,
  `fs_user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` tinyint(2) NOT NULL,
  `scope` tinyint(2) NOT NULL,
  `gift_type` tinyint(2) DEFAULT NULL,
  `receive_qixin_id` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `total_amount` int(11) NOT NULL,
  `number` int(11) NOT NULL DEFAULT '0',
  `amount` int(11) DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(2) NOT NULL,
  `expire_time` datetime NOT NULL,
  `order_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `refund_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `refund_amount` int(11) DEFAULT NULL,
  `refund_time` datetime DEFAULT NULL,
  `grab_amount` int(11) DEFAULT '0',
  `grab_number` int(11) DEFAULT '0',
  `redis_status` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `theme_id` int(4) DEFAULT NULL COMMENT '主题ID',
  `rules` text COLLATE utf8mb4_unicode_ci COMMENT '红包规则{"金额1":"人数1","金额2":"人数2"}',
  `send_status` tinyint(2) DEFAULT '0' COMMENT '发送状态（1已发送、2未发送）',
  `send_type` tinyint(2) DEFAULT '0' COMMENT '发送类型（1即时发送、2定时发送）',
  `send_time` datetime DEFAULT NULL COMMENT '启动时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `money_id` (`money_id`,`order_id`) USING BTREE,
  UNIQUE KEY `money_id_2` (`money_id`,`refund_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3922 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `luck_money_detail` */

CREATE TABLE `luck_money_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `luck_money_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `freeze_order_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ea` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fs_user_id` int(11) NOT NULL,
  `fs_user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total_amount` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `type` tinyint(2) NOT NULL,
  `scope` tinyint(2) NOT NULL,
  `gift_type` tinyint(2) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `receive_ea` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fs_receive_id` int(11) DEFAULT NULL,
  `fs_receive_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `memo` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `grab_time` bigint(18) DEFAULT NULL,
  `order_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `best` tinyint(2) DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `enterprise_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `ea_fs_user_id_status` (`ea`,`fs_user_id`,`type`) USING BTREE,
  KEY `luck_money_id_amount_status_update_time` (`luck_money_id`,`amount`,`status`,`update_time`) USING BTREE,
  KEY `status_amount_order_id` (`status`,`amount`,`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14294 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `luck_money_detail_bak` */

CREATE TABLE `luck_money_detail_bak` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `luck_money_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `freeze_order_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ea` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fs_user_id` int(11) NOT NULL,
  `fs_user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total_amount` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `type` tinyint(2) NOT NULL,
  `scope` tinyint(2) NOT NULL,
  `gift_type` tinyint(2) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `receive_ea` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fs_receive_id` int(11) DEFAULT NULL,
  `fs_receive_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `memo` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `grab_time` bigint(18) DEFAULT NULL,
  `order_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `best` tinyint(2) DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ea_fs_user_id_status` (`ea`,`fs_user_id`,`type`) USING BTREE,
  KEY `luck_money_id_amount_status_update_time` (`luck_money_id`,`amount`,`status`,`update_time`) USING BTREE,
  KEY `status_amount_order_id` (`status`,`amount`,`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `luck_money_summary` */

CREATE TABLE `luck_money_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ea` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fs_user_id` int(11) NOT NULL,
  `fs_user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `year` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` tinyint(2) NOT NULL,
  `total_amount` int(11) NOT NULL DEFAULT '0',
  `best_luck_number` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ea` (`ea`,`fs_user_id`,`year`,`type`) USING BTREE,
  KEY `ea_fs_user_id_type` (`ea`,`fs_user_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `luck_money_summary_bak` */

CREATE TABLE `luck_money_summary_bak` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ea` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fs_user_id` int(11) NOT NULL,
  `fs_user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `year` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` tinyint(2) NOT NULL,
  `total_amount` int(11) NOT NULL DEFAULT '0',
  `best_luck_number` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ea` (`ea`,`fs_user_id`,`year`,`type`) USING BTREE,
  KEY `ea_fs_user_id_type` (`ea`,`fs_user_id`,`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `luck_money_theme` */

CREATE TABLE `luck_money_theme` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '主题名称（普通、开工大吉）',
  `small_img` varchar(256) NOT NULL COMMENT '小图',
  `small_img_webp` varchar(256) DEFAULT NULL COMMENT '小图',
  `big_img` varchar(256) NOT NULL COMMENT '大图',
  `big_img_webp` varchar(256) DEFAULT NULL COMMENT '大图',
  `card_img` varchar(256) NOT NULL,
  `card_img_webp` varchar(256) DEFAULT NULL,
  `background_img` varchar(256) NOT NULL,
  `background_img_webp` varchar(256) DEFAULT NULL,
  `news_img_url` varchar(256) NOT NULL DEFAULT '' COMMENT '图文消息图片地址',
  `status` tinyint(2) NOT NULL DEFAULT '2' COMMENT '状态（1可用、2不可用）',
  `memo` varchar(200) NOT NULL COMMENT '默认祝福语',
  `desc` varchar(256) DEFAULT NULL COMMENT '描述',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `reward_info` */

CREATE TABLE `reward_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reward_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `share_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `share_type` int(11) NOT NULL,
  `share_ea` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `share_user_id` int(11) NOT NULL,
  `share_summary` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ea` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `best` tinyint(2) NOT NULL,
  `order_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `source` int(11) NOT NULL,
  `feed_reward_id` int(11) DEFAULT NULL,
  `feed_time` datetime DEFAULT NULL,
  `mq_status` tinyint(1) DEFAULT '0' COMMENT 'mq记录生产状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `reward_id` (`reward_id`),
  UNIQUE KEY `reward_id_2` (`reward_id`,`share_id`,`share_ea`,`share_user_id`,`user_id`,`status`),
  KEY `idx_share_id_status_ea_user_id` (`share_id`,`status`,`ea`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5274 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
