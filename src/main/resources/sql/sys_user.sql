/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-26 14:19:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) DEFAULT NULL COMMENT '登陆名',
  `name` varchar(50) DEFAULT NULL COMMENT '中文名',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别,0:男,1:女',
  `birth` varchar(50) DEFAULT NULL COMMENT '生日',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `address` varchar(1024) DEFAULT NULL COMMENT '地址',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '更热人',
  `status` tinyint(4) DEFAULT '1' COMMENT '数据状态,1:正常,2:删除,3:禁用账号',
  `is_final` tinyint(4) DEFAULT NULL COMMENT '是否能修改',
  `organization_id` bigint(20) DEFAULT NULL COMMENT '所属组织架构',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('44', 'admin', 'admin', '1', null, '136166087@qq.com', null, null, '062c39de8232e838859c0da0060813f1', '2017-07-28 17:25:19', '2017-07-28 17:25:43', '0', '0', '1', '1', null);
INSERT INTO `sys_user` VALUES ('45', 'ADMINISTRATOR', 'ADMINISTRATOR', '1', null, '1032299142@qq.com', null, null, '51201f6927348fde3b392d8dc948e651', '2017-08-16 11:33:05', '2017-08-16 11:34:14', '0', '0', '1', '1', null);
