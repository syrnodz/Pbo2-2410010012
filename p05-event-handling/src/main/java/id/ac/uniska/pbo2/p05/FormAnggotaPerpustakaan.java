package id.ac.uniska.pbo2.p05;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormAnggotaPerpustakaan extends JFrame {

    private JPanel mainPanel;
    private JTextField idField;
    private JTextField npmField;
    private JLabel digitLabel;
    private JTextField namaField;
    private JComboBox<String> jenisCombo;
    private JTextField telpField;
    private JCheckBox setujuCheck;
    private JButton batalButton;
    private JButton daftarButton;

    public FormAnggotaPerpustakaan() {
        setTitle("Form Pendaftaran Anggota Perpustakaan");


        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);


        if (jenisCombo.getItemCount() == 0) {
            jenisCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Mahasiswa", "Dosen", "Umum"}));
        }


        daftarButton.setEnabled(false);


        setujuCheck.addItemListener(evt -> daftarButton.setEnabled(setujuCheck.isSelected()));


        batalButton.addActionListener(e -> {
            npmField.setText("");
            namaField.setText("");
            telpField.setText("");
            setujuCheck.setSelected(false);
            digitLabel.setText("0 dari 10 digit");
            npmField.requestFocusInWindow();
        });


        daftarButton.addActionListener(e -> {
            String ringkasan = String.format(
                    "ID: %s\nNPM: %s\nNama: %s\nJenis: %s\nNo. Telp: %s",
                    idField.getText(),
                    npmField.getText(),
                    namaField.getText(),
                    jenisCombo.getSelectedItem(),
                    telpField.getText()
            );
            JOptionPane.showMessageDialog(this, ringkasan, "Ringkasan Data Anggota", JOptionPane.INFORMATION_MESSAGE);
        });


        npmField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int panjang = npmField.getText().trim().length();
                digitLabel.setText(panjang + " dari 10 digit");
            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                int jawab = JOptionPane.showConfirmDialog(
                        FormAnggotaPerpustakaan.this,
                        "Keluar dari aplikasi?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );
                if (jawab == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> new FormAnggotaPerpustakaan().setVisible(true));
    }
}