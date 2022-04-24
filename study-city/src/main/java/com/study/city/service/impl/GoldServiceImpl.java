package com.study.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.constant.FeeApiUrl;
import com.study.city.entity.gold.BankGold;
import com.study.city.entity.gold.Gold;
import com.study.city.entity.gold.GoldBase;
import com.study.city.entity.gold.HkGold;
import com.study.city.entity.gold.LdGold;
import com.study.city.entity.gold.ShGold;
import com.study.city.enums.GoldEnum;
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
     * @param exchangeType
     * @return
     */
    public List<Gold> getTodayGolds(String exchangeType) {
        logger.info("黄金数据收集 start...");
        try {
            String url = String.format(FeeApiUrl.GOLD_URL, exchangeType) + appkey;
            logger.info("请求lurl：{}", url);
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

                    Gold gold = new Gold();
                    if (goldJson.get("type") != null) {
                        String type = goldJson.getString("type");
                        if (!type.isEmpty()) {
                            gold.setType(type);
                        }
                    }
                    if (goldJson.get("typename") != null) {
                        String typename = goldJson.getString("typename");
                        if (!typename.isEmpty()) {
                            gold.setTypename(typename);
                        }
                    }
                    if (goldJson.get("price") != null) {
                        String price = goldJson.getString("price");
                        if (!price.isEmpty()) {
                            gold.setPrice(new BigDecimal(price));
                        }
                    }
                    if (goldJson.get("openingprice") != null) {
                        String openingprice = goldJson.getString("openingprice");
                        if (!openingprice.isEmpty()) {
                            gold.setOpeningprice(new BigDecimal(openingprice));
                        }
                    }
                    if (goldJson.get("maxprice") != null) {
                        String maxprice = goldJson.getString("maxprice");
                        if (!maxprice.isEmpty()) {
                            gold.setMaxprice(new BigDecimal(maxprice));
                        }
                    }
                    if (goldJson.get("minprice") != null) {
                        String minprice = goldJson.getString("minprice");
                        if (!minprice.isEmpty()) {
                            gold.setMinprice(new BigDecimal(minprice));
                        }
                    }
                    if (goldJson.get("changepercent") != null) {
                        String changepercent = goldJson.getString("changepercent");
                        if (!changepercent.isEmpty()) {
                            if (changepercent.contains("%")) {
                                changepercent = changepercent.replace("%", "");
                            }
                            gold.setChangepercent(new BigDecimal(changepercent));
                        }
                    }
                    if (goldJson.get("lastclosingprice") != null) {
                        String lastclosingprice = goldJson.getString("lastclosingprice");
                        if (!lastclosingprice.isEmpty()) {
                            gold.setLastclosingprice(new BigDecimal(lastclosingprice));
                        }
                    }
                    if (goldJson.get("tradeamount") != null) {
                        String tradeamount = goldJson.getString("tradeamount");
                        if (!tradeamount.isEmpty()) {
                            gold.setTradeamount(new BigDecimal(tradeamount));
                        }
                    }
                    if (goldJson.get("updatetime") != null) {
                        String updatetime = goldJson.getString("updatetime");
                        if (!updatetime.isEmpty()) {
                            gold.setUpdatetime(DateUtils.prase(updatetime, DateUtils.YYYY_MM_DD_HH_MM_SS));
                        }
                    }
                    if (goldJson.get("buyprice") != null) {
                        String buyprice = goldJson.getString("buyprice");
                        if (!buyprice.isEmpty()) {
                            gold.setBuyprice(new BigDecimal(buyprice));
                        }
                    }
                    if (goldJson.get("sellprice") != null) {
                        String sellprice = goldJson.getString("sellprice");
                        if (!sellprice.isEmpty()) {
                            gold.setSellprice(new BigDecimal(sellprice));
                        }
                    }
                    if (goldJson.get("finalprice") != null) {
                        String finalprice = goldJson.getString("finalprice");
                        if (!finalprice.isEmpty()) {
                            gold.setFinalprice(new BigDecimal(finalprice));
                        }
                    }
                    if (goldJson.get("closingprice") != null) {
                        String closingprice = goldJson.getString("closingprice");
                        if (!closingprice.isEmpty()) {
                            gold.setClosingprice(new BigDecimal(closingprice));
                        }
                    }
                    if (goldJson.get("changequantity") != null) {
                        String changequantity = goldJson.getString("changequantity");
                        if (!changequantity.isEmpty()) {
                            gold.setChangequantity(new BigDecimal(changequantity));
                        }
                    }
                    if (goldJson.get("amplitude") != null) {
                        String amplitude = goldJson.getString("amplitude");
                        if (!amplitude.isEmpty()) {
                            gold.setAmplitude(new BigDecimal(amplitude));
                        }
                    }
                    if (goldJson.get("midprice") != null) {
                        String midprice = goldJson.getString("midprice");
                        if (!midprice.isEmpty()) {
                            gold.setMidprice(new BigDecimal(midprice));
                        }
                    }
                    gold.setExchangeType(exchangeType);
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
            // 遍历枚举类
            for (GoldEnum goldEnum : GoldEnum.values()) {
                String exchangeType = goldEnum.getExchangeType();
                List<Gold> goldList = this.getTodayGolds(exchangeType);
                this.batchAddGolds(goldList);
            }
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

    @Override
    public List<Gold> getGoldHistory(String exchangeType, String startDate, String endDate, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("exchangeType", exchangeType);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("type", type);
        return goldMapper.getGoldHistory(map);
    }

    /**
     * 转化为各类黄金模型集合
     *
     * @param golds        查询出来的集合数据
     * @param exchangeType 交易所类型
     * @return
     */
    @Override
    public List<GoldBase> toGoldList(List<Gold> golds, String exchangeType) {
        List<GoldBase> goldBaseList = new ArrayList<>();
        if (golds != null && !golds.isEmpty()) {
            for (Gold gold : golds) {
                GoldBase goldBase = toGoldInstance(gold, exchangeType);
                goldBaseList.add(goldBase);
            }
        }
        return goldBaseList;
    }

    /**
     * 转化为各类黄金模型
     *
     * @param gold         查询出来的数据
     * @param exchangeType 交易所类型
     * @return
     */
    @Override
    public GoldBase toGoldInstance(Gold gold, String exchangeType) {
        if (GoldEnum.BANK.getExchangeType().equals(exchangeType)) {
            BankGold bankGold = new BankGold();
            this.setBase(bankGold, gold);
            bankGold.setBuyprice(gold.getBuyprice());
            bankGold.setChangequantity(gold.getChangequantity());
            bankGold.setLastclosingprice(gold.getLastclosingprice());
            bankGold.setMidprice(gold.getMidprice());
            bankGold.setSellprice(gold.getSellprice());
            return bankGold;
        }
        if (GoldEnum.HKGOLD.getExchangeType().equals(exchangeType)) {
            HkGold hkGold = new HkGold();
            this.setBase(hkGold, gold);
            hkGold.setBuyprice(gold.getBuyprice());
            hkGold.setClosingprice(gold.getClosingprice());
            hkGold.setFinalprice(gold.getFinalprice());
            hkGold.setSellprice(gold.getSellprice());
            return hkGold;
        }
        if (GoldEnum.SHGOLD.getExchangeType().equals(exchangeType)) {
            ShGold shGold = new ShGold();
            this.setBase(shGold, gold);
            shGold.setChangepercent(gold.getChangepercent());
            shGold.setPrice(gold.getPrice());
            shGold.setLastclosingprice(gold.getLastclosingprice());
            shGold.setTradeamount(gold.getTradeamount());
            shGold.setTypename(gold.getTypename());
            return shGold;
        }
        if (GoldEnum.LONDON.getExchangeType().equals(exchangeType)) {
            LdGold ldGold = new LdGold();
            this.setBase(ldGold, gold);
            ldGold.setAmplitude(gold.getAmplitude());
            ldGold.setBuyprice(gold.getBuyprice());
            ldGold.setChangepercent(gold.getChangepercent());
            ldGold.setPrice(gold.getPrice());
            ldGold.setLastclosingprice(gold.getLastclosingprice());
            ldGold.setChangequantity(gold.getChangequantity());
            ldGold.setSellprice(gold.getSellprice());
            return ldGold;
        }
        return null;
    }


    /**
     * 设置共有的黄金属性
     *
     * @param goldInstance 新建数据数据模型
     * @param gold         查询出来的数据
     * @return
     */
    private GoldBase setBase(GoldBase goldInstance, Gold gold) {
        goldInstance.setType(gold.getType());
        goldInstance.setDate(gold.getDate());
        goldInstance.setMaxprice(gold.getMaxprice());
        goldInstance.setMinprice(gold.getMinprice());
        goldInstance.setOpeningprice(gold.getOpeningprice());
        goldInstance.setUpdatetime(gold.getUpdatetime());
        goldInstance.setExchangeType(gold.getExchangeType());
        goldInstance.setCreateDate(gold.getCreateDate());
        goldInstance.setUpdateDate(gold.getUpdateDate());
        goldInstance.setCreateBy(gold.getCreateBy());
        goldInstance.setUpdateBy(gold.getUpdateBy());
        return goldInstance;
    }
}
