package com.AtlasVoteGate.AtlasVoteGate.security.restController;



import com.AtlasVoteGate.AtlasVoteGate.Service.interfaces.AppointmentService;
import com.AtlasVoteGate.AtlasVoteGate.Service.interfaces.UtilisateurService;
import com.AtlasVoteGate.AtlasVoteGate.model.Appointment;
import com.AtlasVoteGate.AtlasVoteGate.model.Utilisateur;
import com.AtlasVoteGate.AtlasVoteGate.security.jwt.config.JwtTokenUtil;
import com.AtlasVoteGate.AtlasVoteGate.security.jwt.model.JwtRequest;
import com.AtlasVoteGate.AtlasVoteGate.security.jwt.model.JwtResponse;
import com.AtlasVoteGate.AtlasVoteGate.security.service.JwtUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    private UtilisateurService utilisateurService;
    private AppointmentService appointmentService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService,
                                       UtilisateurService utilisateurService,AppointmentService appointmentService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.utilisateurService = utilisateurService;
        this.appointmentService=appointmentService;

    }


    @PostMapping(value = "/auth/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final String USERNAME = authenticationRequest.getUsername();
        final String PASSWORD = authenticationRequest.getPassword();

        authenticate(USERNAME, PASSWORD);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(USERNAME);

        Utilisateur utilisateur = utilisateurService.getUtilisateur(USERNAME);
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), utilisateur));
    }



   /* @PostMapping(value = "/auth/signup")
    public ResponseEntity<?> saveUser(@RequestBody Utilisateur utilisateur) throws Exception {
        return ResponseEntity.ok(utilisateurService.save(utilisateur));

    }
    */
    @PostMapping(value = "/auth/signup")
    public ResponseEntity<?> saveAppointment(@RequestBody Appointment appointment) throws Exception {
        return ResponseEntity.ok(appointmentService.save(appointment));
    }



    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
