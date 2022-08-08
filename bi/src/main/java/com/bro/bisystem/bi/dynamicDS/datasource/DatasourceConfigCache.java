package com.bro.bisystem.bi.dynamicDS.datasource;

import com.bro.bisystem.bi.pojo.DbList;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 数据源配置缓存
 * </p>
 */
public enum DatasourceConfigCache {
    /**
     * 当前实例
     */
    INSTANCE;

    /**
     * 动态管理数据源列表
     */
    private static final Map<Long, DbList> CONFIG_CACHE = new ConcurrentHashMap<>();

    /**
     * 查询数据源配置
     *
     * @param id 数据源配置id
     * @return 数据源配置
     */
    public synchronized DbList getConfig(Long id) {
        if (CONFIG_CACHE.containsKey(id)) {
            return CONFIG_CACHE.get(id);
        }
        return null;
    }
}
