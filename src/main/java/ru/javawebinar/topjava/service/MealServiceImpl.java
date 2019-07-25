package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {
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
    public List<MealTo> getAll(LocalDateTime startTime, LocalDateTime endTime, int userId) {
        return MealsUtil.getFilteredWithExcess(
            repository.getAll(userId),
            MealsUtil.DEFAULT_CALORIES_PER_DAY,
            startTime.toLocalTime(),
            endTime.toLocalTime()
        );
    }

    @Override
    public MealTo get(int mealId, int userId) {
        Meal meal = repository.get(mealId, userId);
        List<MealTo> meals = MealsUtil.getWithExcess(Collections.singletonList(meal), MealsUtil.DEFAULT_CALORIES_PER_DAY);
        return meals.isEmpty() ? null : meals.get(0);
    }

    @Override
    public boolean delete(int mealId, int userId) {
        return repository.delete(mealId, userId);
    }

    @Override
    public int save(Meal meal, int userId) {
        return repository.save(meal, userId).getId();
    }

    @Override
    public int update(Meal meal, int userId) {
        return repository.save(meal, userId).getId();
    }
}