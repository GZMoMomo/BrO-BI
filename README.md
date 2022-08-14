# BrO-BI
BI系统

##  基础配置类(mybatis-plus已经集成了)
#### DatasourceConfiguration.java
- 这个类主要是通过 DataSourceBuilder 读取application.yml文件的数据库信息去构建一个我们自定义的数据源，将其放入 Spring 容器里
#### MybatisConfiguration.java
- 这个类主要是将我们上一步构建出来的数据源配置到 Mybatis 的 SqlSessionFactory 里（关联mapper）

## 动态数据源主要逻辑
#### DatasourceConfigContextHolder.java
- 该类主要用于绑定当前线程所使用的数据源 id，通过 ThreadLocal 保证同一线程内不可被修改（主要是存储数据源的ID值）
#### DynamicDataSource.java
- 该类继承 com.zaxxer.hikari.HikariDataSource，主要用于动态切换数据源连接。（主要是根据数据源ID值获取数据库连接、如果当前数据源ID的数据源不在DatasourceHolder内，则从DatasourceConfigCache里根据ID获取数据源再加入到DatasourceHolder）
#### DatasourceScheduler.java
- 该类主要用于调度任务（调度器）
#### DatasourceManager.java
- 该类主要用于管理数据源，记录数据源最后使用时间，同时判断是否长时间未使用，超过一定时间未使用，会被释放连接（内含HikariDataSource实际存放数据源）
#### DatasourceHolder.java
- 该类主要用于管理数据源，同时通过 DatasourceScheduler 定时检查数据源是否长时间未使用，超时则释放连接（根据id管理DatasourceManager中的数据源、使用调度器调度清理任务）
#### DatasourceConfigCache.java
- 该类主要用于缓存数据源的配置，用户生成数据源时，获取数据源连接参数（存放着数据源配置信息）


-----------------------------------------------------  
## 知识点
1. DataSourceBuilder
2. ConfigurationProperties
3. SneakyThrows
4. sqlSessionFactoryRef & SqlSessionFactory
5. Map & ConcurrentHashMap
6. ThreadLocal
ThreadLocal.withInitial(() -> DatasourceHolder.DEFAULT_ID);
7. enum
8. HikariDataSource
9. synchronized
10. LocalDateTime
11. AtomicInteger & getAndIncrement()
12. ScheduledExecutorService & scheduleAtFixedRate()
13. ScheduledThreadPoolExecutor
14. RequiredArgsConstructor
15. CommandLineRunner
16. @GeneratedValue
