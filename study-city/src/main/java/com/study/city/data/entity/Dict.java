package com.study.city.data.entity;

import java.io.Serializable;

/**
 * 字典表(Dict)实体类
 *
 * @author zhangpba
 * @since 2022-12-12 20:23:30
 */
public class Dict implements Serializable {
    private static final long serialVersionUID = 592369921615687547L;
    /**
     * 字典ID
     */
    private Integer id;
    /**
     * 类型：JIEQI-节气，GOSSIP_SHOW-八卦的展示，GOSSIP_DESC-八卦挂相的描述和分析
     */
    private String dictType;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 字典编号
     */
    private String dictCode;
    /**
     * 描述
     */
    private String description;
    /**
     * 字典状态：0-失效，1-生效
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}

