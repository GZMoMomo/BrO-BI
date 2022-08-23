package com.bro.bisystem.bi;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.bro.bisystem.bi.dynamicDS.datasource.DatasourceConfigCache;
import com.bro.bisystem.bi.dynamicDS.datasource.DatasourceConfigContextHolder;
import com.bro.bisystem.bi.dynamicDS.datasource.DynamicDataSource;
import com.bro.bisystem.bi.mapper.DataMapper;
import com.bro.bisystem.bi.mapper.DbListMapper;
import com.bro.bisystem.bi.pojo.DbList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  启动器
 * </p>
 */
@SpringBootApplication
//@MapperScan("com.bro.bisystem.bi.mapper")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BiApplication implements CommandLineRunner {

    private final DbListMapper dbListMapper;

    public static void main(String[] args) {
        SpringApplication.run(BiApplication.class, args);
    }

    @Override
    public void run(String... args) throws SQLException {
        //设置默认数据源
        DatasourceConfigContextHolder.setDefaultDatasource();
        //查询所有数据源配置列表
        List<DbList> dataSourceConfigs=dbListMapper.selectList(null);
        System.out.println("加载其余数据源配置列表："+dataSourceConfigs);
        //将数据库配置加入缓存
        dataSourceConfigs.forEach(config-> DatasourceConfigCache.INSTANCE.addConfig(config.getId(),config));


    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return factory;
    }
}
