package com.study.city.exam.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ExamPaperDetail)实体类
 *
 * @author zhangpba
 * @since 2023-01-14 19:51:46
 */
public class ExamPaperDetail implements Serializable {
    private static final long serialVersionUID = 627503953144307695L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 题目id

     */
    private Integer questionId;
    /**
     * 考生考卷表ID

     */
    private String examPaperUserId;
    /**
     * 考生答案
     */
    private String answer;
    /**
     * 正确答案
     */
    private String right;
    /**
     * 得分

     */
    private String score;
    /**
     * 本题的分值
     */
    private String points;
    /**
     * 上一道题的id

     */
    private Integer previous;
    /**
     * 下一道题的id

     */
    private Integer next;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人
     */
    private String updateBy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getExamPaperUserId() {
        return examPaperUserId;
    }

    public void setExamPaperUserId(String examPaperUserId) {
        this.examPaperUserId = examPaperUserId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Integer getPrevious() {
        return previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

}

