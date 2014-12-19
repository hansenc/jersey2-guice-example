package com.zb;

import org.eclipse.jetty.server.Server;
import org.junit.rules.ExternalResource;

public class ServerRule extends ExternalResource {
  Server server;

  @Override
  protected void before() throws Throwable {
    server = new ServerBuilder().build();
    server.start();
    while (!server.isStarted()) {
      Thread.sleep(100);
    }
  }

  @Override
  protected void after() {
    try {
      server.stop();
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
    finally {
      server.destroy();
    }
  }

  public String getBaseUri() {
    return server.getURI().toString();
  }
}
