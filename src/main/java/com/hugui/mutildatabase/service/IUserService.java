package com.hugui.mutildatabase.service;

import java.util.List;

import com.hugui.mutildatabase.entity.User;

/**
 * Copyright © 2019 Obexx. All rights reserved.
 * 
 * @Title: IUserService.java
 * @Prject: springboot-mutil-database
 * @Package: com.hugui.mutildatabase.service
 * @Description: 
 * @author: HuGui
 * @date: 2019年2月20日 下午8:22:07
 * @version: V1.0
 */

public interface IUserService{

	List<User> findAll1();
	
	List<User> findAll2();
	
	Long add1(boolean isError, String password , String username);
	
	Long add2(boolean isError, String password , String username);
}
