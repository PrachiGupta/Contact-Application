package com.cntct.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  
  @Column(name = "email")
  private String email;

  @Column(name = "name")
  private String name;
  
  @Column(name = "type")
  private String type;
  
  @Column(name = "phone")
  private String phone;
  
  @Column(name = "created_at")
  private Long createdAt;

  @Column(name = "updated_at")
  private Long updatedAt;
  
  @Version
  @Column(name = "version")
  private Integer version;
}
