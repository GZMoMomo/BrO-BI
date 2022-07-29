package com.bro.bisystem.bi.mapper;

import com.bro.bisystem.bi.pojo.DbList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author BrO
 * @since 2022-07-29
 */
@Component(value = "DbListMapper")
public interface DbListMapper extends BaseMapper<DbList> {
}
