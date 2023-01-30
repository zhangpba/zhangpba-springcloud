--字典类型表
CREATE TABLE `study`.`dict_type`
(
    `dict_id`     bigint(0) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name`   varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典类型',
    `status`      char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_id`) USING BTREE,
    UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('用户性别', 'user_sex', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '用户性别列表');
INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('考试类型', 'exam_type', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '考试类型列表');
INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('24节气', 'solar_terms', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '24节气');
INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('八卦挂相', 'gossip_show', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '八卦挂相');
INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('挂相分析', 'gossip_desc', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '挂相分析');
