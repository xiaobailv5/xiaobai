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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 主数据源配置（Mysql）
 * @author Administrator
 */
@Configuration
@MapperScan(basePackages ="com.example.lv.dao.mapper.base", sqlSessionFactoryRef = "baseSqlSessionFactory")
public class MybatisBaseConfig {

    @Primary
    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource dataSource() {

        return DataSourceBuilder.create().build();

    }

    @Primary
    @Bean(name = "baseSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dataSource);
        //Mapper扫描位置
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:**/base/*.xml"));
        return factoryBean.getObject();
    }

    @Primary
    @Bean(name = "baseTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
