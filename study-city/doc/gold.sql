CREATE TABLE `study`.`gold`  (
                                     `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `price` decimal(10, 2) NULL DEFAULT NULL,
                                     `openingprice` decimal(10, 2) NULL DEFAULT NULL,
                                     `maxprice` decimal(10, 2) NULL DEFAULT NULL,
                                     `minprice` decimal(10, 2) NULL DEFAULT NULL,
                                     `changepercent` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `lastclosingprice` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `tradeamount` decimal(10, 0) NULL DEFAULT NULL,
                                     `updatetime` datetime(0) NULL DEFAULT NULL,
                                     `date` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `create_date` datetime(0) NULL DEFAULT NULL,
                                     `update_date` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;