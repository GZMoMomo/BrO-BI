package com.bro.bisystem.bi.dynamicDS.config;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * <p>
 * mybatis配置
 * 这个类主要是将我们上一步构建出来的数据源配置到 Mybatis 的 SqlSessionFactory 里
 * </p>
 */
@Configuration
@MapperScan(basePackages = "com.bro.bisystem.bi.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfiguration {
    @Bean(name = "sqlSessionFactory")
    @SneakyThrows
    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
}
