package com.mii.server.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Employee;
import com.mii.server.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService {
  
  private EmployeeRepository employeeRepository;

  public List<Employee> getAll(){
    return employeeRepository.findAll();
  }

  public Employee getById(Integer id){
    return employeeRepository
      .findById(id)
      .orElseThrow(
      () -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, 
        "Employee not found"
      ) 
    );
  }

  public Employee create(Employee employee){
    if(employeeRepository.existsByName(employee.getName())){
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Name is already exists...."
      );
    }
    return employeeRepository.save(employee);
  }

  public Employee update(Integer id, Employee employee){
    getById(id);
    employee.setId(id);
    return employeeRepository.save(employee);
  }

  public Employee delete(Integer id){
    Employee employee = getById(id);
    employeeRepository.delete(employee);
    return employee;
  }

  public List<Employee> searchByName(String name){
    return employeeRepository.searchByName(name);
  }

  public List<Employee> searchByNameNative(String name){
    return employeeRepository.searchByNameNative(name);
  } 
 
}
