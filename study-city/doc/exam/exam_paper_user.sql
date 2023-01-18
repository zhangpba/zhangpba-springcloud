CREATE TABLE `study`.`exam_paper_user`  (
                                     `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `user_id` int(0) NULL DEFAULT NULL COMMENT '考生ID\n',
                                     `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考生姓名\n',
                                     `exam_paper_id` int(0) NULL DEFAULT NULL COMMENT '考卷ID\n',
                                     `score` decimal(10, 0) NULL DEFAULT NULL COMMENT '考试成绩\n',
                                     `batch` decimal(10, 0) NULL DEFAULT NULL COMMENT '考试次数',
                                     `status` int(0) NULL DEFAULT NULL COMMENT '考试状态 0-未参加，1-已通过，2-未通过\n',
                                     `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                     `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
                                     `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                                     `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;