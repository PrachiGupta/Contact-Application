package com.cntct.application;

import com.cntct.configuration.ContactConfiguration;
import com.cntct.model.Contact;
import com.cntct.runtime.ContactModule;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class ContactApplication extends Application<ContactConfiguration> {

  public static void main(String[] args) throws Exception {
    new ContactApplication().run(args);
  }

  @Override
  public void run(ContactConfiguration configuration, Environment environment)
      throws Exception {
    environment.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    environment.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  }

  @Override
  public void initialize(Bootstrap<ContactConfiguration> bootstrap) {
    bootstrap.addBundle(hibernate);
    bootstrap.addBundle(migrationBundle);
    bootstrap.addBundle(swaggerBundle);

    bootstrap.addBundle(GuiceBundle.builder().enableAutoConfig("com.cntct")
        .modules(new ContactModule(hibernate)).build());
  }

  private final HibernateBundle<ContactConfiguration> hibernate =
      new HibernateBundle<ContactConfiguration>(Contact.class) {
        public DataSourceFactory getDataSourceFactory(ContactConfiguration configuration) {
          return configuration.getDataSourceFactory();
        }
      };

  private final SwaggerBundle<ContactConfiguration> swaggerBundle =
      new SwaggerBundle<ContactConfiguration>() {
        @Override
        protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
            ContactConfiguration configuration) {
          return configuration.swaggerBundleConfiguration;
        }
      };

  private final MigrationsBundle<ContactConfiguration> migrationBundle =
      new MigrationsBundle<ContactConfiguration>() {
        public DataSourceFactory getDataSourceFactory(ContactConfiguration configuration) {
          return configuration.getDataSourceFactory();
        }
      };
}
