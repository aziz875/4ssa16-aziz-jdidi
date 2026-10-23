package tn.esprit.nomprenomclasse3.Web.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.nomprenomclasse3.Domain.Vehicule;
import tn.esprit.nomprenomclasse3.Repository.IVehiculeRepository;
import tn.esprit.nomprenomclasse3.Service.IVehiculeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("vehicules")
public class VehiculeRestController {

    private final IVehiculeService iVehiculeService;


    @PostMapping("add")
    Vehicule createVehicule(@RequestBody Vehicule vehicule){
        return iVehiculeService.addVehicule(vehicule);
    }

    @PutMapping("update")
    Vehicule updateVehicule(@RequestBody Vehicule vehicule){
        return iVehiculeService.updateVehicule(vehicule);
    }



}
