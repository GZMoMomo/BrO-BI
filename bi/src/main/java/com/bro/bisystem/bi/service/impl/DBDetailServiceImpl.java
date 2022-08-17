
package com.bro.bisystem.bi.service.impl;

import com.bro.bisystem.bi.dynamicDS.datasource.DatasourceConfigContextHolder;
import com.bro.bisystem.bi.dynamicDS.datasource.DynamicDataSource;
import com.bro.bisystem.bi.service.IDBDetailService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BrO
 * @since 2022-08-03
 */
@Service
public class DBDetailServiceImpl implements IDBDetailService {

    @Override
    public Connection getConnection(String id) throws SQLException {
        Long configId=Long.parseLong(id);
        DatasourceConfigContextHolder.setCurrentDatasourceConfig(configId);
        DynamicDataSource dynamicDataSource=new DynamicDataSource();
        Connection c=dynamicDataSource.getConnection();
        return c;
    }

    @Override
    public List<String> getTables(String id,String catalog) throws SQLException {
        DatabaseMetaData databaseMetaData=getConnection(id).getMetaData();
        List<String> tables=new ArrayList<>();
        ResultSet resultSet=databaseMetaData.getTables(catalog,null,null,null);
        while (resultSet.next()){
            tables.add(resultSet.getString(3));
        }
        return tables;
    }

    @Override
    public Map<String, String> getColumns(String id,String catalog, String table) throws SQLException {
        DatabaseMetaData databaseMetaData=getConnection(id).getMetaData();
        Map<String,String> columns=new HashMap<>();
        ResultSet resultSet=databaseMetaData.getColumns(catalog,null,table,null);
        while (resultSet.next()){
            columns.put(resultSet.getString(4),resultSet.getString(6));
        }
        return columns;
    }
}
