package com.bro.bisystem.bi.controller;



import cn.hutool.core.lang.Dict;
import com.bro.bisystem.bi.pojo.DbList;
import com.bro.bisystem.bi.service.IDbListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Dict list(){
        List<DbList> list = iDbListService.list(null);
        return Dict.create().set("code",200).set("message","查询成功").set("list",list);
    }

}
