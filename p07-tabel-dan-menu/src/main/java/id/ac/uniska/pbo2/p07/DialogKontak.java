package id.ac.uniska.pbo2.p07;

import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import javax.swing.*;

public class DialogKontak extends JDialog {
    private Kontak hasil;
    private JPanel mainPanel;
    private JPanel formPanel;
    private JPanel tombolPanel;
    private JLabel namaLabel;
    private JTextField namaField;
    private JLabel teleponLabel;
    private JTextField teleponField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel kategoriLabel;
    private JComboBox<Kategori> kategoriCombo;
    private JLabel pesanLabel;
    private JButton batalButton;
    private JButton simpanButton;

    // Method
    private void createUIComponents() {
        kategoriCombo = new JComboBox<>(Kategori.values());
    }

    public DialogKontak(java.awt.Frame pemilik, String judul, Kontak awal) {
        super(pemilik, judul, true);

        //panel JDialog
        setContentPane(mainPanel);

        if (awal != null) {
            namaField.setText(awal.nama());
            teleponField.setText(awal.telepon());
            emailField.setText(awal.email());
            kategoriCombo.setSelectedItem(awal.kategori());
        }

        emailField.putClientProperty("JTextField.placeholderText", "Boleh dikosongkan");
        pesanLabel.putClientProperty("FlatLaf.styleClass", "small");
        pesanLabel.setForeground(FlatLaf.isLafDark() ? new Color(0xFF8A80) : new Color(0xC62828));

        batalButton.addActionListener(evt -> dispose());
        simpanButton.addActionListener(evt -> simpan());

        getRootPane().setDefaultButton(simpanButton);
        pack();
        setLocationRelativeTo(pemilik);
    }

    private void simpan() {
        String nama = namaField.getText().trim();
        String telepon = teleponField.getText().trim();

        if (nama.isEmpty()) {
            pesanLabel.setText("Nama wajib diisi");
            namaField.requestFocusInWindow();
            return;
        }

        if (!telepon.matches("\\d{10,13}")) {
            pesanLabel.setText("Telepon berisi 10 sampai 13 digit angka");
            teleponField.requestFocusInWindow();
            return;
        }

        Kategori kategori = (Kategori) kategoriCombo.getSelectedItem();
        hasil = new Kontak(nama, telepon, emailField.getText().trim(), kategori);
        dispose();
    }

    public Kontak tampilkan() {
        setVisible(true);
        return hasil;
    }
}