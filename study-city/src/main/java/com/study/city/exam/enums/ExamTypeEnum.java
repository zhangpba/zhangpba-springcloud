package com.study.city.exam.enums;

/**
 * 题目类型
 *
 * @author zhangpba
 * @date 2023/1/20
 */
public enum ExamTypeEnum {

    // 小车科目一
    CAR_ONE("car_one", "小车科目一"),
    // 小车科目四
    CAR_FOUR("car_four", "小车科目四"),
    // 客车科目一
    BUS_ONE("bus_one", "客车科目一"),
    // 客车科目四
    BUS_FOUR("bus_four", "客车科目四"),
    // 货车科目一
    TRACK_ONE("track_one", "货车科目一"),
    // 货车科目四
    TRACK_FOUR("track_four", "货车科目四"),
    // 摩托车科目一
    MOTORCYCLE_ONE("motorcycle_one", "摩托车科目一"),
    // 摩托车科目四
    MOTORCYCLE_FOUR("motorcycle_four", "摩托车科目四");

    private String type;

    private String desc;

    ExamTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 判断是否存在枚举类中
     *
     * @param type 类型
     * @return
     */
    public static boolean contains(String type) {
        for (ExamTypeEnum typeEnum : ExamTypeEnum.values()) {
            if (typeEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
