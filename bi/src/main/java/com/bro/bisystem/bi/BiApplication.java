package com.bro.bisystem.bi;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.bro.bisystem.bi.dynamicDS.datasource.DatasourceConfigCache;
import com.bro.bisystem.bi.dynamicDS.datasource.DatasourceConfigContextHolder;
import com.bro.bisystem.bi.dynamicDS.datasource.DynamicDataSource;
import com.bro.bisystem.bi.mapper.DbListMapper;
import com.bro.bisystem.bi.pojo.DbList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 *  启动器
 * </p>
 */
@SpringBootApplication
@MapperScan("com.bro.bisystem.bi.mapper")
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
}
