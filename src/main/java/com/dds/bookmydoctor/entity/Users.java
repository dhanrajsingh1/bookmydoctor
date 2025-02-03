package com.dds.bookmydoctor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Users {

  @Id
  String username;
  String password;

  @Column(columnDefinition = "TINYINT(1)")
  boolean enabled;

  @OneToMany(mappedBy = "users")
  private List<Authorities> authorities;

}
