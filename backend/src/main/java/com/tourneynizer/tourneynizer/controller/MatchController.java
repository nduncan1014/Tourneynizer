package com.tourneynizer.tourneynizer.controller;

import com.tourneynizer.tourneynizer.error.BadRequestException;
import com.tourneynizer.tourneynizer.error.InternalErrorException;
import com.tourneynizer.tourneynizer.model.ErrorMessage;
import com.tourneynizer.tourneynizer.model.Match;
import com.tourneynizer.tourneynizer.model.MatchStatus;
import com.tourneynizer.tourneynizer.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Controller("MatchController")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/api/tournament/{id}/match/getAll")
    public ResponseEntity<?> getAll(@CookieValue("session") String session, @PathVariable("id") long id) {
        try {
            List<Match> matches = matchService.findByTournament(id);
            return new ResponseEntity<Object>(matches, new HttpHeaders(), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<Object>(new ErrorMessage(e), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } catch (InternalErrorException e) {
            return new ResponseEntity<Object>(new ErrorMessage(e), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static final Map<String, Object> toReturn = getToReturn();

    private static Map<String, Object> getToReturn() {
        Map<String, Object> map = new HashMap<>();

        map.put("actual", MatchStatus.values());

        Map<MatchStatus, String> converter = MatchStatus.getStringMap();
        map.put("user", Arrays.stream(MatchStatus.values())
                .map(converter::get)
                .collect(Collectors.toList())
        );
        return map;
    }

    @GetMapping("/api/enum/match/status")
    public ResponseEntity<?> getEnum() {
        return new ResponseEntity<Object>(toReturn, new HttpHeaders(), HttpStatus.OK);
    }
}
