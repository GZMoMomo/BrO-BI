package com.bro.bisystem.bi.dynamicDS.aspect;

import com.bro.bisystem.bi.dynamicDS.annotation.DefaultDatasource;
import com.bro.bisystem.bi.dynamicDS.datasource.DatasourceConfigContextHolder;
import com.bro.bisystem.bi.service.dService.IDBDetailService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * <p>
 *  数据源选择器切面
 * </p>
 */
@Aspect
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DatasourceSelectAspect {

    @Pointcut("execution(public * com.bro.bisystem.bi.controller.*.*(..))")
    public void datasourcePointcut(){

    }

    /**
     * 前置操作，拦截具体请求，获取header里的数据源id，设置线程变量里，用于后续切换数据源
     *
     */
    @Before("datasourcePointcut()")
    public void doBefore(JoinPoint joinPoint){
        Signature signature=joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method=methodSignature.getMethod();

        //排除不可切换数据源的方法
        DefaultDatasource annotation=method.getAnnotation(DefaultDatasource.class);
        if(null != annotation){
            DatasourceConfigContextHolder.setDefaultDatasource();
        }else{
            RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes attributes=(ServletRequestAttributes)requestAttributes;
            HttpServletRequest request = attributes.getRequest();
            String configIdInHeader=request.getHeader("Config-Id");
            if(StringUtils.hasText(configIdInHeader)){
                long configId=Long.parseLong(configIdInHeader);
                DatasourceConfigContextHolder.setCurrentDatasourceConfig(configId);
            }else{
                DatasourceConfigContextHolder.setDefaultDatasource();
            }
        }
    }

    /**
     * 后置操作，设置回默认的数据源id
     */
    @AfterReturning("datasourcePointcut()")
    public void doAfter(){
      DatasourceConfigContextHolder.setDefaultDatasource();

    }
}
