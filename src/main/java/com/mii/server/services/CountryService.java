package com.mii.server.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mii.server.models.Country;
import com.mii.server.models.Region;
import com.mii.server.models.dto.request.CountryRequest;
import com.mii.server.repositories.CountryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CountryService {
  
  private CountryRepository countryRepository;
  private RegionService regionService;
  private ModelMapper modelMapper;

  public List<Country> getAll() {
    return countryRepository.findAll();
  }

  public Country getById(Integer id) {
    return countryRepository
      .findById(id)
      .orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found!!!")
    );
  }

  // create without dto
  public Country create(Country country) {
    if (countryRepository.findByName(country.getName()).isPresent()) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Name is already exists..."
      );
    }
    return countryRepository.save(country);
  }

  // create with dto
  public Country createDTO(CountryRequest countryRequest) {
    Country country = new Country();
    country.setCode(countryRequest.getCode());
    country.setName(countryRequest.getName());

    Region region = regionService.getById(countryRequest.getRegionId());
    country.setRegion(region);

    return countryRepository.save(country);
  }

  public Country update(Integer id, Country country) {
    getById(id);
    country.setId(id);
    return countryRepository.save(country);
  }

  // create with dto by model mapper
  public Country createDTOV2(CountryRequest countryRequest) {
    Country country = modelMapper.map(countryRequest, Country.class);
    country.setRegion(regionService.getById(countryRequest.getRegionId()));

    return countryRepository.save(country);
  }

  public Country delete(Integer id) {
    Country country = getById(id);
    countryRepository.delete(country);
    return country;
  }

  // JPQL
  public List<Country> searchByName(String name) {
    return countryRepository.searchByName(name);
  }

  // Native
  public List<Country> searchByNameNative(String name) {
    return countryRepository.searchByNameNative(name);
  }

  // Query Method
  public List<Country> findByCode(String code) {
    return countryRepository.findByCode(code);
  }

  public List<Country> findByCodeOrName(String code, String name) {
    return countryRepository.findByCodeContainsAndNameContainsOrderByIdDesc(code, name);
  }
}
