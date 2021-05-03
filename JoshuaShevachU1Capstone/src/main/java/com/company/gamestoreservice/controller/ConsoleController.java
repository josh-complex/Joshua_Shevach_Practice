package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.model.Console;
import com.company.gamestoreservice.service.GameStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consoles")
@RefreshScope
public class ConsoleController {

    GameStoreService service;

    @Autowired
    public ConsoleController(GameStoreService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@Valid @RequestBody Console console) {
        return service.saveConsole(console);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable Integer id) {
        return service.getConsoleById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return service.getAllConsoles();
    }

    @GetMapping("/m/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsolesByManufacturer(@PathVariable String manufacturer) {
        return service.getConsolesByManufacturer(manufacturer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@Valid @RequestBody Console console) {
        service.saveConsole(console);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer id) {
        service.deleteConsole(id);
    }

}
