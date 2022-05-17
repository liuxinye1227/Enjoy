/*
SQLyog v10.2 
MySQL - 5.6.27-log : Database - open_email_proxy
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `open_email_proxy`;

/*Table structure for table `crm_email_bind_1` */

CREATE TABLE `crm_email_bind_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL COMMENT 'CRM对象实例id',
  `crm_api_name` varchar(50) DEFAULT NULL COMMENT 'CRM对象的APINAME，这个对象取决于type',
  `email` varchar(100) DEFAULT NULL COMMENT 'CRM对象的邮箱账号',
  `company` varchar(100) DEFAULT NULL COMMENT 'CRM对象的公司属性',
  `name` varchar(100) DEFAULT NULL COMMENT 'CRM对象的名称属性',
  `type` smallint(1) DEFAULT NULL COMMENT 'CRM对象类型，1联系人 2线索 3客户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最近一次更新时间',
  `email_id` int(11) DEFAULT NULL COMMENT '邮箱用户id',
  `frequency` int(11) DEFAULT NULL COMMENT '更新CRM信息的频度，暂时没用到',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UN_CRM_EMAIL` (`crm_object_id`,`email`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于记录邮箱用户和CRM对象的关联，以及保存了CRM对象的基本信息';

/*Table structure for table `crm_email_bind_10` */

CREATE TABLE `crm_email_bind_10` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL,
  `crm_api_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '1为联系人， 2为线索, 3客户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `UN_CRM_EMAIL` (`crm_object_id`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_bind_2` */

CREATE TABLE `crm_email_bind_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL,
  `crm_api_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '1为联系人， 2为线索, 3客户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `UN_CRM_EMAIL` (`crm_object_id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_bind_3` */

CREATE TABLE `crm_email_bind_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL,
  `crm_api_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '1为联系人， 2为线索, 3客户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `UN_CRM_EMAIL` (`crm_object_id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_bind_4` */

CREATE TABLE `crm_email_bind_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL,
  `crm_api_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '1为联系人， 2为线索, 3客户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `UN_CRM_EMAIL` (`crm_object_id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_bind_5` */

CREATE TABLE `crm_email_bind_5` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL,
  `crm_api_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '1为联系人， 2为线索, 3客户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `UN_CRM_EMAIL` (`crm_object_id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_bind_6` */

CREATE TABLE `crm_email_bind_6` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL,
  `crm_api_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '1为联系人， 2为线索, 3客户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `UN_CRM_EMAIL` (`crm_object_id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_bind_7` */

CREATE TABLE `crm_email_bind_7` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL,
  `crm_api_name` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '1为联系人， 2为线索, 3客户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `CRM_OBJECT_INDEX` (`crm_object_id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_bind_8` */

CREATE TABLE `crm_email_bind_8` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL,
  `crm_api_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '1为联系人， 2为线索, 3客户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `EMAIL_INDEX1` (`email`,`crm_object_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1750 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_bind_9` */

CREATE TABLE `crm_email_bind_9` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(100) DEFAULT NULL,
  `crm_api_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '1为联系人， 2为线索, 3客户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `UN_CRM_EMAIL` (`crm_object_id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_relation_1` */

CREATE TABLE `crm_email_relation_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL COMMENT 'CRM对象实例id',
  `crm_api_name` varchar(100) DEFAULT NULL COMMENT 'CRM对象APINAME',
  `email_id` int(11) DEFAULT NULL COMMENT '邮箱用户id',
  `ea` varchar(20) DEFAULT NULL COMMENT '纷享企业账号',
  `fs_user_id` int(10) DEFAULT NULL COMMENT '纷享用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `message_id` bigint(11) DEFAULT NULL COMMENT '邮件id',
  `folder_type` smallint(2) DEFAULT NULL COMMENT '邮件所有文件夹的类型',
  `account` varchar(1000) DEFAULT NULL COMMENT '邮箱账号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_OBJECT_ID` (`crm_object_id`,`message_id`,`email_id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录CRM对象的邮件信息';

/*Table structure for table `crm_email_relation_10` */

CREATE TABLE `crm_email_relation_10` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL,
  `crm_api_name` varchar(100) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `ea` varchar(20) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `message_id` bigint(11) DEFAULT NULL,
  `folder_type` smallint(2) DEFAULT NULL,
  `account` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_OBJECT_ID` (`crm_object_id`,`message_id`,`email_id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_relation_2` */

CREATE TABLE `crm_email_relation_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL,
  `crm_api_name` varchar(100) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `ea` varchar(20) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `message_id` bigint(11) DEFAULT NULL,
  `folder_type` smallint(2) DEFAULT NULL,
  `account` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_OBJECT_ID` (`crm_object_id`,`message_id`,`email_id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_relation_3` */

CREATE TABLE `crm_email_relation_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL,
  `crm_api_name` varchar(100) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `ea` varchar(20) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `message_id` bigint(11) DEFAULT NULL,
  `folder_type` smallint(2) DEFAULT NULL,
  `account` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_OBJECT_ID` (`crm_object_id`,`message_id`,`email_id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_relation_4` */

CREATE TABLE `crm_email_relation_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL,
  `crm_api_name` varchar(100) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `ea` varchar(20) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `message_id` bigint(11) DEFAULT NULL,
  `folder_type` smallint(2) DEFAULT NULL,
  `account` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_OBJECT_ID` (`crm_object_id`,`message_id`,`email_id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_relation_5` */

CREATE TABLE `crm_email_relation_5` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL,
  `crm_api_name` varchar(100) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `ea` varchar(20) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `message_id` bigint(11) DEFAULT NULL,
  `folder_type` smallint(2) DEFAULT NULL COMMENT '0 或者空  全部 1 收件箱 2 发件箱 3 已发送 4 草稿箱',
  `account` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`),
  KEY `INDEX_OBJECT_ID` (`crm_object_id`)
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_relation_6` */

CREATE TABLE `crm_email_relation_6` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL,
  `crm_api_name` varchar(100) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `ea` varchar(20) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `message_id` bigint(11) DEFAULT NULL,
  `folder_type` smallint(2) DEFAULT NULL,
  `account` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_OBJECT_ID` (`crm_object_id`,`message_id`,`email_id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_relation_7` */

CREATE TABLE `crm_email_relation_7` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL,
  `crm_api_name` varchar(100) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `ea` varchar(20) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `message_id` bigint(11) DEFAULT NULL,
  `folder_type` smallint(2) DEFAULT NULL,
  `account` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_OBJECT_ID` (`crm_object_id`,`message_id`,`email_id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_relation_8` */

CREATE TABLE `crm_email_relation_8` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL,
  `crm_api_name` varchar(100) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `ea` varchar(20) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `message_id` bigint(11) DEFAULT NULL,
  `folder_type` smallint(2) DEFAULT NULL,
  `account` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_OBJECT_ID` (`crm_object_id`,`message_id`,`email_id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2006 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_email_relation_9` */

CREATE TABLE `crm_email_relation_9` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crm_object_id` varchar(50) DEFAULT NULL,
  `crm_api_name` varchar(100) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `ea` varchar(20) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `message_id` bigint(11) DEFAULT NULL,
  `folder_type` smallint(2) DEFAULT NULL,
  `account` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_OBJECT_ID` (`crm_object_id`,`message_id`,`email_id`),
  KEY `INDEX_EA_FS_EMAIL` (`ea`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Table structure for table `crm_msg_read_record` */

CREATE TABLE `crm_msg_read_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `record_id` int(11) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `msg_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `email_id_msg_id` (`email_id`,`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目前这个表没用到';

/*Table structure for table `crm_msg_sediment` */

CREATE TABLE `crm_msg_sediment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fs_corp_id` varchar(30) DEFAULT NULL COMMENT '企业账号',
  `fs_user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `msg_id` int(11) DEFAULT NULL COMMENT '邮件id',
  `email_id` int(11) DEFAULT NULL COMMENT '邮箱用户id',
  `folder_id` int(11) DEFAULT NULL COMMENT '文件夹id',
  `folder_type` smallint(2) DEFAULT NULL COMMENT '文件夹类型',
  `subject` text COMMENT '邮件主题',
  `send_date` datetime DEFAULT NULL COMMENT '邮件发送日期',
  `from` varchar(765) DEFAULT NULL COMMENT '邮件发送人',
  `to_list` text COMMENT '接收人列表',
  `cc_list` text COMMENT '抄送人列表',
  `bcc_list` text COMMENT '密送人列表',
  `message_content_id` int(11) DEFAULT NULL COMMENT '原始邮件正文id',
  `summary` varchar(1536) DEFAULT NULL COMMENT '邮件摘要',
  `attachemenet_count` int(255) DEFAULT NULL COMMENT '邮件附件数量',
  `flagged` varchar(765) DEFAULT NULL COMMENT '是否星标',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  `read` tinyint(4) DEFAULT NULL COMMENT '自己有没有读这个邮件',
  `answered` tinyint(4) DEFAULT NULL COMMENT '是否已回复',
  `forwarded` tinyint(255) DEFAULT NULL COMMENT '是否转发',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `size` int(11) DEFAULT NULL COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT NULL COMMENT '排序id',
  `sequence_number` int(11) DEFAULT NULL COMMENT '冗余字段',
  `old_uid` varchar(765) DEFAULT NULL COMMENT '冗余字段',
  `msg_sediment_content_id` int(11) DEFAULT NULL COMMENT '沉淀邮件的内容ID',
  `crm_object_id` varchar(64) DEFAULT NULL COMMENT 'CRM对象实例id',
  `crm_api_name` varchar(64) DEFAULT NULL COMMENT 'CRM对象apiName',
  `readed` tinyint(4) DEFAULT '0' COMMENT '自己发的邮件有没有人读',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ea_userid_msgid` (`fs_corp_id`,`fs_user_id`,`msg_id`),
  KEY `email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='CRM邮件沉淀表，冗余一份邮件信息';

/*Table structure for table `crm_msg_sediment_content` */

CREATE TABLE `crm_msg_sediment_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` int(11) DEFAULT NULL COMMENT '邮箱用户id',
  `html` varchar(256) DEFAULT NULL COMMENT '邮件正文在文件系统里面的id',
  `resources` text COMMENT '邮件正文资源的名称和文件系统id列表',
  `attachments` text COMMENT '邮件附件的名称/索引值/文件系统id列表',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8 COMMENT='CRM邮件正文，冗余一份正文信息';

/*Table structure for table `domain_config` */

CREATE TABLE `domain_config` (
  `domain` varchar(30) NOT NULL,
  `limit` int(10) DEFAULT NULL COMMENT '每分钟并发数(队列大小)',
  `usercount` int(10) DEFAULT NULL COMMENT '每次拉取的用户数',
  `delay_second` int(4) DEFAULT NULL COMMENT '延迟秒数',
  `printqueue` int(1) DEFAULT NULL COMMENT '1 打印  0 不打印',
  PRIMARY KEY (`domain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮箱服务商配置，用于保存对服务商同步邮件的一些配置信息';

/*Table structure for table `email` */

CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fs_user_id` varchar(255) NOT NULL COMMENT '用户id',
  `fs_corp_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '公司id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '邮件昵称',
  `signature` varchar(355) NOT NULL DEFAULT '' COMMENT '个性签名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `remark` varchar(1024) NOT NULL DEFAULT '' COMMENT '备注',
  `email_type` tinyint(255) NOT NULL COMMENT '类型，1 pop3  2 imap',
  `receive_ssl` tinyint(4) NOT NULL DEFAULT '0' COMMENT '接收是否开启ssl',
  `send_ssl` tinyint(4) NOT NULL DEFAULT '0' COMMENT '发送是否开启ssl，0否，1是',
  `email_config_id` int(11) NOT NULL COMMENT '配置id',
  `status` tinyint(4) NOT NULL COMMENT '邮箱状态，0 不启用  1 启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `bind_first` tinyint(4) DEFAULT '1' COMMENT '绑定状态，1为首次',
  `phone` varchar(20) DEFAULT NULL COMMENT '从通讯录拉取的手机的号码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_fs_corp_id_fs_user_id` (`fs_corp_id`,`fs_user_id`) USING BTREE,
  KEY `idx_status_update_time` (`status`,`update_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23140 DEFAULT CHARSET=utf8 COMMENT='用户绑定的邮箱信息';

/*Table structure for table `email_activity` */

CREATE TABLE `email_activity` (
  `email_id` int(11) NOT NULL COMMENT 'email id',
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '总数',
  `every_day_data` varchar(512) NOT NULL DEFAULT '' COMMENT '每天的值，以`分割，最多保存30天',
  `now_data` int(11) NOT NULL DEFAULT '0' COMMENT '当天的值',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '活跃度等级，默认等级1',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  UNIQUE KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活跃度统计表';

/*Table structure for table `email_config` */

CREATE TABLE `email_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(255) NOT NULL COMMENT '邮箱后缀host',
  `pop3_host` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop协议host',
  `pop3_port` int(11) NOT NULL DEFAULT '0' COMMENT 'pop协议port',
  `pop3_ssl_host` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop协议ssl host',
  `pop3_ssl_port` int(255) NOT NULL DEFAULT '0' COMMENT 'pop协议ssl port',
  `imap_host` varchar(255) NOT NULL DEFAULT '' COMMENT 'imap协议host',
  `imap_port` int(255) NOT NULL DEFAULT '0' COMMENT 'imap协议port',
  `imap_ssl_host` varchar(255) NOT NULL DEFAULT '' COMMENT 'imap协议ssl host',
  `imap_ssl_port` int(255) NOT NULL DEFAULT '0' COMMENT 'imap协议ssl port',
  `smtp_host` varchar(255) DEFAULT '' COMMENT 'stmp 服务host',
  `smtp_port` int(11) DEFAULT '0' COMMENT 'stmp 服务端口',
  `smtp_ssl_port` int(11) DEFAULT '0' COMMENT 'stmp ssl 端口',
  `smtp_ssl_host` varchar(255) DEFAULT '' COMMENT 'stmp ssl host',
  `related_id` int(11) NOT NULL COMMENT '关联的config id',
  `frequency_limit` int(11) NOT NULL DEFAULT '-1' COMMENT '每分钟频率次数限制',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `type` tinyint(4) DEFAULT '1' COMMENT '邮箱的配置类型',
  `create_user` varchar(50) DEFAULT '0' COMMENT '该配置记录的创建用户',
  PRIMARY KEY (`id`),
  KEY `idx_host_type` (`host`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3841 DEFAULT CHARSET=utf8 COMMENT='邮箱配置库';

/*Table structure for table `email_contracts_1` */

CREATE TABLE `email_contracts_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=463 DEFAULT CHARSET=utf8 COMMENT='邮箱用户的联系人记录';

/*Table structure for table `email_contracts_10` */

CREATE TABLE `email_contracts_10` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8;

/*Table structure for table `email_contracts_2` */

CREATE TABLE `email_contracts_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4576 DEFAULT CHARSET=utf8;

/*Table structure for table `email_contracts_3` */

CREATE TABLE `email_contracts_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8667 DEFAULT CHARSET=utf8;

/*Table structure for table `email_contracts_4` */

CREATE TABLE `email_contracts_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

/*Table structure for table `email_contracts_5` */

CREATE TABLE `email_contracts_5` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3138 DEFAULT CHARSET=utf8;

/*Table structure for table `email_contracts_6` */

CREATE TABLE `email_contracts_6` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2958 DEFAULT CHARSET=utf8;

/*Table structure for table `email_contracts_7` */

CREATE TABLE `email_contracts_7` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8;

/*Table structure for table `email_contracts_8` */

CREATE TABLE `email_contracts_8` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=881 DEFAULT CHARSET=utf8;

/*Table structure for table `email_contracts_9` */

CREATE TABLE `email_contracts_9` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `frequency` int(11) NOT NULL DEFAULT '1' COMMENT '出现频次',
  `type` tinyint(4) NOT NULL COMMENT '往来联系人类型',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`),
  KEY `ids_index_emailid_type` (`email_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8;

/*Table structure for table `email_notice` */

CREATE TABLE `email_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ios_url` varchar(256) NOT NULL DEFAULT '' COMMENT 'ios端弹出的h5页面',
  `android_url` varchar(256) NOT NULL DEFAULT '' COMMENT 'android端弹出的h5页面',
  `web_url` varchar(256) NOT NULL DEFAULT '' COMMENT 'web端弹出的h5页面',
  `pc_url` varchar(256) NOT NULL DEFAULT '' COMMENT 'pc端弹出的h5页面',
  `client_type` varchar(512) NOT NULL DEFAULT '' COMMENT '客户端类型，以#分割，android端通用android*',
  `end_time` datetime DEFAULT NULL COMMENT '失效时间',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='运营推广消息表';

/*Table structure for table `email_notice_content` */

CREATE TABLE `email_notice_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_id` int(11) NOT NULL DEFAULT '0' COMMENT '消息模板id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '消息内容描述,用于标记',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '消息标题',
  `content` text COMMENT '消息正文',
  `pic_url` varchar(255) NOT NULL DEFAULT '' COMMENT '图片地址',
  `user_range` tinyint(4) NOT NULL COMMENT '用户范围，1所有人，2已绑定，3未绑定',
  `notice_type` tinyint(4) NOT NULL COMMENT '消息类型，1为红点消息，2为弹窗消息',
  `client_type` varchar(256) NOT NULL DEFAULT '*' COMMENT '发送的客户端类型（包含版本号）',
  `need_qx` tinyint(4) NOT NULL COMMENT '是否发送企信消息，0不发送，1发送',
  `qx_message` varchar(255) NOT NULL DEFAULT '' COMMENT '企信内容',
  `user_data` varchar(255) NOT NULL DEFAULT '' COMMENT '用户数据，为excel文件url',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  `create_user_id` varchar(255) NOT NULL DEFAULT '' COMMENT '创建用户',
  `button_text` varchar(255) NOT NULL DEFAULT '' COMMENT '新增加的按钮文本',
  `button_url` varchar(255) NOT NULL DEFAULT '' COMMENT '按钮url链接地址',
  `app_height` varchar(25) NOT NULL DEFAULT 'multi_1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8 COMMENT='运营推广消息内容表';

/*Table structure for table `email_notice_task` */

CREATE TABLE `email_notice_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fs_user_id` varchar(255) NOT NULL COMMENT '用户id',
  `fs_corp_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '公司id',
  `notice_id` int(11) NOT NULL COMMENT '消息内容id',
  `retry_times` int(11) DEFAULT NULL COMMENT '重试次数',
  `status` tinyint(4) NOT NULL COMMENT '用户范围，1所有人，2已绑定，3未绑定',
  `remark` text COMMENT '情况说明',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=621 DEFAULT CHARSET=utf8 COMMENT='运营推广task执行表';

/*Table structure for table `email_notice_template` */

CREATE TABLE `email_notice_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '模板名称用于标记',
  `ios_url` varchar(256) NOT NULL DEFAULT '' COMMENT 'ios端弹出的h5页面',
  `android_url` varchar(256) NOT NULL DEFAULT '' COMMENT 'android端弹出的h5页面',
  `web_url` varchar(256) NOT NULL DEFAULT '' COMMENT 'web端弹出的h5页面',
  `pc_url` varchar(256) NOT NULL DEFAULT '' COMMENT 'pc端弹出的h5页面',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='运营推广消息模板表';

/*Table structure for table `email_notice_user` */

CREATE TABLE `email_notice_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fs_user_id` varchar(255) NOT NULL COMMENT '用户id',
  `fs_corp_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '公司id',
  `notice_id` int(11) NOT NULL COMMENT '消息id',
  `read` tinyint(4) NOT NULL DEFAULT '0' COMMENT '阅读状态，0 未读  1 已读',
  `client_type` varchar(512) NOT NULL DEFAULT '' COMMENT '消息读取时客户端类型',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  `notice_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '消息类型，1为红点消息，2为弹窗消息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_fs_corp_id_fs_user_id_notice_id_notice_type` (`fs_corp_id`,`fs_user_id`,`notice_id`,`notice_type`),
  KEY `idx_fs_corp_id_fs_user_id_read` (`fs_corp_id`,`fs_user_id`,`read`)
) ENGINE=InnoDB AUTO_INCREMENT=1155 DEFAULT CHARSET=utf8 COMMENT='运营推广用户表';

/*Table structure for table `email_notice_user_history` */

CREATE TABLE `email_notice_user_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `old_id` int(11) NOT NULL COMMENT '历史id',
  `fs_user_id` varchar(255) NOT NULL COMMENT '用户id',
  `fs_corp_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '公司id',
  `notice_id` int(11) NOT NULL COMMENT '消息id',
  `read` tinyint(4) NOT NULL DEFAULT '0' COMMENT '阅读状态，0 未读  1 已读',
  `client_type` varchar(512) NOT NULL DEFAULT '' COMMENT '消息读取时客户端类型',
  `notice_type` tinyint(4) NOT NULL COMMENT '消息类型，1为红点消息，2为弹窗消息',
  `read_client_type` varchar(512) NOT NULL DEFAULT '' COMMENT '消息读取时客户端类型',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_fs_corp_id_fs_user_id_read` (`fs_corp_id`,`fs_user_id`,`read`)
) ENGINE=InnoDB AUTO_INCREMENT=455 DEFAULT CHARSET=utf8 COMMENT='运营推广用户阅读历史表';

/*Table structure for table `email_setting` */

CREATE TABLE `email_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱用户id',
  `web_signature` text NOT NULL COMMENT 'web富文本签名',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `track_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否开启阅读状态',
  `interruption_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否开启消息免打扰',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `email_unbind_exam` */

CREATE TABLE `email_unbind_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` int(11) NOT NULL COMMENT '邮箱用户id',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '用户的纷享昵称',
  `phone` varchar(20) DEFAULT NULL COMMENT '用户的手机号码',
  `reason` varchar(1024) DEFAULT NULL COMMENT '预制的解绑原因',
  `other` varchar(1024) DEFAULT NULL COMMENT '其他解绑原因',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='用户解绑调查记录';

/*Table structure for table `enterprise_email_config` */

CREATE TABLE `enterprise_email_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ea` varchar(20) DEFAULT NULL COMMENT '企业账号',
  `email_switch` int(2) DEFAULT NULL COMMENT '企业查看下级邮件开关',
  `free` int(2) DEFAULT NULL COMMENT '是否付费',
  `opr_user_id` int(10) DEFAULT NULL COMMENT '操作人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `subquery_switch` tinyint(2) DEFAULT '0' COMMENT '下级类型',
  PRIMARY KEY (`id`),
  KEY `EA_INDEX` (`ea`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='企业上级查看下级邮件相关配置';

/*Table structure for table `folder` */

CREATE TABLE `folder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱用户id',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父文件夹的id',
  `full_name` varchar(255) NOT NULL COMMENT '完整名称',
  `name` varchar(255) NOT NULL COMMENT '文件名称',
  `sync_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '同步标识，0 不同步 1 同步',
  `type` int(4) NOT NULL DEFAULT '0' COMMENT '1收件箱，2发件箱，3已发送，4草稿箱，5已删除，6垃圾文件夹，7其他',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_email_id_full_name` (`email_id`,`full_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8059 DEFAULT CHARSET=utf8 COMMENT='邮箱文件夹记录';

/*Table structure for table `message_1` */

CREATE TABLE `message_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext NOT NULL COMMENT '接收人列表',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(512) DEFAULT NULL COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0' COMMENT '同步邮件排序序号',
  `sequence_number` int(11) DEFAULT NULL COMMENT '邮件序号',
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0' COMMENT '是否是CRM邮件',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_deleted` (`deleted`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=8612 DEFAULT CHARSET=utf8 COMMENT='邮件表';

/*Table structure for table `message_10` */

CREATE TABLE `message_10` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext NOT NULL COMMENT '接收人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0',
  `sequence_number` int(11) DEFAULT NULL,
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=7708 DEFAULT CHARSET=utf8;

/*Table structure for table `message_2` */

CREATE TABLE `message_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext NOT NULL COMMENT '接收人列表',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0',
  `sequence_number` int(11) DEFAULT NULL,
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=43045 DEFAULT CHARSET=utf8;

/*Table structure for table `message_3` */

CREATE TABLE `message_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext NOT NULL COMMENT '接收人列表',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0',
  `sequence_number` int(11) DEFAULT NULL,
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=119847 DEFAULT CHARSET=utf8;

/*Table structure for table `message_4` */

CREATE TABLE `message_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext NOT NULL COMMENT '接收人列表',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0',
  `sequence_number` int(11) DEFAULT NULL,
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=5077 DEFAULT CHARSET=utf8;

/*Table structure for table `message_5` */

CREATE TABLE `message_5` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext NOT NULL COMMENT '接收人列表',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0',
  `sequence_number` int(11) DEFAULT NULL,
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=38583 DEFAULT CHARSET=utf8;

/*Table structure for table `message_6` */

CREATE TABLE `message_6` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext NOT NULL COMMENT '接收人列表',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0',
  `sequence_number` int(11) DEFAULT NULL,
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=35560 DEFAULT CHARSET=utf8;

/*Table structure for table `message_7` */

CREATE TABLE `message_7` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext NOT NULL COMMENT '接收人列表',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0',
  `sequence_number` int(11) DEFAULT NULL,
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_deleted` (`deleted`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=19422 DEFAULT CHARSET=utf8;

/*Table structure for table `message_8` */

CREATE TABLE `message_8` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext NOT NULL COMMENT '接收人列表',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0',
  `sequence_number` int(11) DEFAULT NULL,
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=16462 DEFAULT CHARSET=utf8;

/*Table structure for table `message_9` */

CREATE TABLE `message_9` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imap_uid` int(11) NOT NULL DEFAULT '0' COMMENT 'imap uid',
  `pop3_uid` varchar(255) NOT NULL DEFAULT '' COMMENT 'pop3 uid',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `folder_id` int(11) NOT NULL COMMENT '文件夹id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` mediumtext,
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态',
  `deleted` tinyint(4) NOT NULL COMMENT '删除状态，0未删除 1删除',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `order_id` bigint(20) DEFAULT '0',
  `sequence_number` int(11) DEFAULT NULL,
  `old_uid` varchar(255) DEFAULT '',
  `crm` tinyint(4) DEFAULT '0',
  `flag` int(10) unsigned DEFAULT '0' COMMENT '1 需要提示有人@ ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_folder_id_imap_uid_pop3_uid` (`folder_id`,`imap_uid`,`pop3_uid`),
  KEY `idx_flagged` (`flagged`),
  KEY `idx_email_folder_order_id` (`email_id`,`folder_id`,`order_id`),
  KEY `idx_foler_read_delete` (`folder_id`,`read`,`deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=19539 DEFAULT CHARSET=utf8;

/*Table structure for table `message_content_1` */

CREATE TABLE `message_content_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱用户id',
  `html` mediumtext NOT NULL COMMENT '内容文件系统id',
  `resources` mediumtext COMMENT '内容中资源的列表',
  `attachments` text NOT NULL COMMENT '附件列表',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `flag` tinyint(1) DEFAULT '0' COMMENT '0 存储在数据库 1 存储在文件系统',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22511 DEFAULT CHARSET=utf8 COMMENT='邮件内容表';

/*Table structure for table `message_content_10` */

CREATE TABLE `message_content_10` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` mediumtext,
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22522 DEFAULT CHARSET=utf8;

/*Table structure for table `message_content_2` */

CREATE TABLE `message_content_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` mediumtext,
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42193 DEFAULT CHARSET=utf8;

/*Table structure for table `message_content_3` */

CREATE TABLE `message_content_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` mediumtext,
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=125836 DEFAULT CHARSET=utf8;

/*Table structure for table `message_content_4` */

CREATE TABLE `message_content_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` mediumtext,
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40552 DEFAULT CHARSET=utf8;

/*Table structure for table `message_content_5` */

CREATE TABLE `message_content_5` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` mediumtext,
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54578 DEFAULT CHARSET=utf8;

/*Table structure for table `message_content_6` */

CREATE TABLE `message_content_6` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` mediumtext,
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=120723 DEFAULT CHARSET=utf8;

/*Table structure for table `message_content_7` */

CREATE TABLE `message_content_7` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` mediumtext,
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19443 DEFAULT CHARSET=utf8;

/*Table structure for table `message_content_8` */

CREATE TABLE `message_content_8` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` mediumtext,
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18035 DEFAULT CHARSET=utf8;

/*Table structure for table `message_content_9` */

CREATE TABLE `message_content_9` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'emai id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` mediumtext,
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19610 DEFAULT CHARSET=utf8;

/*Table structure for table `message_notify` */

CREATE TABLE `message_notify` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `message_id` int(11) NOT NULL COMMENT '消息id',
  `email_id` int(11) NOT NULL COMMENT '邮箱用户id',
  `status` tinyint(4) NOT NULL COMMENT '消息状态，0未通知 1通知成功 2通知失败',
  `retry_times` int(11) NOT NULL DEFAULT '0' COMMENT '重试次数',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_status_retry_times` (`status`,`retry_times`),
  KEY `idx_status_update_time` (`status`,`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=11953 DEFAULT CHARSET=utf8 COMMENT='邮件通知记录表';

/*Table structure for table `message_read_record_1` */

CREATE TABLE `message_read_record_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件用户id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT '冗余的排序id',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2' COMMENT '2:被阅读',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `message_read_record_10` */

CREATE TABLE `message_read_record_10` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT 'messageID',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `message_read_record_2` */

CREATE TABLE `message_read_record_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT 'messageID',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `message_read_record_3` */

CREATE TABLE `message_read_record_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT 'messageID',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=579 DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `message_read_record_4` */

CREATE TABLE `message_read_record_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT 'messageID',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `message_read_record_5` */

CREATE TABLE `message_read_record_5` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT 'messageID',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `message_read_record_6` */

CREATE TABLE `message_read_record_6` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT 'messageID',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `message_read_record_7` */

CREATE TABLE `message_read_record_7` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT 'messageID',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `message_read_record_8` */

CREATE TABLE `message_read_record_8` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT 'messageID',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=401 DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `message_read_record_9` */

CREATE TABLE `message_read_record_9` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮件id',
  `message_id` int(11) NOT NULL COMMENT '邮件id',
  `order_id` bigint(20) NOT NULL COMMENT 'messageID',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `country` varchar(20) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `type` int(1) DEFAULT '2',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `idx_email_id_message_id` (`email_id`,`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='邮件已读记录表';

/*Table structure for table `monition_detail` */

CREATE TABLE `monition_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `domain` varchar(50) DEFAULT NULL COMMENT '邮箱域名',
  `suc_user` int(6) DEFAULT NULL COMMENT ' 成功拉取邮件的人数',
  `total_user` int(6) DEFAULT NULL COMMENT '拉取邮件的总人数',
  `add_mail` int(4) DEFAULT NULL COMMENT '这次拉取结果，有多少新邮件',
  `update_mail` int(4) DEFAULT NULL COMMENT '这次拉取结果，有多少邮件更新了',
  `del_mail` int(4) DEFAULT NULL COMMENT '这次拉取结果，有多少邮件在服务商测删除了',
  `avg_time` int(4) DEFAULT NULL COMMENT '平均耗时(s)',
  `error_msg` varchar(1024) DEFAULT NULL COMMENT '错误信息',
  `count_time` varchar(50) DEFAULT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COMMENT='监控详细信息';

/*Table structure for table `monition_total` */

CREATE TABLE `monition_total` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empty_pull_times` int(4) DEFAULT NULL,
  `email_pull_times` int(4) DEFAULT NULL,
  `total_user` int(6) DEFAULT NULL,
  `total_add` int(4) DEFAULT NULL,
  `total_update` int(4) DEFAULT NULL,
  `total_del` int(4) DEFAULT NULL,
  `error_msg` varchar(1024) DEFAULT NULL,
  `timeout_times` int(4) DEFAULT NULL COMMENT '超时次数',
  `suc_pull_times` int(4) DEFAULT NULL,
  `fail_pull_times` int(4) DEFAULT NULL,
  `count_time` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

/*Table structure for table `operation` */

CREATE TABLE `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL DEFAULT '0' COMMENT '邮箱用户id',
  `type` tinyint(255) NOT NULL COMMENT '指令类型',
  `data` varchar(255) NOT NULL DEFAULT '' COMMENT '指令的内容数据',
  `status` tinyint(4) NOT NULL COMMENT '状态，0未执行 1执行成功 2执行失败 ',
  `related_id` int(11) NOT NULL DEFAULT '0' COMMENT '邮件的id',
  `retry_times` int(11) NOT NULL DEFAULT '0' COMMENT '重试次数',
  `remark` text COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_status_retry_times` (`status`,`retry_times`),
  KEY `idx_status_update_time` (`status`,`update_time`),
  KEY `idx_email_id_related_id` (`email_id`,`related_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20818 DEFAULT CHARSET=utf8 COMMENT='离线操作指令表';

/*Table structure for table `operation1_bak` */

CREATE TABLE `operation1_bak` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `email_id` int(11) NOT NULL DEFAULT '0' COMMENT 'email id',
  `type` tinyint(255) NOT NULL COMMENT '类型',
  `data` varchar(255) NOT NULL DEFAULT '' COMMENT '数据',
  `status` varchar(255) NOT NULL COMMENT '状态，0未执行 1执行成功 2执行失败 ',
  `related_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联id',
  `retry_times` int(11) NOT NULL DEFAULT '0' COMMENT '重试次数',
  `remark` text COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `share_message` */

CREATE TABLE `share_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT '邮箱用户id',
  `source_account` varchar(255) NOT NULL COMMENT '源账号',
  `source_corp_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '来源公司id',
  `source_user_id` varchar(255) NOT NULL COMMENT '来源用户id',
  `subject` text COMMENT '主题',
  `send_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '消息接收时间',
  `from` varchar(255) NOT NULL COMMENT '发送人',
  `to_list` text NOT NULL COMMENT '接收人列表',
  `cc_list` text NOT NULL COMMENT '抄送人列表',
  `bcc_list` text NOT NULL COMMENT '密送人列表',
  `message_content_id` int(11) NOT NULL COMMENT '消息内容id',
  `summary` varchar(255) NOT NULL DEFAULT '' COMMENT '摘要',
  `attachemenet_count` int(255) NOT NULL COMMENT '附件',
  `flagged` varchar(255) NOT NULL COMMENT '消息状态,是否标星',
  `read` tinyint(4) NOT NULL COMMENT '已读状态 0 未读 1已读',
  `answered` tinyint(4) NOT NULL COMMENT '回复状态，0非回复 1回复',
  `forwarded` tinyint(255) NOT NULL COMMENT '转发状态,0非转发 1转发',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '邮件大小',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '分享类型 1、工作，2、企信，3、销售记录'';',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1279 DEFAULT CHARSET=utf8 COMMENT='邮件分享记录';

/*Table structure for table `share_message_content` */

CREATE TABLE `share_message_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` int(11) NOT NULL COMMENT 'email id',
  `html` mediumtext NOT NULL COMMENT '内容',
  `resources` text COMMENT '资源',
  `attachments` text NOT NULL COMMENT '附件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `flag` tinyint(1) DEFAULT NULL COMMENT '0 DB 1 文件系统',
  PRIMARY KEY (`id`),
  KEY `idx_email_id` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1279 DEFAULT CHARSET=utf8 COMMENT='邮件分享内容表';

/*Table structure for table `statistic_data_day` */

CREATE TABLE `statistic_data_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data` varchar(256) NOT NULL COMMENT '统计项数据',
  `type` int(11) NOT NULL COMMENT '统计项类型标示',
  `date` date NOT NULL COMMENT '日期',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_type_time` (`type`,`date`)
) ENGINE=InnoDB AUTO_INCREMENT=1105 DEFAULT CHARSET=utf8 COMMENT='统计表-日记录';

/*Table structure for table `statistic_data_week` */

CREATE TABLE `statistic_data_week` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data` varchar(256) NOT NULL COMMENT '统计项数据',
  `type` int(11) NOT NULL COMMENT '统计项类型标示',
  `start_time` date NOT NULL COMMENT '开始日期',
  `end_time` date NOT NULL COMMENT '结束日期',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_type_start_time_end_time` (`type`,`start_time`,`end_time`)
) ENGINE=InnoDB AUTO_INCREMENT=471 DEFAULT CHARSET=utf8 COMMENT='统计表-周记录';

/*Table structure for table `statistic_hadoop_data_day` */

CREATE TABLE `statistic_hadoop_data_day` (
  `stat_date` date DEFAULT NULL,
  `n_new_bind_user_account` int(11) DEFAULT '0' COMMENT '新绑定用户数',
  `n_new_unbind_user_account` int(11) DEFAULT '0' COMMENT '新解绑用户数',
  `n_active_user_account` int(11) DEFAULT '0' COMMENT '活跃用户数',
  `n_active_ea_account` int(11) DEFAULT '0' COMMENT '活跃企业数',
  `n_receive_mail_account` int(11) DEFAULT '0' COMMENT '收取邮件数',
  `n_send_mail_account` int(11) DEFAULT '0' COMMENT '发邮件数',
  `n_enter_account` int(11) DEFAULT '0' COMMENT '进入邮箱数',
  `n_read_account` int(11) DEFAULT '0' COMMENT '读信数',
  `n_write_account` int(11) DEFAULT '0' COMMENT '写信数',
  `n_recent_bind_user_account` int(11) DEFAULT '0' COMMENT '当前绑定用户数',
  `n_recent_bind_ea_account` int(11) DEFAULT '0' COMMENT '当前绑定企业数',
  `n_bind_user_account_total` int(11) DEFAULT '0' COMMENT '累计绑定用户数',
  `n_bind_ea_account_total` int(11) DEFAULT '0' COMMENT '累计绑定企业数',
  `n_receive_mail_account_total` int(11) DEFAULT '0' COMMENT '累计收取邮件数',
  `n_send_mail_account_total` int(11) DEFAULT '0' COMMENT '累计发邮件数',
  `n_domain_account` int(11) DEFAULT '0' COMMENT '当前代收域名数',
  `n_mail_provider_account` int(11) DEFAULT '0' COMMENT '邮件服务商数',
  `n_bind_total_user_count` int(11) DEFAULT '0' COMMENT '总共绑定操作用户数',
  `n_read_nofs_message_count` int(11) DEFAULT '0' COMMENT '非纷享读取邮件总次数',
  `n_read_nofs_user_count` int(11) DEFAULT '0' COMMENT '非纷享读取邮件用户数',
  `n_read_fs_user_count` int(11) DEFAULT '0' COMMENT '纷享读取邮件用户数',
  `n_read_fs_message_count` int(11) DEFAULT '0' COMMENT '纷享读取邮件总次数,排除重复邮件',
  `n_active_pc_count` int(11) DEFAULT '0' COMMENT 'pc端活跃总次数',
  `n_active_web_count` int(11) DEFAULT '0' COMMENT 'web端活跃总次数',
  `n_active_android_count` int(11) DEFAULT '0' COMMENT 'android端活跃总次数',
  `n_active_ios_count` int(11) DEFAULT '0' COMMENT 'ios端活跃总次数',
  `fs_exclude` int(11) DEFAULT '0',
  KEY `idx_ stat_date` (`stat_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='hadoop数据统计记录表-日记录';

/*Table structure for table `statistic_type` */

CREATE TABLE `statistic_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '统计项名称',
  `seen` tinyint(4) NOT NULL COMMENT '当前统计项目前端是否可见',
  `valid` tinyint(4) NOT NULL COMMENT '当前统计项是否有效',
  `cycle` tinyint(4) NOT NULL COMMENT '统计类型周期，1、天，2、周',
  `from` tinyint(4) NOT NULL COMMENT '数据统计来源，0、数据库。1、其他',
  `sql` varchar(1024) NOT NULL DEFAULT '' COMMENT '数据库来源的统计sql',
  `type` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  PRIMARY KEY (`id`),
  KEY `udx_cycle_valid_from` (`cycle`,`valid`,`from`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='统计类型表';

/*Table structure for table `testp` */

CREATE TABLE `testp` (
  `id` int(11) NOT NULL,
  `read` tinyint(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/*!50100 PARTITION BY LIST (`read`)
(PARTITION p0 VALUES IN (0) ENGINE = InnoDB,
 PARTITION p1 VALUES IN (1) ENGINE = InnoDB) */;

/*Table structure for table `user_active_record` */

CREATE TABLE `user_active_record` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `fs_corp_id` varchar(20) DEFAULT NULL COMMENT '企业号',
  `fs_user_id` int(11) DEFAULT NULL COMMENT '用户纷享ID',
  `create_time` datetime DEFAULT NULL COMMENT '生成时间',
  `web_read` int(11) DEFAULT NULL COMMENT '读邮件次数',
  `web_send` int(11) DEFAULT NULL COMMENT '写邮件次数',
  `web_bound` int(11) DEFAULT NULL COMMENT '绑定',
  `web_unbound` int(11) DEFAULT NULL COMMENT '解绑',
  `web_update` int(11) DEFAULT NULL COMMENT '更新',
  `web_delete` int(11) DEFAULT NULL COMMENT '删除',
  `web_messageupdate` int(11) DEFAULT NULL COMMENT '邮件更新',
  `web_draft` int(11) DEFAULT NULL COMMENT '垃圾箱',
  `pc_read` int(11) DEFAULT NULL COMMENT '读邮件次数',
  `pc_send` int(11) DEFAULT NULL COMMENT '写邮件次数',
  `pc_bound` int(11) DEFAULT NULL COMMENT '绑定',
  `pc_unbound` int(11) DEFAULT NULL COMMENT '解绑',
  `pc_update` int(11) DEFAULT NULL COMMENT '更新',
  `pc_delete` int(11) DEFAULT NULL COMMENT '删除',
  `pc_messageupdate` int(11) DEFAULT NULL COMMENT '邮件更新',
  `pc_draft` int(11) DEFAULT NULL COMMENT '垃圾箱',
  `android_read` int(11) DEFAULT NULL COMMENT '读邮件次数',
  `android_send` int(11) DEFAULT NULL COMMENT '写邮件次数',
  `android_bound` int(11) DEFAULT NULL COMMENT '绑定',
  `android_unbound` int(11) DEFAULT NULL COMMENT '解绑',
  `android_update` int(11) DEFAULT NULL COMMENT '更新',
  `android_delete` int(11) DEFAULT NULL COMMENT '删除',
  `android_messageupdate` int(11) DEFAULT NULL COMMENT '邮件更新',
  `android_draft` int(11) DEFAULT NULL COMMENT '垃圾箱',
  `android_version` varchar(10) DEFAULT NULL,
  `ios_read` int(11) DEFAULT NULL COMMENT '读邮件次数',
  `ios_send` int(11) DEFAULT NULL COMMENT '写邮件次数',
  `ios_bound` int(11) DEFAULT NULL COMMENT '绑定',
  `ios_unbound` int(11) DEFAULT NULL COMMENT '解绑',
  `ios_update` int(11) DEFAULT NULL COMMENT '更新',
  `ios_delete` int(11) DEFAULT NULL COMMENT '删除',
  `ios_messageupdate` int(11) DEFAULT NULL COMMENT '邮件更新',
  `ios_draft` int(11) DEFAULT NULL COMMENT '垃圾箱',
  `ios_version` varchar(10) DEFAULT NULL,
  `sync` int(11) DEFAULT NULL COMMENT '同步',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户活跃记录表';

/*Table structure for table `user_activity_detail` */

CREATE TABLE `user_activity_detail` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `email_id` bigint(11) DEFAULT NULL COMMENT '邮箱用户id',
  `service_name` varchar(200) DEFAULT NULL COMMENT '服务接口名称',
  `total` int(4) DEFAULT NULL COMMENT '接口调用总数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1120 DEFAULT CHARSET=utf8 COMMENT='用户活跃详细信息';

/*Table structure for table `user_personals` */

CREATE TABLE `user_personals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` int(11) DEFAULT NULL,
  `fs_corp_id` varchar(30) DEFAULT NULL,
  `fs_user_id` int(10) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `fs_status` int(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `domain` varchar(30) DEFAULT NULL,
  `contact_in_company` int(10) DEFAULT NULL,
  `contact_out_company` int(10) DEFAULT NULL,
  `contactCrm` int(10) DEFAULT NULL,
  `userd_terminal` varchar(50) DEFAULT NULL,
  `bind_time` date DEFAULT NULL,
  `un_bund_time` date DEFAULT NULL,
  `total_mail` int(10) DEFAULT NULL,
  `total_send_mail` int(10) DEFAULT NULL,
  `total_read_in_fxiaoke` int(10) DEFAULT NULL,
  `recent_activity_times` int(10) DEFAULT NULL,
  `recent_get_mail` int(10) DEFAULT NULL,
  `recent_read_mail` int(10) DEFAULT NULL,
  `recent_read_in_fxiaoke` int(10) DEFAULT NULL,
  `recent_send_mail` int(10) DEFAULT NULL,
  `memo` varchar(248) DEFAULT NULL,
  `remark` varchar(248) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='这个表暂时没用到';

/*Table structure for table `user_sync_record` */

CREATE TABLE `user_sync_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` int(11) DEFAULT NULL COMMENT '邮箱用户id',
  `domain` varchar(30) DEFAULT NULL COMMENT '邮箱域名',
  `account` varchar(100) DEFAULT NULL COMMENT '邮箱账号',
  `fs_corp_id` varchar(20) DEFAULT NULL COMMENT '企业账号',
  `fs_user_id` int(11) DEFAULT NULL COMMENT '纷享用户id',
  `activity` int(11) DEFAULT '0' COMMENT '日活跃度',
  `weight` int(11) DEFAULT '0' COMMENT '权重',
  `times` int(11) DEFAULT '0' COMMENT '拉取的次数',
  `suc_times` int(11) DEFAULT '0' COMMENT '成功次数',
  `err_times` int(11) DEFAULT '0' COMMENT '失败次数',
  `queue_type` int(11) DEFAULT '0' COMMENT '类型 1 活跃 2 低频 3 可恢复 4 挂起',
  `last_time` bigint(15) DEFAULT '0' COMMENT '上一次拉取邮件时间',
  `create_time` bigint(15) DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT '0' COMMENT '更新时间',
  `err_last_time` bigint(20) DEFAULT '0' COMMENT '最近一次错误的时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNINQ_EMAIL_ID` (`email_id`),
  UNIQUE KEY `UNINQ_corp_user` (`fs_corp_id`,`fs_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='用户同步记录表';

/* Procedure structure for procedure `countFileSysSize` */

DELIMITER $$

/*!50003 CREATE DEFINER=`emailproxy_adm`@`%` PROCEDURE `countFileSysSize`(out result double)
BEGIN
declare done INT default 0;
declare queryResult text;
declare sizeIndex int;
DECLARE cur CURSOR FOR
 SELECT attachments FROM open_email_proxy.message_content_1 where locate('fileId',attachments) > 0
 union 
 SELECT attachments FROM open_email_proxy.message_content_2 where locate('fileId',attachments) > 0
 union 
 SELECT attachments FROM open_email_proxy.message_content_3 where locate('fileId',attachments) > 0
 union 
 SELECT attachments FROM open_email_proxy.message_content_4 where locate('fileId',attachments) > 0
 union 
 SELECT attachments FROM open_email_proxy.message_content_5 where locate('fileId',attachments) > 0
 union 
 SELECT attachments FROM open_email_proxy.message_content_6 where locate('fileId',attachments) > 0
 union 
 SELECT attachments FROM open_email_proxy.message_content_7 where locate('fileId',attachments) > 0
 union 
 SELECT attachments FROM open_email_proxy.message_content_8 where locate('fileId',attachments) > 0
 union 
 SELECT attachments FROM open_email_proxy.message_content_9 where locate('fileId',attachments) > 0
 union 
 SELECT attachments FROM open_email_proxy.message_content_10 where locate('fileId',attachments) > 0;
-- 在游标循环到最后会将 done 设置为 1
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
-- 执行查询
open cur;
-- 遍历游标每一行
REPEAT
  -- 把一行的信息存放在对应的变量中
  FETCH cur INTO queryResult;
	if not done then
       if(result is NULL) THEN 
                 set result = 0;
       END if;
       set sizeIndex = locate('size',queryResult);
        while sizeIndex>0 DO
    	      set result =  result + substring(queryResult,locate('size',queryResult)+6,locate(',"contentType',queryResult)-locate('size',queryResult)-6)/1024/1024;
              set queryResult = substring(queryResult,locate(',"contentType',queryResult)+10);
              set sizeIndex = locate('size',queryResult);
        end while;
	end if;
UNTIL done END REPEAT;
CLOSE cur;
set result = result/1024;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
