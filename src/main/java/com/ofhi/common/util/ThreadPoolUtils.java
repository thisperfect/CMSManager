package com.ofhi.common.util;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


public class ThreadPoolUtils {
	private ThreadPoolUtils(){}
	 
	public static void execByThread(Runnable runnale) {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = ApplicationContextHolder.getBean("threadPoolTaskExecutor");
		threadPoolTaskExecutor.execute(runnale);
	}
}
