package com.hugui.mutildatabase.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hugui.mutildatabase.entity.User;

/**
 * Copyright © 2019 Obexx. All rights reserved.
 * 
 * @Title: UserMapper.java
 * @Prject: springboot-mutil-database
 * @Package: com.hugui.mutildatabase.mapper
 * @Description:
 * @author: HuGui
 * @date: 2019年2月20日 下午1:48:47
 * @version: V1.0
 */

@Mapper
public interface UserMapper {

	List<User> findAll();

	Long add(@Param("password") String password, @Param("username") String username);
}
