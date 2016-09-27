package com.duitang.service.karma.support;

import com.duitang.service.karma.boot.ServerBootstrap;
import com.google.common.collect.Lists;

import org.apache.log4j.Logger;

import java.util.List;


/**
 * Magic starts from here
 *
 * @author kevx
 * @since 12/10/2014
 */
public class ServicesExporter {

  private List<Object> services;
  private List<String> exportedInterfaces = Lists.newArrayList();

  private int port;
  private int maxQueuingLatency = 500;
  private ServerBootstrap boot;

  private final Logger log = Logger.getLogger("server");

  public void init() {
    try {
      boot = new ServerBootstrap();
      for (Object svc : services) {
        //this should never happen
        Class<?>[] allIntfce = svc.getClass().getInterfaces();
        Class<?> itf = null;
        //规则，优先取I打头的接口，若没有则取所实现的第一个接口
        for (Class<?> intfce : allIntfce) {
          if (intfce.getSimpleName().startsWith("I")) {
            itf = intfce;
          }
        }

        if (itf == null) {
          itf = allIntfce[0];
        }
        exportedInterfaces.add(itf.getName());
        boot.addService(itf, svc);
        boot.setMaxQueuingLatency(maxQueuingLatency);
        log.warn("ServicesExporter_inited:" + itf.getName());
      }
      boot.startUp(port);
    } catch (Exception e) {
      log.error("ServicesExporter::init_failed:", e);
    }
  }

  public void halt() {
    if (boot != null) {
      boot.shutdown();
    }
  }

  public List<Object> getServices() {
    return services;
  }

  public void setServices(List<Object> services) {
    this.services = services;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void setMaxQueuingLatency(int maxQueuingLatency) {
    this.maxQueuingLatency = maxQueuingLatency;
  }

  public List<String> getExportedInterfaces() {
    return exportedInterfaces;
  }

}
