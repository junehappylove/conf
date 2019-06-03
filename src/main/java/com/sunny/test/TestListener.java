package com.sunny.test;

import com.sunny.source.listener.ConfListener;

public class TestListener implements ConfListener {

	@Override
	public void doBefore() {
		System.out.println("before");
	}

	@Override
	public void doAfter() {
		System.out.println("after");
	}
}
