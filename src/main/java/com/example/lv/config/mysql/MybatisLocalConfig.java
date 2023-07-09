package com.example.lv.config.mysql;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * SqlServer 数据库配置
 * 数据源 2
 * @author Administrator
 */
@Configuration
@MapperScan(basePackages = "com.example.lv.dao.mapper.local", sqlSessionFactoryRef = "localSqlSessionFactory")
public class MybatisLocalConfig {

    @Bean(name = "localDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.local")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();

    }

    @Bean(name = "localTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("localDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);

    }

    @Bean(name = "localSqlSessionFactory")
    public SqlSessionFactory basicSqlSessionFactory(@Qualifier("localDataSource") DataSource basicDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(basicDataSource);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:**/local/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "localSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("localSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

