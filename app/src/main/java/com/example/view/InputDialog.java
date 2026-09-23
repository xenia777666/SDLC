package com.example.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


public class InputDialog extends JDialog {
    private final JTextField yearsField = new JTextField(12);
    private final JTextField hoursField = new JTextField(12);
    private boolean confirmed;

    public InputDialog(JFrame owner, String lastYears, String lastHours) {
        super(owner, "Ввод данных", true);
        yearsField.setText(lastYears);
        hoursField.setText(lastHours);
        buildUi();
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    private void buildUi() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(16, 18, 8, 18));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(new JLabel("Количество лет:"), gbc);
        gbc.gridx = 1;
        form.add(yearsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(new JLabel("Часов в день за компьютером:"), gbc);
        gbc.gridx = 1;
        form.add(hoursField, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Отмена");
        ok.addActionListener(e -> confirm());
        cancel.addActionListener(e -> dispose());
        buttons.add(ok);
        buttons.add(cancel);
        getRootPane().setDefaultButton(ok);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    private void confirm() {
        confirmed = true;
        dispose();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getYearsText() {
        return yearsField.getText().trim();
    }

    public String getHoursText() {
        return hoursField.getText().trim();
    }
}
