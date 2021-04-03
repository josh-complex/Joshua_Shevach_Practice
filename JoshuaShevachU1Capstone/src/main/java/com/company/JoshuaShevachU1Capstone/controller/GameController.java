package com.company.JoshuaShevachU1Capstone.controller;

import com.company.JoshuaShevachU1Capstone.model.Game;
import com.company.JoshuaShevachU1Capstone.service.GameStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    GameStoreService service;

    @Autowired
    public GameController(GameStoreService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@Valid @RequestBody Game game) {
        return service.addGame(game);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable int id) {
        return service.getGameById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return service.getAllGames();
    }

    @GetMapping("/r")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByEsrbRating(@RequestParam(required = true) String rating) {
        return service.getGamesByEsrbRating(rating);
    }

    @GetMapping("/s")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(@RequestParam(required = true) String studio) {
        return service.getGamesByStudio(studio);
    }

    @GetMapping("/t")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByTitle(@RequestParam(required = true) String title) {
        return service.getGamesByTitle(title);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@Valid @RequestBody Game game) {
        service.updateGame(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        service.deleteGame(id);
    }
}
