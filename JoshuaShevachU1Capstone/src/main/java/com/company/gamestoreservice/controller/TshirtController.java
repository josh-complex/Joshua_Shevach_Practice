package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.model.Tshirt;
import com.company.gamestoreservice.service.GameStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/t-shirts")
@RefreshScope
@CrossOrigin(origins = "*")
public class TshirtController {

    GameStoreService service;

    @Autowired
    public TshirtController(GameStoreService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt addTshirt(@Valid @RequestBody Tshirt tshirt) {
        return service.saveTshirt(tshirt);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable Integer id) {
        return service.getTshirtById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getAllTshirts() {
        return service.getAllTshirts();
    }

    @GetMapping("/c/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsByColor(@PathVariable String color) {
        return service.getTshirtsByColor(color);
    }

    @GetMapping("/s/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsBySize(@PathVariable String size) {
        return service.getTshirtsBySize(size);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@Valid @RequestBody Tshirt tshirt) {
        service.saveTshirt(tshirt);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable Integer id) {
        service.deleteTshirt(id);
    }

}
