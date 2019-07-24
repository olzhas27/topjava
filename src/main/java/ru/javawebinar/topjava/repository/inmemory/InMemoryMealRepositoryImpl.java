package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private Map<Integer, Integer> mealToUser = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, SecurityUtil.authUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            mealToUser.put(meal.getId(), userId);
            return meal;
        }
        // treat case: update, but absent in storage
        Meal meall = repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        mealToUser.put(meall.getId(), userId);
        return meall;
    }

    private boolean mealBelongsToUser(int mealId, int userId) {
        return userId == mealToUser.get(mealId);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (!mealBelongsToUser(id, userId)) {
            return false;
        }
        return repository.remove(id) != null;

    }

    @Override
    public Meal get(int id, int userId) {
        if (mealBelongsToUser(id, userId)) {
            return repository.get(id);
        } else {
            return null;
        }

    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().stream()
                .filter(meal -> mealBelongsToUser(meal.getId(), userId))
                .collect(Collectors.toList());
    }
}

