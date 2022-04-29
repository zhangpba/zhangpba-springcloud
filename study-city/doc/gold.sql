CREATE TABLE `study`.`gold`  (
                                     `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '品种代号',
                                     `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '品种名称',
                                     `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最新价',
                                     `openingprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '开盘价',
                                     `maxprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高价',
                                     `minprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低价',
                                     `changepercent` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '涨跌幅',
                                     `lastclosingprice` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昨收盘价',
                                     `tradeamount` decimal(10, 0) NULL DEFAULT NULL COMMENT '总成交量',
                                     `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                     `date` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据日期',
                                     `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
                                     `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
                                     `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                     `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;
--增加字段脚本
ALTER TABLE `study`.`gold` ADD `buyprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '买入价';
ALTER TABLE `study`.`gold` ADD  `sellprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '买出价';
ALTER TABLE `study`.`gold` ADD `finalprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '成交价';
ALTER TABLE `study`.`gold` ADD  `closingprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '收市价';
ALTER TABLE `study`.`gold` ADD  `changequantity` decimal(10, 2) NULL DEFAULT NULL COMMENT '涨跌量';
ALTER TABLE `study`.`gold` ADD  `amplitude` decimal(10, 2) NULL DEFAULT NULL COMMENT '振幅';
ALTER TABLE `study`.`gold` ADD `midprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '中间价';
ALTER TABLE `study`.`gold` ADD `exchange_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '交易机构';