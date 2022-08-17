/*
package com.bro.bisystem.bi.dynamicDS.config;


import javax.sql.DataSource;

import com.bro.bisystem.bi.dynamicDS.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


*/
/**
 * <p>
 * 数据源配置
 * 这个类主要是通过 DataSourceBuilder 去构建一个我们自定义的数据源，将其放入 Spring 容器里
 * </p>
 *//*


@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(DynamicDataSource.class);
        return dataSourceBuilder.build();
    }
}
*/

