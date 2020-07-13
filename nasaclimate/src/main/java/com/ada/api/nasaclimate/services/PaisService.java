package com.ada.api.nasaclimate.services;

import com.ada.api.nasaclimate.entities.Pais;
import com.ada.api.nasaclimate.repos.PaisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {


    @Autowired
    PaisRepository paisRepository;


    public void crearPais(Pais pais){
        paisRepository.save(pais);

    }

    
}