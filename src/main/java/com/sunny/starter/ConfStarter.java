package com.sunny.starter;

import com.sunny.processor.main.MainProcessor;

/**
 * 启动器<br>
 * 
 * @Author zsunny
 * @Date 2018/11/22 11:19
 * @Mail zsunny@yeah.net
 */
public class ConfStarter {

	public static void start(String pack) {
		MainProcessor.process(pack);
	}

}
