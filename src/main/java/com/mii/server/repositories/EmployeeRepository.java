package com.mii.server.repositories;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  // JPQL
  @Query("SELECT e FROM Employee e WHERE e.name = ?1")
  public List<Employee> searchByName(String name);

  // Native
  @Query(
    value = "SELECT * FROM tb_employee e WHERE e.employee_name = :name",
    nativeQuery = true
  )
  public List<Employee> searchByNameNative(String name);

  // Query Method
  // public List<Employee> findByCode(String code);

  // public List<Employee> findByCodeContainsAndNameContainsOrderByIdDesc(
  //   String name,
  //   String email,
  //   Integer phone
  // );

  // public Optional<Employee> findByName(String name);

  public Boolean existsByName(String name);
}
