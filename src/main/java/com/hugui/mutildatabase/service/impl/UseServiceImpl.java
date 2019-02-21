package com.hugui.mutildatabase.service.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hugui.mutildatabase.annotation.MyDataSource;
import com.hugui.mutildatabase.entity.User;
import com.hugui.mutildatabase.mapper.UserMapper;
import com.hugui.mutildatabase.service.IUserService;

/**
 * Copyright © 2019 Obexx. All rights reserved.
 * 
 * @Title: UseService.java
 * @Prject: springboot-mutil-database
 * @Package: com.hugui.mutildatabase.service.impl
 * @Description:
 * @author: HuGui
 * @date: 2019年2月20日 下午8:22:33
 * @version: V1.0
 */

@Service
public class UseServiceImpl implements IUserService {

	@Autowired
	private UserMapper mapper;

	@MyDataSource("datasource2")
	@Transactional
	@Override
	public List<User> findAll2() {
		return mapper.findAll();
	}

	@MyDataSource
	@Transactional
	@Override
	public List<User> findAll1() {
		return mapper.findAll();
	}

	@SuppressWarnings("unused")
	@MyDataSource("datasource2")
	@Transactional
	@Override
	public Long add2(boolean isError, String password, String username) {
		Long id = mapper.add(password, username);
		throw new RuntimeErrorException(new Error("error!!!!!"));
	}

	@SuppressWarnings("unused")
	@MyDataSource
	@Transactional
	@Override
	public Long add1(boolean isError, String password, String username) {
		Long id = mapper.add(password, username);
		throw new RuntimeErrorException(new Error("error!!!!!"));
	}
}
