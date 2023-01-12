package com.study.city.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.city.data.constant.FeeApiUrl;
import com.study.city.data.entity.gold.BankGold;
import com.study.city.data.entity.gold.Gold;
import com.study.city.data.entity.gold.GoldBase;
import com.study.city.data.entity.gold.HkGold;
import com.study.city.data.entity.gold.LdGold;
import com.study.city.data.entity.gold.ShGold;
import com.study.city.data.service.IGoldService;
import com.study.city.data.enums.GoldEnum;
import com.study.city.data.mapper.GoldMapper;
import com.study.city.utils.RedisUtils;
import com.study.city.utils.excle.ExcleUtils;
import com.study.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
        Map<String, List<Gold>> goldList = golds.stream().collect(Collectors.groupingBy(Gold::getExchangeType, Collectors.toList()));
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

    /**
     * 按条件查询历史黄金数据
     *
     * @param exchangeType 交易所类型
     * @param startDate    开始时间
     * @param endDate      结束时间
     * @param type         黄金类型
     * @param typename     类型名称
     * @return 历史黄金数据
     */
    @Override
    public List<Gold> getGoldHistory(String exchangeType, String startDate, String endDate, String type, String typename) {
        Map<String, String> map = new HashMap<>();
        map.put("exchangeType", exchangeType);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("type", type);
        map.put("typename", typename);
        List<Gold> golds = new ArrayList<>();
        //  先从redis中去取，如果缓存中没有，再查数据库
        String key = exchangeType + "_" + type;
        Boolean exists = redisUtils.exists(key);
        if (exists) {
            logger.info("从redis缓存中获取数据...");
            golds = (List<Gold>) redisUtils.get(key);
        } else {
            logger.info("从数据库中获取数据...");
            golds = goldMapper.getGoldHistory(map);
            if (golds != null && !golds.isEmpty()) {
                redisUtils.set(key, golds, 1L, TimeUnit.MINUTES);
            }
        }
        return golds;
    }

    /**
     * 转化为各类黄金模型集合
     *
     * @param golds        查询出来的集合数据
     * @param exchangeType 交易所类型
     * @return
     */
    @Override
    public List<Object> toGoldList(List<Gold> golds, String exchangeType) {
        List<Object> goldBaseList = new ArrayList<>();
        if (golds != null && !golds.isEmpty()) {
            for (Gold gold : golds) {
                Object goldBase = toGoldInstance(gold, exchangeType);
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
    public Object toGoldInstance(Gold gold, String exchangeType) {
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
            hkGold.setType(gold.getType());
            hkGold.setBuyprice(gold.getBuyprice());
            hkGold.setClosingprice(gold.getClosingprice());
            hkGold.setFinalprice(gold.getFinalprice());
            hkGold.setSellprice(gold.getSellprice());
            return hkGold;
        }
        if (GoldEnum.SHGOLD.getExchangeType().equals(exchangeType)) {
            ShGold shGold = new ShGold();
            this.setBase(shGold, gold);
            shGold.setType(gold.getType());
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
            ldGold.setType(gold.getType());
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

    /**
     * execle导出黄金信息
     *
     * @param response     返回请求
     * @param exchangeType 交易机构类型
     * @param startDate    开始时间
     * @param endDate      结束时间
     */
    @Override
    public void export(HttpServletResponse response, String exchangeType, String startDate, String endDate) {
        List<List<Object>> sheetDataList = new ArrayList<>();
        // 表头数据
        List<Object> head = Arrays.asList("品种代号", "开盘价", "最高价", "最低价", "更新时间",
                "当天时间", "机构代号", "昨收盘价", "买入价", "买出价",
                "涨跌量", "中间价", "品种名称", "最新价", "涨跌幅",
                "总成交量", "成交价", "收市价", "振幅");
        sheetDataList.add(head);

        Map<String, String> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("exchangeType", exchangeType);
        List<Gold> goldList = goldMapper.getGoldHistory(map);
        for (Gold gold : goldList) {
            List<Object> goldObject = new ArrayList<>();
            // 品种代号
            goldObject.add(gold.getType());
            // 开盘价
            goldObject.add(gold.getOpeningprice());
            // 最高价
            goldObject.add(gold.getMaxprice());
            // 最低价
            goldObject.add(gold.getMinprice());
            // 更新时间
            goldObject.add(gold.getUpdatetime());
            // 当天时间
            goldObject.add(gold.getDate());
            // 品种代号
            goldObject.add(gold.getExchangeType());
            // 昨收盘价
            goldObject.add(gold.getLastclosingprice());
            // 买入价
            goldObject.add(gold.getBuyprice());
            // 买出价
            goldObject.add(gold.getSellprice());
            // 涨跌量
            goldObject.add(gold.getChangequantity());
            // 中间价
            goldObject.add(gold.getMidprice());
            // 品种名称
            goldObject.add(gold.getTypename());
            // 最新价
            goldObject.add(gold.getPrice());
            // 涨跌幅
            goldObject.add(gold.getClosingprice());
            // 总成交量
            goldObject.add(gold.getTradeamount());
            // 成交价
            goldObject.add(gold.getFinalprice());
            // 收市价
            goldObject.add(gold.getClosingprice());
            // 振幅
            goldObject.add(gold.getAmplitude());
            sheetDataList.add(goldObject);
        }
        if (exchangeType == null || exchangeType.isEmpty()) {
            exchangeType = "全部";
        }
        // 导出数据
        ExcleUtils.export(response, exchangeType + "黄金数据", sheetDataList);
    }
}
