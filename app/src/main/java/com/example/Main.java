package com.example;

import com.example.controller.LifeCalculatorController;
import com.example.model.LifeCalculatorModel;
import com.example.view.MainView;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            LifeCalculatorModel model = new LifeCalculatorModel();
            MainView view = new MainView(model);
            LifeCalculatorController controller = new LifeCalculatorController(model, view);
            view.setController(controller);
            view.setVisible(true);
        });
    }
}
