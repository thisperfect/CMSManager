/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-26 14:19:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(11) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT '' COMMENT '操作描述',
  `username` varchar(50) DEFAULT '' COMMENT '操作用户',
  `start_time` bigint(20) DEFAULT '0' COMMENT '操作时间',
  `spend_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  `base_path` varchar(255) DEFAULT NULL COMMENT '根路径',
  `uri` varchar(100) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `user_agent` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `ip` varchar(50) DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  `parameter` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `module` varchar(100) DEFAULT NULL COMMENT '模块名称',
  `type` varchar(10) CHARACTER SET utf32 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
