package com.bro.bisystem.bi.controller;


import cn.hutool.core.lang.Dict;
import com.bro.bisystem.bi.mapper.DataMapper;
import com.bro.bisystem.bi.service.dService.IDBDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    DataMapper dataMapper;

    //返回指定数据库下的表目录
    @RequestMapping("/listTables")
    public Dict listTables(@RequestParam(value = "catalog") String catalog) throws SQLException {
        List<String> tables=idbDetailService.getTables(catalog);
        return Dict.create().set("code",200).set("message","查询成功").set("tables",tables);
    }

    //返回指定表下的字段目录
    @RequestMapping("/listColumns")
    public Dict listColumns(@RequestParam("catalog") String catalog,@RequestParam("table") String table ) throws SQLException {
        Map<String,String> columns=idbDetailService.getColumns(catalog,table);
        return Dict.create().set("code",200).set("message","查询成功").set("columns",columns);
    }

    //查找所有数据
    @RequestMapping("getAllDatas")
    public Dict getAllDatas(@RequestParam("catalog") String catalog,@RequestParam("table") String table) throws SQLException {
        List<String> columns= idbDetailService.getAllColumns(catalog,table);
        List<Map<String, Object>> datas = dataMapper.listDataById(table,columns);
        return Dict.create().set("code",200).set("message","查询成功").set("datas",datas);
    }

    //根据列名和表名查找数据
    @RequestMapping("/getDatas")
    public Dict getDatas(@RequestParam("table") String table,@RequestParam("colNameList") List<String> colNameList){
        List<Map<String, Object>> datas = dataMapper.listDataById(table,colNameList);
        return Dict.create().set("code",200).set("message","查询成功").set("partdatas",datas);
    }
}
