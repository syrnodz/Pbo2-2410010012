package id.ac.uniska.pbo2.p04;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class FormAnggotaPerpustakaan extends JFrame {

    public FormAnggotaPerpustakaan() {
        setTitle("Form Pendaftaran Anggota Perpustakaan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 450);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("ID Anggota:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField idField = new JTextField(15);
        idField.setText("LIB-2026-001");
        idField.setEditable(false);
        add(idField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Nama Lengkap:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField namaField = new JTextField(20);
        add(namaField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Jenis Anggota:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        String[] jenisList = {"Mahasiswa", "Dosen", "Umum"};
        JComboBox<String> jenisCombo = new JComboBox<>(jenisList);
        add(jenisCombo, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("No. Telepon:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextField telpField = new JTextField(15);
        add(telpField, gbc);


        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton batalButton = new JButton("Batal");
        JButton daftarButton = new JButton("Daftar");
        buttonPanel.add(batalButton);
        buttonPanel.add(daftarButton);

        add(buttonPanel, gbc);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> new FormAnggotaPerpustakaan().setVisible(true));
    }
}