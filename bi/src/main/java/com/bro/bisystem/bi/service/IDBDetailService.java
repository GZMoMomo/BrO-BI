package com.bro.bisystem.bi.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  每个数据源下具体数据库服务类
 * </p>
 *
 * @author BrO
 * @since 2022-08-03
 */
public interface IDBDetailService {
    //获取数据源连接Connecion
    Connection getConnection(String id) throws SQLException;

    //获取指定数据库下的表目录
    List<String> getTables(String id,String catalog) throws SQLException;

    //返回指定表下的字段目录
    Map<String, String> getColumns(String id,String catalog, String table) throws SQLException;
}
