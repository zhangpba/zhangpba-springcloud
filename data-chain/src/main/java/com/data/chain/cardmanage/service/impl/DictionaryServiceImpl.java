package com.data.chain.cardmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.chain.cardmanage.dao.DictionaryDao;
import com.data.chain.cardmanage.entity.Dictionary;
import com.data.chain.cardmanage.service.DictionaryService;
import org.springframework.stereotype.Service;

/**
 * (Dictionary)表服务实现类
 *
 * @author makejava
 * @since 2022-12-15 19:32:20
 */
@Service("dictionaryService")
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary> implements DictionaryService {

}

