CREATE TABLE `sys_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`description`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`rank`  bigint(20) NULL DEFAULT 0 COMMENT '排序' ,
`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
`create_by`  bigint(20) NULL DEFAULT 0 COMMENT '创建人id' ,
`update_by`  bigint(20) NULL DEFAULT 0 COMMENT '更新人id' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6
ROW_FORMAT=DYNAMIC
;
