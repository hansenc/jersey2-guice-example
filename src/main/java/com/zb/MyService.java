package com.zb;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;

import com.google.inject.servlet.RequestScoped;

@RequestScoped
public class MyService {
  @Inject
  private HttpHeaders headers;

  public void run() {
    System.out.println(headers);
  }
}
