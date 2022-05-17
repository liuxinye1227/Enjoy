/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - pay_order
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `pay_order`;

/*Table structure for table `order_log` */

CREATE TABLE `order_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `pre_status` int(10) NOT NULL COMMENT '前置状态',
  `after_status` int(10) NOT NULL COMMENT '后置状态',
  `create_time` bigint(14) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1911030 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_order` */

CREATE TABLE `t_enterprise_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(32) DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `enterprise_wallet_id` varchar(30) DEFAULT NULL COMMENT '企业钱包ID',
  `associated_enterprise_name` varchar(100) DEFAULT NULL COMMENT '关联企业名称',
  `associated_enterprise_account` varchar(32) DEFAULT '' COMMENT '关联者企业号',
  `associated_sub_enterprise_account` varchar(64) DEFAULT '' COMMENT '分账户id',
  `associated_enterprise_wallet_id` varchar(30) DEFAULT '' COMMENT '关联者企业id',
  `associated_fs_user_id` int(11) DEFAULT '0' COMMENT '关联者用户ID',
  `associated_fs_user_name` varchar(32) DEFAULT '' COMMENT '关联者用户名',
  `op_fs_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `op_fs_user_name` varchar(20) DEFAULT '' COMMENT '纷享用户名(发起方)',
  `order_type` int(10) NOT NULL COMMENT '订单类型',
  `order_source` varchar(256) NOT NULL DEFAULT '' COMMENT '订单来源',
  `amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单金额',
  `status` int(10) NOT NULL COMMENT 'INIT_ORDER(10, "初始化订单"),PROCESSING_ORDER(20, "处理中"),FAIL_ORDER(46, "订单失败"),ORDER_FINISH(1, "订单完成")',
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(256) DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) DEFAULT '0' COMMENT '处理时间',
  `product_name` varchar(256) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(512) NOT NULL DEFAULT '' COMMENT '商户名',
  `rel_order_no` varchar(64) DEFAULT NULL COMMENT '关联订单号（）',
  `caller_ip` varchar(39) DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) DEFAULT NULL COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) DEFAULT NULL COMMENT '冗余字段2',
  `features` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `features_version` int(11) DEFAULT '0' COMMENT '扩展字段版本',
  `pay_channel` int(5) NOT NULL COMMENT '支付渠道：ALIPAY(0,"支付包"),KUAIQIAN(1,"快钱"),WALLET(2,"企业余额")',
  `pay_way` smallint(4) DEFAULT NULL,
  `merchant_id` varchar(64) DEFAULT NULL COMMENT '商户号',
  `ware_id` varchar(64) DEFAULT NULL COMMENT '物品ID',
  `ware_name` varchar(100) DEFAULT NULL COMMENT '物品名称',
  `goods_id` varchar(64) DEFAULT NULL COMMENT '商品ID，注册时给的ID',
  `use_agent` int(5) NOT NULL DEFAULT '0' COMMENT '是否通过代理商支付',
  PRIMARY KEY (`id`),
  KEY `IDX_ORDER_NO` (`order_no`),
  KEY `IDX_EA_ACCT_WALLET_ID` (`enterprise_account`,`sub_enterprise_account`,`enterprise_wallet_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38053 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order` */

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_0` */

CREATE TABLE `t_order_0` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_1` */

CREATE TABLE `t_order_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_10` */

CREATE TABLE `t_order_10` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_11` */

CREATE TABLE `t_order_11` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_12` */

CREATE TABLE `t_order_12` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_13` */

CREATE TABLE `t_order_13` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_14` */

CREATE TABLE `t_order_14` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_15` */

CREATE TABLE `t_order_15` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_2` */

CREATE TABLE `t_order_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1076 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_3` */

CREATE TABLE `t_order_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_4` */

CREATE TABLE `t_order_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_5` */

CREATE TABLE `t_order_5` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_6` */

CREATE TABLE `t_order_6` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=71298 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_7` */

CREATE TABLE `t_order_7` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1521 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_8` */

CREATE TABLE `t_order_8` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_9` */

CREATE TABLE `t_order_9` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号(发起人)',
  `fs_user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '纷享用户ID(发起方)',
  `fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '纷享用户名(发起方)',
  `biz_type` int(10) NOT NULL COMMENT '订单类型',
  `business_type` int(10) NOT NULL COMMENT '业务类型',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '订单金额',
  `status` int(10) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(14) DEFAULT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '申请时间',
  `end_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '处理时间',
  `biz_order_no` varchar(100) NOT NULL DEFAULT '' COMMENT '业务订单号',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名',
  `suppliers_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商户名',
  `associated_enterprise_account` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者企业号',
  `associated_fs_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联者用户id',
  `associated_fs_user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '关联者用户名',
  `order_source` varchar(50) NOT NULL DEFAULT '' COMMENT '订单来源',
  `caller_ip` varchar(39) NOT NULL DEFAULT '' COMMENT '调用者ip:ipv6有39个字符,ipv4有14个字符',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示:1显示.0不显示',
  `redundant_field_1` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段1',
  `redundant_field_2` varchar(255) NOT NULL DEFAULT '' COMMENT '冗余字段2',
  `features` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `features_version` int(11) NOT NULL DEFAULT '0' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_update_order` (`order_no`),
  KEY `index_query_order` (`enterprise_account`,`fs_user_id`,`biz_type`,`amount`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
