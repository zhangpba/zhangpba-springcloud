package com.study.starter.utils;

import com.study.starter.vo.BaiJiaXingVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 姓氏帮助类
 *
 * @author zhangpba
 * @date 2022-04-12
 */
public class FirstNameUtils {

    /**
     * 获取百家姓的名字和读音
     *
     * @return
     */
    public static List<BaiJiaXingVo> getBaijaiXing() {
        List<BaiJiaXingVo> list = new ArrayList<>();
        List<String> zhNamesList = getZhName();
        List<String> letterNamesList = getLetterName();
        for (int i = 0; i < zhNamesList.size(); i++) {
            BaiJiaXingVo baiJiaXingVo = new BaiJiaXingVo();
            baiJiaXingVo.setZhName(zhNamesList.get(i));
            baiJiaXingVo.setLetterName(letterNamesList.get(i));
            list.add(baiJiaXingVo);
        }
        return list;
    }

    /**
     * 获取汉字名称
     *
     * @return
     */
    public static List<String> getZhName() {
        return getNameList(zhNames);
    }

    /**
     * 获取字母名称
     *
     * @return
     */
    public static List<String> getLetterName() {
        return getNameList(letterNames);
    }

    private static List<String> getNameList(String names) {
        String[] nameArray = names.replaceAll(" ", "")
                .replaceAll("，", "")
                .replaceAll("。", "")
                .replaceAll("\n", "\t")
                .split("\t");

        List<String> namesList = new ArrayList<>();
        StringBuffer namesBuffer = new StringBuffer();
        for (String name : nameArray) {
            if (!name.isEmpty()) {
                namesBuffer.append(name.trim());
                namesBuffer.append("\t");
                namesList.add(name);
            }
        }
        System.out.println(namesList);
        return namesList;
    }

