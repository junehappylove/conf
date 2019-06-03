package com.sunny.processor.main;

import java.util.ArrayList;
import java.util.List;

import com.sunny.processor.ConfClassProcessor;
import com.sunny.processor.ConfListenerProcessor;
import com.sunny.processor.ConfProcessor;
import com.sunny.processor.ConfValueProcessor;
import com.sunny.source.LoadResult;
import com.sunny.source.listener.ConfListener;

public class MainProcessor {

	private static List<ConfListener> confListeners = new ArrayList<>();
	private static List<Class<? extends ConfProcessor>> confProcessors = new ArrayList<>();

	// processor处理
	static {
		try {
			LoadResult.loadResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ConfListenerProcessor().process();
		confProcessors.add(ConfValueProcessor.class);
		confProcessors.add(ConfClassProcessor.class);
	}

	public static void addListener(ConfListener confListener) {
		confListeners.add(confListener);
	}

	public static void addProcessor(Class<? extends ConfProcessor> confProcessor) {
		confProcessors.add(confProcessor);
	}

	public static void process() {
		confListeners.forEach(ConfListener::doBefore);
		// do at 20190305
		confProcessors.forEach(confProcessor -> {
			try {
				confProcessor.newInstance().process();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		confListeners.forEach(ConfListener::doAfter);
	}

}
