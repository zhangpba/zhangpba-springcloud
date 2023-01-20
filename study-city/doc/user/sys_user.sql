CREATE TABLE `study`.`sys_user`  (
                                     `user_id` int(0) NOT NULL AUTO_INCREMENT,
                                     `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户账号',
                                     `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
                                     `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
                                     `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮件',
                                     `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话',
                                     `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地址',
                                     `birthday` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '生日',
                                     `sex` int(0) NULL DEFAULT NULL COMMENT '性别',
                                     `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `create_date` timestamp(0) NULL DEFAULT NULL,
                                     `update_date` timestamp(0) NULL DEFAULT NULL,
                                     `realname` varchar(255) NULL COMMENT '真实姓名',
                                     PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;