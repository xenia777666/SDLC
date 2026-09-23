package com.example.view;

import com.example.controller.LifeCalculatorController;
import com.example.model.LifeCalculatorModel;
import com.example.model.LifeStats;
import com.example.model.ModelObserver;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainView extends JFrame implements ModelObserver {
    private static final DecimalFormat NUMBER = createFormat();

    private final LifeCalculatorModel model;
    private LifeCalculatorController controller;

    private String lastYears = "";
    private String lastHours = "";

    private final JLabel yearsValue = resultValue();
    private final JLabel hoursValue = resultValue();
    private final JLabel totalHoursValue = resultValue();
    private final JLabel totalDaysValue = resultValue();
    private final JLabel cursorValue = resultValue();
    private final JLabel clicksValue = resultValue();
    private final JLabel keysValue = resultValue();

    public MainView(LifeCalculatorModel model) {
        super("Калькулятор жизни");
        this.model = model;
        this.model.addObserver(this);
        buildUi();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(560, 520));
        pack();
        setLocationRelativeTo(null);
    }

    public void setController(LifeCalculatorController controller) {
        this.controller = controller;
    }

    public String getLastYears() {
        return lastYears;
    }

    public String getLastHours() {
        return lastHours;
    }

    public void rememberInput(String years, String hours) {
        this.lastYears = years;
        this.lastHours = hours;
    }

    @Override
    public void onModelChanged() {
        refresh();
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(0, 12));
        root.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        root.setBackground(new Color(0xF7F5F2));

        JLabel title = new JLabel("Калькулятор жизни", SwingConstants.LEFT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));

        JLabel hint = new JLabel("<html>По введённому стажу и часам за ПК программа оценит,<br>"
                + "сколько времени, кликов и километров курсора «набежало» за жизнь.</html>");
        hint.setForeground(new Color(0x4A4A4A));

        JButton enterButton = new JButton("Ввести данные");
        enterButton.setPreferredSize(new Dimension(180, 34));
        enterButton.addActionListener(e -> {
            if (controller != null) {
                controller.onEnterDataClicked();
            }
        });

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        title.setAlignmentX(LEFT_ALIGNMENT);
        hint.setAlignmentX(LEFT_ALIGNMENT);
        enterButton.setAlignmentX(LEFT_ALIGNMENT);
        header.add(title);
        header.add(Box.createVerticalStrut(8));
        header.add(hint);
        header.add(Box.createVerticalStrut(14));
        header.add(enterButton);

        JPanel results = new JPanel();
        results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));
        results.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Результаты расчёта"),
                BorderFactory.createEmptyBorder(8, 10, 10, 10)
        ));
        results.setBackground(Color.WHITE);
        results.add(row("Количество лет", yearsValue));
        results.add(row("Часов в день за компьютером", hoursValue));
        results.add(row("Всего часов за компьютером", totalHoursValue));
        results.add(row("Всего суток за компьютером", totalDaysValue));
        results.add(row("Пробег курсора, км", cursorValue));
        results.add(row("Примерно кликов мышью", clicksValue));
        results.add(row("Примерно нажатий клавиш", keysValue));

        root.add(header, BorderLayout.NORTH);
        root.add(results, BorderLayout.CENTER);
        setContentPane(root);
        refresh();
    }

    private void refresh() {
        if (!model.hasResult()) {
            String empty = "—";
            yearsValue.setText(empty);
            hoursValue.setText(empty);
            totalHoursValue.setText(empty);
            totalDaysValue.setText(empty);
            cursorValue.setText(empty);
            clicksValue.setText(empty);
            keysValue.setText(empty);
            return;
        }

        LifeStats stats = model.getStats();
        yearsValue.setText(String.valueOf(stats.years()));
        hoursValue.setText(NUMBER.format(stats.hoursPerDay()));
        totalHoursValue.setText(NUMBER.format(stats.totalHours()));
        totalDaysValue.setText(NUMBER.format(stats.totalDays()));
        cursorValue.setText(NUMBER.format(stats.cursorKm()));
        clicksValue.setText(NUMBER.format(stats.clicks()));
        keysValue.setText(NUMBER.format(stats.keystrokes()));
    }

    private static JPanel row(String caption, JLabel value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        JLabel label = new JLabel(caption);
        label.setForeground(new Color(0x333333));
        panel.add(label, BorderLayout.WEST);
        panel.add(value, BorderLayout.EAST);
        return panel;
    }

    private static JLabel resultValue() {
        JLabel label = new JLabel("—");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        return label;
    }

    private static DecimalFormat createFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("ru", "RU"));
        DecimalFormat format = new DecimalFormat("#,##0.##", symbols);
        format.setGroupingUsed(true);
        return format;
    }
}