    // 百家姓的读音
    public static String letterNames = "zhào\tqián\tsūn\tlǐ\t \tzhōu\twú\tzhèng\twáng\n" +
            "féng\tchén\tchǔ\twèi\t \tjiǎng\tshěn\thán\tyáng\n" +
            "zhū\tqín\tyoú\txǔ\t \thé\tLǚ\tshī\tzhāng\n" +
            "kǒng\tcáo\tyán\thuà\t \tjīn\twèi\ttáo\tjiāng\n" +
            "qī\txiè\tzōu\tyù\t \tbǎi\tshuǐ\tdòu\tzhāng\n" +
            "yún\tsū\tpān\tgě\t \txī\tfàn\tpéng\tLáng\n" +
            "Lǔ\twéi\tchāng\tmǎ\t \tmiáo\tfèng\thuā\tfāng\n" +
            "yú\tRén\tyuán\tLiǔ\t \tfēng\tbào\tshǐ\ttáng\n" +
            "fèi\tLián\tcén\txuē\t \tLéi\thè\tní\ttāng\n" +
            "téng\tyīn\tLuó\tbì\t \thǎo\twū\tān\tcháng\n" +
            "yuè\tyú\tshí\tfù\t \tpí\tbiàn\tqí\tkāng\n" +
            "wǔ\tyú\tyuán\tbǔ\t \tgù\tmèng\tpíng\thuáng\n" +
            "hé\tmù\txiāo\tyǐn\t \tyáo\tshào\tzhàn\twāng\n" +
            "qí\tmáo\tyǔ\tdí\t \tmǐ\tbèi\tmíng\tzāng\n" +
            "jì\tfú\tchéng\tdài\t \ttán\tsòng\tmáo\tpáng\n" +
            "xióng\tjǐ\tshū\tqū\t \txiàng\tzhù\tdǒng\tLiáng\n" +
            "dù\tRuǎn\tLán\tmǐn\t \txí\tjì\tmá\tqiáng\n" +
            "jiǎ\tLù\tLóu\twēi\t \tjiāng\ttóng\tyán\tguō\n" +
            "méi\tshèng\tLín\tdiāo\t \tzhōng\txú\tqiū\tLuò\n" +
            "gāo\txià\tcài\ttián\t \tfán\thú\tLíng\thuò\n" +
            "yú\twàn\tzhī\tkē\t \tzǎn\tguǎn\tLú\tmò\n" +
            "jīng\tfáng\tqiú\tmiào\t \tgān\txiè\tyīng\tzōng\n" +
            "dīng\txuān\tbēn\tdèng\t \tyù\tshàn\tháng\thóng\n" +
            "bāo\tzhū\tzuǒ\tshí\t \tcuī\tjí\tniǔ\tgōng\n" +
            "chéng\tjī\txíng\thuá\t \tpéi\tLù\tRóng\twēng\n" +
            "xún\tyáng\tyú\thuì\t \tzhēn\tqū\tjiā\tfēng\n" +
            "Ruì\tyì\tchǔ\tjìn\t \tjí\tbǐng\tmí\tsōng\n" +
            "jǐng\tduàn\tfù\twū\t \twū\tjiāo\tbā\tgōng\n" +
            "mù\tkuí\tshān\tgǔ\t \tchē\thóu\tfú\tpéng\n" +
            "quán\txī\tbān\tyǎng\t \tqiū\tzhòng\tyī\tgōng\n" +
            "nìng\tqiú\tLuán\tbào\t \tgān\ttǒu\tLì\tRóng\n" +
            "zǔ\twǔ\tfú\tLiú\t \tjǐng\tzhān\tshù\tLóng\n" +
            "yè\txìng\tsī\tsháo\t \tgào\tLí\tjì\tbó\n" +
            "yìn\tsù\tbái\thuái\t \tpú\ttái\tcóng\tè\n" +
            "suǒ\txián\tjí\tLài\t \tzhuó\tLìn\ttù\tméng\n" +
            "chí\tqiáo\tyīn\tyù\t \txū\tnài\tcāng\tshuāng\n" +
            "wén\tshēn\tdǎng\tzhái\t \ttán\tgòng\tLáo\tpáng\n" +
            "jī\tshēn\tfú\tdǔ\t \tRǎn\tzǎi\tLì\tyōng\n" +
            "xì\tqú\tsāng\tguì\t \tpú\tniú\tshòu\ttōng\n" +
            "biān\thù\tyān\tjì\t \tjiá\tpǔ\tshàng\tnóng\n" +
            "wēn\tbié\tzhuāng\tyàn\t \tchái\tqú\tyán\tchōng\n" +
            "mù\tLián\tRú\txí\t \thuàn\tài\tyú\tRóng\n" +
            "xiàng\tgǔ\tyì\tshèn\t \tgē\tLiào\tyǔ\tzhōng\n" +
            "jì\tjū\théng\tbù\t \tdū\tgěng\tmǎn\thóng\n" +
            "kuāng\tguó\twén\tkòu\t \tguǎng\tLù\tquē\tdōng\n" +
            "ōu\tshū\twò\tLì\t \tyù\tyuè\tkuí\tLóng\n" +
            "shī\tgǒng\tshè\tniè\t \tcháo\tgōu\táo\tRóng\n" +
            "Lěng\tzī\txīn\tkàn\t \tnā\tjiǎn\tRáo\tkōng\n" +
            "zēng\twú\tshā\tniè\t \tyǎng\tjū\txū\tféng\n" +
            "cháo\tguān\tkuǎi\txiāng\t \tzhā\thòu\tjīng\thóng\n" +
            "yóu\tzhú\tquán\tLù\t \tgě\tyì\thuán\tgōng\n" +
            "mò\tqí\tsī\tmǎ\t \tshàng\tguān\tōu\tyáng\n" +
            "xià\thóu\tzhū\tgě\t \twén\trén\tdōng\tfāng\n" +
            "hè\tlián\thuáng\tfǔ\t \tyù\tchí\tgōng\tyáng\n" +
            "tán\ttái\tgōng\tyě\t \tzōng\tzhèng\tpú\tyáng\n" +
            "chún\tyú\tchán\tyú\t \ttài\tshū\tshēn\ttú\n" +
            "gōng\tsūn\tzhòng\tsūn\t \txuān\tyuán\tLíng\thú\n" +
            "zhōng\tlí\tyǔ\twén\t \tzhǎng\tsūn\tmù\tróng\n" +
            "xiān\tyú\tLǘ\tqiū\t \tsī\ttú\tsī\tkōng\n" +
            "qí\tguān\tsī\tkòu\t \tzhǎng\tdū\tzǐ\tjū\n" +
            "zhuān\tsūn\tduān\tmù\t \twū\tmǎ\tgōng\txī\n" +
            "qī\tdiāo\tyuè\tzhèng\t \tRǎng\tsì\tgōng\tliáng\n" +
            "tuò\tbá\tjiá\tgǔ\t \tzǎi\tfǔ\tgǔ\tliáng\n" +
            "jìn\tchǔ\tyán\tfǎ\t \trǔ\tyān\ttú\tqīn\n" +
            "duàn\tgān\tbǎi\tlǐ\t \tdōng\tguō\tnán\tmén\n" +
            "hū\tyán\tguī\thǎi\t \tyáng\tshé\twēi\tshēng\n" +
            "yuè\tshuài\tgōu\tkòng\t \tkuàng\thòu\tyǒu\tqín\n" +
            "Liáng\tqiū\tzuǒ\tqiū\t \tdōng\tmén\txī\tmén\n" +
            "shāng\tmóu\tshé\tnài\t \tbó\tshǎng\tnán\tgōng\n" +
            "mò\thǎ\tqiáo\tdá\t \tnián\tài\tyáng\ttóng\n" +
            "dì\twǔ\tyán\tfú";

