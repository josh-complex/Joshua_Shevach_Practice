package com.company.JoshuaShevachU1Capstone.controller;

import com.company.JoshuaShevachU1Capstone.model.Tshirt;
import com.company.JoshuaShevachU1Capstone.service.GameStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/t-shirts")
public class TshirtController {

    GameStoreService service;

    @Autowired
    public TshirtController(GameStoreService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt addTshirt(@Valid @RequestBody Tshirt tshirt) {
        return service.addTshirt(tshirt);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable int id) {
        return service.getTshirtById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getAllTshirts() {
        return service.getAllTshirts();
    }

    @GetMapping("/c")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsByColor(@RequestParam(required = true) String color) {
        return service.getTshirtsByColor(color);
    }

    @GetMapping("/s")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsBySize(@RequestParam(required = true) String size) {
        return service.getTshirtsBySize(size);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@Valid @RequestBody Tshirt tshirt) {
        service.updateTshirt(tshirt);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int id) {
        service.deleteTshirt(id);
    }

}
