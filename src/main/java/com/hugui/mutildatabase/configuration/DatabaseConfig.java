package com.hugui.mutildatabase.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Copyright © 2019 Obexx. All rights reserved.
 * 
 * @Title: DatabaseConfig.java
 * @Prject: springboot-mutil-database
 * @Package: com.hugui.mutildatabase.configuration
 * @Description: 
 * @author: HuGui
 * @date: 2019年2月20日 下午2:20:22
 * @version: V1.0
 */

@Configuration
public class DatabaseConfig {

	@Bean(name = "datasource1")
	@ConfigurationProperties(prefix = "spring.datasource.db1")
	public DataSource dataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "datasource2")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}

	
	/**
	 * 动态数据源: 通过AOP在不同数据源之间动态切换
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Primary
	@Bean(name = "dynamicDataSource")
	public DataSource dynamicDataSource() {
		DynamicDatabase dynamicDataSource = new DynamicDatabase();
		// 默认数据源
		dynamicDataSource.setDefaultTargetDataSource(dataSource1());
		// 配置多数据源
		Map<Object, Object> dsMap = new HashMap();
		dsMap.put("datasource1", dataSource1());
		dsMap.put("datasource2", dataSource2());

		dynamicDataSource.setTargetDataSources(dsMap);
		return dynamicDataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dynamicDataSource());
	}

}
