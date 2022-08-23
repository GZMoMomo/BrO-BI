package com.bro.bisystem.bi.service.defaultService;


import com.bro.bisystem.bi.pojo.DbList;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  数据源信息服务类
 * </p>
 *
 * @author BrO
 * @since 2022-08-03
 */

public interface IDbListService extends IService<DbList> {
    Integer getAutoId(DbList dbList);
}

