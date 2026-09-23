package com.example.model;


public record LifeStats(
        int years,
        double hoursPerDay,
        double totalHours,
        double totalDays,
        double cursorKm,
        long clicks,
        long keystrokes
) {
  
    public static final double CURSOR_KM_PER_HOUR = 0.2;

   
    public static final double CLICKS_PER_HOUR = 375.0;

    
    public static final double KEYSTROKES_PER_HOUR = 1250.0;

    public static LifeStats compute(int years, double hoursPerDay) {
        double totalHours = years * 365.25 * hoursPerDay;
        return new LifeStats(
                years,
                hoursPerDay,
                totalHours,
                totalHours / 24.0,
                totalHours * CURSOR_KM_PER_HOUR,
                Math.round(totalHours * CLICKS_PER_HOUR),
                Math.round(totalHours * KEYSTROKES_PER_HOUR)
        );
    }
}
