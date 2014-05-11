package com.cherrypicks.boc.common.performance;

import java.util.Date;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cherrypicks.boc.common.util.DateUtil;

/**
 * 功能描述：局部性能测试模板
 * 
 * @author carlye
 * 
 */
public abstract class PerformanceTemplete {
	protected Log logger = LogFactory.getLog(this.getClass());

	protected Vector<Thread> threads;
	protected int runCounts = 1000;// 运行次数
	protected int runThreads = 1;// 启动线程数
	protected String name = "";// 启动线程数

	public PerformanceTemplete(int runCounts, int runThreads) {
		this.runCounts = runCounts;
		this.runThreads = runThreads;
		threads = new Vector<Thread>(this.runThreads);
	}

	public PerformanceTemplete(int maxTime, int runThreads, int baseCount) {
		int maxTime2 = maxTime / runThreads;
		int baseAvg = this.baseAvg(baseCount);
		this.runCounts = baseAvg * maxTime2 / runThreads;
		String result = (DateUtil.parseDateToString(new Date(), "yyyy-MM-dd HH:mm:ss") + " " + name + " runCounts:" + runCounts + " baseAvg:" + baseAvg + " maxTime2:" + maxTime2);
		System.out.println(result);
		logger.info(result);
		this.runThreads = runThreads;
		threads = new Vector<Thread>(this.runThreads);
	}

	protected int baseAvg(int baseCount) {
		int size = baseCount;
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			this.runTest();
		}
		long stop = System.currentTimeMillis();
		long time = stop - start;
		int avg = (int) (size * 1000 / time);
		return avg;
	}

	// 用例测试
	protected abstract void runTest();

	// 启动一个线程
	public void startThread(final int index) {
		new Thread() {
			@Override
			public void run() {
				this.setName(String.valueOf(index));
				threads.add(this);
				// Clock clock = Clock.start();
				for (int count = 0; count < runCounts; count++) {
					runTest();
				}
				// System.out.println("线程[" + index + "]循环" + runCounts + "次用时:"
				// + clock.time());
				threads.remove(this);
			}
		}.start();
	}

	protected void start() {
		// this.runTest();

		long start = System.currentTimeMillis();
		for (int thread = 0; thread < this.runThreads; thread++) {
			this.startThread(thread);
		}

		for (int i = 0; i < 200000; i++) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int count = threads.size();
			// System.out.println("activeCount:" + count);
			if (count <= 0) {
				break;
			}
			// else{
			// System.out.println(threads.get(0).getName());
			// }
		}
		long end = System.currentTimeMillis();
		long time = end - start;
		int totalCount = this.runCounts * this.runThreads;

		double avg = (Double.parseDouble(totalCount + "") / Double.parseDouble(time + "") * 1000d);
		// System.out.println("time:" + time + " avg:" + (int) avg);

		String result = (DateUtil.getCurrentDateStr("yyyy-MM-dd HH:mm:ss") + " " + name + " threadCount:" + this.runThreads + " runCounts:" + (this.runCounts * this.runThreads) + " totalTime:" + time
				+ " avg:" + (int) avg);
		logger.info(result);
	}
}
