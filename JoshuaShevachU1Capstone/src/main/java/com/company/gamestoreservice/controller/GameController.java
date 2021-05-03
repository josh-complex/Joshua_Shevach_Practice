package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.model.Game;
import com.company.gamestoreservice.service.GameStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/games")
@RefreshScope
public class GameController {

    GameStoreService service;

    @Autowired
    public GameController(GameStoreService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@Valid @RequestBody Game game) {
        return service.saveGame(game);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable Integer id) {
        return service.getGameById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return service.getAllGames();
    }

    @GetMapping("/r/{rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByEsrbRating(@PathVariable String rating) {
        return service.getGamesByEsrbRating(rating);
    }

    @GetMapping("/s/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(@PathVariable String studio) {
        return service.getGamesByStudio(studio);
    }

    @GetMapping("/t/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByTitle(@PathVariable String title) {
        return service.getGamesByTitle(title);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@Valid @RequestBody Game game) {
        service.saveGame(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id) {
        service.deleteGame(id);
    }
}
