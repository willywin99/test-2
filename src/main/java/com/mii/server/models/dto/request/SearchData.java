package com.mii.server.models.dto.request;

import lombok.Data;

@Data
public class SearchData {

  private String name;
  private String username;
  private String searchKey;
  private String otherKey;
}
