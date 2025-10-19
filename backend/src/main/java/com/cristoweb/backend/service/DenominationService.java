package com.cristoweb.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cristoweb.backend.entity.Denomination;
import com.cristoweb.backend.repository.DenominationRepository;


@Service
public class DenominationService {
    
private final DenominationRepository denominationRepository;

  // esto es un constructor para inyectar la dependencia del UserRepository en el
public DenominationService(DenominationRepository denominationRepository) {
    this.denominationRepository = denominationRepository;};
   
    // Crear denominación
    public Denomination createDenomination(Denomination denomination) {
        if (denominationRepository.existByName(denomination.getName())) {
            throw new RuntimeException("La denominación ya existe");
        }
        return denominationRepository.save(denomination);
    }

//objeter denominacion por id
    public Denomination getDenominationById(Long id){
              return denominationRepository.findById(id).orElseThrow(()-> new RuntimeException("Denominación no existe"));
    };


    //obetenr todas las denominaciones
    public  List<Denomination> getAllDenominations(){
       return denominationRepository.findAll();
    };

    //update denonmination
    public Denomination updateDenomination(Long id, Denomination denominationData){

        Denomination denomination = getDenominationById(id);

        if (denominationData.getName() != null){

            denomination.setName(denominationData.getName());
        };
        
        if (denominationData.getDescription() != null){

            denomination.setDescription(denominationData.getDescription());
        };
        
        
       return denominationRepository.save(denomination);
    };


    //delete denomination
    public void deleteDenomination(Long id){

        getDenominationById(id);
        denominationRepository.deleteById(id);
    };

       // Buscar denominación por nombre
    public Denomination getDenominationByName(String name) {
        return denominationRepository.findByName(name).orElseThrow(() -> new RuntimeException("Denominación no encontrada"));
    }
}
