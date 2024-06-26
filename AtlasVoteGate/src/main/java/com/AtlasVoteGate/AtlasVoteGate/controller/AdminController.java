package com.AtlasVoteGate.AtlasVoteGate.controller;

import com.AtlasVoteGate.AtlasVoteGate.Service.interfaces.*;
import com.AtlasVoteGate.AtlasVoteGate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    ElectoralPartyService electoralPartyService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    VoteService voteService;

    @Autowired
    SupportMessageService supportMessageService;

    // Fonctions liées à la gestion des utilisateurs (UtilisateurService)

    @PostMapping("/users")
    public void saveUser(@RequestBody Utilisateur utilisateur){
        utilisateurService.save(utilisateur);
    }

    @GetMapping("/users/{idUser}")
    public Utilisateur getUser(@PathVariable Long idUser){
        return utilisateurService.getUserById(idUser);
    }

    @PutMapping("/users/{idUser}")
    public void updateUser(@PathVariable Long idUser ,@RequestBody Utilisateur utilisateur){
        utilisateurService.update(idUser,utilisateur);
    }

    @GetMapping("/functionaries")
    public List<Utilisateur> getFunctionaries(){
        return utilisateurService.getAllFunctionaries();
    }

    @GetMapping("/allusers")
    public List<Utilisateur> getAllUsers(){
        return utilisateurService.getAllUsers();
    }

    @GetMapping("/voters")
    public List<Utilisateur> getVoters(){
        return utilisateurService.getAllVoters();
    }

    // Fonctions liées à la gestion des rendez-vous (AppointmentService)

    @PostMapping("/appointments")
    public Appointment saveAppointment(@RequestBody Appointment appointment) {
        return appointmentService.save(appointment);
    }

    @GetMapping("/appointments/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointment(id);
    }

    @DeleteMapping("/appointments/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.delete(id);
    }

    @PutMapping("/appointments/{id}")
    public void updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        appointmentService.update(id, appointment);
    }

    @PostMapping("/appointments/{id}/cancel")
    public void cancelAppointment(@PathVariable Long id) {
        appointmentService.cancel(id);
    }

    @PostMapping("/appointments/{id}/verify")
    public void verifyAppointment(@PathVariable Long id) {
        appointmentService.verify(id);
    }

    @GetMapping("/appointments/today")
    public List<Appointment> getAppointmentsForToday() {
        return appointmentService.getAppointmentsForToday();
    }

    @GetMapping("/appointments/date/{date}")
    public List<Appointment> getAppointmentsForDate(@PathVariable LocalDate date) {
        return appointmentService.getAppointmentsForDate(date);
    }

    // Fonctions liées à la gestion des partis électoraux (ElectoralPartyService)

    @PostMapping("/electoralparties")
    public ElectoralParty saveElectoralParty(@RequestBody ElectoralParty electoralParty) {
        return electoralPartyService.save(electoralParty);
    }

    @GetMapping("/electoralparties/{id}")
    public ElectoralParty getElectoralPartyById(@PathVariable Long id) {
        return electoralPartyService.getElectoralParty(id);
    }

    @DeleteMapping("/electoralparties/{id}")
    public void deleteElectoralParty(@PathVariable Long id) {
        electoralPartyService.delete(id);
    }

    @PutMapping("/electoralparties/{id}")
    public void updateElectoralParty(@PathVariable Long id, @RequestBody ElectoralParty electoralParty) {
        electoralPartyService.updateElectoralParty(id, electoralParty);
    }

    @GetMapping("/electoralparties")
    public List<ElectoralParty> getAllElectoralParties() {
        return electoralPartyService.getAllElectoralParty();
    }

    // Fonctions liées à la gestion des votes (VoteService)

    @PostMapping("/votes/{userId}/{partyId}")
    public void createVote(@PathVariable Long userId, @PathVariable Long partyId) {
        voteService.createVote(userId, partyId);
    }

    @GetMapping("/votes/{id}")
    public Vote getVoteById(@PathVariable Long id) {
        return voteService.getVoteById(id);
    }

    @PutMapping("/votes/{id}")
    public void updateVote(@PathVariable Long id, @RequestBody Vote updatedVote) {
        voteService.updateVote(id, updatedVote);
    }

    @DeleteMapping("/votes/{id}")
    public void deleteVote(@PathVariable Long id) {
        voteService.deleteVote(id);
    }

    @GetMapping("/votes")
    public List<Vote> getAllVotes() {
        return voteService.getAllVotes();
    }

    @GetMapping("/votes/user/{userId}")
    public Vote getVoteByUserId(@PathVariable Long userId) {
        return voteService.getVoteByUserId(userId);
    }

    @GetMapping("/votes/count/{partyId}")
    public long countVotesForElectoralParty(@PathVariable Long partyId) {
        return voteService.countVotesForElectoralParty(partyId);
    }

    @GetMapping("/votes/count/all")
    public Map<ElectoralParty, Long> countVotesForAllParties() {
        return voteService.countVotesForAllParties();
    }

    @GetMapping("/votes/winningParty")
    public ElectoralParty getWinningParty() {
        return voteService.getWinningParty();
    }

    @PostMapping("/votes/startVotingProcess")
    public void startVotingProcess() {
        voteService.startVotingProcess();
    }

    @PostMapping("/votes/pauseVotingProcess")
    public void pauseVotingProcess() {
        voteService.pauseVotingProcess();
    }

    @PostMapping("/votes/resumeVotingProcess")
    public void resumeVotingProcess() {
        voteService.resumeVotingProcess();
    }

    @PostMapping("/votes/endVotingProcess")
    public void endVotingProcess() {
        voteService.endVotingProcess();
    }

    @PostMapping("/votes/updateVotingStartTime")
    public void updateVotingStartTime(@RequestBody LocalDateTime newStartTime) {
        voteService.updateVotingStartTime(newStartTime);
    }

    @GetMapping("/supportMessages")
    public List<SupportMessage> allSupportMessages(){
        return this.supportMessageService.allSupportMessages();
    }

    @DeleteMapping("/utilisateurs/{id}")
    public void deleteUtilisateur ( @PathVariable Long id){
        this.utilisateurService.deleteUtilisateur(id);
    }
    @GetMapping("/electoralPart")
    public ResponseEntity<Map<String, Integer>> getElectoralParties() {
        Map<String, Integer> statistics = new HashMap<>();
        List<ElectoralParty> sortedParties = electoralPartyService.getAllElectoralParty().stream().limit(3)
                .sorted(Comparator.comparingInt(party -> voteService.countVotesForElectoralParty(party.getId())))
                .collect(Collectors.toList());
        for (ElectoralParty party : sortedParties) {
            statistics.put(party.getName(), voteService.countVotesForElectoralParty(party.getId()));
        }
        return ResponseEntity.ok(statistics);
    }

    public String test() {
        return "test";
    }




}





