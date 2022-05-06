package com.study.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.constant.FeeApiUrl;
import com.study.city.entity.characters.Characters;
import com.study.city.mapper.CharactersMapper;
import com.study.city.service.ICharactersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharactersServiceImpl implements ICharactersService {

    private static final Logger logger = LoggerFactory.getLogger(CharactersServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CharactersMapper charactersMapper;

    @Value("${module.character.key}")
    private String key;

    public List<Characters> getCharacters(String month, String day) {
        String url = String.format(FeeApiUrl.CHARACTERS_URL, key, month, day);
        logger.info("请求lurl：{}", url);
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        String body = (String) responseEntity.getBody();
        JSONObject bodyJson = (JSONObject) JSON.parse(body);
        logger.info("请求返回：{}", bodyJson);

        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        String borthday = month + day;

        String msg = bodyJson.getString("msg");
        Integer code = bodyJson.getInteger("code");
        JSONArray newslist = bodyJson.getJSONArray("newslist");
        List<Characters> charactersList = new ArrayList<>();
        if ("success".equals(msg)) {
            for (Object obj : newslist) {
                JSONObject newsJson = (JSONObject) obj;
                String title = newsJson.getString("title");
                String content = newsJson.getString("content");
                Characters characters = new Characters();
                characters.setTitle(title);
                characters.setContent(content);
                characters.setBrithday(borthday);
                charactersList.add(characters);
            }
        }
        return charactersList;
    }

    // 批量插入
    @Override
    public void batchAddcharacters(List<Characters> charactersList) {
        if (charactersList != null && !charactersList.isEmpty()) {
            for (Characters c : charactersList) {
                this.addCharacters(c);
            }
        }
    }

    @Override
    public int addCharacters(Characters characters) {
        Characters c = charactersMapper.getCharacters(characters.getBrithday());
        if (c == null) {
            return charactersMapper.addCharacters(characters);
        }
        return 0;
    }

    @Override
    public List<Characters> getAllCharacters() {
        return charactersMapper.getAllCharacters();
    }

    @Override
    public Characters getCharacters(String brithday) {
        return charactersMapper.getCharacters(brithday);
    }
}
