package com.ada.api.nasaclimate.services;

import java.util.Optional;

import com.ada.api.nasaclimate.entities.Pais;
import com.ada.api.nasaclimate.repos.PaisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {

    @Autowired
    PaisRepository paisRepository;


    public void crearPais(Pais pais) {
        paisRepository.save(pais);

    }

    public Pais traerPaisPorId(int codigoPais) {

        Optional<Pais> eo = paisRepository.findById(codigoPais);
        // TODO Aillin, este if no entiendo. Son del pack util.optional pero no sé que hacen.

        if (eo.isPresent()) {

            return eo.get();
        }

        return null;

    }

    public boolean actualizarNombrePais(Pais paisOriginal, Pais paisInfoNueva) {

        paisOriginal.setCodigoPais(paisInfoNueva.getCodigoPais());
        paisOriginal.setNombre(paisInfoNueva.getNombre());

        paisRepository.save(paisOriginal);

        return true;
    }

}