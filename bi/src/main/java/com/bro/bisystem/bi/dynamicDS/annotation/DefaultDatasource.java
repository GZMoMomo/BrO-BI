package com.bro.bisystem.bi.dynamicDS.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 用户标识仅可以使用默认数据源
 * </p>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefaultDatasource {
}
