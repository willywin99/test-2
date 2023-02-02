package com.mii.server.models.dto.request;

import com.mii.server.models.User;

import lombok.Data;

@Data
public class EmployeeRequest {
  
  private String name;
  private String email;
  private Integer phone;
  private User userId;
}
