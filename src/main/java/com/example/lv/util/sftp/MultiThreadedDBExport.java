package com.example.lv.util.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.util.sftp
 * @className: MultiThreadedDBExport
 * @author: dus
 * @description: 把数据写到txt文件
 * @date: 2024/11/6 11:38
 * @version: 1.0
 */
public class MultiThreadedDBExport {

    // 数据库连接信息
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/xiaobai?useSSL=false&autoReconnet=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String QUERY = "SELECT * FROM test";

    // SFTP服务器信息
    private static final String SFTP_HOST = "127.0.0.1";
    private static final int SFTP_PORT = 22;
    private static final String SFTP_USER = "aaa";
    private static final String SFTP_PASS = "123456";
    private static final String SFTP_REMOTE_DIR = "/file/";
    private static final String SFTP_FILE_NAME = "output.txt";

    // 多线程参数
    private static final int THREAD_COUNT = 10;
    private static final int BATCH_SIZE = 1800000 / THREAD_COUNT; // 根据线程数调整

    // 临时文件存储路径
    private static final String TEMP_FILE_PATH = "output_part_";

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt = conn.prepareStatement(QUERY);
            ResultSet rs = stmt.executeQuery();

            List<List<String>> dataBatches = fetchDataInBatches(rs, BATCH_SIZE);

            for (int i = 0; i < THREAD_COUNT; i++) {
                final int batchIndex = i;
                executor.submit(() -> {
                    String tempFileName = TEMP_FILE_PATH + batchIndex + ".txt";
                    writeDataToFile(dataBatches.get(batchIndex), tempFileName);
                    try {
                        uploadFileToSFTP(tempFileName, SFTP_FILE_NAME);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 删除临时文件
                        new java.io.File(tempFileName).delete();
                    }
                });
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.HOURS); // 等待所有任务完成

            // 可选：合并文件（如果需要将多个部分合并为一个文件）
            // mergeFiles(SFTP_FILE_NAME, THREAD_COUNT);

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static List<List<String>> fetchDataInBatches(ResultSet rs, int batchSize) throws SQLException {
        List<List<String>> batches = new ArrayList<>();
        List<String> currentBatch = new ArrayList<>();

        int rowCount = 0;
        while (rs.next()) {
            currentBatch.add(rs.getString(1) + "," + rs.getString(2)); // Adjust field count and types
            rowCount++;

            if (currentBatch.size() == batchSize || rowCount == 1800000) {
                batches.add(currentBatch);
                currentBatch = new ArrayList<>();
            }
        }

        if (!currentBatch.isEmpty()) {
            batches.add(currentBatch);
        }

        return batches;
    }

    private static void writeDataToFile(List<String> data, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) { // Append mode
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void uploadFileToSFTP(String localFilePath, String remoteFileName) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
        session.setPassword(SFTP_PASS);

        // 禁用主机密钥检查（在生产环境中应该使用更安全的方法）
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        session.connect();

        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();

        try {
            channelSftp.put(localFilePath, SFTP_REMOTE_DIR + remoteFileName);
        } catch (SftpException e) {
            throw new Exception("SFTP upload failed: " + e.getMessage());
        } finally {
            channelSftp.disconnect();
            session.disconnect();
        }
    }

    // 可选：合并文件的方法（如果需要）
    // private static void mergeFiles(String remoteFileName, int threadCount) {
    //     // 实现文件合并逻辑（这通常需要在SFTP服务器上执行，或者先下载到本地再合并）
    // }

}
