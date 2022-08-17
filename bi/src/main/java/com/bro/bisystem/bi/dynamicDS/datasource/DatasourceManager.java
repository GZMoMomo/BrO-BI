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
     * 默认释放时间
     */
    private static final long DEFAULT_RELEASE = 10L;

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

    /**
     * 是否已过期，如果过期则关闭数据源
     * @return 是否过期，{@code true} 过期，{@code false} 未过期
     */
    public boolean isExpired() {
        if(LocalDateTime.now().isBefore(this.lastUseTime.plusMinutes(DEFAULT_RELEASE))){
            return false;
        }
        this.dataSource.close();
        return true;
    }
}
