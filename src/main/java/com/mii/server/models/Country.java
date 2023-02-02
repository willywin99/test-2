package com.mii.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_country")
public class Country {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "country_id")
  private Integer id;

  @Column(name = "country_code", unique = true, nullable = false, length = 2)
  private String code;

  @Column(name = "country_name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "region_id", nullable = false)
  private Region region;
}
