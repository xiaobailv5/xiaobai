package com.example.lv.util.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.util.sftp
 * @className: SFTPToDatabase
 * @author: dus
 * @description: 测试sftp
 * @date: 2024/11/5 20:55
 * @version: 1.0
 */
public class SFTPToDatabase {

    private static final String SFTP_HOST = "127.0.0.1";
    private static final int SFTP_PORT = 22;
    private static final String SFTP_USER = "aaa";
    private static final String SFTP_PASS = "123456";
    private static final String SFTP_REMOTE_DIR = "/file/";
    private static final String SFTP_LOCAL_DIR = "D:\\home";
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/xiaobai?useSSL=false&autoReconnet=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASS = "root";
    private static final int THREAD_COUNT = 8; // Number of threads to use

    public static void main(String[] args) {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASS);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            Vector<ChannelSftp.LsEntry> files = channelSftp.ls(SFTP_REMOTE_DIR);
            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

            for (ChannelSftp.LsEntry entry : files) {
                String filename = entry.getFilename();
                if (filename.endsWith(".txt")) {
                    String remoteFilePath = SFTP_REMOTE_DIR + filename;
                    String localFilePath = SFTP_LOCAL_DIR + filename;

                    ChannelSftp finalChannelSftp = channelSftp;
                    executorService.submit(() -> {
                        try {
                            finalChannelSftp.get(remoteFilePath, localFilePath);
                            processFileAndWriteToDatabase(localFilePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            // Clean up local file after processing
                            new java.io.File(localFilePath).delete();
                        }
                    });
                }
            }

            executorService.shutdown();
            while (!executorService.isTerminated()) {
                // Waiting for all tasks to complete
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }

    private static void processFileAndWriteToDatabase(String filePath) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
             java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {

            String sql = "INSERT INTO test2 (id, name) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Assuming the line is formatted as "value1,value2"
                    String[] values = line.split(",");
                    preparedStatement.setString(1, values[0].trim());
                    if (values.length>1) {
                        preparedStatement.setString(2, values[1].trim());
                    } else {
                        preparedStatement.setString(2, "");
                    }

                    preparedStatement.executeUpdate();
                }
            }

        } catch (SQLException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
