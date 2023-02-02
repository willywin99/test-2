package com.mii.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.Country;
import com.mii.server.models.dto.request.CountryRequest;
import com.mii.server.models.dto.request.SearchData;
import com.mii.server.services.CountryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {

  private CountryService countryService;

  @GetMapping
  public List<Country> getAll() {
    return countryService.getAll();
  }

  @GetMapping(value = "/{id}")
  public Country getById(@PathVariable Integer id) {
    return countryService.getById(id);
  }

  // tanpa DTO
  @PostMapping
  public Country create(@RequestBody Country country) {
    return countryService.create(country);
  }

  // dengan DTO
  @PostMapping("/v2")
  public Country createDTO(@RequestBody CountryRequest countryRequest) {
    return countryService.createDTO(countryRequest);
  }

  // dengan dto by model mapper
  @PostMapping("/v3")
  public Country createDTOV2(@RequestBody CountryRequest countryRequest) {
    return countryService.createDTOV2(countryRequest);
  }

  @PutMapping(value = "/{id}")
  public Country update(@PathVariable Integer id, @RequestBody Country country) {
    return countryService.update(id, country);
  }

  @DeleteMapping(value = "/{id}")
  public Country delete(@PathVariable Integer id) {
    return countryService.delete(id);
  }

  // JPQL
  @PostMapping("/jpql")
  public List<Country> searchByName(@RequestBody SearchData searchData) {
    return countryService.searchByName(searchData.getSearchKey());
  }

  // Native
  @PostMapping("/native")
  public List<Country> searchByNameNative(@RequestBody SearchData searchData) {
    return countryService.searchByNameNative(searchData.getSearchKey());
  }

  // Query Method
  @PostMapping("/query")
  public List<Country> findByCode(@RequestBody SearchData searchData) {
    return countryService.findByCode(searchData.getSearchKey());
  }

  @PostMapping("/query/v2")
  public List<Country> findByCodeOrName(@RequestBody SearchData searchData) {
    return countryService.findByCodeOrName(
      searchData.getSearchKey(),
      searchData.getOtherKey()
    );
  }
}