    // 百家姓的汉字
    public static String zhNames = "赵\t钱\t孙\t李\t周\t吴\t郑\t王\n" +
            "冯\t陈\t褚\t卫\t蒋\t沈\t韩\t杨\n" +
            "朱\t秦\t尤\t许\t何\t吕\t施\t张\n" +
            "孔\t曹\t严\t华\t金\t魏\t陶\t姜\n" +
            "戚\t谢\t邹\t喻\t柏\t水\t窦\t章\n" +
            "云\t苏\t潘\t葛\t奚\t范\t彭\t郎\n" +
            "鲁\t韦\t昌\t马\t苗\t凤\t花\t方\n" +
            "俞\t任\t袁\t柳\t酆\t鲍\t史\t唐\n" +
            "费\t廉\t岑\t薛\t雷\t贺\t倪\t汤\n" +
            "滕\t殷\t罗\t毕\t郝\t邬\t安\t常\n" +
            "乐\t于\t时\t傅\t皮\t卞\t齐\t康\n" +
            "伍\t余\t元\t卜\t顾\t孟\t平\t黄\n" +
            "和\t穆\t萧\t尹\t姚\t邵\t湛\t汪\n" +
            "祁\t毛\t禹\t狄\t米\t贝\t明\t臧\n" +
            "计\t伏\t成\t戴\t谈\t宋\t茅\t庞\n" +
            "熊\t纪\t舒\t屈\t项\t祝\t董\t梁\n" +
            "杜\t阮\t蓝\t闵\t席\t季\t麻\t强\n" +
            "贾\t路\t娄\t危\t江\t童\t颜\t郭\n" +
            "梅\t盛\t林\t刁\t钟\t徐\t邱\t骆\n" +
            "高\t夏\t蔡\t田\t樊\t胡\t凌\t霍\n" +
            "虞\t万\t支\t柯\t昝\t管\t卢\t莫\n" +
            "经\t房\t裘\t缪\t干\t解\t应\t宗\n" +
            "丁\t宣\t贲\t邓\t郁\t单\t杭\t洪\n" +
            "包\t诸\t左\t石\t崔\t吉\t钮\t龚\n" +
            "程\t嵇\t邢\t滑\t裴\t陆\t荣\t翁\n" +
            "荀\t羊\t於\t惠\t甄\t曲\t家\t封\n" +
            "芮\t羿\t储\t靳\t汲\t邴\t糜\t松\n" +
            "井\t段\t富\t巫\t乌\t焦\t巴\t弓\n" +
            "牧\t隗\t山\t谷\t车\t侯\t宓\t蓬\n" +
            "全\t郗\t班\t仰\t秋\t仲\t伊\t宫\n" +
            "宁\t仇\t栾\t暴\t甘\t钭\t厉\t戎\n" +
            "祖\t武\t符\t刘\t景\t詹\t束\t龙\n" +
            "叶\t幸\t司\t韶\t郜\t黎\t蓟\t薄\n" +
            "印\t宿\t白\t怀\t蒲\t邰\t从\t鄂\n" +
            "索\t咸\t籍\t赖\t卓\t蔺\t屠\t蒙\n" +
            "池\t乔\t阴\t鬱\t胥\t能\t苍\t双\n" +
            "闻\t莘\t党\t翟\t谭\t贡\t劳\t逄\n" +
            "姬\t申\t扶\t堵\t冉\t宰\t郦\t雍\n" +
            "卻\t璩\t桑\t桂\t濮\t牛\t寿\t通\n" +
            "边\t扈\t燕\t冀\t郏\t浦\t尚\t农\n" +
            "温\t别\t庄\t晏\t柴\t瞿\t阎\t充\n" +
            "慕\t连\t茹\t习\t宦\t艾\t鱼\t容\n" +
            "向\t古\t易\t慎\t戈\t廖\t庾\t终\n" +
            "暨\t居\t衡\t步\t都\t耿\t满\t弘\n" +
            "匡\t国\t文\t寇\t广\t禄\t阙\t东\n" +
            "欧\t殳\t沃\t利\t蔚\t越\t夔\t隆\n" +
            "师\t巩\t厍\t聂\t晁\t勾\t敖\t融\n" +
            "冷\t訾\t辛\t阚\t那\t简\t饶\t空\n" +
            "曾\t毋\t沙\t乜\t养\t鞠\t须\t丰\n" +
            "巢\t关\t蒯\t相\t查\t后\t荆\t红\n" +
            "游\t竺\t权\t逯\t盖\t益\t桓\t公\n" +
            "万\t俟\t司\t马\t上\t官\t欧\t阳\n" +
            "夏\t侯\t诸\t葛\t闻\t人\t东\t方\n" +
            "赫\t连\t皇\t甫\t尉\t迟\t公\t羊\n" +
            "澹\t台\t公\t冶\t宗\t政\t濮\t阳\n" +
            "淳\t于\t单\t于\t太\t叔\t申\t屠\n" +
            "公\t孙\t仲\t孙\t轩\t辕\t令\t狐\n" +
            "钟\t离\t宇\t文\t长\t孙\t慕\t容\n" +
            "鲜\t于\t闾\t丘\t司\t徒\t司\t空\n" +
            "丌\t官\t司\t寇\t仉\t督\t子\t车\n" +
            "颛\t孙\t端\t木\t巫\t马\t公\t西\n" +
            "漆\t雕\t乐\t正\t壤\t驷\t公\t良\n" +
            "拓\t跋\t夹\t谷\t宰\t父\t谷\t梁\n" +
            "晋\t楚\t闫\t法\t汝\t鄢\t涂\t钦\n" +
            "段\t干\t百\t里\t东\t郭\t南\t门\n" +
            "呼\t延\t归\t海\t羊\t舌\t微\t生\n" +
            "岳\t帅\t缑\t亢\t况\t郈\t有\t琴\n" +
            "梁\t丘\t左\t丘\t东\t门\t西\t门\n" +
            "商\t牟\t佘\t佴\t伯\t赏\t南\t宫\n" +
            "墨\t哈\t谯\t笪\t年\t爱\t阳\t佟\n" +
            "第\t五\t言\t福";
}
