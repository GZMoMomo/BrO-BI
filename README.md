# BrO-BI
BI系统  
![tutieshi_640x381_22s](https://user-images.githubusercontent.com/91240419/186159678-69dbae76-3352-4240-b3bf-72ec24823759.gif)

![image](https://user-images.githubusercontent.com/91240419/186123082-0bc0e8b3-aac8-473b-a61b-15298ac7fa93.png)
![image](https://user-images.githubusercontent.com/91240419/186123165-32983a2b-2319-44f2-910a-c58090bbf03c.png)

##### 难点：
- 问题：数据源切换成功后，使用mybatis查询其他数据源数据时依旧停留在默认数据源，使用JDBC可以查询成功，猜想mybatis解析的时候没有解析到新的数据源配置。
- 问题：查完数据后没有迅速释放connection。
  - 这个报错跟spring.datasource.hikari.connection-timeout和spring.datasource.hikari.maximum-pool-size都有关系。
  - 连接是一种非常宝贵的资源，获取到connection之后，马上就应该开始执行SQL，执行完SQL之后马上就应该close。一定要尽快归还connection，这样也不影响其他业务的操作。
  - 同一个线程中多次getConnection，获取到的是不同的connection。
##### 复习一下流程：
![image](https://user-images.githubusercontent.com/91240419/185528084-73e6d0e6-36e4-436d-823c-2af1b0579efd.png)
1. mybatis是什么时候获取到数据源的呢？要从测试方法生成SqlSessionFactory说起。通过断点进入到SqlSessionFactoryBuilder的build方法中，方法体就两行关键代码，首先new了一个XML 配置生成器，接着调用了其parse()生成一个Configuration对象。
2. parser.evalNode会生成一个mybatis封装的XNode对象，copy后发现就是我们配置文件中<configuration>标签中的内容。
3. 进入到parseConfiguration方法中，可以看出好多方法的字符串参数都和我们<configuration>标签中的一些标签名称相同。没错，每一步都是去扫描到对应参数的标签内容从而进行一些配置处理。
4. 我们此处不研究其他处内容，直接看environmentsElement方法的内容。root.evalNode("environments")返回的XNode对象的value就是我们的environments标签内容。
5. 进入到environmentsElement方法中，会循环遍历下一级的environment，此处便是解析xml配置多数据源的地方。
6. dataSourceElement方法会拿到dataSource标签的内容生成一个DataSourceFactory ，并根据我们的配置给其属性赋值。通过getDataSource()方法便可以拿到我们的数据源。最后调用configuration.setEnvironment给到全局配置中的。
##### 解决：通过 DataSourceBuilder 去构建一个我们自定义的数据源，通过dataSourceBuilder.type(DynamicDataSource.class)注入我们的数据源到datasource，在build的时候会实例化我们的自定义数据源。

##  基础配置类
![5XmXbM1Mcw](https://user-images.githubusercontent.com/91240419/185086087-f842d987-084a-4af4-ad3b-944eac09181a.jpg)

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
17. mapper.xml
18. @Aspect   @Pointcut  JoinPoint   Signature
19. @Component
20. @RequiredArgsConstructor(onConstructor_ = @Autowired)
21. header请求头信息
22. @interface   @Target({ElementType.METHOD})  @Retention(RetentionPolicy.RUNTIME)    @Documented
23. RequestAttributes
24. ServletRequestAttributes
25. HttpServletRequest
