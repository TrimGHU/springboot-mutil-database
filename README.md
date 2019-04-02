# springboot-mutil-database
Springboot整合多数据源，AOP自由切换数据源，解决Transaction注解切换数据源不生效问题


### 原理
关于数据源的控制，在spring中留了AbstractRoutingDataSource接口供使用者控制，其determineCurrentLookupKey方法关联对应的datasource即可。
>**AbstractRoutingDataSource**

>Abstract javax.sql.DataSource implementation that routes getConnection() calls to one of various target DataSources based on a lookup key.The latter is usually (but not necessarily) determined through some thread-bound transaction context.


将数据源放入Spring容器中管理,通过@Configuration @Bean的定义将datasource1，datasource2加入容器中
并且在每个线程中定义ThreadLocal，控制每个线程的在决定使用哪个数据源时不产生冲突 **（记住必须在使用用对ThreanLocal进行clear，不然重复使用线程时有可能导致获取到旧的数据源配置）**

### 问题
##### 加入@Transaction后多数据源切换失败？先看下文的调用过程

### 调用过程
##### 无Transaction的AOP调用过程
>repository@Annotation(AOP)-->
DefaultSqlSession-->
SimpleExecutor-->
BaseExecutor.getConnection()-->
SpringManagedTransaction.getConnection()--->连接为空-->
AbstractRoutingDataSource.getConnection()-->
拿到beforeAOP中注入的datasource的key,所以每次都会动态切换数据源

##### 有Transaction的AOP调用过程
>TransactionInterceptor.interpter()-->
TransactionAspectSupport.createTransactionIfNecessary()-->
AbstractPlatformTransactionManager.getTransaction()-->
DataSourceTransactionManager.doBegin()-->
AbstractRoutingDataSource.determineTargetDataSource()[lookupKey==null去拿默认的Datasource,不为空则使用获取到的连接]-->
DataSourceTransactionManager.setTransactional()[将连接设置到TransactionUtils的threadLocal中]--->
Repository@Annotation-->执行AOP数据源调用链,
问题在于SpringManagedTransaction.getConnection()-->openConnection()-->
DataSourceUtils.getConnection()-->
TransactionSynchronizationManager.getResource(dataSource)不为空[从TransactionUtils的threadLocal中获取数据源],
这样发现不会再去调用DynamicDataSource去获取配置的动态数据源

##### 解决办法是：
在transaction interpter执行之前就把动态数据源配置好，所以在动态数据源的配置的AOP切片上加入Order(1),让其先执行即可。

### 测试加上@Transaction
> 启动服务执行run as SpringbootMutiDatabaseApplication

> http://localhost:8080/user/add1 访问数据源1，未加入@Transaction，后台报错Error，但是插入成功了！
![测试1](https://github.com/TrimGHU/springboot-mutil-database/blob/master/src/main/resources/static/add_ds1_before.png)
![测试2](https://github.com/TrimGHU/springboot-mutil-database/blob/master/src/main/resources/static/add_ds1_after.png)
![测试3](https://github.com/TrimGHU/springboot-mutil-database/blob/master/src/main/resources/static/add_ds1_error.png)

> http://localhost:8080/user/add2 访问数据源2，加入@Transaction，后台报错Error，数据库会滚成功！

[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)
