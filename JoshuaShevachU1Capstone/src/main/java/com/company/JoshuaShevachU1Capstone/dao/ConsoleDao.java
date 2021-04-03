package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.Console;

import java.util.List;

public interface ConsoleDao {

    Console addConsole(Console console);
    Console getConsole(int id);
    List<Console> getAllConsoles();
    List<Console> getConsolesByManufacturer(String manufacturer);
    void updateConsole(Console console);
    void deleteConsole(int id);

}
