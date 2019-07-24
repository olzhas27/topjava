package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

public interface MealService {
    List<MealTo> getAll(int userId);

    MealTo get(int mealId, int userId);

    boolean delete(int mealId, int userId);

    MealTo save(Meal meal, int userId);

    int update(Meal meal, int userId);
}