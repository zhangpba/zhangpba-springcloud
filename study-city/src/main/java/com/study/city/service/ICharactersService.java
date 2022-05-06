package com.study.city.service;

import com.study.city.entity.characters.Characters;

import java.util.List;

public interface ICharactersService {
    // 从天行数据获取生日性格数据
    List<Characters> getCharacters(String month, String day);

    // 批量插入
    void batchAddcharacters(List<Characters> charactersList);

    // 插入
    int addCharacters(Characters characters);

    // 查询所有的城市
    List<Characters> getAllCharacters();

    // 根据生日查询
    Characters getCharacters(String brithday);
}


