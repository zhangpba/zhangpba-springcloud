# 题目选项表
CREATE TABLE if not exists `study`.`exam_question_options`  (
                                     `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `key` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项 (A、B、C、D)',
                                     `option` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项内容',
                                     `question_id` int(0) NULL DEFAULT NULL COMMENT '题目主键',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;