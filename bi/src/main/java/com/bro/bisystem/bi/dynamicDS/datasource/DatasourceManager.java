package com.bro.bisystem.bi.dynamicDS.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * <p>
 * 数据源管理类
 * </P>
 */
public class DatasourceManager {

    /**
     * 数据源
     */
    @Getter
    private HikariDataSource dataSource;

    /**
     * 上一次使用时间
     */
    private LocalDateTime lastUseTime;

    public DatasourceManager(HikariDataSource dataSource) {
        this.dataSource = dataSource;
        this.lastUseTime = LocalDateTime.now();
    }

    /**
     * 刷新上次使用时间
     */
    public void refreshTime() {
        this.lastUseTime = LocalDateTime.now();
    }
}
