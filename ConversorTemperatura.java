package com.conversor.temperatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConversorTemperatura extends JFrame implements ActionListener {
    private JTextField campoCelsius, campoFahrenheit, campoKelvin;
    private boolean bloqueioEvento = false;

    public ConversorTemperatura() {
        setTitle("Conversor de Temperatura");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(null);

        Font fonte = new Font("Segoe UI", Font.BOLD, 16);

        JLabel labelC = new JLabel("Celsius (°C):");
        labelC.setFont(fonte);
        campoCelsius = new JTextField();
        campoCelsius.setFont(fonte);

        JLabel labelF = new JLabel("Fahrenheit (°F):");
        labelF.setFont(fonte);
        campoFahrenheit = new JTextField();
        campoFahrenheit.setFont(fonte);

        JLabel labelK = new JLabel("Kelvin (K):");
        labelK.setFont(fonte);
        campoKelvin = new JTextField();
        campoKelvin.setFont(fonte);

        add(labelC);
        add(campoCelsius);
        add(labelF);
        add(campoFahrenheit);
        add(labelK);
        add(campoKelvin);

        campoCelsius.addActionListener(this);
        campoFahrenheit.addActionListener(this);
        campoKelvin.addActionListener(this);

        getContentPane().setBackground(new Color(30, 30, 30));
        labelC.setForeground(Color.WHITE);
        labelF.setForeground(Color.WHITE);
        labelK.setForeground(Color.WHITE);

        campoCelsius.setBackground(new Color(50, 50, 50));
        campoCelsius.setForeground(Color.WHITE);
        campoFahrenheit.setBackground(new Color(50, 50, 50));
        campoFahrenheit.setForeground(Color.WHITE);
        campoKelvin.setBackground(new Color(50, 50, 50));
        campoKelvin.setForeground(Color.WHITE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (bloqueioEvento) return;

        try {
            bloqueioEvento = true;
            Object source = e.getSource();

            if (source == campoCelsius) {
                double c = Double.parseDouble(campoCelsius.getText());
                campoFahrenheit.setText(String.format("%.2f", c * 9 / 5 + 32));
                campoKelvin.setText(String.format("%.2f", c + 273.15));
            } 
            else if (source == campoFahrenheit) {
                double f = Double.parseDouble(campoFahrenheit.getText());
                campoCelsius.setText(String.format("%.2f", (f - 32) * 5 / 9));
                campoKelvin.setText(String.format("%.2f", (f - 32) * 5 / 9 + 273.15));
            } 
            else if (source == campoKelvin) {
                double k = Double.parseDouble(campoKelvin.getText());
                campoCelsius.setText(String.format("%.2f", k - 273.15));
                campoFahrenheit.setText(String.format("%.2f", (k - 273.15) * 9 / 5 + 32));
            }
        } 
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um valor numérico válido!");
        } 
        finally {
            bloqueioEvento = false;
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        
        new ConversorTemperatura();
    }
}
