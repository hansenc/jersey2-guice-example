package com.zb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.ClassRule;
import org.junit.Test;

public class MyResourceIT {

  @ClassRule
  public static ServerRule serverRule = new ServerRule();

  private Client client = createClient();

  @Test
  public void get() {
    String result = client.target(serverRule.getBaseUri() + "myresource").request().get(String.class);
    assertNotNull(result);
    assertEquals("Got it!", result);
  }

  private Client createClient() {
    return ClientBuilder.newClient();
  }
}
