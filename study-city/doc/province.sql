--#创建表
CREATE TABLE `study`.`province`  (
                                     `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地区',
                                     `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
                                     `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '行政代码',
                                     `generation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简称',
                                     `centre` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '行政中心',
                                     `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '属性'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

--#2021年版的 中国各省会城市、各省地区、编码
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华北地区', '北京市', '11', '京', '北京', '直辖市');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华北地区', '天津市', '12', '津', '天津', '直辖市');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华北地区', '河北省', '13', '冀', '石家庄', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华北地区', '山西省', '14', '晋', '太原', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华北地区', '内蒙古自治区', '15', '内蒙古', '呼和浩特', '自治区');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('东北地区', '辽宁省', '21', '辽', '沈阳', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('东北地区', '吉林省', '22', '吉', '长春', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('东北地区', '黑龙江省', '23', '黑', '哈尔滨', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华东地区', '上海市', '31', '沪', '上海', '直辖市');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华东地区', '江苏省', '32', '苏', '南京', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华东地区', '浙江省', '33', '浙', '杭州', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华东地区', '安徽省', '34', '皖', '合肥', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华东地区', '福建省', '35', '闽', '福州', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华东地区', '江西省', '36', '赣', '南昌', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华东地区', '山东省', '37', '鲁', '济南', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华中地区', '河南省', '41', '豫', '郑州', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华中地区', '湖北省', '42', '鄂', '武汉', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华中地区', '湖南省', '43', '湘', '长沙', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华南地区', '广东省', '44', '粤', '广州', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华南地区', '广西壮族自治区', '45', '贵', '南宁', '自治区');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华南地区', '海南省', '46', '琼', '海口', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('西南地区', '重庆市', '50', '渝', '重庆', '直辖市');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('西南地区', '四川省', '51', '川或蜀', '成都', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('西南地区', '贵州省', '52', '贵或黔', '贵阳', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('西南地区', '云南省', '53', '云或滇', '昆明', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('西南地区', '西藏自治区', '54', '藏', '拉萨', '自治区');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('西北地区', '陕西省', '61', '陕或秦', '西安', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('西北地区', '甘肃省', '62', '甘或陇', '兰州', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('西北地区', '青海省', '63', '青', '西宁', '省');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('西北地区', '宁夏回族自治区', '64', '宁', '银川', '自治区');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('华北地区', '新疆维吾尔族自治区', '65', '新', '乌鲁木齐', '自治区');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('港澳台地区', '香港特别行政区', '81', '港', '香港', '特别区');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('港澳台地区', '澳门特别行政区', '82', '澳', '澳门', '特别区');
INSERT INTO `study`.`province`(`area`, `name`, `code`, `generation`, `centre`, `type`) VALUES ('港澳台地区', '台湾省', '71', '台', '台北', '省');