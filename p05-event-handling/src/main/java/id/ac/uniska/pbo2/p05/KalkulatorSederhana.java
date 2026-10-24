package id.ac.uniska.pbo2.p05;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorSederhana extends JFrame {

    private JPanel isiPanel;
    private JTextField angka1Field;
    private JTextField angka2Field;
    private JButton tambahButton;
    private JButton kurangButton;
    private JButton kaliButton;
    private JButton bagiButton;
    private JButton hapusButton;
    private JLabel hasilLabel;

    public KalkulatorSederhana() {
        isiPanel = new JPanel(new java.awt.GridLayout(3, 1, 0, 12));
        isiPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JPanel isianPanel = new JPanel(new java.awt.GridLayout(1, 2, 8, 0));
        angka1Field = new JTextField();
        angka2Field = new JTextField();
        isianPanel.add(angka1Field);
        isianPanel.add(angka2Field);

        JPanel operatorPanel = new JPanel(new java.awt.GridLayout(1, 4, 8, 0));
        tambahButton = new JButton("+");
        kurangButton = new JButton("-");
        kaliButton = new JButton("x");
        bagiButton = new JButton("/");
        operatorPanel.add(tambahButton);
        operatorPanel.add(kurangButton);
        operatorPanel.add(kaliButton);
        operatorPanel.add(bagiButton);

        JPanel bawahPanel = new JPanel(new java.awt.BorderLayout(8, 0));
        hasilLabel = new JLabel("Hasil: 0");
        hapusButton = new JButton("Hapus");
        bawahPanel.add(hasilLabel, java.awt.BorderLayout.CENTER);
        bawahPanel.add(hapusButton, java.awt.BorderLayout.EAST);

        isiPanel.add(isianPanel);
        isiPanel.add(operatorPanel);
        isiPanel.add(bawahPanel);

        setContentPane(isiPanel);
        setTitle("Kalkulator Sederhana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        angka1Field.putClientProperty("JTextField.placeholderText", "Angka pertama");
        angka2Field.putClientProperty("JTextField.placeholderText", "Angka kedua");
        hasilLabel.putClientProperty("FlatLaf.styleClass", "h3");


        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hitung('+');
            }
        });

        kurangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hitung('-');
            }
        });

        bagiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hitung('/');
            }
        });


        kaliButton.addActionListener(e -> hitung('x'));


        hapusButton.addActionListener(this::hapus);

        pack();
        setLocationRelativeTo(null);
    }

    private void hitung(char operator) {
        try {
            double a = Double.parseDouble(angka1Field.getText().trim());
            double b = Double.parseDouble(angka2Field.getText().trim());
            if (operator == '/' && b == 0) {
                hasilLabel.setText("Tidak dapat membagi dengan nol");
                return;
            }
            double hasil = switch (operator) {
                case '+' -> a + b;
                case '-' -> a - b;
                case 'x' -> a * b;
                default -> a / b;
            };
            hasilLabel.setText("Hasil: " + rapikan(a) + " " + operator + " " + rapikan(b) + " = " + rapikan(hasil));
        } catch (NumberFormatException ex) {
            hasilLabel.setText("Isi kedua angka dengan benar");
        }
    }

    private void hapus(ActionEvent e) {
        angka1Field.setText("");
        angka2Field.setText("");
        hasilLabel.setText("Hasil: 0");
        angka1Field.requestFocusInWindow();
    }

    private static String rapikan(double nilai) {
        if (nilai == Math.rint(nilai)) {
            return String.valueOf((long) nilai);
        }
        return String.valueOf(nilai);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KalkulatorSederhana().setVisible(true));
    }
}