--# 删除字典表
drop table dict;
--# 创建字典表
CREATE TABLE `study`.`dict`  (
                                 `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
                                 `dict_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
                                 `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典名称',
                                 `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典编号',
                                 `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
                                 `status` int(0) NULL DEFAULT NULL COMMENT '字典状态：0-失效，1-生效',
                                 `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = Dynamic;

select * from dict;
--# 24节气:立春、雨水、惊蛰、春分、清明、谷雨、立夏、小满、芒种、夏至、小暑、大暑、立秋、处暑、白露、秋分、寒露、霜降、立冬、小雪、大雪、冬至、小寒、大寒
--# 二十四节气主要跟农业生产有关，所以在相对应的日子里人们所对应的农业活动如下：
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '立春', 'JIEQI_01','开始预备春耕', 1, 1);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '雨水', 'JIEQI_02','天气变暖，春回大地', 1, 2);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '惊蛰', 'JIEQI_03','打雷的话预示风调雨顺', 1, 3);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '春分', 'JIEQI_04','越冬作物，比如冬小麦开始生长，昼夜等长', 1, 4);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '清明', 'JIEQI_05','清明前后一场雨，强如秀才中了举', 1, 5);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '谷雨', 'JIEQI_06','雨生百谷', 1, 6);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '立夏', 'JIEQI_07','作物生长繁茂', 1, 7);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '小满', 'JIEQI_08','从小满开始，大麦、冬小麦等夏收作物已经结果，籽粒渐见饱满，但尚未成熟，所以叫小满', 1, 8);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '芒种', 'JIEQI_09','有芒作物开始播种', 1, 9);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '夏至', 'JIEQI_10','白昼最长，光合作用最长的时候', 1, 10);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '小暑', 'JIEQI_11','天气更热，雨水增加', 1, 11);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '大暑', 'JIEQI_12','气温最高，农作物生长最快，雨涝灾害最频繁', 1, 12);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '立秋', 'JIEQI_13','秋天到了', 1, 13);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '处暑', 'JIEQI_14','夏天过去了', 1, 14);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '白露', 'JIEQI_15','草木上开始有露珠，天气真正转凉', 1, 15);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '秋分', 'JIEQI_16','昼夜等长', 1, 16);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '寒露', 'JIEQI_17','比白露更冷的露水', 1, 17);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '霜降', 'JIEQI_18','开始下霜了，这时候柿子才好吃', 1, 18);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '立冬', 'JIEQI_19','冬天到了', 1, 19);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '小雪', 'JIEQI_20','开始下雪了，但是不大', 1, 20);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '大雪', 'JIEQI_21','瑞雪兆丰年', 1, 21);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '冬至', 'JIEQI_22','白昼最短', 1, 22);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '小寒', 'JIEQI_23','开始进入最冷的时候，要防止农作物冻害', 1, 23);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('JIEQI', '大寒', 'JIEQI_24','天寒地冻但是开始有转暖迹象', 1, 24);

--# 八卦的顺口溜，八卦的展示: https://m.shiyunlaile.com/zhouyi/21420.html
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_SHOW', '乾三连', 'GOSSIP_SHOW_01','乾三连：即乾卦的符号，从上至下由三条不会中断的长直线——阳爻组成', 1, 1);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_SHOW', '坤六断', 'GOSSIP_SHOW_02','坤六断：即坤卦的符号，从上至下由三条中断的断线——阴爻组成，共计六条短线', 1, 2);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_SHOW', '震仰盂', 'GOSSIP_SHOW_03','震仰盂：即震卦的符号，从上至下由两条断线（阴爻）、一条直线（阳爻）组成，符号形状类似于仰放在地上的方盂', 1, 3);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_SHOW', '艮覆碗', 'GOSSIP_SHOW_04','艮覆碗：即艮卦的符号，从上至下由一条直线（阳爻）、两条断线（阴爻）组成，符号形状类似于扣在地上的碗', 1, 4);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_SHOW', '离中虚', 'GOSSIP_SHOW_05','离中虚：即离卦的符号，从上至下由一条直线（阳爻）、一条断线（阴爻）、一条直线（阳爻）组成，符号形状只有中间是断开的线，中间空虚', 1, 5);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_SHOW', '坎中满', 'GOSSIP_SHOW_06','坎中满：即坎卦的符号，从上至下由一条断线（阴爻）、一条直线（阳爻）、一条断线（阴爻）组成，符号形状的中间是一条不中断的直线，中间盈满', 1, 6);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_SHOW', '兑上缺', 'GOSSIP_SHOW_07','兑上缺：即兑卦的符号，从上至下由一条断线（阴爻）、两条直线（阳爻）组成，符号形状中只有最上面的是一条断开的线，犹如一个缺口', 1, 7);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_SHOW', '巽下断', 'GOSSIP_SHOW_08','巽下断：即巽卦的符号，从上至下由两条直线（阳爻）、一条断线（阴爻）组成，符号性状中只有最下方的是一条断开的线', 1, 8);

