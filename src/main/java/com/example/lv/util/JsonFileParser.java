package com.example.lv.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String jsonFilePath = "C:\\Users\\gxjh2\\Desktop\\品牌资费.json"; // 替换为你的JSON文件路径
        String excelFilePath = "C:\\Users\\gxjh2\\Desktop\\品牌资费.xlsx";
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

            JSONArray articleList = (JSONArray) jsonObject2.get("document");
            List<Map<String, String>> list = new ArrayList<>();

            for (int i = 0; i < articleList.length(); i++) {

                Map<String, String> map = new HashMap<>();
                JSONObject jsonObject1 = articleList.getJSONObject(i);

                map.put("knowledgeId", jsonObject1.getString("knowledgeId"));
                map.put("knowledgeName", jsonObject1.getString("knowledgeName"));
                map.put("bossCode", jsonObject1.isNull("bossCode") ? "" : jsonObject1.getString("bossCode"));
                map.put("bossName", jsonObject1.isNull("bossName") ? "" : jsonObject1.getString("bossName"));
                list.add(map);

            }

            // 创建工作簿和工作表
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("sheet1");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("knowledgeId");
            headerRow.createCell(1).setCellValue("knowledgeName");
            headerRow.createCell(2).setCellValue("bossCode");
            headerRow.createCell(3).setCellValue("bossName");

            // 填充数据
            int rowNum = 1;
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> mapData = list.get(i);
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(mapData.get("knowledgeId"));
                row.createCell(1).setCellValue(mapData.get("knowledgeName"));
                row.createCell(2).setCellValue(mapData.get("bossCode"));
                row.createCell(3).setCellValue(mapData.get("bossName"));
            }

            // 写入 Excel 文件
            try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
                workbook.write(fileOut);
            }

            // 关闭工作簿
            workbook.close();


        } catch (IOException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing the JSON string: " + e.getMessage());
        }
    }


}
