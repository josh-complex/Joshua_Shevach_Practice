package com.company.gamestore.dao;

import com.company.gamestore.model.TShirt;

import java.util.List;

public interface TShirtDao {
    TShirt addTShirt(TShirt console);

    TShirt getTShirt(long id);

    List<TShirt> getAllTShirts();

    List<TShirt> getAllTShirtsByColor(String color);

    List<TShirt> getAllTShirtsBySize(String size);

    void updateTShirt(TShirt console);

    void deleteTShirt(long id);
}
