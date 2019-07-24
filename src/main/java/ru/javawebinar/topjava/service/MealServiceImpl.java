package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class MealServiceImpl implements MealService{
    private final Logger log = LoggerFactory.getLogger(MealServiceImpl.class);
    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MealTo> getAll(int userId) {
        Collection<Meal> meals = repository.getAll(userId);
        return MealsUtil.getWithExcess(meals, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public MealTo get(int mealId, int userId) {
        Meal meal = repository.get(mealId, userId);
        List<MealTo> meals = MealsUtil.getWithExcess(Collections.singletonList(meal), MealsUtil.DEFAULT_CALORIES_PER_DAY);
        return meals.isEmpty() ? null : meals.get(0);
    }

    @Override
    public MealTo delete(int mealId, int userId) {
        repository.delete(mealId, userId);
        return null;
    }

    @Override
    public MealTo save(Meal meal, int userId) {
        return null;
    }

    @Override
    public int update(Meal meal, int userId) {
        return 0;
    }
}