package com.example.lv.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.util
 * @className: SqlUtil
 * @author: dus
 * @description: alter table add column 分表增加字段用
 * @date: 2024/8/30 11:08
 * @version: 1.0
 */
public class SqlUtil {

    public static void main(String[] args) {
        String tableNamePrefix = "orders_";
        String newColumnName = "new_column";
        String newColumnType = "VARCHAR(255)";
        String startDate = "2023-01";
        String endDate = "2024-12";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        try {
            start.setTime(sdf.parse(startDate));
            end.setTime(sdf.parse(endDate));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while (start.before(end) || start.equals(end)) {
            String month = sdf.format(start.getTime());
            String tableName = tableNamePrefix + month.replace("-", "");
            System.out.println("ALTER TABLE " + tableName + " ADD COLUMN " + newColumnName + " " + newColumnType + ";");
            start.add(Calendar.MONTH, 1);
        }
    }

}
