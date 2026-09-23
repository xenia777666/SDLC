package com.example.model;

import java.util.ArrayList;
import java.util.List;


public class LifeCalculatorModel {
    private final List<ModelObserver> observers = new ArrayList<>();
    private LifeStats stats;

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public LifeStats getStats() {
        return stats;
    }

    public boolean hasResult() {
        return stats != null;
    }

    public void calculate(int years, double hoursPerDay) {
        this.stats = LifeStats.compute(years, hoursPerDay);
        notifyObservers();
    }

    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.onModelChanged();
        }
    }
}
