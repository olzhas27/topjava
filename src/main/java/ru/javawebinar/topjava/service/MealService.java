package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService {
    List<MealTo> getAll(int userId);

    List<MealTo> getAll(LocalDateTime start, LocalDateTime end, int userId);

    MealTo get(int mealId, int userId);

    boolean delete(int mealId, int userId);

    int save(Meal meal, int userId);

    int update(Meal meal, int userId);
}