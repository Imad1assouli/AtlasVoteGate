package com.AtlasVoteGate.AtlasVoteGate.controller;

import com.AtlasVoteGate.AtlasVoteGate.Repository.UtilisateurRepo;
import com.AtlasVoteGate.AtlasVoteGate.Service.interfaces.AppointmentService;
import com.AtlasVoteGate.AtlasVoteGate.Service.interfaces.UtilisateurService;
import com.AtlasVoteGate.AtlasVoteGate.model.Appointment;
import com.AtlasVoteGate.AtlasVoteGate.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/fonctionnaire")

public class FonctionnaireController {
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    AppointmentService appointmentService;

    //fonction pour afficher la liste des demandeurs

    @GetMapping("/appointments/today")
    public List<Appointment> getAppointmentsForToday() {
        return appointmentService.getAppointmentsForToday();
    }


  /*  @GetMapping("/demandeurs")
     public List<Utilisateur> getAllDemandeurs(){
        return  this.utilisateurService.getAllDemandeur();
    }
*/
    @GetMapping("/appointments/date/{date}")
    public List<Appointment> getAppointmentsForDate(@PathVariable LocalDate date) {
        return appointmentService.getAppointmentsForDate(date);
    }

    //fonction pour enregistrer un voteur
  /*  @PutMapping ("/validate/{idDemendeur}")
    public void makeVoter (@PathVariable Long idDemandeur){
        this.utilisateurService.makeVoter(idDemandeur);

 }
   */

    @PostMapping("/appointments/{id}/cancel")
    public void cancelAppointment(@PathVariable Long id) {
        appointmentService.cancel(id);
    }

    @PostMapping("/appointments/{id}/verify")
    public void verifyAppointment(@PathVariable Long id) {
        appointmentService.verify(id);
    }



}
