package com.ada.api.nasaclimate.repos;

import com.ada.api.nasaclimate.entities.Temperatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperaturaRepository extends JpaRepository <Temperatura, Integer>{

    public Temperatura findById(int temperaturaId);
    
}