package com.ada.api.nasaclimate.services;

import java.util.ArrayList;
import java.util.List;

import com.ada.api.nasaclimate.entities.Pais;
import com.ada.api.nasaclimate.entities.Temperatura;
import com.ada.api.nasaclimate.models.requests.TemperaturaRequest;
import com.ada.api.nasaclimate.repos.TemperaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperaturaService {

    @Autowired
    TemperaturaRepository temperaturaRepository;
    @Autowired
    PaisService paisService;

public Temperatura cargarTemperatura(int codigoPais, int anioTemperatura, double grados){
    
    Temperatura temperatura = new Temperatura();
    temperatura.setPais(paisService.traerPaisPorId(codigoPais));
    temperatura.setAnioTemperatura(anioTemperatura);
    temperatura.setGrado(grados);
    
    temperaturaRepository.save(temperatura);

    return temperatura;
}


public List<Temperatura> listarTemperatura(){

    return temperaturaRepository.findAll();
}


public Temperatura buscarPorTemperaturaId(int temperaturaId){
    
    return temperaturaRepository.findById(temperaturaId);

}

public void actualizarEstadoTemperatura(Temperatura temperatura, int aniotemperatura){

    temperatura.setAnioTemperatura(aniotemperatura);

    temperaturaRepository.save(temperatura);

}

public void borrarTemperatura(Temperatura temperatura) {

    this.actualizarEstadoTemperatura(temperatura, 0);

}



// --------------- Bonus ------------

public List<Temperatura> buscarPorAnio(int anioTemperatura) {
    List<Temperatura> temperaturasAnio = new ArrayList<>();
    List<Pais> paises = paisService.traerPaisPorId();

    for(Pais pa : paises){

        for(Temperatura temp : pa.getTemperaturas()){
            if (temp.getAnioTemperatura().equalls(anioTemperatura)) {
                temperaturasAnio.add(temp);
            }
        }
    }
    return temperaturasAnio;
}



public Temperatura buscarTempMax(int codigoPais){

    Temperatura tempMax = null;

    Pais pais = paisService.traerPaisPorId(codigoPais);

    double TempMaximaNum = -1000;

    for(Temperatura t : pais.getTemperaturas()) {
        if (t.getGrado() > TempMaximaNum) {
            TempMaximaNum = t.getGrado();
            tempMax = t;
        }

    }
    return tempMax;
}

}