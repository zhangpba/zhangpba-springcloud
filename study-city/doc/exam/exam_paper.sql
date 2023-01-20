CREATE TABLE if not exists `study`.`exam_paper`
(
    `id`                  int(0) NOT NULL AUTO_INCREMENT COMMENT '主键\n',
    `paper_name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '试卷名称',
    `exam_type`           varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考试类型',
    `score_line`          decimal(10, 0) NULL DEFAULT NULL COMMENT '及格线\n',
    `score`               decimal(10, 0) NULL DEFAULT NULL COMMENT '考试成绩\n',
    `total_num`           decimal(10, 0) NULL DEFAULT NULL COMMENT '考卷总共包含多少个题目',
    `count`               int(0) NULL DEFAULT NULL COMMENT '可考次数\n',
    `choice_single_num`   decimal(10, 0) NULL DEFAULT NULL COMMENT '单选题数量\n',
    `choice_single_score` decimal(10, 0) NULL DEFAULT NULL COMMENT '单选题分数',
    `choice_many_num`     decimal(10, 0) NULL DEFAULT NULL COMMENT '多选题数量\n',
    `choice_many_score`   decimal(10, 0) NULL DEFAULT NULL COMMENT '多选题分数\n',
    `judge_num`           decimal(10, 0) NULL DEFAULT NULL COMMENT '判断题数量\n',
    `judge_score`         decimal(10, 0) NULL DEFAULT NULL COMMENT '判断题分数',
    `start_time`          datetime(0) NULL DEFAULT NULL COMMENT '考试开始时间\n',
    `end_time`            datetime(0) NULL DEFAULT NULL COMMENT '考试结束时间',
    `create_time`         datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by`           varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
    `update_time`         datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `update_by`           varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;