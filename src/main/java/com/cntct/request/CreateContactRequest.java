package com.cntct.request;

import org.hibernate.validator.constraints.Email;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSnakeCase
public class CreateContactRequest {

  @Email
  private String email;
  
  private String name;
  
  private String type;
  
  private String phone;
}
