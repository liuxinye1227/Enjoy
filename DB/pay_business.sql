/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - pay_business
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `pay_business`;

/*Table structure for table `aes_key_entity` */

CREATE TABLE `aes_key_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `aes_key` varchar(50) NOT NULL COMMENT 'AES Key以Base64编码形式',
  `version` varchar(30) NOT NULL COMMENT '版本号',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `binding_time` bigint(14) NOT NULL COMMENT '绑定时间',
  `expire_time` bigint(14) NOT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_VERSION_ID` (`version`)
) ENGINE=InnoDB AUTO_INCREMENT=22475 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=532214 DEFAULT CHARSET=utf8;

/*Table structure for table `charge_channel` */

CREATE TABLE `charge_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_name` varchar(30) NOT NULL COMMENT '渠道名称',
  `charge_fee` decimal(20,4) NOT NULL COMMENT '充值手续费',
  `enable` int(1) NOT NULL COMMENT '是否可用',
  `min_fee` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '保底手续费',
  `max_fee` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '封顶手续费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `merchant` */

CREATE TABLE `merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `merchant_id` varchar(50) NOT NULL DEFAULT '' COMMENT '商家ID',
  `merch_name` varchar(50) NOT NULL COMMENT '商户名称',
  `url` varchar(200) DEFAULT NULL COMMENT 'url',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `remark` text,
  `create_time` bigint(14) NOT NULL,
  `update_time` bigint(14) NOT NULL,
  `aes_key` varchar(50) DEFAULT NULL,
  `token` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_MERID` (`merchant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4974517 DEFAULT CHARSET=utf8;

/*Table structure for table `t_alipay_order` */

CREATE TABLE `t_alipay_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` int(1) NOT NULL COMMENT '标识订单来源(1:mobile;2:web)',
  `order_type` int(1) NOT NULL,
  `out_order_no` varchar(100) NOT NULL COMMENT '订单号',
  `subject` varchar(128) NOT NULL COMMENT '商户名称',
  `total_fee` bigint(20) NOT NULL COMMENT '总金额',
  `seller_id` varchar(64) DEFAULT NULL,
  `seller_account_name` varchar(64) DEFAULT NULL,
  `seller_email` varchar(64) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `quantity` bigint(20) DEFAULT NULL COMMENT '购买数量',
  `show_url` varchar(512) DEFAULT NULL COMMENT '商品展示URL',
  `paymethod` varchar(32) DEFAULT NULL COMMENT '默认支付方式',
  `body` varchar(512) DEFAULT NULL COMMENT '商品描述',
  `it_b_pay` varchar(32) DEFAULT NULL COMMENT '超时时间',
  `payment_type` int(1) DEFAULT NULL COMMENT '支付类型。默认值为：1（商品购买）',
  `goods_type` int(1) DEFAULT NULL COMMENT '商品类型(1:实物交易；0：虚拟交易)',
  `trade_no` varchar(100) DEFAULT NULL COMMENT '支付宝交易号',
  `trade_status` int(1) DEFAULT NULL COMMENT '交易状态',
  `gmt_create` datetime DEFAULT NULL COMMENT '交易创建时间',
  `gmt_payment` datetime DEFAULT NULL COMMENT '交易付款时间',
  `gmt_close` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `refund_status` varchar(16) DEFAULT NULL COMMENT '退款状态',
  `gmt_refund` datetime DEFAULT NULL COMMENT '退款时间',
  `buyer_id` varchar(64) DEFAULT NULL,
  `buyer_account_name` varchar(64) DEFAULT NULL,
  `buyer_email` varchar(64) DEFAULT NULL,
  `discount` varchar(20) DEFAULT NULL COMMENT '折扣',
  `is_total_fee_adjust` varchar(16) DEFAULT NULL COMMENT '是否调整总价',
  `use_coupon` varchar(16) DEFAULT NULL COMMENT '买家是否使用红包',
  `extra_common_param` varchar(256) DEFAULT NULL COMMENT '公用回传参数',
  `business_scene` varchar(16) DEFAULT NULL COMMENT '是否扫码支付',
  `batch_no` varchar(64) DEFAULT NULL COMMENT '退款批次号',
  `success_num` varchar(20) DEFAULT NULL COMMENT '退款成功笔数',
  `result_details` varchar(256) DEFAULT NULL COMMENT '退款结果明细',
  `pay_user_name` varchar(32) DEFAULT NULL COMMENT '付款账号姓名',
  `success_details` varchar(256) DEFAULT NULL COMMENT '成功的详细信息',
  `fail_details` varchar(256) DEFAULT NULL COMMENT '失败的详细信息',
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_OUT_ORDER_NO` (`out_order_no`),
  KEY `IDX_TRADE_NO` (`trade_no`),
  KEY `IDX_BATCH_NO` (`batch_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1986 DEFAULT CHARSET=utf8 COMMENT='支付宝订单流水';

/*Table structure for table `t_amount_limit` */

CREATE TABLE `t_amount_limit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `biz_type` int(11) NOT NULL COMMENT '业务类型',
  `base_lower` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '基础企业限额下限(单笔)',
  `base_upper` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '基础企业限额上限(单笔)',
  `auth_up` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '认证企业提升额度(单笔)',
  `paid_up` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '付费企业提升额度(单笔)',
  `base_day_upper` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '基础企业额度上限(累日)',
  `base_month_upper` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '基础企业限额上限，单位元(累月)',
  `base_month_free_upper` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '免费额度（累月）',
  `auth_day_up` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '认证企业提升额度(累日)',
  `paid_day_up` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '付费企业提升额度(累日)',
  `auth_month_up` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '认证企业提升额度(累月)',
  `auth_month_free_up` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '认证企业提升免费额度(累月)',
  `paid_month_up` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '付费企业提升额度(累月)',
  `paid_month_free_up` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '付费企业提升免费额度(累月)',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INX_BIZ_TYPE` (`biz_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Table structure for table `t_amount_limit_custom` */

CREATE TABLE `t_amount_limit_custom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业账号',
  `fs_user_id` bigint(20) DEFAULT '0' COMMENT '用户ID',
  `biz_type` int(11) NOT NULL DEFAULT '0' COMMENT '业务类型',
  `base_lower` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '基础企业限额下限(单笔)',
  `base_upper` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '基础企业限额上限(单笔)',
  `base_day_upper` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '基础企业额度上限(累日)',
  `base_month_upper` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '基础企业限额上限，单位元(累日)',
  `base_month_free_upper` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '免费额度（累月）',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付限额自定义表';

/*Table structure for table `t_area` */

CREATE TABLE `t_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(20) NOT NULL COMMENT '名称',
  `area_code` varchar(20) DEFAULT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=utf8;

/*Table structure for table `t_audit_log` */

CREATE TABLE `t_audit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) DEFAULT NULL COMMENT '企业账户',
  `fs_user_id` bigint(20) DEFAULT NULL COMMENT '纷享用户ID',
  `fs_user_name` varchar(32) NOT NULL COMMENT '纷享用户名称',
  `op_type` int(2) DEFAULT '1' COMMENT '操作类型：1-审核；',
  `objective_type` varchar(32) DEFAULT NULL COMMENT '操作对象，EA_AUTH：企业认证 EA_SM_APPLY',
  `objective_id` bigint(20) NOT NULL COMMENT '操作对象ID',
  `status_from` int(2) NOT NULL COMMENT '审核状态原始状态',
  `status_to` int(2) NOT NULL COMMENT '变更后的审核状态',
  `reason` text COMMENT '原因',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_OBID` (`objective_id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='保存审核操作的记录';

/*Table structure for table `t_bank` */

CREATE TABLE `t_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_code` varchar(20) NOT NULL COMMENT '银行编号',
  `bank_name` varchar(50) NOT NULL COMMENT '银行名称',
  `card_type` smallint(1) NOT NULL DEFAULT '1' COMMENT '1 储蓄卡 2信用卡',
  `bg_color` varchar(20) DEFAULT NULL COMMENT '背景颜色',
  `icon_key` varchar(300) DEFAULT NULL COMMENT '银行图片',
  `bg_image` varchar(100) DEFAULT NULL COMMENT '背景图片地址',
  `single_limit` decimal(10,2) DEFAULT NULL COMMENT '单笔限额',
  `day_limit` decimal(10,2) DEFAULT NULL COMMENT '单日限额',
  `month_limit` decimal(10,2) DEFAULT NULL COMMENT '单月限额',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注（如维护）',
  `status` smallint(1) DEFAULT '1' COMMENT '状态 1 可用 0 不可用',
  `icon_key3` varchar(300) DEFAULT NULL COMMENT '银行图标  3x',
  `begin_time` bigint(14) DEFAULT NULL,
  `end_time` bigint(14) DEFAULT NULL,
  `need_branch` smallint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `INDEX_CODE_TYPE` (`bank_code`,`card_type`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

/*Table structure for table `t_bank_branch` */

CREATE TABLE `t_bank_branch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_code` varchar(20) NOT NULL COMMENT '支行号',
  `branch_name` varchar(50) NOT NULL COMMENT '支行名称',
  `bank_code` varchar(20) NOT NULL COMMENT '银行编码',
  `area_id` int(10) NOT NULL COMMENT '所属市ID',
  `remark` varchar(100) DEFAULT '',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=435 DEFAULT CHARSET=utf8;

/*Table structure for table `t_blacklist` */

CREATE TABLE `t_blacklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(30) NOT NULL DEFAULT '' COMMENT '值',
  `type` int(4) NOT NULL COMMENT '类型：1 身份证，2：企业号 3：手机号  4：卡号',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Table structure for table `t_delete_card_log` */

CREATE TABLE `t_delete_card_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `bank_name` varchar(30) DEFAULT NULL COMMENT '开户行名称',
  `bank_no` int(10) DEFAULT NULL COMMENT '开户行行号',
  `bank_branch_name` varchar(30) DEFAULT '' COMMENT '开户行支行',
  `card_no` varchar(70) NOT NULL COMMENT '用户卡号',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `update_time` bigint(14) NOT NULL COMMENT '修改时间',
  `bank_id` varchar(20) DEFAULT '' COMMENT '银行ID',
  `bank_code` varchar(20) DEFAULT 'CMB' COMMENT '银行编码',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `phone` varchar(70) NOT NULL DEFAULT '' COMMENT '绑定银行卡的手机号',
  `card_name` varchar(50) NOT NULL DEFAULT '' COMMENT '开户名',
  `status` smallint(2) NOT NULL DEFAULT '0' COMMENT '0普通卡，1默认卡',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `fs_user_id` bigint(11) NOT NULL COMMENT '纷享用户ID',
  `card_type` smallint(1) DEFAULT '1' COMMENT '卡类型1借记卡（储蓄卡)2 贷记卡（信用卡）',
  `card_info_id` varchar(30) DEFAULT NULL COMMENT '客户银行卡ID（对应customId）',
  `short_card_no` varchar(50) DEFAULT NULL COMMENT '短卡号',
  `cvv2` varchar(20) DEFAULT NULL,
  `expire_date` varchar(20) DEFAULT NULL COMMENT '有效期',
  `type` smallint(1) DEFAULT '0' COMMENT '操作类型 0 注销 1解绑',
  PRIMARY KEY (`id`),
  KEY `INDEX_EA_FSUSERID` (`enterprise_account`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_account_info` */

CREATE TABLE `t_enterprise_account_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL COMMENT '企业号',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `enterprise_wallet_id` bigint(20) DEFAULT NULL COMMENT '企业钱包ID',
  `op_fs_user_id` bigint(20) DEFAULT NULL COMMENT '操作人员纷享用户ID',
  `op_fs_user_name` varchar(32) DEFAULT NULL COMMENT '操作人员昵称',
  `type` enum('BANK','ALIPAY') DEFAULT NULL COMMENT '账号类型',
  `is_default` tinyint(1) DEFAULT '0' COMMENT '是否为默认账号',
  `usable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用',
  `account_no` varchar(128) DEFAULT NULL COMMENT '第三方账号，银行卡号，加密保存',
  `account_name` varchar(32) DEFAULT NULL COMMENT '开户名',
  `bank_code` varchar(16) DEFAULT NULL COMMENT '银行代码( CMB:招商银行)',
  `bank_no` varchar(16) DEFAULT NULL COMMENT '开户行行号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '开户行名称',
  `bank_branch_name` varchar(64) DEFAULT NULL COMMENT '开户行支行名称',
  `phone` varchar(64) DEFAULT NULL COMMENT '绑定银行卡的手机号，加密保存',
  `card_type` varchar(10) DEFAULT NULL COMMENT '卡类型：CREDIT：贷记卡(信用卡)DEBIT：借记卡(储蓄卡)',
  `cvv2` varchar(16) DEFAULT NULL COMMENT '信用卡cvv2',
  `expire_date` varchar(16) DEFAULT NULL COMMENT '信用卡有效期',
  `features_version` tinyint(1) DEFAULT NULL,
  `features` varchar(256) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `province` varchar(32) DEFAULT NULL,
  `city` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_EA_WALLET_ID` (`enterprise_wallet_id`),
  KEY `IDX_EA_ACCOUNT` (`enterprise_account`,`sub_enterprise_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8 COMMENT='保存提现的银行卡或者第三方账户信息';

/*Table structure for table `t_enterprise_auth_info` */

CREATE TABLE `t_enterprise_auth_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL COMMENT '企业账户',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `fs_user_id` bigint(20) NOT NULL COMMENT '纷享用户ID',
  `fs_user_name` varchar(32) DEFAULT NULL COMMENT '纷享用户名称',
  `enterprise_name` varchar(256) NOT NULL COMMENT '企业名称',
  `corporation_name` varchar(32) DEFAULT NULL COMMENT '企业法人名',
  `operation_license_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '营业执照号',
  `operation_license_url` varchar(256) DEFAULT NULL COMMENT '企业营业执照url地址',
  `orgn_code_license_url` varchar(256) DEFAULT NULL COMMENT '组织代码证',
  `corporation_license_url` varchar(256) DEFAULT NULL COMMENT '企业法人执照',
  `id_card_front_url` varchar(256) DEFAULT NULL COMMENT '身份证正面',
  `id_card_back_url` varchar(256) DEFAULT NULL COMMENT '身份证背面',
  `status` int(5) NOT NULL DEFAULT '0' COMMENT '认证状态：0初始化；1处理中；98认证通过；99认证不通过。98与99的状态为审核操作固定的状态',
  `audit_status` int(5) DEFAULT '0' COMMENT '审核状态：0初始化；1待审核；2审核通过；3审核不通过',
  `fail_reason` text COMMENT '失败原因',
  `step` tinyint(4) NOT NULL DEFAULT '9' COMMENT '申请步骤，默认值为9(last step)',
  `account_info_id` bigint(20) DEFAULT NULL COMMENT '账号信息ID',
  `features` varchar(256) DEFAULT NULL COMMENT '扩展字段json格式',
  `features_version` tinyint(4) DEFAULT NULL COMMENT '扩展字段版本',
  `phone` varchar(16) DEFAULT NULL COMMENT '联系人电话',
  `id_card` varchar(32) DEFAULT NULL COMMENT '法人身份证号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_EAACCOUNT` (`enterprise_account`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='企业认证信息表，保存企业认证所需的信息，以及企业认证的状态';

/*Table structure for table `t_enterprise_bank` */

CREATE TABLE `t_enterprise_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_code` varchar(60) DEFAULT NULL,
  `bank_name` varchar(150) DEFAULT NULL,
  `card_type` smallint(1) DEFAULT NULL COMMENT '1：企业存储卡；3：提现卡； 4： 个人存储卡；5： 个人信用卡',
  `bg_color` varchar(60) DEFAULT NULL,
  `icon_key` varchar(900) DEFAULT NULL,
  `bg_image` varchar(300) DEFAULT NULL,
  `single_limit` decimal(12,0) DEFAULT NULL,
  `day_limit` decimal(12,0) DEFAULT NULL,
  `month_limit` decimal(12,0) DEFAULT NULL,
  `remark` varchar(600) DEFAULT NULL,
  `status` smallint(1) DEFAULT NULL,
  `icon_key3` varchar(900) DEFAULT NULL,
  `begin_time` bigint(14) DEFAULT NULL,
  `end_time` bigint(14) DEFAULT NULL,
  `need_branch` smallint(1) DEFAULT NULL,
  `channel_type` smallint(1) NOT NULL DEFAULT '1' COMMENT '1 快钱，4 连连支付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_charge` */

CREATE TABLE `t_enterprise_charge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL COMMENT '企业号',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `enterprise_wallet_id` varchar(30) DEFAULT '0' COMMENT '企业钱包ID，支持后续企业多账户',
  `op_fs_user_id` bigint(20) NOT NULL COMMENT '操作人员纷享用户ID',
  `op_fs_user_name` varchar(32) NOT NULL COMMENT '操作人员昵称',
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `third_trans_no` varchar(32) DEFAULT NULL COMMENT '第三方对应的交易流水号',
  `status` int(5) NOT NULL COMMENT '充值状态 0 初始化 1 成功 2失败',
  `trans_amount` bigint(20) NOT NULL COMMENT '用户充值交易金额，单位分',
  `actual_amount` bigint(20) NOT NULL COMMENT '减去手续费之后的金额',
  `sys_fee` bigint(20) DEFAULT '0' COMMENT '系统成本手续费',
  `actual_fee` bigint(20) DEFAULT '0' COMMENT '实际收取的手续费',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `fail_reason` varchar(128) DEFAULT NULL COMMENT '失败原因',
  `channel_id` enum('ALIPAY','KUAIQIAN','LIANLIAN','WECHAT') DEFAULT NULL COMMENT '渠道：ALIPAY:支付宝;KUAIQIAN:快钱;WECHAT:微信;lianlian:连连',
  `channel_pay_way` smallint(2) DEFAULT '-1' COMMENT '方式（0支付宝 1刷卡 2余额 3微信 4applepay）',
  `request_time` bigint(20) DEFAULT NULL COMMENT '请求时间',
  `response_time` bigint(20) DEFAULT NULL COMMENT '响应时间',
  `features_version` tinyint(1) DEFAULT '0',
  `features` varchar(256) DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  `card_no` varchar(64) DEFAULT NULL,
  `card_name` varchar(64) DEFAULT NULL,
  `bank_name` varchar(128) DEFAULT NULL,
  `bank_no` int(10) DEFAULT NULL,
  `able_balance` bigint(20) DEFAULT NULL COMMENT '资金操作后当时账户余额',
  PRIMARY KEY (`id`),
  KEY `IDX_WALLET_ID` (`enterprise_wallet_id`),
  KEY `IDX_OP_FS_USER_ID` (`op_fs_user_id`),
  KEY `IDX_ORDER_NO` (`order_no`),
  KEY `IDX_EA_ACCOUNT` (`enterprise_account`,`sub_enterprise_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2115 DEFAULT CHARSET=utf8 COMMENT='t_enterprise_charge保存企业充值记录';

/*Table structure for table `t_enterprise_getmoney` */

CREATE TABLE `t_enterprise_getmoney` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL COMMENT '企业号',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `enterprise_wallet_id` varchar(50) DEFAULT '0' COMMENT '企业钱包ID，支持后续企业多账户',
  `op_fs_user_id` bigint(20) NOT NULL COMMENT '操作人员纷享用户ID',
  `op_fs_user_name` varchar(32) NOT NULL COMMENT '操作人员昵称',
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `third_trans_no` varchar(32) DEFAULT NULL COMMENT '第三方对应的交易流水号',
  `batch_no` varchar(32) DEFAULT NULL COMMENT '批次号',
  `status` int(5) NOT NULL DEFAULT '0' COMMENT '提现状态INIT(0, "初始化"),AUDITING(1, "待审核"),AUDIT_PASS(2, "审核通过"),AUDIT_NOPASS(3, "审核不通过"),TELLER_PASS(4, "出纳通过"),TELLER_NOPASS(5, "出纳不通过"),TELLER_THIRD_ERROR(6, "提现失败"),ADUIT_NOPASS_REFUND(11, "审核不通过已退款"),ADUIT_NOPASS_NOREFUND(12, "审核不通过无退款"),ADUIT_NOPASS_REFUND_FAIL(13, "审核不通过退款失败"),TELLER_NOPASS_REFUND(14, "出纳不通过已退款"),TELLER_NOPASS_NOREFUND(15, "出纳不通过无退款"),TELLER_NOPASS_REFUND_FAIL(16, "出纳不通过退款失败"),SEND_THIRD_TELLER(17, "发送第三方申请"),SEND_THIRD_SUC(18, "第三方申请成功"),SEND_THIRD_FAIL(19, "第三方申请异常"),THIRD_WITHDRAW_FAIL(20, "第三方返回提现失败"),FINISH(10, "已提现")',
  `trans_amount` bigint(20) NOT NULL COMMENT '用户充值交易金额，单位分',
  `actual_amount` bigint(20) NOT NULL COMMENT '减去手续费之后的金额',
  `sys_fee` bigint(20) NOT NULL DEFAULT '0' COMMENT '系统成本手续费',
  `actual_fee` bigint(20) DEFAULT '0' COMMENT '实际收取的手续费',
  `city` varchar(32) DEFAULT NULL,
  `province` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `account_no` varchar(128) DEFAULT NULL COMMENT '第三方账号、银行卡号加密保存',
  `account_name` varchar(32) DEFAULT NULL COMMENT '户名',
  `bank_no` varchar(16) DEFAULT NULL COMMENT '开户行行号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '开户行名称',
  `bank_branch_name` varchar(64) DEFAULT NULL COMMENT '开户行支行名称',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `fail_reason` varchar(128) DEFAULT NULL COMMENT '失败原因',
  `channel_id` enum('ALIPAY','KUAIQIAN') DEFAULT NULL COMMENT '渠道：ALIPAY:支付宝;KUAIQIAN:快钱',
  `request_time` bigint(20) DEFAULT NULL COMMENT '请求时间',
  `response_time` bigint(20) DEFAULT NULL COMMENT '响应时间',
  `features_version` tinyint(1) DEFAULT '0',
  `features` varchar(256) DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  `card_no` varchar(64) DEFAULT NULL,
  `card_name` varchar(128) DEFAULT NULL,
  `able_balance` bigint(20) DEFAULT NULL COMMENT '资金操作后当时账户余额',
  `confirm_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '连连支付的提现验证码，当用户被风控时需要用到这个值;',
  PRIMARY KEY (`id`),
  KEY `IDX_WALLET_ID` (`enterprise_wallet_id`),
  KEY `IDX_OP_FS_USER_ID` (`op_fs_user_id`),
  KEY `IDX_ORDER_NO` (`order_no`),
  KEY `IDX_EA_ACCOUNT` (`enterprise_account`,`sub_enterprise_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=864 DEFAULT CHARSET=utf8 COMMENT='t_enterprise_charge保存企业提现记录';

/*Table structure for table `t_enterprise_isv_alipay` */

CREATE TABLE `t_enterprise_isv_alipay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL DEFAULT '' COMMENT '企业账号',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `user_id` varchar(64) NOT NULL DEFAULT '' COMMENT '授权商户的ID',
  `auth_app_id` varchar(64) NOT NULL DEFAULT '' COMMENT '授权商户的AppId',
  `app_auth_token` varchar(64) NOT NULL COMMENT '令牌',
  `token_expire_time` bigint(20) NOT NULL COMMENT '令牌的有效期，unix时间戳',
  `app_refresh_token` varchar(64) NOT NULL COMMENT '刷新令牌',
  `refresh_expire_time` bigint(20) NOT NULL COMMENT '刷新令牌有效期，unix时间戳',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，1有效，2无效',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_isv_wechat` */

CREATE TABLE `t_enterprise_isv_wechat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL DEFAULT '' COMMENT '企业账号',
  `sub_enterprise_account` varchar(32) NOT NULL COMMENT '分账户id',
  `sub_mch_id` varchar(64) NOT NULL COMMENT '子商户号',
  `sub_app_id` varchar(64) DEFAULT '' COMMENT '子商户公众账号ID',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态，1有效，2无效',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_msg_send_record` */

CREATE TABLE `t_enterprise_msg_send_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL COMMENT '企业账户',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `count` int(10) NOT NULL DEFAULT '1' COMMENT '统计次数',
  `type` int(11) DEFAULT '1' COMMENT '消息类型。1完善企业资料提醒消息',
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_EA_TYPE` (`enterprise_account`,`sub_enterprise_account`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='发送消息记录';

/*Table structure for table `t_enterprise_pay` */

CREATE TABLE `t_enterprise_pay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL COMMENT '企业号',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `enterprise_name` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `enterprise_wallet_id` varchar(50) DEFAULT '0' COMMENT '企业钱包ID，支持后续企业多账户',
  `to_enterprise_account` varchar(32) NOT NULL,
  `to_sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `to_enterprise_wallet_id` varchar(50) DEFAULT '0',
  `to_fs_user_name` varchar(100) DEFAULT NULL COMMENT '接收人名称，可为空',
  `to_fs_user_id` int(11) DEFAULT NULL COMMENT '接收人ID，可为空',
  `to_enterprise_name` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `op_fs_user_id` bigint(20) NOT NULL COMMENT '操作人员纷享用户ID',
  `op_fs_user_name` varchar(32) NOT NULL COMMENT '操作人员昵称',
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `associated_order_no` varchar(32) DEFAULT NULL COMMENT '关联的订单号，退款等业务用到',
  `third_trans_no` varchar(32) DEFAULT NULL COMMENT '第三方对应的交易流水号',
  `status` int(5) NOT NULL COMMENT '交易状态INIT_PAY(0, "支付初始化"),IN_PAY(1, "支付中"),FINISH_PAY(2, "支付完成"),FAIL_PAY(3, "支付失败"),OUTTIME_PAY(4, "支付过期")',
  `trans_amount` bigint(20) NOT NULL COMMENT '用户充值交易金额，单位分',
  `actual_amount` bigint(20) NOT NULL COMMENT '减去手续费之后的金额',
  `sys_fee` bigint(20) NOT NULL DEFAULT '0' COMMENT '系统成本手续费',
  `actual_fee` bigint(20) DEFAULT '0' COMMENT '实际收取的手续费',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `fail_reason` varchar(128) DEFAULT NULL COMMENT '失败原因',
  `channel_id` enum('ALIPAY','KUAIQIAN','WALLET','WECHAT','LIANLIAN') DEFAULT NULL COMMENT '支付方式：ALIPAY：支付宝支付;KUAIQIAN：快钱支付;WALLET_PAY：企业零钱支付,WECHAT:微信支付',
  `channel_pay_way` smallint(2) DEFAULT '-1' COMMENT '方式（0支付宝 1刷卡 2余额 3微信 4applepay）',
  `ware_id` varchar(64) DEFAULT NULL COMMENT '商品单号',
  `ware_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `merchant_id` varchar(64) DEFAULT NULL COMMENT '商户ID',
  `rel_order_no` varchar(64) DEFAULT NULL COMMENT '关联订单号（）',
  `goods_id` varchar(64) DEFAULT NULL COMMENT '商品ID，注册时给的',
  `merch_name` varchar(64) DEFAULT NULL COMMENT '商户名称',
  `request_time` bigint(20) DEFAULT NULL COMMENT '请求时间',
  `response_time` bigint(20) DEFAULT NULL COMMENT '响应时间',
  `expire_time` bigint(20) DEFAULT NULL COMMENT '过期时间',
  `features_version` tinyint(1) DEFAULT '0',
  `features` varchar(256) DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `card_no` varchar(64) DEFAULT NULL,
  `card_name` varchar(64) DEFAULT NULL,
  `bank_name` varchar(128) DEFAULT NULL,
  `bank_no` int(10) DEFAULT NULL,
  `trans_type` int(10) DEFAULT NULL COMMENT '业务子类型',
  `able_balance` bigint(20) DEFAULT NULL COMMENT '资金操作后当时账户余额',
  `use_agent` int(5) NOT NULL DEFAULT '0' COMMENT '是否使用代理商支付',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_ORDER_NO` (`order_no`),
  KEY `IDX_WALLET_ID` (`enterprise_wallet_id`),
  KEY `IDX_OP_FS_USER_ID` (`op_fs_user_id`),
  KEY `IDX_WARE_ID` (`ware_id`),
  KEY `IDX_EA_ACCOUNT` (`enterprise_account`,`sub_enterprise_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35609 DEFAULT CHARSET=utf8 COMMENT='t_enterprise_charge保存企业购买记录';

/*Table structure for table `t_enterprise_permission` */

CREATE TABLE `t_enterprise_permission` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '权限项名称',
  `desc` varchar(100) DEFAULT NULL COMMENT '权限项描述',
  `create_time` bigint(20) DEFAULT NULL COMMENT '权限项创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_rate` */

CREATE TABLE `t_enterprise_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据库自增ID',
  `enterprise_account` varchar(32) DEFAULT '' COMMENT '企业账号',
  `enterprise_wallet_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '企业钱包子账户类型',
  `biz_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '业务类型',
  `amount_valve_lower` bigint(20) NOT NULL DEFAULT '0' COMMENT '金额范围下限',
  `amount_valve_upper` bigint(20) NOT NULL DEFAULT '0' COMMENT '金额范围上限',
  `rate` double NOT NULL DEFAULT '0' COMMENT '费率',
  `expire_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '有效期',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用,可用(1),不可用(2)',
  `remark` varchar(128) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='企业钱包费率表';

/*Table structure for table `t_enterprise_refund` */

CREATE TABLE `t_enterprise_refund` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) DEFAULT NULL,
  `enterprise_name` varchar(100) DEFAULT NULL,
  `enterprise_wallet_id` varchar(50) NOT NULL,
  `op_fs_user_name` varchar(50) NOT NULL,
  `op_fs_user_id` int(32) NOT NULL,
  `trans_amount` bigint(20) NOT NULL,
  `refund_amount` bigint(20) NOT NULL,
  `pay_channel` int(2) DEFAULT NULL,
  `channel_pay_way` smallint(2) DEFAULT '-1' COMMENT '0支付宝 1刷卡 2余额 3微信 4applepay',
  `create_time` bigint(14) DEFAULT NULL,
  `update_time` bigint(14) DEFAULT NULL,
  `src_order_no` varchar(60) DEFAULT NULL,
  `associated_order_no` varchar(60) DEFAULT NULL COMMENT '关联的转账订单',
  `refund_order_no` varchar(60) DEFAULT NULL,
  `refund_reason` varchar(256) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `ware_id` varchar(64) DEFAULT NULL,
  `ware_name` varchar(128) DEFAULT NULL,
  `merchant_id` varchar(64) DEFAULT NULL,
  `goods_id` varchar(64) DEFAULT NULL,
  `merch_name` varchar(128) DEFAULT NULL,
  `response_time` bigint(14) DEFAULT NULL,
  `refund_id` varchar(64) DEFAULT NULL COMMENT '第三方支付返回的订单号',
  `status` tinyint(1) NOT NULL COMMENT '0 初始化 1 退款中 2 退款成功 3 第三方发送失败 4 退款失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_regulate` */

CREATE TABLE `t_enterprise_regulate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单号',
  `enterprise_account` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '企业号',
  `enterprise_wallet_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '企业钱包ID',
  `enterprise_wallet_type` int(4) NOT NULL COMMENT '钱包类型',
  `fs_user_id` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  `amount` decimal(20,4) NOT NULL COMMENT '金额',
  `status` int(2) NOT NULL COMMENT '状态（待审核、处理完成/审核不通过）',
  `create_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `create_username` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `trans_type` int(10) NOT NULL COMMENT '交易类型（减钱/加钱）',
  `start_time` bigint(14) NOT NULL COMMENT '申请时间',
  `end_time` bigint(14) DEFAULT NULL COMMENT '完成时间',
  `auditor_id` bigint(11) DEFAULT NULL COMMENT '审核人ID',
  `auditor_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '审核人',
  `auditor_time` bigint(14) DEFAULT NULL COMMENT '审核时间',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `update_time` bigint(14) NOT NULL COMMENT '更新时间',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_ORDER_NO` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `t_enterprise_sub_account` */

CREATE TABLE `t_enterprise_sub_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL DEFAULT '',
  `op_fs_user_id` bigint(20) NOT NULL COMMENT '操作人用户id',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账号id',
  `name` varchar(256) NOT NULL COMMENT '分账户名称',
  `is_main_account` tinyint(4) NOT NULL DEFAULT '2' COMMENT '是否主账号，1是，2不是',
  `status` tinyint(4) NOT NULL DEFAULT '2' COMMENT '有效状态，1有效，2无效',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDE_SUBEA` (`sub_enterprise_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_trade_summary` */

CREATE TABLE `t_enterprise_trade_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(32) NOT NULL COMMENT '企业号',
  `amount` bigint(20) NOT NULL COMMENT '金额',
  `trade_type` int(10) NOT NULL COMMENT '交易类型',
  `summary_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '日汇总：1，月汇总：2',
  `trade_number` int(10) NOT NULL COMMENT '交易次数',
  `trade_time` varchar(12) DEFAULT NULL COMMENT '结算时间2017-02-15',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `IDX_EA_ACCOUNT` (`enterprise_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3666 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_wallet_user` */

CREATE TABLE `t_enterprise_wallet_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `enterprise_account` varchar(20) DEFAULT '' COMMENT '企业账号',
  `fs_user_id` int(8) DEFAULT NULL COMMENT '用户账号（管理员或操作员）',
  `status` tinyint(1) DEFAULT NULL COMMENT '用户状态：1-可用， 0-不可用',
  `password` varchar(35) DEFAULT NULL COMMENT '密码',
  `password_error_num` smallint(4) DEFAULT NULL COMMENT '密码错误次数',
  `password_error_time` bigint(11) DEFAULT NULL COMMENT '输错密码时间',
  `type` tinyint(1) DEFAULT NULL COMMENT '管理类型：管理员或操作员，  1：为管理员，   2：操作员',
  `create_time` bigint(14) DEFAULT NULL COMMENT '记录创建时间',
  `operation_authority` text COMMENT '操作权限(操作权限，json格式',
  `features` varchar(200) DEFAULT NULL COMMENT '扩展字段default “{}”',
  `mq_status` tinyint(1) DEFAULT '0' COMMENT 'mq消息记录生产状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_enterpriseaccount_fsuserid` (`enterprise_account`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1132 DEFAULT CHARSET=utf8;

/*Table structure for table `t_enterprise_withdraw_blob` */

CREATE TABLE `t_enterprise_withdraw_blob` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `enterprise_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '企业账号',
  `sub_enterprise_account` varchar(64) NOT NULL DEFAULT '' COMMENT '分账户id',
  `op_fs_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '纷享用户ID',
  `wallet_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '子账户ID',
  `account_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '账户类型，1=对公账号，2=对私账号',
  `id_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '对私账号-银行卡-持卡人身份证号码',
  `id_card_front_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '对私账号-银行卡-持卡人-身份证正面图片URL',
  `id_card_back_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '对私账号-银行卡-持卡人-身份证反面图片URL',
  `bank_card_holder_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '对私账号-银行卡-持卡人名字',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态,有效(1),未生效(2)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '2' COMMENT '逻辑删除标志位，1=已删除，2=未删除',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后一次更新时间',
  `features` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '扩展字段json格式',
  `features_version` tinyint(4) DEFAULT '1' COMMENT '扩展字段版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_EA` (`enterprise_account`,`sub_enterprise_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

/*Table structure for table `t_identity_log` */

CREATE TABLE `t_identity_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_account` varchar(30) DEFAULT NULL,
  `fs_user_id` int(11) DEFAULT NULL,
  `card_name` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `type` smallint(1) DEFAULT '0' COMMENT '操作类型 0 实名注销',
  `id_card` varchar(70) DEFAULT NULL COMMENT '身份证号码',
  `phone_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `t_kuaiqian_order` */

CREATE TABLE `t_kuaiqian_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `source` int(1) NOT NULL,
  `order_type` int(1) NOT NULL,
  `trade_status` int(1) NOT NULL,
  `merchant_acct_id` varchar(100) NOT NULL,
  `payer_name` varchar(128) NOT NULL,
  `payer_contact_type` varchar(20) NOT NULL,
  `payer_contact` varchar(64) DEFAULT NULL,
  `payer_id_type` varchar(64) DEFAULT NULL,
  `payer_id` varchar(64) DEFAULT NULL,
  `payer_ip` varchar(50) DEFAULT NULL,
  `order_id` varchar(100) DEFAULT NULL,
  `order_amount` bigint(20) DEFAULT NULL,
  `order_time` varchar(512) DEFAULT NULL,
  `order_timestamp` varchar(50) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `product_num` int(1) DEFAULT NULL,
  `product_id` varchar(40) DEFAULT NULL,
  `product_desc` varchar(500) DEFAULT NULL,
  `ext1` varchar(256) DEFAULT NULL,
  `ext2` varchar(256) DEFAULT NULL,
  `pay_type` varchar(100) DEFAULT NULL,
  `bank_id` varchar(100) DEFAULT NULL,
  `card_issuer` varchar(100) DEFAULT NULL,
  `card_num` varchar(16) DEFAULT NULL,
  `remit_type` varchar(100) DEFAULT NULL,
  `remit_code` varchar(64) DEFAULT NULL,
  `redo_flag` varchar(64) DEFAULT NULL,
  `pid` varchar(64) DEFAULT NULL,
  `submit_type` varchar(20) DEFAULT NULL,
  `order_time_out` varchar(16) DEFAULT NULL,
  `ext_data_type` varchar(16) DEFAULT NULL,
  `ext_data_content` varchar(256) DEFAULT NULL,
  `refer_data_type` varchar(16) DEFAULT NULL,
  `refer_data` varchar(256) DEFAULT NULL,
  `deal_id` varchar(50) DEFAULT NULL,
  `bind_card` varchar(256) DEFAULT NULL,
  `bind_mobile` varchar(256) DEFAULT NULL,
  `bank_deal_id` varchar(32) DEFAULT NULL,
  `deal_time` varchar(256) DEFAULT NULL,
  `pay_amount` int(10) DEFAULT NULL,
  `fee` int(10) DEFAULT NULL,
  `pay_result` varchar(256) DEFAULT NULL,
  `province_city` varchar(256) DEFAULT NULL,
  `bank_name` varchar(256) DEFAULT NULL,
  `kaihuhang` varchar(256) DEFAULT NULL,
  `credit_name` varchar(256) DEFAULT NULL,
  `bank_card_number` varchar(256) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `deal_charge` varchar(256) DEFAULT NULL,
  `debit_charge` varchar(256) DEFAULT NULL,
  `credit_charge` varchar(256) DEFAULT NULL,
  `result_flag` varchar(256) DEFAULT NULL,
  `failure_cause` varchar(256) DEFAULT NULL,
  `redirect_url` varchar(200) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_ORDER_ID` (`order_id`),
  KEY `IDX_DEAL_ID` (`deal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=868 DEFAULT CHARSET=utf8 COMMENT='快钱订单流水';

/*Table structure for table `t_lianlian_charge_order` */

CREATE TABLE `t_lianlian_charge_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_url` varchar(200) DEFAULT NULL,
  `version` varchar(10) DEFAULT NULL COMMENT '版本号',
  `charset_name` varchar(10) DEFAULT NULL COMMENT '参数字符编码集',
  `oid_partner` varchar(50) DEFAULT NULL COMMENT '支付交易商户编',
  `user_id` varchar(50) DEFAULT NULL COMMENT '商户用户唯一编',
  `timestamp` varchar(20) DEFAULT NULL COMMENT '时间戳',
  `sign_type` varchar(10) DEFAULT NULL COMMENT '签名方式',
  `sign` varchar(100) DEFAULT NULL COMMENT '签名',
  `busi_partner` varchar(20) DEFAULT NULL COMMENT '商户业务类型',
  `no_order` varchar(50) DEFAULT NULL COMMENT '商户唯一订单号',
  `dt_order` varchar(20) DEFAULT NULL COMMENT '商户订单时间',
  `name_goods` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `info_order` varchar(100) DEFAULT NULL COMMENT '订单描述',
  `money_order` varchar(20) DEFAULT NULL COMMENT '交易金额',
  `notify_url` varchar(255) DEFAULT NULL COMMENT '服务器异步通知',
  `url_return` varchar(255) DEFAULT NULL COMMENT '支付结束回显',
  `userreq_ip` varchar(100) DEFAULT NULL COMMENT '用户端申请 IP',
  `url_order` varchar(255) DEFAULT NULL COMMENT '订单地址',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付方式',
  `risk_item` text COMMENT '风险控制参数',
  `bank_code` varchar(20) DEFAULT NULL COMMENT '指定银行网银编',
  `oid_pay_bill` varchar(50) DEFAULT NULL COMMENT '连连支付支付单',
  `result_pay` varchar(20) DEFAULT NULL COMMENT '支付结果',
  `settle_date` varchar(20) DEFAULT NULL COMMENT '清算日期',
  `update_time` bigint(14) DEFAULT NULL,
  `create_time` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_NO_ORDER` (`no_order`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

/*Table structure for table `t_lianlian_order` */

CREATE TABLE `t_lianlian_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no_order` varchar(32) NOT NULL COMMENT '商家订单号',
  `oid_partner` varchar(32) NOT NULL COMMENT '商户在银通所开商户号',
  `oid_biz` varchar(20) DEFAULT '' COMMENT '区分同一商户进行不同业务的交易',
  `money_order` varchar(12) NOT NULL COMMENT '该笔订单的资金总额，单位为 RMB-元。',
  `name_goods` varchar(255) DEFAULT '' COMMENT '商品名称',
  `dt_order` varchar(14) NOT NULL COMMENT '格式：YYYYMMDDH24MISS 14 位数字',
  `notify_url` varchar(256) DEFAULT '',
  `info_order` varchar(256) DEFAULT '' COMMENT '订单描述',
  `pay_type` varchar(2) NOT NULL COMMENT '0-微信扫码支付,5-支付宝扫码支付',
  `risk_item` varchar(256) DEFAULT '' COMMENT '风控参数',
  `sign_type` varchar(20) DEFAULT NULL COMMENT '请求签名方式',
  `sign` varchar(256) DEFAULT NULL COMMENT '签名串',
  `oid_order` varchar(50) DEFAULT NULL COMMENT '银+订单号',
  `dimension_url` varchar(256) DEFAULT NULL COMMENT '二维码地址',
  `pay_status` varchar(10) DEFAULT NULL COMMENT '0 成功 1 未支付 9 支付失败 2 已冲正 3已退款 4 部分退款 5 已撤销 6 支付关闭 7 预授权申请成功',
  `ret_code` varchar(20) DEFAULT NULL COMMENT '交易结果代码',
  `ret_msg` varchar(20) DEFAULT NULL COMMENT '交易结果描述',
  `result_pay` varchar(20) DEFAULT NULL COMMENT 'SUCCESS-消费成功 CORRECT-刷卡消费冲正成功 CANCEL-刷卡消费撤销',
  `dt_finish` varchar(14) DEFAULT NULL COMMENT '交易完成时间',
  `settle_date` varchar(14) DEFAULT NULL COMMENT '入账时间',
  `update_time` bigint(14) DEFAULT NULL,
  `create_time` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_NO_ORDER` (`no_order`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=693 DEFAULT CHARSET=utf8;

/*Table structure for table `t_merchandise` */

CREATE TABLE `t_merchandise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `merchant_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商家ID',
  `merchant_name` varchar(256) DEFAULT NULL COMMENT '商户名称',
  `ware_name` varchar(50) NOT NULL COMMENT '商品名称',
  `ware_id` varchar(50) NOT NULL COMMENT '商品ID',
  `url` varchar(200) DEFAULT NULL COMMENT '回调url',
  `pay_type` tinyint(2) DEFAULT '1' COMMENT '1：转账，2：冻结',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `remark` text,
  `create_time` bigint(14) NOT NULL,
  `update_time` bigint(14) NOT NULL,
  `aes_key` varchar(50) DEFAULT NULL,
  `token` varchar(70) DEFAULT NULL,
  `wallet_type` tinyint(2) NOT NULL COMMENT '钱包的子账户类型',
  PRIMARY KEY (`id`),
  KEY `INDEX_MERID` (`ware_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `t_merchant` */

CREATE TABLE `t_merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `merchant_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商家ID',
  `merchant_name` varchar(50) NOT NULL COMMENT '商户名称',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` bigint(14) NOT NULL,
  `update_time` bigint(14) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_MERID` (`merchant_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `t_notice` */

CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `begin_time` bigint(14) NOT NULL COMMENT '开始时间',
  `end_time` bigint(14) NOT NULL COMMENT '结束时间',
  `url` varchar(100) DEFAULT NULL COMMENT '链接地址',
  `notice_type` varchar(100) NOT NULL COMMENT '业务类型（多个，隔开）',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `t_online_apply` */

CREATE TABLE `t_online_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ea` varchar(50) NOT NULL,
  `fs_user_id` int(11) NOT NULL,
  `en` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `usage` text COMMENT '用途',
  `status` tinyint(1) DEFAULT NULL COMMENT '1 申请中 2 申请通过 3 申请不通过',
  `create_time` bigint(14) DEFAULT NULL COMMENT '申请时间',
  `fs_user_name` varchar(20) DEFAULT NULL,
  `ispay` tinyint(1) DEFAULT NULL COMMENT '1',
  `phone` varchar(20) DEFAULT NULL,
  `reason` varchar(1000) DEFAULT NULL COMMENT '审核通过/不通过原因',
  `remark` varchar(1000) DEFAULT NULL,
  `update_time` bigint(14) DEFAULT NULL,
  `trade` varchar(100) DEFAULT NULL COMMENT '所在行业',
  `type` varchar(30) DEFAULT NULL COMMENT '业务类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Table structure for table `user_card_info` */

CREATE TABLE `user_card_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `bank_name` varchar(30) DEFAULT NULL COMMENT '开户行名称',
  `bank_no` int(10) DEFAULT NULL COMMENT '开户行行号',
  `bank_branch_name` varchar(30) DEFAULT '' COMMENT '开户行支行',
  `card_no` varchar(70) NOT NULL COMMENT '用户卡号',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `update_time` bigint(14) NOT NULL COMMENT '修改时间',
  `bank_id` varchar(20) DEFAULT '' COMMENT '银行ID',
  `bank_code` varchar(20) DEFAULT 'CMB' COMMENT '银行编码',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `phone` varchar(70) NOT NULL DEFAULT '' COMMENT '绑定银行卡的手机号',
  `card_name` varchar(50) NOT NULL DEFAULT '' COMMENT '开户名',
  `status` smallint(2) NOT NULL DEFAULT '0' COMMENT '0普通卡，1默认卡',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `fs_user_id` bigint(11) NOT NULL COMMENT '纷享用户ID',
  `card_type` smallint(1) DEFAULT '1' COMMENT '卡类型1借记卡（储蓄卡)2 贷记卡（信用卡）',
  `card_info_id` varchar(30) DEFAULT NULL COMMENT '客户银行卡ID（对应customId）',
  `features_version` int(11) DEFAULT NULL,
  `features` varchar(200) DEFAULT NULL,
  `short_card_no` varchar(50) DEFAULT NULL COMMENT '短卡号',
  `cvv2` varchar(20) DEFAULT NULL,
  `expire_date` varchar(20) DEFAULT NULL COMMENT '有效期',
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_EA_FSUSERID` (`enterprise_account`,`fs_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36477 DEFAULT CHARSET=utf8;

/*Table structure for table `user_charge` */

CREATE TABLE `user_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `enterprise_account` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '企业号',
  `fs_user_id` bigint(11) NOT NULL COMMENT '纷享用户ID',
  `charge_no` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '充值流水编号',
  `charge_way` int(10) NOT NULL DEFAULT '0' COMMENT '充值方式(银行)',
  `actual_fee` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '实际收取的手续费',
  `sys_fee` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '系统成本手续费',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '充值状态 0 初始化 1 成功 2失败 ',
  `amount` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '充值金额',
  `channel_id` int(10) NOT NULL DEFAULT '0' COMMENT '充值渠道()',
  `request_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '请求时间',
  `request_info` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '请求信息',
  `response_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '第三方响应时间',
  `response_info` text COLLATE utf8mb4_unicode_ci COMMENT '响应内容',
  `charge_pay_no` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '充值商对应的流水号（可根据流水号查询第三方交易信息）',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT '0' COMMENT '更新时间',
  `card_no` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `card_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bank_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `bank_no` int(10) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `INDEX_ORDERNO` (`order_no`) USING BTREE,
  KEY `INDEX_ORDERNO_STATUS` (`order_no`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=480210 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `user_getmoney` */

CREATE TABLE `user_getmoney` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '订单号，出纳通过的时候生成',
  `enterprise_account` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '企业号',
  `fs_user_id` bigint(11) NOT NULL COMMENT '纷享用户ID',
  `amount` decimal(20,4) NOT NULL COMMENT '金额',
  `fee` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '应收手续费',
  `actual_fee` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '实际收的手续费',
  `bank_no` int(10) DEFAULT '0' COMMENT '开户行行号',
  `bank_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '开户行名称',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `auditor_id` int(11) DEFAULT NULL,
  `auditor` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '审核人',
  `teller_id` int(11) DEFAULT NULL,
  `teller` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '出纳人',
  `invoice` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '银行交易凭证',
  `update_time` bigint(14) DEFAULT '0' COMMENT '操作时间',
  `city` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `province` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `fail_reason` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '提款失败原因',
  `batch_id` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '提款批次ID',
  `batch_no` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '提款批次号',
  `busi_trade_no` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商户交易号',
  `status` int(5) NOT NULL,
  `bank_branch_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '开户行支行',
  `card_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '持卡人姓名',
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `card_no` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户卡号',
  `start_time` bigint(14) NOT NULL COMMENT '申请时间',
  `end_time` bigint(14) DEFAULT '0' COMMENT '完成时间',
  `auditor_time` bigint(14) DEFAULT '0' COMMENT '审批时间',
  `pay_channel` int(4) DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `teller_time` bigint(14) DEFAULT '0' COMMENT '出纳时间',
  PRIMARY KEY (`id`),
  KEY `INDEX_ORDERNO` (`order_no`) USING BTREE,
  KEY `INDEX_EA_USERID_ORDERNO` (`order_no`,`enterprise_account`,`fs_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=92897 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `user_info` */

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `enterprise_account` varchar(20) NOT NULL COMMENT '企业号',
  `fs_user_id` bigint(11) NOT NULL COMMENT '纷享用户ID',
  `status` int(1) NOT NULL COMMENT '用户状态',
  `password` varchar(35) NOT NULL,
  `password_error_num` int(5) NOT NULL DEFAULT '0' COMMENT '密码错误次数',
  `password_error_time` bigint(14) DEFAULT NULL COMMENT '输错密码时间',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `card_name` varchar(20) DEFAULT '',
  `features` varchar(200) NOT NULL DEFAULT '{}' COMMENT '扩展字段default “{}”',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '1 员工 2 企业帐号',
  `id_card` varchar(70) DEFAULT NULL COMMENT '用户身份证号',
  `phone_no` varchar(70) DEFAULT NULL COMMENT '用户手机号',
  `identity_verify` smallint(2) DEFAULT '0' COMMENT '是否身份认证 1 是 0 否',
  `create_time` bigint(14) NOT NULL,
  `update_time` bigint(14) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_EA_USERID` (`enterprise_account`,`fs_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2475 DEFAULT CHARSET=utf8;

/*Table structure for table `user_regulate` */

CREATE TABLE `user_regulate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单号',
  `enterprise_account` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '企业号',
  `fs_user_id` bigint(11) NOT NULL COMMENT '用户ID',
  `username` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `amount` decimal(20,4) NOT NULL COMMENT '金额',
  `status` int(2) NOT NULL COMMENT '状态（待审核、处理完成/审核不通过）',
  `create_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `create_username` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `trans_type` int(10) NOT NULL COMMENT '交易类型（减钱/加钱）',
  `start_time` bigint(14) NOT NULL COMMENT '申请时间',
  `end_time` bigint(14) DEFAULT NULL COMMENT '完成时间',
  `auditor_id` bigint(11) DEFAULT NULL COMMENT '审核人ID',
  `auditor_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '审核人',
  `auditor_time` bigint(14) DEFAULT NULL COMMENT '审核时间',
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `update_time` bigint(14) NOT NULL COMMENT '更新时间',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`,`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `user_transfer` */

CREATE TABLE `user_transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单号',
  `amount` decimal(20,4) NOT NULL,
  `from_user_id` bigint(11) NOT NULL,
  `to_user_id` bigint(11) DEFAULT NULL,
  `from_enterprise_account` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `to_enterprise_account` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` bigint(14) NOT NULL COMMENT '创建时间',
  `status` int(1) NOT NULL COMMENT '状态1转账中 2 转账完成 3 过期',
  `update_time` bigint(14) DEFAULT '0',
  `start_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '转账开始时间',
  `end_time` bigint(14) DEFAULT '0' COMMENT '转账结束时间',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `bank_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '银行名称',
  `card_no` varchar(70) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '银行卡号',
  `pay_way` smallint(1) NOT NULL DEFAULT '1' COMMENT '支付方式 1 零钱 2 银行卡',
  `charge_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '充值流水号',
  `expire_time` bigint(14) NOT NULL DEFAULT '0' COMMENT '过期时间',
  `from_user_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '转账人姓名',
  `to_user_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '接收人姓名',
  `card_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '银行卡真实姓名',
  `ware_id` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商品ID',
  `merchant_id` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商户ID',
  `rel_order_no` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '关联订单号（）',
  `merch_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商户名称',
  `ware_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商品名称',
  `trans_type` int(10) NOT NULL DEFAULT '0' COMMENT '交易类型',
  PRIMARY KEY (`id`),
  KEY `INDEX_ORDERNO` (`order_no`) USING BTREE,
  KEY `INDEX_MERID_WAREID` (`ware_id`,`merchant_id`) USING BTREE,
  KEY `INDEX_RELORDERNO_STATUS` (`rel_order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=614054 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `weixin_log` */

CREATE TABLE `weixin_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(32) DEFAULT NULL,
  `mch_id` varchar(32) DEFAULT NULL,
  `nonce_str` varchar(32) DEFAULT NULL,
  `sign` varchar(32) DEFAULT NULL,
  `body` varchar(128) DEFAULT NULL,
  `detail` varchar(6000) DEFAULT NULL,
  `out_trade_no` varchar(32) DEFAULT NULL,
  `total_fee` int(11) DEFAULT NULL,
  `spbill_create_ip` varchar(16) DEFAULT NULL,
  `time_start` varchar(14) DEFAULT NULL,
  `time_expire` varchar(14) DEFAULT NULL,
  `notify_url` varchar(256) DEFAULT NULL,
  `trade_type` varchar(16) DEFAULT NULL,
  `product_id` varchar(32) DEFAULT NULL,
  `limit_pay` varchar(32) DEFAULT NULL,
  `open_id` varchar(128) DEFAULT NULL,
  `return_code` varchar(16) DEFAULT NULL,
  `result_code` varchar(16) DEFAULT NULL,
  `bank_type` varchar(16) DEFAULT NULL,
  `transaction_id` varchar(32) DEFAULT NULL,
  `time_end` varchar(14) DEFAULT NULL,
  `is_subscribe` varchar(1) DEFAULT NULL,
  `rel_order_no` varchar(60) DEFAULT NULL COMMENT '订单号 （退款时，存原始交易订单号）',
  PRIMARY KEY (`id`),
  KEY `INDEX_OUT_TRADE_NO` (`out_trade_no`)
) ENGINE=InnoDB AUTO_INCREMENT=739 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
