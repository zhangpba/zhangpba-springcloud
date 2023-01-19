package com.study.common.utils;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @author zhangpba
 * @description List集合帮助类
 * @date 2023/1/20
 */
public class ListUtils {


    /**
     * 从集合中随机获取N个元素
     *
     * @param givenList 原来的集合
     * @param number    需要返回的集合大小
     * @param <T>
     * @return 随机取出的number个元素
     */
    public static <T> List<T> shuffle(List<T> givenList, int number) {
        // 将List顺序打乱，获得一个相同的随机序列
        Collections.shuffle(givenList);
        // 从集合中取N个数据
        List<T> randomSeries = givenList.subList(0, number);
        return randomSeries;
    }


    public static void main(String[] args) {
        List<String> givenList = Lists.newArrayList("zero", "one", "two", "three", "four", "five", "six");
        List<String> randomSeries = shuffle(givenList, 3);
        for (int i = 0; i < randomSeries.size(); i++) {
            System.out.println(givenList.get(i));
        }
    }
}
