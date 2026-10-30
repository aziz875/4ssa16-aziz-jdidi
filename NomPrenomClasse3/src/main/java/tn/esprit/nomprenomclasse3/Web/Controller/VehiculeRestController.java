package tn.esprit.nomprenomclasse3.Web.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.nomprenomclasse3.Domain.Vehicule;
import tn.esprit.nomprenomclasse3.Repository.IVehiculeRepository;
import tn.esprit.nomprenomclasse3.Service.IVehiculeService;

import java.util.List;
@Tag(name = "VehiculesAzizjdidi" , description ="Gestion: vehicules")
@RestController
@RequiredArgsConstructor
@RequestMapping("vehicules")
@CrossOrigin("*")
public class VehiculeRestController {

    private final IVehiculeService iVehiculeService;


    @PostMapping("add")
    Vehicule createVehicule(@RequestBody Vehicule vehicule){
        return iVehiculeService.addVehicule(vehicule);
    }

    @PutMapping("update/{id}")
    Vehicule updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule){
        return iVehiculeService.updateVehicule(id, vehicule);
    }
    @GetMapping("/getById/{id}")
    Vehicule getById(@PathVariable Long id){
        return iVehiculeService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteVehicule(@PathVariable Long id){
        iVehiculeService.deleteVehicule(id);
    }

    @GetMapping("/getAll")
    List<Vehicule> getAllVehicules(){
        return iVehiculeService.findAll();
    }

}
