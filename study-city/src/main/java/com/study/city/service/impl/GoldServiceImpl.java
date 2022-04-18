package com.study.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.constant.FeeApiUrl;
import com.study.city.entity.Gold;
import com.study.city.mapper.GoldMapper;
import com.study.city.service.IGoldService;
import com.study.city.utils.RedisUtils;
import com.study.starter.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 黄金数据实现类
 *
 * @author zhangpab
 * @date 2022-04-16
 */
@Service
public class GoldServiceImpl implements IGoldService {
    private static final Logger logger = LoggerFactory.getLogger(GoldServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private GoldMapper goldMapper;

    @Value("${module.gold.appkey}")
    private String appkey;

    /**
     * 获取黄金数据模型
     *
     * @return
     */
    public List<Gold> getTodayGolds() {
        logger.info("黄金数据收集 start...");
        try {
            String url = FeeApiUrl.GOLD_URL + appkey;
            ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
            String body = (String) responseEntity.getBody();
            JSONObject bodyJson = (JSONObject) JSON.parse(body);
            logger.info("请求返回：{}", bodyJson);

            String msg = bodyJson.getString("msg");
            Integer status = bodyJson.getInteger("status");
            JSONArray result = bodyJson.getJSONArray("result");
            List<Gold> golds = new ArrayList<>();
            if ("ok".equals(msg)) {
                for (Object obj : result) {
                    JSONObject goldJson = (JSONObject) obj;
                    String type = goldJson.getString("type");
                    String typename = goldJson.getString("typename");
                    String price = goldJson.getString("price");
                    String openingprice = goldJson.getString("openingprice");
                    String maxprice = goldJson.getString("maxprice");
                    String minprice = goldJson.getString("minprice");
                    String changepercent = goldJson.getString("changepercent");
                    String lastclosingprice = goldJson.getString("lastclosingprice");
                    String tradeamount = goldJson.getString("tradeamount");
                    String updatetime = goldJson.getString("updatetime");

                    Gold gold = new Gold();
                    gold.setChangepercent(new BigDecimal(changepercent));
                    gold.getLastclosingprice();
                    gold.setMaxprice(new BigDecimal(maxprice));
                    gold.setLastclosingprice(new BigDecimal(lastclosingprice));
                    gold.setMinprice(new BigDecimal(minprice));
                    gold.setOpeningprice(new BigDecimal(openingprice));
                    gold.setPrice(new BigDecimal(price));
                    gold.setTradeamount(new BigDecimal(tradeamount));
                    gold.setTypename(typename);
                    gold.setType(type);
                    gold.setUpdatetime(DateUtils.prase(updatetime, DateUtils.YYYY_MM_DD_HH_MM_SS));
                    gold.setDate(DateUtils.format(new Date(), DateUtils.YYYY_MM_DD));
                    gold.setCreateDate(new Date());
                    gold.setUpdateDate(new Date());
                    gold.setCreateBy("syn-admin");
                    gold.setUpdateBy("syn-admin");
                    golds.add(gold);
                }
                // 存入redis
                redisUtils.hmSet("gold", DateUtils.format(new Date(), DateUtils.YYYY_MM_DD), body);
            }
            return golds;

        } catch (Exception e) {
            logger.error("请求失败，e：{}", e.getMessage(), e);
            return null;
        }
    }


    /**
     * 获取并保存所有黄金数据
     *
     * @return
     */
    @Override
    public String saveTodayGold() {
        try {
            List<Gold> goldList = this.getTodayGolds();
            this.batchAddGolds(goldList);
            return "保存成功！";
        } catch (Exception e) {
            return "保存失败！";
        }
    }

    /**
     * 获取历史黄金数据
     *
     * @return
     */
    @Override
    public Map<String, List<Gold>> getHistoryGolds() {
        List<Gold> golds = goldMapper.getGolds();
        Map<String, List<Gold>> goldList = golds.stream().collect(Collectors.groupingBy(Gold::getTypename, Collectors.toList()));
        return goldList;
    }

    @Override
    public List<Gold> getGoldByTypeAndDate(String type, String date) {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("date", date);
        return goldMapper.getGoldByTypeAndDate(map);
    }

    /**
     * 批量增加
     *
     * @param goldList
     */
    @Override
    public void batchAddGolds(List<Gold> goldList) {
        if (goldList != null) {
            for (Gold gold : goldList) {
                List<Gold> list = this.getGoldByTypeAndDate(gold.getType(), gold.getDate());
                if (list == null || list.isEmpty()) {
                    // 新增
                    try {
                        goldMapper.addGold(gold);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    // 修改
                    goldMapper.updateGlod(gold);
                }
            }
        }
    }
}
