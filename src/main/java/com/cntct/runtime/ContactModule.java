package com.cntct.runtime;

import org.hibernate.SessionFactory;
import com.cntct.configuration.ContactConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import io.dropwizard.hibernate.HibernateBundle;

public class ContactModule extends AbstractModule {

  private final HibernateBundle<ContactConfiguration> hibernateBundle;

  public ContactModule(HibernateBundle<ContactConfiguration> hibernateBundle) {
    this.hibernateBundle = hibernateBundle;
  }

  @Override
  protected void configure() {
    bind(ObjectMapper.class).asEagerSingleton();
    bind(SessionFactory.class).toInstance(hibernateBundle.getSessionFactory());
  }
}
