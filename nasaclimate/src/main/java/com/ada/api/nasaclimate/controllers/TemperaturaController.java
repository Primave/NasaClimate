package com.ada.api.nasaclimate.controllers;

import java.util.List;

import com.ada.api.nasaclimate.entities.Temperatura;
import com.ada.api.nasaclimate.models.requests.TemperaturaRequest;
import com.ada.api.nasaclimate.models.responses.GenericResponse;
import com.ada.api.nasaclimate.services.PaisService;
import com.ada.api.nasaclimate.services.TemperaturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperaturaController {
    @Autowired
    PaisService PaisService;
    @Autowired
    TemperaturaService temperaturaService;
    @Autowired
    TemperaturaRequest TemperaturaRequest;

    // POST /temperaturas : que registre una temperatura de un país en un año
    // específico
    // RequestBody {“codigoPais”: 32,“anio”: 2010,“grados”: 38.6}
    // Respuesta Esperada(JSON): {“id”: 25 //O cualquier número de temperatura que
    // devuelva.}
    // Nota: que no permita cargar una temperatura ya existente para el año
    // especificado. Es decir, si hay una temperatura en el país correspondiente al
    // año ingresado, debe generar un error.
    @PostMapping("/temperaturas")
    public ResponseEntity<GenericResponse> crearTemperatura(@RequestBody TemperaturaRequest tempRequest) {
        Temperatura temperatura = temperaturaService.cargarTemperatura(tempRequest.codigoPais, tempRequest.anioTemperatura, tempRequest.grado);

        GenericResponse responseGen = new GenericResponse();

        responseGen.isOk = true;
        responseGen.message = "Se cargo la temperatura con exito";
        responseGen.id = temperatura.getTemperaturaId();

        return ResponseEntity.ok(responseGen);
    }

    // GET /temperaturas/paises/{codigoPais} : que devuelva la lista de temperaturas
    // con sus años de un país especifico, indicado por “codigoPais”.

    @GetMapping("temperaturas/paises/{codigoPais}")
    public ResponseEntity<List<Temperatura>> listarTemperaturas(@PathVariable int codigoPais) {

        return ResponseEntity.ok(temperaturaService.listarTemperatura());

    }

    // DELETE /temperaturas/{id}: no se borrará la temperatura id, deberá cambiar el
    // año a 0.

    @DeleteMapping("/temperaturas/{id}")
    public ResponseEntity<?> borrarTemperatura(@PathVariable int id) {

        Temperatura temperatura = temperaturaService.buscarPorTemperaturaId(id);

        if (temperatura != null) {

            temperaturaService.borrarTemperatura(temperatura);

            GenericResponse resp = new GenericResponse();

            resp.isOk = true;
            resp.id = temperatura.getTemperaturaId();
            resp.message = "Se borro la temperatura con exito";

            return ResponseEntity.ok(resp);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    
// --------------- Bonus ------------

    // GET /temperaturas/anios/{anio} : que devuelva la lista de temperaturas de un
    // año en particular en el siguiente formato JSON Array:
    @GetMapping("/temperaturas/anios/{anio}")
    public ResponseEntity<List<TemperaturaAnioResponse>> listarTemperaturaAnio(@PathVariable int anioTemperatura) {

        List<TemperaturaAnioResponse> resultado = new ArrayList<>();
        // lista de temperaturas
        List<Temperatura> tempPorAnio = TemperaturaService.buscarPorAnio(anioTemperatura);

        for (Temperatura t : tempPorAnio) {
            TemperaturaAnioResponse t2 = new TemperaturaAnioResponse();
            t2.nombrePais = t.getPais().getNombre();
            t2.grados = t.getTemperatura();
            resultado.add(t2);
        }
        return ResponseEntity.ok(resultado);

    }

    // GET /temperaturas/maximas/{codigoPais} : que devuelva la temperatura máxima
    // para un país en particular en este formato JSON(informar el año en que
    // ocurrió)

}
