package com.zb;

import javax.servlet.DispatcherType;
import java.util.Arrays;
import java.util.EnumSet;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.ServletModule;
import com.squarespace.jersey2.guice.BootstrapUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ServerBuilder {
  private int port = 8080;

  public ServerBuilder() {
  }

  public ServerBuilder(int port) {
    this.port = port;
  }

  public Server build() {
    ServiceLocator locator = BootstrapUtils.newServiceLocator();
    Injector injector = BootstrapUtils.newInjector(locator, Arrays.asList(new ServletModule(), new MyModule()));

    BootstrapUtils.install(locator);

    Server server = new Server(port);

    ResourceConfig config = ResourceConfig.forApplication(new MyApplication());

    ServletContainer servletContainer = new ServletContainer(config);

    ServletHolder sh = new ServletHolder(servletContainer);
    ServletContextHandler context = new ServletContextHandler(
        ServletContextHandler.SESSIONS);
    context.setContextPath("/");

    FilterHolder filterHolder = new FilterHolder(GuiceFilter.class);
    context.addFilter(filterHolder, "/*",
                      EnumSet.allOf(DispatcherType.class));

    context.addServlet(sh, "/*");
    server.setHandler(context);
    return server;
  }
}
