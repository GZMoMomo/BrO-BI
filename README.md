# BrO-BI
BI系统

# 基础配置类(mybatis-plus已经集成了)
## DatasourceConfiguration.java
这个类主要是通过 DataSourceBuilder 去构建一个我们自定义的数据源，将其放入 Spring 容器里
## MybatisConfiguration.java
这个类主要是将我们上一步构建出来的数据源配置到 Mybatis 的 SqlSessionFactory 里

# 动态数据源主要逻辑
## DatasourceConfigContextHolder.java
该类主要用于绑定当前线程所使用的数据源 id，通过 ThreadLocal 保证同一线程内不可被修改
