package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        List<MealTo> meals = service.getAll(SecurityUtil.authUserId());
        return meals;
    }

    public List<MealTo> getAll(int startDate, int startTime, int endDate, int endTime) {
        // TODO: разобраться какого типа должны быть параметры
        return null;
    }

    public MealTo get(int mealId) throws NotFoundException {
        int userId = SecurityUtil.authUserId();
        MealTo meal = service.get(mealId, userId);
        if (meal == null) {
            throw new NotFoundException("Couldn't get meal " + mealId + " for user " + userId);
        }
        return meal;
    }

    public int delete(int mealId) throws NotFoundException {
        int userId = SecurityUtil.authUserId();
        boolean isDeleted = service.delete(mealId, userId);
        if (!isDeleted) {
            throw new NotFoundException("Couldn't delete meal " + mealId + " for user " + userId);
        }
        return 1;
    }

    public MealTo save(Meal meal) throws NotFoundException {
        int userId = SecurityUtil.authUserId();
        MealTo mealTo = service.save(meal, userId);
        if (mealTo == null) {
            throw new NotFoundException("Couldn't get save " + meal.toString() + " for user " + userId);
        }
        return mealTo;
    }

    public int update(Meal meal) throws NotFoundException {
        int userId = SecurityUtil.authUserId();
        int mealId = service.update(meal, userId);
        if (mealId == 0) {
            throw new NotFoundException("Couldn't update meal " + meal.toString() + " for user " + userId);
        }
        return mealId;
    }

}