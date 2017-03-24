package com.yao.security;

import org.springframework.security.core.AuthenticationException;

public class MyAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	public MyAuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}