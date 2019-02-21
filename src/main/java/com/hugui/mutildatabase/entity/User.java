package com.hugui.mutildatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright © 2019 Obexx. All rights reserved.
 * 
 * @Title: User.java
 * @Prject: springboot-mutil-database
 * @Package: com.hugui.mutildatabase.entity
 * @Description: 
 * @author: HuGui
 * @date: 2019年2月20日 上午9:47:39
 * @version: V1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {

	private Long id;
	private String password;
	private String username;

}
