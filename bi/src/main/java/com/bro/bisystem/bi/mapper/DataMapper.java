package com.bro.bisystem.bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bro.bisystem.bi.config.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface DataMapper extends MyMapper {

    List<Map<String,Object>> listDataById(@Param("tableName") String tableName,
                                          @Param("colNameList") List<String> colNameList);
}
