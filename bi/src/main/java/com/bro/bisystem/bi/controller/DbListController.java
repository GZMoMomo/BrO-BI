package com.bro.bisystem.bi.controller;



import cn.hutool.core.lang.Dict;
import com.bro.bisystem.bi.dynamicDS.datasource.DatasourceConfigContextHolder;
import com.bro.bisystem.bi.dynamicDS.datasource.DynamicDataSource;
import com.bro.bisystem.bi.pojo.DbList;
import com.bro.bisystem.bi.service.IDBDetailService;
import com.bro.bisystem.bi.service.IDbListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author BrO
 * @since 2022-07-29
*/

@RestController
@RequestMapping("/db-list")
public class DbListController {
   @Autowired
    IDbListService iDbListService;

    @RequestMapping("/list")
    public Dict list() throws SQLException {
        List<DbList> list = iDbListService.list(null);
        return Dict.create().set("code",200).set("message","查询成功").set("list",list);
    }

    @RequestMapping("/add")
    public Dict add(DbList dbList){
        iDbListService.save(dbList);
        return Dict.create().set("code",200).set("message","添加成功");
    }

    @RequestMapping("/update")
    public Dict update(DbList dbList){
        iDbListService.updateById(dbList);
        return Dict.create().set("code",200).set("message","更改成功");
    }

    @RequestMapping("/delete")
    public Dict delete(DbList dbList){
        iDbListService.removeById(dbList);
        return Dict.create().set("code",200).set("message","删除成功");
    }


}
