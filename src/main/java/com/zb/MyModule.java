package com.zb;

import javax.inject.Named;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class MyModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(MyResource.class);
    bind(MyService.class);
  }

  @Provides
  @Named("content")
  public String provideContent() {
    return "Got it!";
  }
}
