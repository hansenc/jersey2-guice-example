package com.zb;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class MyApplication extends ResourceConfig {
  public MyApplication() {
    packages(MyResource.class.getPackage().getName());
  }
}
