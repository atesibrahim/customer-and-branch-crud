package com.softtech_ventures.customer_and_branch_crud.dao.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "branches")
public class Branch {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.ALL
      })

  @JoinTable(name = "branch_customers",
      joinColumns = {@JoinColumn(name = "branch_id",
              referencedColumnName = "id",
          nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "customer_id",
              referencedColumnName = "id",
              nullable = false, updatable = false)})
  private Set<Customer> customers = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Branch branch= (Branch) o;
    return id.equals(branch.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Branch() {
    super();
  }

  public Branch(String name) {
    this.name = name;
  }

  public Branch(String name, Set<Customer> customers) {
    this.name = name;
    this.customers = customers;
  }
}
