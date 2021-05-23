package com.company.gamestore.dao;

import com.company.gamestore.model.Console;

import java.util.List;

public interface ConsoleDao {
    Console addConsole(Console console);

    Console getConsole(long id);

    List<Console> getAllConsoles();

    void updateConsole(Console console);

    void deleteConsole(long id);

    List<Console> getConsolesByManufacturer(String manufacturer);
}
