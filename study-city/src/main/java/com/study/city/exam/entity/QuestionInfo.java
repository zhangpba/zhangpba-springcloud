package com.study.city.exam.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * (QuestionInfo)表实体类-用于数据库交互
 *
 * @author zhangpba
 * @since 2023-01-11 15:12:56
 */
@Setter
@Getter
public class QuestionInfo {
    //id
    private Integer id;
    //题目内容
    private String question;
    //驾照类型，0：小车（C1、C2），1：客车（A1、A3、B1），2：货车（A2、B2），3：摩托车（D、E、F）
    private String licenseType;
    //科目类型，0：科目一，1：科目四（文明考试）
    private String subjectType;
    //题目类型（1：判断题，2：单选题，3：多选题）
    private String questionType;
    //选项A
    private String optionA;
    //选项B
    private String optionB;
    //选项C
    private String optionC;
    //选项D
    private String optionD;
    //选项E
    private String optionE;
    //选项F
    private String optionF;
    //选项G
    private String optionG;
    //选项H
    private String optionH;
    //答案
    private String key;
    //最佳解释
    private String explains;
    //图片
    private String imageUrl;
    //原始题目的ID
    private Integer sourceId;
}

