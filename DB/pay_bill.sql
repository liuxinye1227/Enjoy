/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - pay_bill
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `pay_bill`;

/*Table structure for table `t_alipay_charge` */

CREATE TABLE `t_alipay_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` varchar(30) DEFAULT NULL,
  `income` varchar(30) DEFAULT NULL,
  `outcome` varchar(30) DEFAULT NULL,
  `trans_date` varchar(30) DEFAULT NULL,
  `sub_trans_code_msg` varchar(50) DEFAULT NULL,
  `trans_code_msg` varchar(50) DEFAULT NULL,
  `merchant_out_order_no` varchar(50) DEFAULT NULL,
  `trans_out_order_no` varchar(50) DEFAULT NULL,
  `bank_name` varchar(50) DEFAULT NULL,
  `bank_account_no` varchar(50) DEFAULT NULL,
  `bank_account_name` varchar(50) DEFAULT NULL,
  `memo` varchar(50) DEFAULT NULL,
  `buyer_account` varchar(50) DEFAULT NULL,
  `seller_account` varchar(50) DEFAULT NULL,
  `seller_fullname` varchar(50) DEFAULT NULL,
  `currency` varchar(30) DEFAULT NULL,
  `deposit_bank_no` varchar(50) DEFAULT NULL,
  `goods_title` varchar(50) DEFAULT NULL,
  `iw_account_log_id` varchar(50) DEFAULT NULL,
  `trans_account` varchar(50) DEFAULT NULL,
  `other_account_email` varchar(50) DEFAULT NULL,
  `other_account_full_name` varchar(50) DEFAULT NULL,
  `other_user_id` varchar(50) DEFAULT NULL,
  `partner_id` varchar(50) DEFAULT NULL,
  `service_fee` varchar(30) DEFAULT NULL,
  `service_fee_ratio` varchar(30) DEFAULT NULL,
  `total_fee` varchar(30) DEFAULT NULL,
  `trade_no` varchar(50) DEFAULT NULL,
  `trade_refund_amount` varchar(30) DEFAULT NULL,
  `sign_product_name` varchar(50) DEFAULT NULL,
  `rate` varchar(30) DEFAULT NULL,
  `ext_info` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6849 DEFAULT CHARSET=utf8;

/*Table structure for table `t_kq_ea_charge` */

CREATE TABLE `t_kq_ea_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deal_id` varchar(30) NOT NULL COMMENT '快钱交易号',
  `deal_time` varchar(14) NOT NULL COMMENT '快钱交易时间',
  `fee` decimal(20,4) NOT NULL COMMENT '快钱收取商户的手续费',
  `order_amount` decimal(20,4) NOT NULL COMMENT '商户订单金额',
  `order_id` varchar(50) NOT NULL COMMENT '商户订单号',
  `order_time` varchar(14) NOT NULL COMMENT '商户订单提交时间',
  `pay_amount` decimal(20,4) NOT NULL COMMENT '订单实际支付金额',
  `pay_result` varchar(10) NOT NULL COMMENT '支付结果',
  `pay_type` varchar(10) NOT NULL COMMENT '支付方式',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `INDEX_ORDER_ID` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Table structure for table `t_kq_ea_withdraw` */

CREATE TABLE `t_kq_ea_withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deal_begin_date` varchar(20) NOT NULL,
  `deal_end_date` varchar(20) NOT NULL,
  `deal_id` varchar(30) NOT NULL,
  `query_type` varchar(20) NOT NULL,
  `order_id` varchar(50) NOT NULL,
  `result_flag` tinyint(2) NOT NULL,
  `amount` decimal(20,4) NOT NULL,
  `deal_status` varchar(20) NOT NULL,
  `failure_cause` varchar(20) DEFAULT '',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=367 DEFAULT CHARSET=utf8;

/*Table structure for table `t_kq_single_charge` */

