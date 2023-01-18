package com.study.city.exam.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (ExamPaperUser)实体类
 *
 * @author zhangpba
 * @since 2023-01-14 19:52:19
 */
public class ExamPaperUser implements Serializable {
    private static final long serialVersionUID = 911745969199173963L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 考生ID

     */
    private Integer userId;
    /**
     * 考生姓名

     */
    private String userName;
    /**
     * 考卷ID

     */
    private Integer examPaperId;
    /**
     * 考试成绩

     */
    private BigDecimal score;
    /**
     * 考试次数
     */
    private BigDecimal batch;
    /**
     * 考试状态 0-未参加，1-已通过，2-未通过

     */
    private Integer status;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Integer examPaperId) {
        this.examPaperId = examPaperId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getBatch() {
        return batch;
    }

    public void setBatch(BigDecimal batch) {
        this.batch = batch;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime(Date date) {
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

    @Override
    public String toString() {
        return "ExamPaperUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", examPaperId=" + examPaperId +
                ", score=" + score +
                ", batch=" + batch +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}

