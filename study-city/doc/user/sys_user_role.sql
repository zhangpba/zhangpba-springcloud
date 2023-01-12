CREATE TABLE `study`.`sys_user_role`  (
                                     `user_id` bigint(0) NOT NULL COMMENT '用户ID',
                                     `role_id` bigint(0) NOT NULL COMMENT '角色ID',
                                     PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;