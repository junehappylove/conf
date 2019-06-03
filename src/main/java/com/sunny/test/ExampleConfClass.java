package com.sunny.test;

import com.sunny.annotation.ConfClass;
import com.sunny.annotation.ConfClassDefault;
import com.sunny.annotation.ConfClassIgnore;
import com.sunny.annotation.ConfClassPrefix;

@ConfClass
@ConfClassPrefix("test.example.")
public class ExampleConfClass {

	private static String a;
	private static String b = "2";

	@ConfClassIgnore
	private static String c;

	@ConfClassDefault("ddddd")
	private static String d;

	public static void print() {
		System.out.println("class-a:" + a);
		System.out.println("class-b:" + b);
		System.out.println("class-c-null:" + c);
		System.out.println("class-d-default:" + d);
	}
}