--# 八卦的描述，八卦的分析
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_DESC', '乾卦', 'GOSSIP_DESC_01','乾物金玉与珠宝，冠镜木果钱钟表。高档用品如轿车，圆物刚物全是了。动物马狮天鹅象，寺院皇宫都市撞。高楼宫殿博物馆，君父长官有名望。英雄独裁掌权人，刚毅果断积极行。冷酷傲慢是缺点，还有强制任专横。身体胸部头骨肺，季节秋天戌亥配。颜色大赤玄金黄，一四九数西北位。', 1, 1);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_DESC', '坤卦', 'GOSSIP_DESC_02','坤人勤劳又恭敬，沉默迟缓性柔顺。消极懒躺懦弱穷，依赖吝啬要谨慎。人物普通之大众，农夫乡人泥瓦工。老母后母妻寡妇，臣民顾问村干部。大腹之人地产商，阴气盛者小人狂。牛马雌兽脾胃肉，西南八五十色黄。辰戌丑未均旺季，五谷布衣包瓦器。矮旧农舍会市场，田野仓库平原地。', 1, 2);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_DESC', '震卦', 'GOSSIP_DESC_03','震怒躁动起虚惊，性急无礼强霸民。表现进步显勇敢，追求紧迫便粗心。人物有名是将帅，警察法官长子在。狂士声大善吹牛，运动驾驶跑得快。住宅楼阁闹市中，公园车站大道同。唯有庭院山林静，歌厅舞厅乱轰轰。春来寅卯四八三，龙蛇鹰蜂鹤鹿眠。足肝神经声音发，竹木制品采花鲜。', 1, 3);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_DESC', '巽卦', 'GOSSIP_DESC_04','巽为长女为僧道，教师商人手艺妙。优柔寡断科技人，额宽面白头发少。奔波忙碌飘难定，多欲空虚基不牢。进退忧疑生权谋，鼓舞附和直爽好。场地山林之寺观，商店码头邮局连。窄路管道长直物，木制纤维丝线牵。蝇笔蚊香电风扇，兰花草约救生圈。鸡鹅鸭蛇五三八，青兰辰巳股肱边。', 1, 4);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_DESC', '坎卦', 'GOSSIP_DESC_05','坎人算计太聪明，义气漂泊不安宁。时时险陷灾难困，狡猾欺诈亦劳心。善则思想多创造，恶则暗昧匪与盗。劳苦沉默变酒鬼，耳血腰肾防不妙。江湖泉井与河沟，酒店澡堂监狱囚。车库饭店地下室，油盐酱醋饮料流。带核桃李放酒具，磁盘影带计算器。一六亥子属北方，色黑猪鱼狐鼠遇。', 1, 5);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_DESC', '离卦', 'GOSSIP_DESC_06','离为火兮为日电，聪明虚心重表现。煽动轻浮好虚荣，自满扩张示鲜艳。中层美人文艺人，目疾兵戈幻多情。报刊印章图书信，影视机器灯照明。大腹中空瓶笼类，玻璃门窗烧烤会。名胜书馆影剧场，医院学校火山射。公安法院检察院，三二七南红紫变。雉龟虾蟹凤孔雀，心目上焦小肠患。', 1, 6);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_DESC', '艮卦', 'GOSSIP_DESC_07','艮为少男与儿童，僧道闲人土建工。石匠囚犯监狱吏，奴仆官僚可怜虫。主观任性太顽固，困阻偏激保守路。沉着冷静慢更新，建设改善莫停步。丘陵坟墓山高台，银行寺庙休息宅。堤坎监狱派出所，仓库门坎桌子鞋。磁器石碑床与伞，硬木硬果钱袋满。五黄十七在东北，手背胃鼻虎鼠犬。', 1, 7);
insert into dict (dict_type, dict_name, dict_code, description, status, sort) values ('GOSSIP_DESC', '兑卦', 'GOSSIP_DESC_08','兑为少女妾可爱。动口职业如歌女，饮食金融与破败。喜悦温柔逞雄辩，谗毁谤说成淫滥。笑骂吵闹空议论，服从脱落装伪善。身体口舌肺咽痰，泽中之物羊猴猿。饮食用具缺损品，金刀金类乐器玩。有口瓶子酒杯罐，山洞池井水泽畔。废墟路口破壁屋，四二九白西方看', 1, 8);