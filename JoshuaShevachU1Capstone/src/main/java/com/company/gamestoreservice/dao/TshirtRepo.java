package com.company.gamestoreservice.dao;

import com.company.gamestoreservice.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TshirtRepo extends JpaRepository<Tshirt, Integer> {

    List<Tshirt> findAllByColor(String color);
    List<Tshirt> findAllBySize(String size);

}
