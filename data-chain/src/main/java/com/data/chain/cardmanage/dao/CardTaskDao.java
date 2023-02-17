package com.data.chain.cardmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.chain.cardmanage.entity.CardTask;
import com.data.chain.cardmanage.vo.CardTaskVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 卡片消息表，每一个卡片代表一个任务(CardTask)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-14 11:07:06
 */
@Mapper
public interface CardTaskDao extends BaseMapper<CardTask> {

    Long saveCardTask(CardTask cardTask);

    @Select("SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tCARD_TASK ct \n" +
            "WHERE\n" +
            "\tct.NODE_DETAIL_ID IN (\n" +
            "\tSELECT\n" +
            "\t\tID \n" +
            "\tFROM\n" +
            "\t\tEVENT_NODE_DETAIL \n" +
            "WHERE\n" +
            "\tEVENT_ID = ( SELECT ID FROM `EVENT` WHERE UNIQUE_CODE = #{unicode} ))")
    List<CardTaskVO> queryByUnicode(String unicode);
}

