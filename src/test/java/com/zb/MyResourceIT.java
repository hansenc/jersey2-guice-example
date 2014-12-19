package com.zb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.Test;

public class MyResourceIT {

  private String baseUri = "http://localhost:8080";

  private Client client = createClient();

  @Test
  public void get() {
    String result = client.target(baseUri + "/myresource").request().get(String.class);
    assertNotNull(result);
    assertEquals("Got it!", result);
  }

  private Client createClient() {
    return ClientBuilder.newClient();
  }
}
