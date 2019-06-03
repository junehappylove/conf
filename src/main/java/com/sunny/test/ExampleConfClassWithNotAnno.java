package com.sunny.test;

import com.sunny.annotation.ConfClassDefault;
import com.sunny.annotation.ConfClassIgnore;
import com.sunny.annotation.ConfClassPrefix;
import com.sunny.annotation.ConfPath;
import com.sunny.annotation.ConfSource;

@ConfSource
@ConfClassPrefix("test.exampleWithNotAnno")
public class ExampleConfClassWithNotAnno {

    private static String a2;
    private static String b2 = "2";

    @ConfClassIgnore
    @ConfPath("no.conf.class.anno")
    private static String c2;

    @ConfClassDefault("ddddd")
    private static String d2;


    public static void print(){
        System.out.println("class-a2:" + a2);
        System.out.println("class-b2:" + b2);
        System.out.println("class-c2:" + c2);
        System.out.println("class-d2:" + d2);
    }
}