CREATE TABLE `t_kq_single_charge` (
  `mechant_name` varchar(50) DEFAULT NULL COMMENT '商户名称',
  `mechant_id` varchar(50) DEFAULT NULL COMMENT '商户编号',
  `trade_name` varchar(50) DEFAULT NULL COMMENT '交易产品',
  `trade_no` varchar(50) DEFAULT NULL COMMENT '交易号',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单号',
  `trade_start_date` varchar(50) DEFAULT NULL COMMENT '交易开始日期',
  `trade_start_time` varchar(50) DEFAULT NULL COMMENT '交易开始时间',
  `trade_status` varchar(50) DEFAULT NULL COMMENT '交易状态',
  `trade_type` varchar(50) DEFAULT NULL COMMENT '交易类型',
  `trade_amount` decimal(20,2) DEFAULT NULL COMMENT '交易金额',
  `trade_fee` decimal(20,2) DEFAULT NULL COMMENT '手续费',
  `trade_end_date` varchar(50) DEFAULT NULL COMMENT '交易结束日期',
  `trade_end_time` varchar(50) DEFAULT NULL COMMENT '交易结束时间',
  `settle_time` varchar(50) DEFAULT NULL COMMENT '结算时间',
  `actual_settle_amount` decimal(20,2) DEFAULT NULL COMMENT '实际结算金额',
  `settle_account` varchar(50) DEFAULT NULL COMMENT '结算账号',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41448 DEFAULT CHARSET=utf8;

/*Table structure for table `t_kq_single_withdraw` */

CREATE TABLE `t_kq_single_withdraw` (
  `kuaiqian_id` int(11) DEFAULT NULL,
  `trade_id` varchar(50) DEFAULT NULL,
  `pay_account` varchar(50) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `bank_name` varchar(50) DEFAULT NULL,
  `card_no` varchar(50) DEFAULT NULL,
  `opening_bank_name` varchar(50) DEFAULT NULL,
  `trade_amount` decimal(20,2) DEFAULT NULL,
  `commission` decimal(20,2) DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `trade_type` varchar(50) DEFAULT NULL,
  `trade_status` varchar(20) DEFAULT NULL,
  `batch_id` varchar(50) DEFAULT NULL,
  `fail_reason` varchar(100) DEFAULT NULL,
  `error_code` varchar(50) DEFAULT NULL,
  `original_trade_id` varchar(50) DEFAULT NULL,
  `refund_time` varchar(50) DEFAULT NULL,
  `merchant_order_no` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `merchant_order_no` (`merchant_order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=84699 DEFAULT CHARSET=utf8;

/*Table structure for table `t_lianlian_ea_charge` */

CREATE TABLE `t_lianlian_ea_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no_order` varchar(50) NOT NULL COMMENT '商户订单号',
  `oid_partner` varchar(50) NOT NULL DEFAULT '' COMMENT '商户号',
  `dt_order` varchar(20) NOT NULL DEFAULT '' COMMENT '商家订单时间',
  `oid_biz` varchar(30) NOT NULL DEFAULT '' COMMENT '商户业务编号',
  `oid_order` varchar(50) NOT NULL DEFAULT '' COMMENT '银通订单号',
  `oid_settle_date` varchar(20) NOT NULL DEFAULT '' COMMENT '财务日期',
  `money_order` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `collect_type` varchar(2) NOT NULL DEFAULT '' COMMENT '商户收款标志(0收款 1付款)',
  `pay_status` varchar(2) NOT NULL DEFAULT '' COMMENT '交易状态(0-成功 5-已退款)',
  `update_time` varchar(20) NOT NULL DEFAULT '' COMMENT '更新时间',
  `fee` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '手续费',
  `product` varchar(50) NOT NULL DEFAULT '' COMMENT '支付产品',
  `pay_type` varchar(50) NOT NULL DEFAULT '' COMMENT '支付方式',
  `order_info` varchar(200) NOT NULL DEFAULT '' COMMENT '订单信息',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;

/*Table structure for table `t_lianlian_ea_code_pay` */

CREATE TABLE `t_lianlian_ea_code_pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid_order` varchar(50) NOT NULL COMMENT '银加订单号',
  `no_order` varchar(50) NOT NULL COMMENT '商户订单号',
  `oid_partner` varchar(50) NOT NULL DEFAULT '' COMMENT '商户号',
  `dt_order` varchar(20) NOT NULL DEFAULT '' COMMENT '商家订单时间',
  `oid_biz` varchar(30) NOT NULL DEFAULT '' COMMENT '商户业务编号',
  `oid_order2` varchar(50) NOT NULL DEFAULT '' COMMENT '银通订单号',
  `oid_settle_date` varchar(20) NOT NULL DEFAULT '' COMMENT '财务日期',
  `money_order` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `collect_type` varchar(2) NOT NULL DEFAULT '' COMMENT '商户收款标志(0收款 1付款)',
  `pay_status` varchar(2) NOT NULL DEFAULT '' COMMENT '交易状态(0-成功 5-已退款)',
  `update_time` varchar(20) NOT NULL DEFAULT '' COMMENT '更新时间',
  `fee` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '手续费',
  `product` varchar(50) NOT NULL DEFAULT '' COMMENT '支付产品',
  `pay_type` varchar(50) NOT NULL DEFAULT '' COMMENT '支付方式',
  `order_info` varchar(200) NOT NULL DEFAULT '' COMMENT '订单信息',
  `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '操作员',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11316 DEFAULT CHARSET=utf8;

/*Table structure for table `t_lianlian_ea_withdraw` */

CREATE TABLE `t_lianlian_ea_withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no_order` varchar(50) NOT NULL COMMENT '商户订单号',
  `oid_partner` varchar(50) NOT NULL DEFAULT '' COMMENT '商户号',
  `dt_order` varchar(20) NOT NULL DEFAULT '' COMMENT '商家订单时间',
  `oid_biz` varchar(30) NOT NULL DEFAULT '' COMMENT '商户业务编号',
  `oid_order` varchar(50) DEFAULT '' COMMENT '银通订单号',
  `oid_settle_date` varchar(20) DEFAULT '' COMMENT '财务日期',
  `money_order` decimal(20,2) DEFAULT '0.00' COMMENT '订单金额',
  `collect_type` varchar(2) DEFAULT '' COMMENT '商户收款标志(0收款 1付款)',
  `pay_status` varchar(2) DEFAULT '' COMMENT '交易状态(0-成功 5-已退款)',
  `update_time` varchar(20) DEFAULT '' COMMENT '更新时间',
  `fee` decimal(20,2) DEFAULT '0.00' COMMENT '手续费',
  `product` varchar(50) DEFAULT '' COMMENT '支付产品',
  `pay_type` varchar(50) DEFAULT '' COMMENT '支付方式',
  `order_info` varchar(200) DEFAULT '' COMMENT '订单信息',
  `bank_name` varchar(50) DEFAULT NULL COMMENT '收款银行',
  `card_no` varchar(30) DEFAULT NULL COMMENT '收款方卡号',
  `card_name` varchar(50) DEFAULT NULL COMMENT '收款方名称',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1411 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_error_account` */

CREATE TABLE `t_pay_bill_error_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(20) NOT NULL,
  `account` varchar(20) NOT NULL,
  `real_balance` decimal(20,4) NOT NULL COMMENT '实际的余额',
  `bill_balance` decimal(20,4) NOT NULL COMMENT '对账的余额(上一次对账余额+流水)',
  `status` int(6) NOT NULL COMMENT '0未处理，1已处理',
  `remark` text NOT NULL,
  `update_time` bigint(14) DEFAULT NULL,
  `create_time` bigint(14) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=477 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_error_order` */

CREATE TABLE `t_pay_bill_error_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_order_no` varchar(32) NOT NULL COMMENT '支付订单',
  `merchant_order_no` varchar(32) NOT NULL COMMENT '商户ID',
  `delta_amount` decimal(20,2) NOT NULL COMMENT '资金差额',
  `merchant_id` varchar(64) NOT NULL COMMENT '商户号',
  `goods_id` varchar(64) NOT NULL COMMENT '商品ID',
  `error_type` int(2) NOT NULL COMMENT '1：漏单，2：长款，3：短款，4：未知错误',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(2) NOT NULL COMMENT '处理状态，1：已处理，0：未处理',
  `update_time` bigint(14) NOT NULL,
  `create_time` bigint(14) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=224901 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_inner_order` */

CREATE TABLE `t_pay_bill_inner_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `account` varchar(20) NOT NULL COMMENT '用户帐号，（个人:fsUserId,企业：innerAccount）',
  `pay_order_no` varchar(32) NOT NULL COMMENT '支付订单号',
  `related_order_no` varchar(32) DEFAULT NULL COMMENT '商户订单号',
  `merchant_id` varchar(64) NOT NULL COMMENT '商户',
  `goods_id` varchar(64) NOT NULL COMMENT '商品ID（未对账时，能知道是那个商户缺了）',
  `balance` decimal(20,2) NOT NULL COMMENT '账户余额',
  `trade_amount` decimal(20,2) NOT NULL COMMENT '交易金额',
  `fee` decimal(20,2) NOT NULL DEFAULT '0.00',
  `balance_delta` decimal(20,2) NOT NULL COMMENT '变动金额（可用变动+冻结变动）如：冻结10块钱，可用-10+10 = 0',
  `status` int(5) NOT NULL COMMENT '0，未对账，1，已对账（用户数据迁移及漏单）,或许：0不能迁移，1可以迁移',
  `request_time` bigint(14) unsigned NOT NULL COMMENT '支付请求时间',
  `update_time` bigint(14) NOT NULL,
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `INDEX_USER_QUERY` (`enterprise_account`,`account`) USING BTREE,
  KEY `INDEX_ORDER` (`pay_order_no`) USING BTREE,
  KEY `INDEX_RELATED_ORDER` (`related_order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3567 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_inner_order_history` */

CREATE TABLE `t_pay_bill_inner_order_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `account` varchar(20) NOT NULL COMMENT '用户帐号，（个人:fsUserId,企业：innerAccount）',
  `pay_order_no` varchar(32) NOT NULL COMMENT '支付订单号',
  `related_order_no` varchar(32) DEFAULT NULL COMMENT '商户订单号',
  `merchant_id` varchar(64) NOT NULL COMMENT '商户',
  `goods_id` varchar(64) NOT NULL COMMENT '商品ID（未对账时，能知道是那个商户缺了）',
  `balance` decimal(20,2) NOT NULL COMMENT '账户余额',
  `trade_amount` decimal(20,2) NOT NULL COMMENT '交易金额',
  `fee` decimal(20,2) NOT NULL DEFAULT '0.00',
  `balance_delta` decimal(20,2) NOT NULL COMMENT '变动金额（可用变动+冻结变动）如：冻结10块钱，可用-10+10 = 0',
  `status` int(5) NOT NULL COMMENT '0，未对账，1，已对账（用户数据迁移及漏单）',
  `request_time` bigint(14) unsigned NOT NULL COMMENT '支付请求时间',
  `update_time` bigint(14) NOT NULL,
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2895 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_merchant` */

CREATE TABLE `t_pay_bill_merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_id` varchar(64) NOT NULL,
  `goods_id` varchar(64) NOT NULL,
  `merchant_name` varchar(30) DEFAULT NULL,
  `manager` varchar(200) DEFAULT NULL COMMENT '管理人',
  `remark` text,
  `status` smallint(2) NOT NULL DEFAULT '0' COMMENT '启用',
  `update_time` bigint(14) NOT NULL,
  `create_time` bigint(14) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_merchant_order` */

CREATE TABLE `t_pay_bill_merchant_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_order_no` varchar(32) NOT NULL COMMENT '业务单号',
  `pay_order_no` varchar(32) NOT NULL COMMENT '支付单号',
  `trade_amount` decimal(20,2) NOT NULL COMMENT '金额',
  `free_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '手续费',
  `merchant_id` varchar(64) NOT NULL COMMENT '商户',
  `goods_id` varchar(64) NOT NULL COMMENT '商品ID',
  `trade_time` bigint(20) NOT NULL COMMENT '数据导入时间，以该时间对账',
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1311 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_outer_order` */

CREATE TABLE `t_pay_bill_outer_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_order_no` varchar(32) NOT NULL COMMENT '业务单号',
  `pay_order_no` varchar(32) NOT NULL COMMENT '支付单号',
  `trade_amount` decimal(20,2) NOT NULL COMMENT '金额',
  `free_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '手续费',
  `merchant_id` varchar(64) NOT NULL COMMENT '商户',
  `goods_id` varchar(64) NOT NULL COMMENT '商品ID',
  `trade_time` bigint(20) NOT NULL COMMENT '数据导入时间，以该时间对账',
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147993 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_result` */

CREATE TABLE `t_pay_bill_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_total` int(11) NOT NULL COMMENT '支付单总数(适用于内部单)',
  `merchant_total` int(11) NOT NULL COMMENT '商户单总数',
  `error_total` int(11) NOT NULL COMMENT '异单数量',
  `start_time` bigint(14) NOT NULL COMMENT '开始对账时间',
  `end_time` bigint(14) NOT NULL COMMENT '结束对账时间',
  `cost_time` int(11) NOT NULL COMMENT '对账花费时间',
  `merchant_id` varchar(64) NOT NULL,
  `goods_id` varchar(64) NOT NULL,
  `create_time` bigint(14) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4531 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_result_detail` */

CREATE TABLE `t_pay_bill_result_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(20) NOT NULL,
  `account` varchar(20) NOT NULL,
  `balance` decimal(20,2) NOT NULL COMMENT '正确的余额',
  `bill_time` bigint(14) NOT NULL COMMENT '对账时间（以该时间下次对账时间）',
  `create_time` bigint(14) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_EA_USERID` (`enterprise_account`,`account`)
) ENGINE=InnoDB AUTO_INCREMENT=1876 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pay_bill_schedule` */

CREATE TABLE `t_pay_bill_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_name` varchar(100) NOT NULL COMMENT '计划名称',
  `content` varchar(200) DEFAULT NULL COMMENT '执行数量',
  `cost_time` int(11) DEFAULT NULL,
  `status` smallint(2) DEFAULT NULL COMMENT '状态（1成功，0失败）',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9042 DEFAULT CHARSET=utf8;

/*Table structure for table `t_wx_charge` */

CREATE TABLE `t_wx_charge` (
  `trade_time` varchar(20) DEFAULT '' COMMENT '交易时间',
  `app_id` varchar(50) DEFAULT '' COMMENT '公众账号ID',
  `mch_id` varchar(50) DEFAULT '' COMMENT '商户号',
  `mch_child_id` varchar(50) DEFAULT '' COMMENT '子商户号',
  `device_info` varchar(50) DEFAULT '' COMMENT '设备号',
  `wx_order_id` varchar(50) DEFAULT '' COMMENT '微信订单号',
  `order_no` varchar(50) DEFAULT '' COMMENT '商户订单号',
  `wx_user_id` varchar(50) DEFAULT '' COMMENT '用户标识',
  `trade_type` varchar(50) DEFAULT '' COMMENT '交易类型',
  `trade_status` varchar(50) DEFAULT '' COMMENT '交易状态',
  `pay_bank` varchar(50) DEFAULT '' COMMENT '付款银行',
  `currency_type` varchar(50) DEFAULT '' COMMENT '货币种类',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '总金额',
  `discount_amount` varchar(50) DEFAULT '' COMMENT '代金券或立减优惠金额',
  `wx_refund_order` varchar(50) DEFAULT '' COMMENT '微信退款单号',
  `fs_refund_order` varchar(50) DEFAULT '' COMMENT '商户退款单号',
  `refund_amount` varchar(50) DEFAULT '' COMMENT '退款金额',
  `discount_refund_amount` varchar(50) DEFAULT '' COMMENT '代金券或立减优惠退款金额',
  `refund_type` varchar(50) DEFAULT '' COMMENT '退款类型',
  `refund_status` varchar(50) DEFAULT '' COMMENT '退款状态',
  `mch_name` varchar(50) DEFAULT '' COMMENT '商品名称',
  `mch_data` varchar(50) DEFAULT '' COMMENT '商户数据包',
  `fee` decimal(20,2) DEFAULT NULL COMMENT '手续费',
  `fee_rate` varchar(50) DEFAULT '' COMMENT '费率',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=320126 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
