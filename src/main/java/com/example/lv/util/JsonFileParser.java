package com.example.lv.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.util
 * @className: JsonFileParser
 * @author: dus
 * @description:
 * @date: 2024/11/19 17:57
 * @version: 1.0
 */
public class JsonFileParser {

    public static void main(String[] args) throws Exception {
        String jsonFilePath = "D:\\Downloads\\1.json"; // 替换为你的JSON文件路径

        try {
            // 读取JSON文件内容
            String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            // 解析JSON字符串
            JSONObject jsonObject = new JSONObject(jsonString);

            // 如果JSON文件是一个数组，则应该使用JSONArray来解析
            // JSONArray jsonArray = new JSONArray(jsonString);

            // 现在你可以根据JSON结构来访问数据了
            // 例如，如果JSON文件包含一个名为"name"的字段
            String object = jsonObject.getString("object");
            JSONObject jsonObject2 = new JSONObject(object);
            System.out.println(jsonObject2);
            JSONArray articleList = (JSONArray) jsonObject2.get("articleList");
            System.out.println(articleList);
            // 如果JSON文件包含一个数组，你可以这样遍历它
            // JSONArray array = jsonObject.getJSONArray("arrayKey");
            // for (int i = 0; i < array.length(); i++) {
            //     JSONObject item = array.getJSONObject(i);
            //     // 处理每个数组项...
            // }

        } catch (IOException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing the JSON string: " + e.getMessage());
        }
    }


}
