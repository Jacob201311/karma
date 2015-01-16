package com.duitang.service.karma.support;

import java.util.List;
import java.util.Observable;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.google.common.collect.Lists;

/**
 * RPC配置
 * 
 * @author kevx
 * @since 5:27:23 PM Jan 13, 2015
 */
public class RpcClientConfig extends Observable implements Watcher {

	private String appName;
	private String group;
	private String connString;
	private boolean usingStaticRpcEndpoint;
	private String staticRpcEndpoint;
	private ZooKeeper zk;
	private List<String> children = null;
	
	boolean zkFailed() {
		return zk == null || !zk.getState().isAlive();
	}
	
	private void reset() {
		if (zkFailed()) {
			try {
				zk = new ZooKeeper(connString, 3000, this);
			} catch (Exception e) {
			}
		}
	}
	
	
	public void init() {
		reset();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					//每10秒检测一次zk是否挂掉，如果挂掉就重新生成
					try {
						Thread.sleep(1000 * 10);
						if (zkFailed()) {
							reset();
						}
						List<String> c = zk.getChildren("/app/" + appName, false);
						if (c == null || c.size() == 0) continue;
						if (children == null) {
							children = Lists.newArrayList();
							if (c != null) children.addAll(c);
							continue;
						}
						if (c.size() != children.size() || !c.containsAll(children)) {
							setChanged();
							notifyObservers();
							children.clear();
							children.addAll(c);
						}
					} catch (Throwable e) {
						System.out.println(e);
					}
					
				}
			}
		}).start();
	}
	
	public ZooKeeper getZk() {
		return zk;
	}

	public String getConnString() {
		return connString;
	}

	public void setConnString(String connString) {
		this.connString = connString;
	}

	public boolean isUsingStaticRpcEndpoint() {
		return usingStaticRpcEndpoint;
	}

	public void setUsingStaticRpcEndpoint(boolean usingStaticRpcEndpoint) {
		this.usingStaticRpcEndpoint = usingStaticRpcEndpoint;
	}

	public String getStaticRpcEndpoint() {
		return staticRpcEndpoint;
	}

	public void setStaticRpcEndpoint(String staticRpcEndpoint) {
		this.staticRpcEndpoint = staticRpcEndpoint;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public void process(WatchedEvent w) {
	}

}
