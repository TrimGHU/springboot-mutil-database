package com.hugui.mutildatabase.configuration;

/**
 * Copyright © 2019 Obexx. All rights reserved.
 * 
 * @Title: DatabaseHolder.java
 * @Prject: springboot-mutil-database
 * @Package: com.hugui.mutildatabase.configuration
 * @Description: 
 * @author: HuGui
 * @date: 2019年2月20日 下午2:55:41
 * @version: V1.0
 */

public class DatabaseHolder {

	// ThreadLocal每个线程都独有的保存其线程所属的变量值
	private static ThreadLocal<String> holder = new ThreadLocal<>();

	public static void setDatabaseSource(String ds) {
		holder.set(ds);
	}

	public static String getDatabaseSource() {
		return holder.get();
	}

	public static void clearDatabaseSource() {
		holder.remove();
	}
}
