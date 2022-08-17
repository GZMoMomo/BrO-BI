package com.bro.bisystem.bi.dynamicDS.datasource;

import cn.hutool.extra.spring.SpringUtil;
import com.bro.bisystem.bi.pojo.DbList;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 * 动态数据源
 * 该类继承 com.zaxxer.hikari.HikariDataSource，主要用于动态切换数据源连接。
 * </p>
 */
public class DynamicDataSource extends HikariDataSource {
    @Override
    public Connection getConnection() throws SQLException {
        //获取当前数据源id
        Long id = DatasourceConfigContextHolder.getCurrentDatasourceConfig();
        //根据当前id获取数据源
        HikariDataSource dataSource = DatasourceHolder.INSTANCE.getDatasource(id);
        if (null == dataSource) {
            dataSource = initDatasource(id);
        }
        return dataSource.getConnection();
    }

    /**
     * 初始化数据源
     *
     * @param id 数据源id
     * @return 数据源
     */
    private HikariDataSource initDatasource(Long id) {
        HikariDataSource dataSource = new HikariDataSource();

        //判断是否是默认数据源
        if (DatasourceHolder.DEFAULT_ID.equals(id)) {
            //默认数据源根据application.yml配置的生成
            DataSourceProperties properties = SpringUtil.getBean(DataSourceProperties.class);
            dataSource.setJdbcUrl(properties.getUrl());
            dataSource.setUsername(properties.getUsername());
            dataSource.setPassword(properties.getPassword());
            dataSource.setDriverClassName(properties.getDriverClassName());
        } else {
            //不是默认数据源，通过缓存获取对应id的数据源的配置
            DbList dbList = DatasourceConfigCache.INSTANCE.getConfig(id);
            if (dbList == null) {
                throw new RuntimeException("无此数据源");
            }
            dataSource.setJdbcUrl(dbList.buildJdbcUrl());
            dataSource.setUsername(dbList.getUsername());
            dataSource.setPassword(dbList.getPassword());
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        }
        //将创建的数据源添加到数据源管理器中，绑定当前线程
        DatasourceHolder.INSTANCE.addDatasource(id, dataSource);
        return dataSource;
    }
}
