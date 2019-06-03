package com.sunny.test;

import com.sunny.annotation.ConfPath;
import com.sunny.annotation.ConfSource;
import com.sunny.annotation.SystemConfPath;

/**
 * create by zsunny
 * data: 2018/8/11
 **/
@ConfSource("configer.properties")
public class Example {

	@ConfPath("other.file.configer")
	private static String other;

    @ConfPath("server.port")
    private static String port;

    @ConfPath("test.example.b")
    private static String test;

    @SystemConfPath("system.conf.active")
    private static String active;

	@ConfPath("test.not.string")
    private static int thisIsAInteger = 100;

    public static void printPort(){
        System.out.println("other:" + other);
        System.out.println("prop-port:" + port);
        System.out.println("prop-test:" + test);
        System.out.println("prop-active:" + active);
        System.out.println("thisIsAInteger:" + thisIsAInteger);
    }

}
