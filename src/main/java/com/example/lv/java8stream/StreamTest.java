package com.example.lv.java8stream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 案例
 * @date 2023/7/5 11:08:59
 */
@SpringBootTest
public class StreamTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamTest.class);

    @Test
    public void test(){

        //初始化list
        List<Map<String,Object>> list = new ArrayList<>();
        List<Map<String,Object>> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        initList(list,list2,list3);
        //list有 list2没有
        List<Map<String,Object>> l = new ArrayList<>();
        //list有 list2有
        List<Map<String,Object>> l2 = new ArrayList<>();
        for (Map<String, Object> map : list) {
            //标识 0代表不同
            int flag = 0;
            for (Map<String, Object> map2 : list2) {
                //如果相同
                if(map.get("name").equals(map2.get("name"))){
                    flag++;
                }
            }
            if(flag==0){
                l.add(map);
            }else {
                l2.add(map);
            }
        }
        LOGGER.info("list有 list2没有"+l);
        LOGGER.info("list有 list2有"+l2);
        //获取两个列表不同的对象 list有 list2没有
        List<Map<String,Object>> res = list.stream().filter(a -> !list2.stream().map(b -> b.get("name")).collect(Collectors.toList()).contains(a.get("name"))).collect(Collectors.toList());
        //[{name=小明, id=3, age=19}, {name=小朋, id=5, age=21}]
        LOGGER.info("list有 list2没有"+res);
        //获取两个列表不同的对象 list没有 list2有
        List<Map<String,Object>> res2 = list2.stream().filter(a -> !list.stream().map(b -> b.get("name")).collect(Collectors.toList()).contains(a.get("name"))).collect(Collectors.toList());
        //[{name=小天, id=6, age=21}]
        LOGGER.info("list没有 list2有"+res2);
        //获取两个列表相同的对象
        List<Map<String,Object>> res3 = list.stream().filter(list2::contains).collect(Collectors.toList());
        //[{name=小刚, id=1, age=18}, {name=小红, id=2, age=17}, {name=小志, id=4, age=20}]
        LOGGER.info("list有 list2有"+res3);
        //去重 Map 根据name
        list.addAll(list2);
        List<Map<String, Object>> res4 = list.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(m -> (String) m.get("name")))), ArrayList::new));
        //[{name=小刚, id=1, age=18}, {name=小天, id=6, age=21}, {name=小志, id=4, age=20}, {name=小明, id=3, age=19}, {name=小朋, id=5, age=21}, {name=小红, id=2, age=17}]
        LOGGER.info("去重 Map 根据name"+res4);
        // 去重 String 根据name
        List<String> res5 = list3.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(String::toString))), ArrayList::new));
        LOGGER.info("去重 String 根据name"+res5);

    }

    private void initList(List<Map<String, Object>> list, List<Map<String, Object>> list2, List<String> list3) {

        Map<String,Object> map = new HashMap<>(16);
        map.put("id","1");
        map.put("name","小刚");
        map.put("age","18");
        Map<String,Object> map2 = new HashMap<>(16);
        map2.put("id","2");
        map2.put("name","小红");
        map2.put("age","17");
        Map<String,Object> map3 = new HashMap<>(16);
        map3.put("id","3");
        map3.put("name","小明");
        map3.put("age","19");
        Map<String,Object> map4 = new HashMap<>(16);
        map4.put("id","4");
        map4.put("name","小志");
        map4.put("age","20");
        Map<String,Object> map5 = new HashMap<>(16);
        map5.put("id","5");
        map5.put("name","小朋");
        map5.put("age","21");
        Map<String,Object> map6 = new HashMap<>(16);
        map6.put("id","6");
        map6.put("name","小天");
        map6.put("age","21");

        list.add(map);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);

        list2.add(map);
        list2.add(map2);
        list2.add(map4);
        list2.add(map6);


        list3.add("A");
        list3.add("B");
        list3.add("C");
        list3.add("A");
        list3.add("B");
    }

}
