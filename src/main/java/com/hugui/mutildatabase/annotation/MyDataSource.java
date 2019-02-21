package com.hugui.mutildatabase.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright © 2019 Obexx. All rights reserved.
 * 
 * @Title: DataSource.java
 * @Prject: springboot-mutil-database
 * @Package: com.hugui.mutildatabase.annotation
 * @Description: 
 * @author: HuGui
 * @date: 2019年2月20日 下午3:12:46
 * @version: V1.0
 */

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyDataSource {

	String value() default "datasource1";

}
