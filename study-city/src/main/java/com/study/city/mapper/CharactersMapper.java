package com.study.city.mapper;

import com.study.city.entity.characters.Characters;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 获取生日性格数据
 */
@Mapper
public interface CharactersMapper {
    // 插入
    int addCharacters(Characters area);

    // 查询所有的城市
    List<Characters> getAllCharacters();

    // 根据生日查询
    Characters getCharacters(String brithday);
}
