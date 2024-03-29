CREATE TABLE `springcloud`.`config_info`  (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `application` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

INSERT INTO `springcloud`.`config_info`(`id`, `key`, `value`, `application`, `profile`, `label`, `create_date`) VALUES (1, 'foo', 'this is foo', 'study-config-server', 'dev', 'master', '2021-08-24 23:39:26');
INSERT INTO `springcloud`.`config_info`(`id`, `key`, `value`, `application`, `profile`, `label`, `create_date`) VALUES (2, 'server.port', '9003', 'study-config-server', 'dev', 'master', '2021-08-24 00:19:05');
INSERT INTO `springcloud`.`config_info`(`id`, `key`, `value`, `application`, `profile`, `label`, `create_date`) VALUES (3, 'server.port', '9004', 'study-config-client', 'dev', 'master', '2021-08-24 00:55:26');
INSERT INTO `springcloud`.`config_info`(`id`, `key`, `value`, `application`, `profile`, `label`, `create_date`) VALUES (4, 'foo', 'hello world', 'study-config-client', 'dev', 'master', '2021-08-24 00:56:02');