package com.mii.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.Region;
import com.mii.server.services.RegionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/region")
public class RegionController {
  
  @Autowired
  private RegionService regionService;

  @GetMapping
  public List<Region> getAll() {
    return regionService.getAll();
  }

  @GetMapping(value = "/{id}")
  public Region getById(@PathVariable Integer id) {
    return regionService.getById(id);
  }
  
  // Param digunakan biasanya untuk search - ini hanya contoh
  @GetMapping(value = "/v2")
  public Region getByIdWithParam(@RequestParam(name = "id") Integer id) {
    return regionService.getById(id);
  }

  @PostMapping
  public Region create(@RequestBody Region region) {
    return regionService.create(region);
  }

  @PutMapping(value = "/{id}")
  public Region update(@PathVariable Integer id, @RequestBody Region region) {
    return regionService.update(id, region);
  }

  @DeleteMapping(value = "/{id}")
  public Region delete(@PathVariable Integer id) {
    return regionService.delete(id);
  }
}
