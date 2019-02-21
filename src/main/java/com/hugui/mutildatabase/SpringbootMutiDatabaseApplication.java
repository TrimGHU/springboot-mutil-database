package com.hugui.mutildatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Copyright © 2019 Obexx. All rights reserved.
 * 
 * @Title: SpringbootMutiDatabaseApplication.java
 * @Prject: springboot-mutil-database
 * @Package: com.hugui.mutildatabase
 * @Description: 
 * @author: HuGui
 * @date: 2019年2月19日 下午5:33:40
 * @version: V1.0
 */

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SpringbootMutiDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMutiDatabaseApplication.class, args);
	}

}
