package com.mii.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mii.server.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
  // JPQL
  @Query("SELECT c FROM Country c WHERE c.name = ?1")
  public List<Country> searchByName(String name);

  // Native
  @Query(
    value = "SELECT * FROM tb_country c WHERE c.country_name = :name",
    nativeQuery = true
  )
  public List<Country> searchByNameNative(String name);

  // Query Method
  public List<Country> findByCode(String code);

  public List<Country> findByCodeContainsAndNameContainsOrderByIdDesc(
    String code,
    String name
  );

  public Optional<Country> findByName(String name);
}
