package com.duitang.service.demo;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.avro.ipc.NettyTransceiver;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.duitang.service.base.MetricCenter;
import com.duitang.service.base.ServerBootstrap;

public class CacheNettyServiceTest {

	ServerBootstrap boot = null;
	MemoryCacheClientFactory fac = null;

	@Before
	public void setUp() {
		LogManager.getLogger("org.apache.avro.ipc").setLevel(Level.ALL);
		LogManager.getLogger(NettyTransceiver.class.getName()).setLevel(Level.ALL);
		MemoryCacheService impl = new MemoryCacheService();
		boot = new ServerBootstrap();
		try {
			boot.startUp(DemoService.class, impl, 9090, "netty");
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		fac = new MemoryCacheClientFactory();
		fac.setUrl("netty://127.0.0.1:9090");
	}

	@After
	public void destroy() {
		boot.shutdown();
	}

	@Test
	public void testBoot() {
		DemoService cli = fac.create();
		try {
			String key = "aaaa";
			String value = "bbbb";
			System.out.println(cli.memory_setString(key, value, 1111));
			CharSequence sss = (CharSequence) cli.memory_getString(key);
			Assert.assertEquals(value, String.valueOf(sss));
			System.out.println(sss);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		} finally {
			fac.release(cli);
		}
		cli = fac.create();
		try {
			String key = "aaaa";
			String value = "bbbb";
			System.out.println(cli.memory_setString(key, value, 1111));
			CharSequence sss = (CharSequence) cli.memory_getString(key);
			Assert.assertEquals(value, String.valueOf(sss));
			System.out.println(sss);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		} finally {
			fac.release(cli);
		}

		long stime = 10L;
		// long stime = 10000000L;
		for (long i = 1000; i < stime; i += 1000) {
			try {
				// System.out.println(fac.isValid((Client) cli));
				Thread.sleep(1000);
				if (i > 5000) {
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// @Test
	public void testMetric() throws Exception {
		MetricCenter.enableConsoleReporter(1);
		DemoService cli = fac.create();
		for (int i = 0; i < 10; i++) {
			System.out.println("----->" + cli.trace_msg("wait_100", 100));
		}
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println("----->" + cli.trace_msg("wait_600", 600));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fac.release(cli);
		Thread.sleep(5000);
	}
}