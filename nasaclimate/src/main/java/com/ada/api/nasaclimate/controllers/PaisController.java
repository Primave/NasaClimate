package com.ada.api.nasaclimate.controllers;

import com.ada.api.nasaclimate.entities.Pais;
import com.ada.api.nasaclimate.models.requests.PaisRequest;
import com.ada.api.nasaclimate.models.responses.GenericResponse;
import com.ada.api.nasaclimate.services.PaisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaisController {

    @Autowired
    PaisService paisService;
    GenericResponse genericResponse;
    PaisRequest paisRequest;

    @PostMapping("/paises")
    public ResponseEntity<?> crearPais(@RequestBody PaisRequest infoPais) {

        Pais pais = new Pais();
        pais.setCodigoPais(infoPais.codigoPais);
        pais.setNombre(infoPais.nombrePais);
        paisService.crearPais(pais);
        return ResponseEntity.ok(pais);

    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<?> buscarPaisPorId(@PathVariable int codigoPais) {

        Pais pais = paisService.traerPaisPorId(codigoPais);

        // TODO Aillin, pienso que entendi esse if. Si el codigo del pais existe, 
        //exibir en postman el pais, si no traer error not found. Es eso?

        if (pais != null) {
            return ResponseEntity.ok(pais);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/paises/{id}")
    public ResponseEntity<?> actualizarNombrePais(@PathVariable int codigoPais, @RequestBody Pais paisInfoNueva){

        //TODO estiar como hacer put, pues solo tengo ese ejemplo

        GenericResponse responseG = new GenericResponse();

        Pais paisOriginal = paisService.traerPaisPorId(codigoPais);

        if (paisOriginal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;

        resultado = paisService.actualizarNombrePais(paisOriginal, paisInfoNueva);

        if (resultado) {
            responseG.isOk = true;
            responseG.id = paisInfoNueva.getCodigoPais();
            responseG.message = "Pais actualizado con éxito.";
            return ResponseEntity.ok(responseG);
        } else {

            responseG.isOk = false;
            responseG.message = "No se pudo actualizra el pais.";

            return ResponseEntity.badRequest().body(responseG);
        }

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