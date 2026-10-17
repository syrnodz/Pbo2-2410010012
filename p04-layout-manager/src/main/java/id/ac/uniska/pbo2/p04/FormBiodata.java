package id.ac.uniska.pbo2.p04;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class FormBiodata extends JFrame {

    public FormBiodata() {
        setTitle("Form Biodata Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);


        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nama Lengkap:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField namaField = new JTextField(20);
        add(namaField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Jenis Kelamin:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JRadioButton lakiRadio = new JRadioButton("Laki-laki");
        JRadioButton perempuanRadio = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(lakiRadio);
        genderGroup.add(perempuanRadio);
        genderPanel.add(lakiRadio);
        genderPanel.add(perempuanRadio);
        add(genderPanel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Program Studi:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        String[] prodiList = {"Teknik Informatika", "Sistem Informasi", "Manajemen"};
        JComboBox<String> prodiCombo = new JComboBox<>(prodiList);
        add(prodiCombo, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Alamat:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextArea alamatArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(alamatArea);
        add(scrollPane, gbc);


        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        JButton simpanButton = new JButton("Simpan");
        add(simpanButton, gbc);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> new FormBiodata().setVisible(true));
    }
}