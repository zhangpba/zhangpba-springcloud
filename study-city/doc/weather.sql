CREATE TABLE `study`.`weather`  (
                                     `wt_id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
                                     `high` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '最高温',
                                     `fx` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '风向',
                                     `low` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '最低温',
                                     `fl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '风力',
                                     `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '天气类型',
                                     `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建人',
                                     `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改人',
                                     `date` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '天气日期',
                                     `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '城市',
                                     `warn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '温馨提示',
                                     `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
                                     `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
                                     PRIMARY KEY (`wt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;