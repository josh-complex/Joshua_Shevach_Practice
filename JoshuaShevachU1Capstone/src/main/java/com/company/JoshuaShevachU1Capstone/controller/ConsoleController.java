package com.company.JoshuaShevachU1Capstone.controller;

import com.company.JoshuaShevachU1Capstone.model.Console;
import com.company.JoshuaShevachU1Capstone.service.GameStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consoles")
public class ConsoleController {

    GameStoreService service;

    @Autowired
    public ConsoleController(GameStoreService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@Valid @RequestBody Console console) {
        return service.addConsole(console);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable int id) {
        return service.getConsoleById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return service.getAllConsoles();
    }

    @GetMapping("/m")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsolesByManufacturer(@RequestParam(required = true) String manufacturer) {
        return service.getConsolesByManufacturer(manufacturer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@Valid @RequestBody Console console) {
        service.updateConsole(console);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        service.deleteConsole(id);
    }

}
