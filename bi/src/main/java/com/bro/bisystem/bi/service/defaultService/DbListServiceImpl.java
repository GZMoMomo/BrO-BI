package com.bro.bisystem.bi.service.defaultService;



import com.bro.bisystem.bi.pojo.DbList;
import com.bro.bisystem.bi.mapper.DbListMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;



/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BrO
 * @since 2022-08-03
 */

@Service
public class DbListServiceImpl extends ServiceImpl<DbListMapper, DbList> implements IDbListService {

    @Override
    public Integer getAutoId(DbList dbList) {
        return null;
    }
}

