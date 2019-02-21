package com.hugui.mutildatabase.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Copyright © 2019 Obexx. All rights reserved.
 * 
 * @Title: DynamicDatabase.java
 * @Prject: springboot-mutil-database
 * @Package: com.hugui.mutildatabase.configuration
 * @Description: 
 * @author: HuGui
 * @date: 2019年2月20日 下午3:06:13
 * @version: V1.0
 */

public class DynamicDatabase extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseHolder.getDatabaseSource();
	}

}
