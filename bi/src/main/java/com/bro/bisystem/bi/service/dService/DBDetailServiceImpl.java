
package com.bro.bisystem.bi.service.dService;

import com.bro.bisystem.bi.dynamicDS.datasource.DynamicDataSource;
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
    public Connection getConnection() throws SQLException {
        /*Long configId=Long.parseLong(id);
        DatasourceConfigContextHolder.setCurrentDatasourceConfig(configId);*/
        DynamicDataSource dynamicDataSource=new DynamicDataSource();
        Connection c=dynamicDataSource.getConnection();
        return c;
    }

    @Override
    public List<String> getTables(String catalog) throws SQLException {
        DatabaseMetaData databaseMetaData=getConnection().getMetaData();
        List<String> tables=new ArrayList<>();
        ResultSet resultSet=databaseMetaData.getTables(catalog,null,null,null);
        while (resultSet.next()){
            tables.add(resultSet.getString(3));
        }
        return tables;
    }

    @Override
    public Map<String, String> getColumns(String catalog, String table) throws SQLException {
        DatabaseMetaData databaseMetaData=getConnection().getMetaData();
        Map<String,String> columns=new HashMap<>();
        ResultSet resultSet=databaseMetaData.getColumns(catalog,null,table,null);
        while (resultSet.next()){
            columns.put(resultSet.getString(4),resultSet.getString(6));
        }
        return columns;
    }

    @Override
    public List<String> getAllColumns(String catalog, String table) throws SQLException {
        DatabaseMetaData databaseMetaData=getConnection().getMetaData();
        List<String> columns=new ArrayList<>();
        ResultSet resultSet=databaseMetaData.getColumns(catalog,null,table,null);
        while (resultSet.next()){
            columns.add(resultSet.getString(4));
        }
        return columns;
    }
}
