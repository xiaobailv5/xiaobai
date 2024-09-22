package com.example.lv.config.mysql;

import com.example.lv.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @project xiaobai
 * @description 数据源切换处理
 * @author gxjh2
 * @date 2024/9/22 20:31:28
 * @version 1.0
 */
public class DynamicDataSourceHolder {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);

    /**
     * 保存动态数据源名称
     */
    private static final ThreadLocal<String> DYNAMIC_DATASOURCE_KEY = new ThreadLocal<>();

    /**
     * 设置数据源，决定当前线程使用哪个数据源
     * @param key
     */
    public static void setDynamicDatasourceKey(String key) {
        logger.info("数据源切换为：", key);
        DYNAMIC_DATASOURCE_KEY.set(key);
    }

    /**
     * 获取动态数据源名称，默认使用local数据源
     * @return
     */
    public static String getDynamicDataSourceKey() {
        String key = DYNAMIC_DATASOURCE_KEY.get();
        return key == null ? Constant.LOCAL : key;
    }

    /**
     * 移除当前数据源
     */
    public static void removeDynamicDataSourceKey() {
        logger.info("移除数据源：", DYNAMIC_DATASOURCE_KEY.get());
        DYNAMIC_DATASOURCE_KEY.remove();
    }
}
