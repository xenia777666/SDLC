package com.example.controller;

import com.example.model.LifeCalculatorModel;
import com.example.view.InputDialog;
import com.example.view.MainView;

import javax.swing.JOptionPane;

public class LifeCalculatorController {
    private final LifeCalculatorModel model;
    private final MainView view;

    public LifeCalculatorController(LifeCalculatorModel model, MainView view) {
        this.model = model;
        this.view = view;
    }

    public void onEnterDataClicked() {
        InputDialog dialog = new InputDialog(view, view.getLastYears(), view.getLastHours());
        dialog.setVisible(true);
        if (!dialog.isConfirmed()) {
            return;
        }

        try {
            int years = parseYears(dialog.getYearsText());
            double hours = parseHours(dialog.getHoursText());
            view.rememberInput(dialog.getYearsText(), dialog.getHoursText());
            model.calculate(years, hours);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    view,
                    ex.getMessage(),
                    "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private int parseYears(String raw) {
        if (raw.isBlank()) {
            throw new IllegalArgumentException("Введите количество лет.");
        }
        if (raw.contains(".") || raw.contains(",")) {
            throw new IllegalArgumentException("Количество лет должно быть целым числом.");
        }
        int years;
        try {
            years = Integer.parseInt(raw);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Количество лет должно быть целым числом.");
        }
        if (years <= 0 || years > 120) {
            throw new IllegalArgumentException("Количество лет должно быть от 1 до 120.");
        }
        return years;
    }

    private double parseHours(String raw) {
        if (raw.isBlank()) {
            throw new IllegalArgumentException("Введите число часов в день.");
        }
        double hours;
        try {
            hours = Double.parseDouble(raw.replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Часы в день должны быть числом.");
        }
        if (hours <= 0 || hours > 24) {
            throw new IllegalArgumentException("Часы в день должны быть больше 0 и не больше 24.");
        }
        return hours;
    }
}
