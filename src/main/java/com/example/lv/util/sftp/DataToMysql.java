package com.example.lv.util.sftp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.util.sftp
 * @className: DataToText
 * @author: dus
 * @description: 插入1800000数据到数据库  36分钟
 * @date: 2024/11/6 8:28
 * @version: 1.0
 */
public class DataToMysql {

    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/xiaobai?useSSL=false&autoReconnet=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASS = "root";

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS)){


            String sql = "INSERT INTO test (id, name) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int id = 1;
                System.out.println("开始时间"+System.currentTimeMillis());
                while (id <= 1800000) {

                    preparedStatement.setLong(1, id);
                    preparedStatement.setString(2, "小白");
                    preparedStatement.executeUpdate();
                    id++;
                }
                System.out.println("结束时间"+System.currentTimeMillis());
            }catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
