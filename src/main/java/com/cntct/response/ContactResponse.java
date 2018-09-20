package com.cntct.response;

import java.util.Date;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSnakeCase
public class ContactResponse {

  private Long id;
  
  private String email;

  private String name;
  
  private String type;
  
  private String phone;
  
  private Date createdAt;
  
  private Date updatedAt;
}
