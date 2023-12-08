package org.system.onlineelection.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.system.onlineelection.application.mapper.CandidateDto;
import org.system.onlineelection.application.mapper.ResultDto;
import org.system.onlineelection.application.mapper.VoteDto;
import org.system.onlineelection.application.service.CandidateServiceImpl;
import org.system.onlineelection.application.service.ElectorServiceImpl;
import org.system.onlineelection.application.service.ResultServiceImpl;
import org.system.onlineelection.domain.model.PoliticalParty;
import org.system.onlineelection.infrastructure.adapter.payload.JwtResponse;
import org.system.onlineelection.infrastructure.adapter.payload.LoginRequest;
import org.system.onlineelection.infrastructure.adapter.payload.RegisterRequest;
import org.system.onlineelection.infrastructure.mapper.AuthResponse;
import org.system.onlineelection.application.service.AuthService;

import java.util.ArrayList;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CandidateServiceImpl candidateService;
    private final ResultServiceImpl resultService;
    private final ElectorServiceImpl electorService;

    @PostMapping(value = "login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("save-vote")
    public ResponseEntity<?> saveVote(@RequestBody VoteDto vote)
    {
        if(electorService.saveVote(vote))
        {
            return new ResponseEntity<>("Voto almacenado",HttpStatus.OK);
        }

        return new ResponseEntity<>("No se puede guardar el voto", HttpStatus.CONFLICT);
    }


    @GetMapping("all-candidate")
    public ResponseEntity<?> getCandidateOfPoliticalParties()
    {
        ArrayList<CandidateDto> candidateDtos = candidateService.getAllCandidates();
        if(!candidateDtos.isEmpty())
        {
            return new ResponseEntity<>(candidateDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encuentran candidatos", HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("online-results")
    public ResponseEntity<?> getResultsElectionOnline()
    {
        ArrayList<ResultDto> resultDtos = resultService.getResult();
        if(!resultDtos.isEmpty())
        {
            return new ResponseEntity<>(resultDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay resultados", HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("all-political-party")
    public ResponseEntity<?> getAllPoliticalParty()
    {
        ArrayList<PoliticalParty> politicalParties = electorService.getPoliticalParty();
        if(!politicalParties.isEmpty())
        {
            return new ResponseEntity<>(politicalParties, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay partidos politicos", HttpStatus.EXPECTATION_FAILED);
    }
}
