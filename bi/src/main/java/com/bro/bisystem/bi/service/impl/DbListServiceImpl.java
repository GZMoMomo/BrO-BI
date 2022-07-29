package com.bro.bisystem.bi.service.impl;

import com.bro.bisystem.bi.pojo.DbList;
import com.bro.bisystem.bi.mapper.DbListMapper;
import com.bro.bisystem.bi.service.IDbListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BrO
 * @since 2022-07-29
 */
@Service
public class DbListServiceImpl extends ServiceImpl<DbListMapper, DbList> implements IDbListService {

}
