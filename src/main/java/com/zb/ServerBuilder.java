package com.zb;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerBuilder {
  private String baseUri = "http://localhost/";

  private int port = 8080;

  public ServerBuilder() {
  }

  public ServerBuilder(String baseUri, int port) {
    this.baseUri = baseUri;
    this.port = port;
  }

  public Server build() {
    URI uri = UriBuilder.fromUri(baseUri).port(port).build();
    ResourceConfig config = new ResourceConfig(MyResource.class);
    return JettyHttpContainerFactory.createServer(uri, config);
  }
}
