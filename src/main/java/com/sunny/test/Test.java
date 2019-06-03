/**
 * 
 */
package com.sunny.test;

import com.sunny.starter.ConfStarter;

/**
 * @author junehappylove
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        ConfStarter.start();
        Example.printPort();
        ExampleConfClass.print();
        ExampleConfClassWithNotAnno.print();
	}

}
