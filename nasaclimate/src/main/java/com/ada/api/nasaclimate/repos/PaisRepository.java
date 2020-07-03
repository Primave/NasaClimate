package com.ada.api.nasaclimate.repos;

import com.ada.api.nasaclimate.entities.Pais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository <Pais, Integer>{
    
}