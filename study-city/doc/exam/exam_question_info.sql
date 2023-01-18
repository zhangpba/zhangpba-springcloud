# 题目表
CREATE TABLE  if not exists `study`.`exam_question_info`  (
                                     `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `question` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目内容',
                                     `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '答案（options_info表中的key）',
                                     `explain` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '最佳解释',
                                     `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
                                     `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目类型（1：判断题，2：单选题，3：多选题）',
                                     `exam_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考试类型（0-小车科目一，1-小车科目四，2-客车科目一，3-客车科目四，4-货车科目一，5-货车科目四，6-摩托车科目一，7-摩托车科目四）',
                                     `source_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '原始题目id',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;