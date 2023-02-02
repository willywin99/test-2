package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.Employee;
import com.mii.server.services.EmployeeService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

  private EmployeeService employeeService;
  
  @GetMapping
  private List<Employee> getAll(){
    return employeeService.getAll();
  }

  @GetMapping(value = "/{id}")
  public Employee getById(@PathVariable Integer id){
    return employeeService.getById(id);
  }

  @GetMapping(value = "/v2")
  public Employee getByIdwithParam(@RequestParam(name = "id") Integer id){
    return employeeService.getById(id);
  }

  @PostMapping
  public Employee create(@RequestBody Employee employee){
    return employeeService.create(employee);
  }

  @PutMapping(value = "/{id}")
  public Employee update(@PathVariable Integer id, @RequestBody Employee employee){
    return employeeService.update(id, employee);
  }

  @DeleteMapping(value = "{id}")
  public Employee delete(@PathVariable Integer id){
    return employeeService.delete(id);
  }

  @GetMapping("/jpql")
  public List<Employee> searchByName(@RequestParam(name = "name") String name){
    return employeeService.searchByName(name);
  }

  @GetMapping("/native")
  public List<Employee> searchByNameNative(@RequestParam(name = "name") String name){
    return employeeService.searchByNameNative(name);
  }

}