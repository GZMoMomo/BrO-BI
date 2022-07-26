package com.bro.bisystem.bi.dynamicDS.datasource;

import com.zaxxer.hikari.HikariDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 数据源管理
 * </P>
 */
public enum DatasourceHolder {

    /**
     * 当前实例
     */
    INSTANCE;

    /**
     * 管理动态数据源列表
     */
    private static final Map<Long, DatasourceManager> DATASOURCE_CACHE = new ConcurrentHashMap<>();

    /**
     * 启动执行，定时5分钟清理一次
     */
    DatasourceHolder() {
        DatasourceScheduler.INSTANCE.schedule(this::clearExpireDatasource,5*60*1000);
    }

    /**
     * 默认数据源ID
     */
    public static final Long DEFAULT_ID = -1L;

    /**
     * 查询动态数据源
     *
     * @param id 数据源id
     * @return 数据源
     */
    public synchronized HikariDataSource getDatasource(Long id) {
        if (DATASOURCE_CACHE.containsKey(id)) {
            DatasourceManager datasourceManager = DATASOURCE_CACHE.get(id);
            datasourceManager.refreshTime();
            return datasourceManager.getDataSource();
        }
        return null;
    }

    /**
     * 添加动态数据源
     *
     * @param id         数据源id
     * @param dataSource 数据源
     */
    public synchronized void addDatasource(Long id, HikariDataSource dataSource) {
        DatasourceManager datasourceManager = new DatasourceManager(dataSource);
        DATASOURCE_CACHE.put(id, datasourceManager);
    }

    /**
     * 清除超时的数据源
     */
    public synchronized void clearExpireDatasource(){
        DATASOURCE_CACHE.forEach((k,v)->{
            //排除默认数据源
            if(!DEFAULT_ID.equals(k)){
                if(v.isExpired()){
                    DATASOURCE_CACHE.remove(k);
                }
            }
        });
    }

    /**
     * 清除动态数据源
     * @param id 数据源id
     */
    public void removeDatasource(Long id) {
        if(DATASOURCE_CACHE.containsKey(id)){
            DATASOURCE_CACHE.get(id).getDataSource().close();
            DATASOURCE_CACHE.remove(id);
        }
    }
}
