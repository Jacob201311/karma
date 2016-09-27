package com.duitang.service.karma.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class MetricUnitTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void test() {
    MetricUnit u = new MetricUnit("mandala.somemethod");

    for (int i = 0; i < 100; i++) {
      u.record(i);
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    Map m = u.sample();
    System.out.println(m);

  }

}
