CREATE TABLE `sys_user` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`login_name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名' ,
`name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名' ,
`sex`  tinyint(4) NULL DEFAULT 0 COMMENT '性别,0:男,1:女' ,
`birth`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生日' ,
`email`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱' ,
`phone`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话' ,
`address`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址' ,
`password`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码' ,
`organization_id`  bigint(20) NULL DEFAULT NULL COMMENT '所属组织架构' ,
`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
`create_by`  bigint(20) NULL DEFAULT 0 COMMENT '创建人' ,
`update_by`  bigint(20) NULL DEFAULT 0 COMMENT '更热人' ,
`status`  tinyint(4) NULL DEFAULT 1 COMMENT '数据状态,1:正常,2:删除,3:禁用账号' ,
`is_final`  tinyint(4) NULL DEFAULT NULL COMMENT '是否能修改' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=4
ROW_FORMAT=DYNAMIC
;

