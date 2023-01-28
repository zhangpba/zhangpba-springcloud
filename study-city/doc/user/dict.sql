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

--字典数据表
CREATE TABLE `study`.`dict_data`
(
    `dict_code`   bigint(0) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `dict_sort`   int(0) NULL DEFAULT 0 COMMENT '字典排序',
    `dict_label`  varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典标签',
    `dict_value`  varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '字典类型',
    `is_default`  char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `status`      char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典数据表' ROW_FORMAT = Dynamic;


INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('用户性别', 'user_sex', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '用户性别列表');
INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('考试类型', 'exam_type', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '考试类型列表');
INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('24节气', 'solar_terms', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '24节气');
INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('八卦挂相', 'gossip_show', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '八卦挂相');
INSERT INTO `study`.`dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('挂相分析', 'gossip_desc', '0', 'admin', '2023-05-18 18:37:16', '', NULL, '挂相分析');


INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 1, '男', '0', 'user_sex', 'Y', '0', 'admin', '2022-05-18 18:37:16', '', NULL, '性别男');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 2, '女', '1', 'user_sex', 'N', '0', 'admin', '2022-05-18 18:37:16', '', NULL, '性别女');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 1, '小车科目一', 'car_one', 'exam_type', 'Y', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '小车科目一');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 2, '小车科目四', 'car_four', 'exam_type', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '小车科目四');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 3, '客车科目一', 'bus_one', 'exam_type', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '客车科目一');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 4, '客车科目四', 'bus_four', 'exam_type', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '客车科目四');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 5, '货车科目一', 'track_one', 'exam_type', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '货车科目一');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 6, '货车科目四', 'track_four', 'exam_type', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '货车科目四');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 7, '摩托车科目一', 'motorcycle_one', 'exam_type', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '摩托车科目一');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 8, '摩托车科目四', 'motorcycle_four', 'exam_type', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '摩托车科目四');


INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 1, '立春', '1', 'solar_terms', 'Y', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '开始预备春耕');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 2, '雨水', '2', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '天气变暖，春回大地');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 3, '惊蛰', '3', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '打雷的话预示风调雨顺');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 4, '春分', '4', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '越冬作物，比如冬小麦开始生长，昼夜等长');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 5, '清明', '5', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '清明前后一场雨，强如秀才中了举');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 6, '谷雨', '6', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '雨生百谷');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 7, '立夏', '7', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '作物生长繁茂');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 8, '小满', '8', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '从小满开始，大麦、冬小麦等夏收作物已经结果，籽粒渐见饱满，但尚未成熟，所以叫小满');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 9, '芒种', '9', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '有芒作物开始播种');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 10, '夏至', '10', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '白昼最长，光合作用最长的时候');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 11, '小暑', '11', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '天气更热，雨水增加');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 12, '大暑', '12', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '气温最高，农作物生长最快，雨涝灾害最频繁');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 13, '立秋', '13', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '秋天到了');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 14, '处暑', '14', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '夏天过去了');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 15, '白露', '15', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '草木上开始有露珠，天气真正转凉');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 16, '秋分', '16', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '昼夜等长');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 17, '寒露', '17', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '比白露更冷的露水');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 18, '霜降', '18', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '开始下霜了，这时候柿子才好吃');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 19, '立冬', '19', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '冬天到了');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 20, '小雪', '20', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '开始下雪了，但是不大');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 21, '大雪', '21', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '瑞雪兆丰年');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 22, '冬至', '22', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '白昼最短');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 23, '小寒', '23', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '开始进入最冷的时候，要防止农作物冻害');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 24, '大寒', '24', 'solar_terms', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '天寒地冻但是开始有转暖迹象');

INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 1, '乾三连', '1', 'gossip_show', 'Y', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '乾三连：即乾卦的符号，从上至下由三条不会中断的长直线——阳爻组成');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 2, '坤六断', '2', 'gossip_show', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '坤六断：即坤卦的符号，从上至下由三条中断的断线——阴爻组成，共计六条短线');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 3, '震仰盂', '3', 'gossip_show', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '震仰盂：即震卦的符号，从上至下由两条断线（阴爻）、一条直线（阳爻）组成，符号形状类似于仰放在地上的方盂');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 4, '艮覆碗', '4', 'gossip_show', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '艮覆碗：即艮卦的符号，从上至下由一条直线（阳爻）、两条断线（阴爻）组成，符号形状类似于扣在地上的碗');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 5, '离中虚', '5', 'gossip_show', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '离中虚：即离卦的符号，从上至下由一条直线（阳爻）、一条断线（阴爻）、一条直线（阳爻）组成，符号形状只有中间是断开的线，中间空虚');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 6, '坎中满', '6', 'gossip_show', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '坎中满：即坎卦的符号，从上至下由一条断线（阴爻）、一条直线（阳爻）、一条断线（阴爻）组成，符号形状的中间是一条不中断的直线，中间盈满');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 7, '兑上缺', '7', 'gossip_show', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '兑上缺：即兑卦的符号，从上至下由一条断线（阴爻）、两条直线（阳爻）组成，符号形状中只有最上面的是一条断开的线，犹如一个缺口');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 8, '巽下断', '8', 'gossip_show', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '巽下断：即巽卦的符号，从上至下由两条直线（阳爻）、一条断线（阴爻）组成，符号性状中只有最下方的是一条断开的线');

INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '乾卦', '1', 'gossip_desc', 'Y', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '乾物金玉与珠宝，冠镜木果钱钟表。高档用品如轿车，圆物刚物全是了。动物马狮天鹅象，寺院皇宫都市撞。高楼宫殿博物馆，君父长官有名望。英雄独裁掌权人，刚毅果断积极行。冷酷傲慢是缺点，还有强制任专横。身体胸部头骨肺，季节秋天戌亥配。颜色大赤玄金黄，一四九数西北位。');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '坤卦', '2', 'gossip_desc', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '坤人勤劳又恭敬，沉默迟缓性柔顺。消极懒躺懦弱穷，依赖吝啬要谨慎。人物普通之大众，农夫乡人泥瓦工。老母后母妻寡妇，臣民顾问村干部。大腹之人地产商，阴气盛者小人狂。牛马雌兽脾胃肉，西南八五十色黄。辰戌丑未均旺季，五谷布衣包瓦器。矮旧农舍会市场，田野仓库平原地。');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '震卦', '3', 'gossip_desc', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '震怒躁动起虚惊，性急无礼强霸民。表现进步显勇敢，追求紧迫便粗心。人物有名是将帅，警察法官长子在。狂士声大善吹牛，运动驾驶跑得快。住宅楼阁闹市中，公园车站大道同。唯有庭院山林静，歌厅舞厅乱轰轰。春来寅卯四八三，龙蛇鹰蜂鹤鹿眠。足肝神经声音发，竹木制品采花鲜。');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, '巽卦', '4', 'gossip_desc', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '巽为长女为僧道，教师商人手艺妙。优柔寡断科技人，额宽面白头发少。奔波忙碌飘难定，多欲空虚基不牢。进退忧疑生权谋，鼓舞附和直爽好。场地山林之寺观，商店码头邮局连。窄路管道长直物，木制纤维丝线牵。蝇笔蚊香电风扇，兰花草约救生圈。鸡鹅鸭蛇五三八，青兰辰巳股肱边。');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, '坎卦', '5', 'gossip_desc', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '坎人算计太聪明，义气漂泊不安宁。时时险陷灾难困，狡猾欺诈亦劳心。善则思想多创造，恶则暗昧匪与盗。劳苦沉默变酒鬼，耳血腰肾防不妙。江湖泉井与河沟，酒店澡堂监狱囚。车库饭店地下室，油盐酱醋饮料流。带核桃李放酒具，磁盘影带计算器。一六亥子属北方，色黑猪鱼狐鼠遇。');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (6, '离卦', '6', 'gossip_desc', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '离为火兮为日电，聪明虚心重表现。煽动轻浮好虚荣，自满扩张示鲜艳。中层美人文艺人，目疾兵戈幻多情。报刊印章图书信，影视机器灯照明。大腹中空瓶笼类，玻璃门窗烧烤会。名胜书馆影剧场，医院学校火山射。公安法院检察院，三二七南红紫变。雉龟虾蟹凤孔雀，心目上焦小肠患。');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (7, '艮卦', '7', 'gossip_desc', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '艮为少男与儿童，僧道闲人土建工。石匠囚犯监狱吏，奴仆官僚可怜虫。主观任性太顽固，困阻偏激保守路。沉着冷静慢更新，建设改善莫停步。丘陵坟墓山高台，银行寺庙休息宅。堤坎监狱派出所，仓库门坎桌子鞋。磁器石碑床与伞，硬木硬果钱袋满。五黄十七在东北，手背胃鼻虎鼠犬。');
INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (8, '兑卦', '8', 'gossip_desc', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '兑为少女妾可爱。动口职业如歌女，饮食金融与破败。喜悦温柔逞雄辩，谗毁谤说成淫滥。笑骂吵闹空议论，服从脱落装伪善。身体口舌肺咽痰，泽中之物羊猴猿。饮食用具缺损品，金刀金类乐器玩。有口瓶子酒杯罐，山洞池井水泽畔。废墟路口破壁屋，四二九白西方看');

INSERT INTO `study`.`dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '满月', 'yoyo', '2023-01-05', 'N', '0', 'admin', '2023-01-29 18:37:16', '', NULL, '满月');
