package com.duitang.service.karma.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RPCNodeHashingTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRawConnURL() {
		String u = "udp";
		String u1 = "abc.de:1234";
		String url = RPCNodeHashing.getRawConnURL(u + "://" + u1);
		Assert.assertTrue(url.equals(u1));

		String s = RPCNodeHashing.getRawConnSchema(u + "://" + u1);
		Assert.assertTrue(s.equals(u));

		String s1 = RPCNodeHashing.getSafeConnURL(u + "://" + u1);
		Assert.assertFalse(s1.contains("/"));
	}

	@Test
	public void testCompareTo() {
		List<String> nodes = Arrays.asList("aa:11", "bb:22", "cc:33", "dd:44");
		RPCNodeHashing a = RPCNodeHashing.createFromString(nodes);
		RPCNodeHashing b = RPCNodeHashing.createFromString(nodes);
		for (int i = 0; i < a.getNodes().size(); i++) {
			a.getNodes().get(i).up = b.getNodes().get(i).up;
		}

		boolean r = a.equals(b);
		Assert.assertTrue(r);

		RPCNode n1 = new RPCNode();
		n1.url = "aa:21";
		RPCNode n2 = new RPCNode();
		n2.url = "bb:12";

		Assert.assertTrue(n1.compareTo(n2) < 0);

	}

	@Test
	public void testGetNodes() {
		String[] hosts = { "aa:11", "cc:22", "bb:33" };
		RPCNodeHashing r1 = RPCNodeHashing.createFromString(Arrays.asList(hosts));
		try {
			RPCNodeHashing.createFromString(null);
			Assert.fail();
		} catch (Exception e) {

		}
		try {
			RPCNodeHashing.createFromString(new ArrayList<String>());
			Assert.fail();
		} catch (Exception e) {

		}

		LinkedHashMap<String, Float> h = new LinkedHashMap<>();
		h.put(hosts[0], 1f);
		h.put(hosts[1], 1f);
		h.put(hosts[2], 1f);
		RPCNodeHashing r2 = RPCNodeHashing.createFromHashMap(h);
		for (int i = 0; i < r2.getNodes().size(); i++) {
			r2.getNodes().get(i).up = r1.getNodes().get(i).up;
		}

		try {
			RPCNodeHashing.createFromHashMap(null);
			Assert.fail();
		} catch (Exception e) {

		}
		try {
			RPCNodeHashing.createFromHashMap(new LinkedHashMap<String, Float>());
			Assert.fail();
		} catch (Exception e) {

		}

		RPCNode n0 = new RPCNode();
		n0.url = hosts[0];
		n0.up = r1.getNodes().get(0).up;
		RPCNode n1 = new RPCNode();
		n1.url = hosts[1];
		n1.up = r1.getNodes().get(1).up;
		RPCNode n2 = new RPCNode();
		n2.url = hosts[2];
		n2.up = r1.getNodes().get(2).up;
		RPCNodeHashing r3 = RPCNodeHashing.createFromNodes(Arrays.asList(n0, n1, n2));
		for (int i = 0; i < r3.getNodes().size(); i++) {
			r3.getNodes().get(i).up = r1.getNodes().get(i).up;
		}
		try {
			RPCNodeHashing.createFromNodes(null);
			Assert.fail();
		} catch (Exception e) {

		}
		try {
			RPCNodeHashing.createFromNodes(new ArrayList<RPCNode>());
			Assert.fail();
		} catch (Exception e) {

		}

		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		Assert.assertTrue(r1.equals(r2));
		Assert.assertTrue(r3.equals(r2));

		Assert.assertTrue(r1.compareTo(r2) == 0);
		Assert.assertTrue(r3.compareTo(r2) == 0);

		System.out.println(r1.getNodes());
		System.out.println(r3.getURLs());

		RPCNodeHashing r4 = RPCNodeHashing.createFromNodes(Arrays.asList(n2, n1, n0));
		Assert.assertTrue(r4.equals(r3));
		Assert.assertTrue(r4.compareTo(r3) == 0);

		LinkedHashMap<String, Float> m = r4.reverseToMap();
		Set<String> s1 = new HashSet(h.keySet());
		Set<String> s2 = new HashSet(m.keySet());
		s1.removeAll(s2);
		Assert.assertTrue(s1.isEmpty());
		s1 = new HashSet(h.keySet());
		s2 = new HashSet(m.keySet());
		s2.removeAll(s1);
		Assert.assertTrue(s2.isEmpty());

		Assert.assertFalse(r4.equals("s1"));

		r4.toString();
	}

	@Test
	public void testNull() {
		try {
			RPCNodeHashing.createFromNodes(null);
			Assert.fail();
		} catch (Exception e) {

		}
		Assert.assertNull(RPCNodeHashing.getRawConnSchema(null));

		Assert.assertNull(RPCNodeHashing.getRawConnURL(null));

		try {
			RPCNodeHashing.createFromString(Arrays.asList("tcp://aa:11", "http://bb:22"));
			Assert.fail();
		} catch (Exception e) {

		}
		try {
			LinkedHashMap<String, Float> m = new LinkedHashMap<>();
			m.put("tcp://aa:11", 1f);
			m.put("http://bb:22", 1f);
			RPCNodeHashing.createFromHashMap(m);
			Assert.fail();
		} catch (Exception e) {

		}
		try {
			RPCNode a = new RPCNode();
			a.url = "tcp://aa:11";
			RPCNode b = new RPCNode();
			b.url = "http://bb:22";
			RPCNodeHashing.createFromNodes(Arrays.asList(a, b));
			Assert.fail();
		} catch (Exception e) {

		}

	}

}
