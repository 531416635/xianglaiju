package com.yao.test;

public class ClassTest {

	public static void main(String[] args) {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		System.out.println(path);
	}
}
