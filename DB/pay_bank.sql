/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - pay_bank
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `pay_bank`;

/*Table structure for table `operate_log` */

CREATE TABLE `operate_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `busi_no` int(10) NOT NULL COMMENT '业务类型',
  `operate_type` int(10) NOT NULL,
  `busi_type` int(10) NOT NULL COMMENT '钱包子业务类型',
  `request_info` text NOT NULL COMMENT '请求参数',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4919284 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_wallet` */

CREATE TABLE `t_enterprise_wallet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `associate_enterprise_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '关联企业号',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账号id',
  `inner_account` varchar(30) NOT NULL COMMENT '内部账号',
  `parent_account` varchar(30) NOT NULL COMMENT '父账号',
  `account_name` varchar(50) NOT NULL,
  `account_description` varchar(50) NOT NULL,
  `account_type` int(5) NOT NULL COMMENT '账户类型',
  `able_balance` decimal(20,4) unsigned zerofill NOT NULL COMMENT '可用余额',
  `freeze_balance` decimal(20,4) unsigned zerofill NOT NULL COMMENT '冻结金额',
  `is_show` int(1) NOT NULL,
  `status` int(2) NOT NULL COMMENT '状态',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `update_time` bigint(14) NOT NULL COMMENT '更新时间',
  `flag_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `verify_md5` varchar(50) NOT NULL COMMENT '金额验证MD5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_INNERACCOUNT` (`inner_account`),
  KEY `INDEX_EA_VERSION` (`enterprise_account`,`flag_version`),
  KEY `INDEX_EA` (`enterprise_account`)
) ENGINE=InnoDB AUTO_INCREMENT=588 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_wallet_log` */

CREATE TABLE `t_enterprise_wallet_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `wallet_log_no` varchar(30) NOT NULL COMMENT '钱包流水编号',
  `bank_busi_type` int(10) NOT NULL COMMENT '资金系统业务类型',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `related_order_no` varchar(50) NOT NULL COMMENT '关联订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业账号',
  `inner_account` varchar(20) NOT NULL,
  `account_type` int(5) NOT NULL,
  `fs_user_id` bigint(11) NOT NULL COMMENT '纷享用户ID',
  `gross_amount` decimal(20,4) NOT NULL COMMENT '毛总额',
  `fee` decimal(20,4) NOT NULL COMMENT '手续费',
  `net_amount` decimal(20,4) NOT NULL COMMENT '净总量',
  `fund_source` varchar(20) NOT NULL COMMENT '资金来源',
  `fund_destination` varchar(20) NOT NULL COMMENT '资金去向',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `able_balance_delta` decimal(20,4) NOT NULL COMMENT '可用金额偏移量',
  `freeze_balance_delta` decimal(20,4) NOT NULL COMMENT '冻结金额偏移量',
  `able_balance` decimal(20,4) unsigned zerofill NOT NULL COMMENT '此订单后可用余额',
  `freeze_balance` decimal(20,4) unsigned zerofill NOT NULL COMMENT '此订单后冻结金额',
  `trans_time` bigint(14) NOT NULL COMMENT '请求交易时间',
  `client_ip` varchar(45) NOT NULL COMMENT '请求方ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_ORDER_NO_AND_TYPE` (`order_no`,`bank_busi_type`),
  KEY `INDEX_EA_USERID` (`enterprise_account`,`fs_user_id`),
  KEY `INDEX_RELATED_ORDER_NO` (`related_order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=23473 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_wallet_type` */

CREATE TABLE `t_enterprise_wallet_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` int(5) NOT NULL,
  `account_name` varchar(20) NOT NULL,
  `account_description` varchar(50) NOT NULL,
  `permission` varchar(50) DEFAULT NULL COMMENT '子账户权限',
  `fee_getmoney` double(10,4) DEFAULT NULL COMMENT '提现手续费费率',
  `fee_transin` double(10,4) DEFAULT NULL COMMENT '入账手续费费率',
  `enable_type` int(5) DEFAULT NULL COMMENT '1- 默认开通，2-不默认开通',
  `is_initialized` tinyint(4) NOT NULL DEFAULT '1' COMMENT '初始化(1),不初始化(2)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `user_wallet` */

CREATE TABLE `user_wallet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `fs_user_id` bigint(11) NOT NULL COMMENT '纷享用户ID',
  `able_balance` decimal(20,4) unsigned zerofill NOT NULL DEFAULT '0000000000000000.0000' COMMENT '用户可用余额',
  `freeze_balance` decimal(20,4) unsigned zerofill NOT NULL DEFAULT '0000000000000000.0000' COMMENT '冻结余额',
  `used_balance` decimal(20,4) unsigned zerofill NOT NULL DEFAULT '0000000000000000.0000' COMMENT '已用金额',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '更新时间',
  `features` varchar(200) NOT NULL DEFAULT '{}',
  `flag_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `features_version` int(11) NOT NULL DEFAULT '0',
  `verify_md5` varchar(50) NOT NULL COMMENT '金额验证Md5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_EA_USERID` (`enterprise_account`,`fs_user_id`) USING BTREE,
  KEY `INDEX_EA_USERID_VERSION` (`enterprise_account`,`fs_user_id`,`flag_version`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=666 DEFAULT CHARSET=utf8;

/*Table structure for table `user_wallet_log` */

CREATE TABLE `user_wallet_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `wallet_log_no` varchar(30) NOT NULL COMMENT '用户钱包流水编号',
  `trans_type` int(10) NOT NULL DEFAULT '0' COMMENT '交易类型',
  `busi_no` int(10) NOT NULL DEFAULT '0' COMMENT '业务类型编号',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `related_order_no` varchar(50) NOT NULL DEFAULT '0' COMMENT '相关订单号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `fs_user_id` bigint(11) NOT NULL COMMENT '纷享用户ID',
  `amount` decimal(20,4) NOT NULL COMMENT '交易金额',
  `sys_fee` decimal(20,4) NOT NULL COMMENT '系统手续费',
  `actual_amount` decimal(20,4) NOT NULL COMMENT '实际交易金额',
  `fund_source` varchar(20) NOT NULL COMMENT '资金来源，用于对账',
  `fund_destination` varchar(20) NOT NULL COMMENT '资金去向，用于对账',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `bank_busi_type` int(10) NOT NULL DEFAULT '0' COMMENT '资金变动类型',
  `able_balance_delta` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '可用余额偏移量',
  `freeze_balance_delta` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '冻结余额偏移量',
  `able_balance` decimal(20,4) unsigned zerofill NOT NULL DEFAULT '0000000000000000.0000' COMMENT '用户可用余额',
  `freeze_balance` decimal(20,4) unsigned zerofill NOT NULL DEFAULT '0000000000000000.0000' COMMENT '冻结余额',
  `trans_time` bigint(14) NOT NULL COMMENT '请求j交易时间',
  `client_ip` varchar(45) NOT NULL DEFAULT '0' COMMENT 'IP地址',
  `features` varchar(200) NOT NULL DEFAULT '{}' COMMENT '扩展字段default “{}”',
  `features_version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `IDNEX_RELATE_ORDER` (`related_order_no`) USING BTREE,
  KEY `order_no` (`order_no`) USING BTREE,
  KEY `INDEX_EA_USERID` (`enterprise_account`,`fs_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57305 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
