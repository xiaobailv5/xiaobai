package com.example.lv.config.mysql;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @project xiaobai
 * @description 动态数据源切换
 * @author gxjh2
 * @date 2024/9/22 20:27:30
 * @version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    //备份所有数据源
    private Map<Object, Object> defineTargetDataSources;

    /**
     * 决定当前线程使用哪个数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDynamicDataSourceKey();
    }

    public Map<Object, Object> getDefineTargetDataSources() {
        return defineTargetDataSources;
    }

    public void setDefineTargetDataSources(Map<Object, Object> defineTargetDataSources) {
        this.defineTargetDataSources = defineTargetDataSources;
    }

    public DynamicDataSource() {

    }

    public DynamicDataSource(Map<Object, Object> defineTargetDataSources) {
        this.defineTargetDataSources = defineTargetDataSources;
    }

}
