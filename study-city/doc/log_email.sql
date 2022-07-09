CREATE TABLE `study`.`log_email`  (
                                     `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `title` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主题',
                                     `context` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '邮件内容',
                                     `send_users` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发送人',
                                     `receive_bcc` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '隐秘接收人',
                                     `receive` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接收人',
                                     `count` int(0) NULL DEFAULT NULL COMMENT '发送次数',
                                     `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
                                     `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                     `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;