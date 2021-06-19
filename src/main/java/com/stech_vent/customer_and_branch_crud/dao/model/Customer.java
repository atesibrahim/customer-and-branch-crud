package com.stech_vent.customer_and_branch_crud.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "full_name")
  private String fullName;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.ALL
      })
  @JoinTable(name = "customer_branches",
      joinColumns = {@JoinColumn(name = "customer_id",
              referencedColumnName = "id",
              nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "branch_id",
              referencedColumnName = "id",
              nullable = false, updatable = false)})
  private Set<Branch> branches = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer= (Customer) o;
    return id.equals(customer.id);
  }



  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Customer() {
    super();
  }

  public Customer(String fullName) {
    this.fullName = fullName;
  }

  public Customer(String fullName, Set<Branch> branches) {
    this.fullName = fullName;
    this.branches = branches;
  }
}
