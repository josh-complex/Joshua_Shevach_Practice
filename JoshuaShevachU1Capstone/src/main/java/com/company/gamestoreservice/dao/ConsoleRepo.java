package com.company.gamestoreservice.dao;

import com.company.gamestoreservice.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsoleRepo extends JpaRepository<Console, Integer> {

    List<Console> findAllByManufacturer(String manufacturer);

}
