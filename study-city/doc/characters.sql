CREATE TABLE `study`.`characters`  (
                                       `brithday` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '生日',
                                       `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
                                       `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '内容'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;