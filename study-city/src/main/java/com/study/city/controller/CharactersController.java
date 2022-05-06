package com.study.city.controller;

import com.study.city.entity.characters.Characters;
import com.study.city.service.ICharactersService;
import com.study.starter.utils.DateUtils;
import com.study.starter.vo.web.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 生日性格
 *
 * @author zhangpba
 * @date 2022-05-06
 */
@Api(value = "生日性格", tags = "生日性格")
@RestController
@RequestMapping("/character")
public class CharactersController {
    private static final Logger logger = LoggerFactory.getLogger(CharactersController.class);

    @Autowired
    ICharactersService charactersService;

    /**
     * 根据生日查询人的性格
     *
     * @param month 生日-月
     * @param day   生日-日
     * @return
     */
    @ApiOperation(value = "根据生日查询人的性格")
    @GetMapping(value = "/getCharacterByBrithday")
    public ResponseMessage getCharacterByBrithday(@ApiParam(name = "month", value = "生日-月", required = true) @RequestParam String month,
                                                  @ApiParam(name = "day", value = "生日-日", required = true) @RequestParam String day) {

        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        String birthday = month + day;

        List<Characters> charactersList = new ArrayList<>();
        // 查询数据库中的
        Characters characters = charactersService.getCharacters(birthday);
        if (characters != null) {
            charactersList.add(characters);
        } else {
            // 查询天行数据往
            charactersList = charactersService.getCharacters(month, day);
            charactersService.batchAddcharacters(charactersList);
        }
        return ResponseMessage.success(charactersList);
    }

    /**
     * 同步数据100天的生日性格数据
     * 0 30 1 * * * # 每天01：30执行一次
     */
    @Scheduled(cron = "${module.character.syn-cron}")
    public void synData() {
        List<Characters> charactersList = charactersService.getAllCharacters();
        if (charactersList == null) {
            charactersList = new ArrayList<>();
        }
        if (charactersList.size() >= 356) {
            logger.info("一年366天，每一天的性格全部已经同步过！");
            return;
        }
        for (int i = charactersList.size(); i < charactersList.size() + 100; i++) {
            String date = DateUtils.getDay(new Date(), i);
            String[] dataArray = date.split("-");
            String month = dataArray[1];
            String day = dataArray[2];
            List<Characters> characterses = charactersService.getCharacters(month, day);
            charactersService.batchAddcharacters(characterses);
        }
    }
}
