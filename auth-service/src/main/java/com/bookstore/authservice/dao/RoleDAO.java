package com.bookstore.authservice.dao;

import com.bookstore.authservice.common.util.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@Builder
public class RoleDAO extends DateAudit {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "role_id", updatable = false, nullable = false)
  private String id;

  @Column(name = "role_name", nullable = false)
  private String roleName;

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      mappedBy = "roles")
  @JsonIgnore
  private Set<UserDAO> users = new HashSet<>();

  @Column(name = "role_description")
  private String roleDescription;

  public void addUser(UserDAO user) {
    this.users.add(user);
    user.getRoles().add(this);
  }

  public void removeUser(UserDAO user) {
    this.users.remove(user);
    user.getRoles().remove(this);
  }
}