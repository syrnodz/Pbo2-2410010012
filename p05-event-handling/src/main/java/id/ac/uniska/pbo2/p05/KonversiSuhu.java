package id.ac.uniska.pbo2.p05;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class KonversiSuhu extends JFrame {

    private JPanel isiPanel;
    private JTextField celsiusField;
    private JComboBox<String> satuanCombo;
    private JLabel hasilLabel;
    private JButton konversiButton;

    public KonversiSuhu() {
        isiPanel = new JPanel(new java.awt.GridLayout(0, 1, 0, 6));
        isiPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        celsiusField = new JTextField(12);
        satuanCombo = new JComboBox<>(new String[]{"Fahrenheit", "Kelvin", "Reamur"});
        konversiButton = new JButton("Konversi");
        hasilLabel = new JLabel("Hasil akan tampil di sini");

        isiPanel.add(new JLabel("Suhu dalam Celsius"));
        isiPanel.add(celsiusField);
        isiPanel.add(new JLabel("Konversi ke"));
        isiPanel.add(satuanCombo);
        isiPanel.add(konversiButton);
        isiPanel.add(hasilLabel);

        setContentPane(isiPanel);
        setTitle("Konversi Suhu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        celsiusField.putClientProperty("JTextField.placeholderText", "Contoh: 36.5");
        hasilLabel.putClientProperty("FlatLaf.styleClass", "h3");


        celsiusField.addActionListener(evt -> konversi());

        celsiusField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                konversi();
            }
        });

        satuanCombo.addItemListener(evt -> {

            if (evt.getStateChange() == ItemEvent.SELECTED) {
                konversi();
            }
        });

        konversiButton.addActionListener(evt -> konversi());

        pack();
        setLocationRelativeTo(null);
    }


    private void konversi() {
        String teks = celsiusField.getText().trim().replace(',', '.');
        if (teks.isEmpty()) {
            hasilLabel.setText("Hasil akan tampil di sini");
            return;
        }
        try {
            double celsius = Double.parseDouble(teks);
            String satuan = (String) satuanCombo.getSelectedItem();
            double hasil = switch (satuan) {
                case "Fahrenheit" -> KonverterSuhu.keFahrenheit(celsius);
                case "Kelvin" -> KonverterSuhu.keKelvin(celsius);
                default -> KonverterSuhu.keReamur(celsius);
            };
            hasilLabel.setText(String.format("%.2f %s", hasil, satuan));
        } catch (NumberFormatException ex) {
            hasilLabel.setText("Masukkan angka yang valid");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KonversiSuhu().setVisible(true));
    }
}