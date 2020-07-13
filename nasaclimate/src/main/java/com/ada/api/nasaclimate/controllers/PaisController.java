package com.ada.api.nasaclimate.controllers;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.ada.api.nasaclimate.entities.Pais;
import com.ada.api.nasaclimate.models.PaisRequest;
import com.ada.api.nasaclimate.services.PaisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaisController {

    @Autowired
    PaisService paisService;

    @PostMapping("/paises")
    public ResponseEntity<?> crearPais(@RequestBody PaisRequest infoPais) {

        Pais pais = new Pais();
        pais.setCodigoPais(infoPais.codigoPais);
        pais.setNombre(infoPais.nombrePais);
        paisService.crearPais(pais);
        return ResponseEntity.ok(pais);
        
    }

    /**
     * POST /paises : que permita la creación de un país RequestBody:
     * 
     * { “codigoPais”: 32, “nombre”: “Argentina” } GET /paises : que devuelva la
     * lista de países SIN las temperaturas.
     * 
     * GET /paises/{id} : que devuelva la info de un pais en particular(SIN las
     * temperaturas) PUT /paises/{id} : que actualice solo el nombre del país. Usar
     * un requestBody que solo tenga el nombre del país.
     */

}