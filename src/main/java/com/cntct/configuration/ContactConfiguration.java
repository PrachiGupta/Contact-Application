package com.cntct.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Data;

@Data
public class ContactConfiguration extends Configuration {

  @JsonProperty("swagger")
  public SwaggerBundleConfiguration swaggerBundleConfiguration;

  @Valid
  @NotNull
  @JsonProperty("database")
  private DataSourceFactory dataSourceFactory = new DataSourceFactory();

}
