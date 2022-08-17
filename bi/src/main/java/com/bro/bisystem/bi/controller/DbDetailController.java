package com.bro.bisystem.bi.controller;


import cn.hutool.core.lang.Dict;
import com.bro.bisystem.bi.dynamicDS.datasource.DynamicDataSource;
import com.bro.bisystem.bi.service.IDBDetailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author BrO
 * @since 2022-07-29
 */
@RestController
@RequestMapping("/db-detail")
public class DbDetailController {
    @Autowired
    IDBDetailService idbDetailService;

    //返回指定数据库下的表目录
    @RequestMapping("/listTables")
    public Dict listTables(@RequestParam(value = "id") String id,@RequestParam(value = "catalog") String catalog) throws SQLException {
        List<String> tables=idbDetailService.getTables(id,catalog);
        return Dict.create().set("code",200).set("message","查询成功").set("tables",tables);
    }

    //返回指定表下的字段目录
    @RequestMapping("/listColumns")
    public Dict listColumns(@RequestParam(value = "id") String id,@RequestParam("catalog") String catalog,@RequestParam("table") String table ) throws SQLException {
        Map<String,String> columns=idbDetailService.getColumns(id,catalog,table);
        return Dict.create().set("code",200).set("message","查询成功").set("columns",columns);
    }
}
