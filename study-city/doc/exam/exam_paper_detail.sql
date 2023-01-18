CREATE TABLE `study`.`exam_paper_detail`  (
                                     `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `question_id` int(0) NULL DEFAULT NULL COMMENT '题目id\n',
                                     `exam_paper_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考生考卷表ID\n',
                                     `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考生答案',
                                     `right` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '正确答案',
                                     `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '得分\n',
                                     `points` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本题的分值',
                                     `previous` int(0) NULL DEFAULT NULL COMMENT '上一道题的id\n',
                                     `next` int(0) NULL DEFAULT NULL COMMENT '下一道题的id\n',
                                     `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                     `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
                                     `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                                     `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;