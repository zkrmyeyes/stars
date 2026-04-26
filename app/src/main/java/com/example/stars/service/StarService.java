package com.example.stars.service;

import com.example.stars.R;
import com.example.stars.classes.Star;
import com.example.stars.dao.IDao;
import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {

    private static StarService instance;
    private List<Star> stars = new ArrayList<>();

    private StarService() {
        stars.add(new Star("Kate Bosworth", 3.0f, R.drawable.star1));
        stars.add(new Star("George Clooney", 3.0f, R.drawable.star2));
        stars.add(new Star("Michelle Rodriguez", 5.0f, R.drawable.star3));
        stars.add(new Star("George Clooney", 1.0f, R.drawable.star2));
    }

    public static StarService getInstance() {
        if (instance == null) instance = new StarService();
        return instance;
    }

    @Override
    public List<Star> getAll() { return stars; }
}