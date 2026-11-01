package id.ac.uniska.pbo2.p06;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FormAnggota extends JFrame {

    // GUI Components
    private JPanel mainPanel;
    private JPanel formPanel;
    private JLabel judulLabel;
    private JLabel keteranganLabel;
    private JLabel dataAnggotaLabel;
    private JLabel kontakLabel;

    private JTextField npmField;
    private JLabel npmPesanLabel;
    private JTextField namaField;
    private JLabel namaPesanLabel;
    private JComboBox<String> prodiCombo;
    private JLabel prodiPesanLabel;

    private JRadioButton mahasiswaRadio;
    private JRadioButton dosenRadio;
    private JRadioButton umumRadio;
    private ButtonGroup jenisGroup;

    private JTextField emailField;
    private JLabel emailPesanLabel;
    private JTextField hpField;
    private JLabel hpPesanLabel;

    private JTextArea alamatArea;
    private JCheckBox setujuCheck;

    private JLabel statusLabel;
    private JButton batalButton;
    private JButton daftarButton;

    public FormAnggota() {
        setContentPane(mainPanel);

        initComponentsManual();

        //FlatLaf
        judulLabel.putClientProperty("FlatLaf.styleClass", "h2");
        keteranganLabel.putClientProperty("FlatLaf.style", "foreground: $Label.disabledForeground");
        dataAnggotaLabel.putClientProperty("FlatLaf.styleClass", "h4");
        kontakLabel.putClientProperty("FlatLaf.styleClass", "h4");

        npmField.putClientProperty("JTextField.placeholderText", "Contoh: 2410010123");
        emailField.putClientProperty("JTextField.placeholderText", "nama@contoh.com");
        hpField.putClientProperty("JTextField.placeholderText", "08xxxxxxxxxx");

        //pesan kesalahan
        for (JLabel pesan : List.of(npmPesanLabel, namaPesanLabel, prodiPesanLabel, emailPesanLabel, hpPesanLabel)) {
            pesan.putClientProperty("FlatLaf.styleClass", "small");
            pesan.setForeground(warnaKesalahan());
        }
        statusLabel.setForeground(warnaKesalahan());

        getRootPane().setDefaultButton(daftarButton);
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    private void initComponentsManual() {
        jenisGroup = new ButtonGroup(); //radio button
        jenisGroup.add(mahasiswaRadio);
        jenisGroup.add(dosenRadio);
        jenisGroup.add(umumRadio);

        // validasi Input
        npmField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                if (!npmField.getText().isBlank()) periksaNpm();
            }
        });

        namaField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                if (!namaField.getText().isBlank()) periksaNama();
            }
        });

        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                if (!emailField.getText().isBlank()) periksaEmail();
            }
        });

        hpField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                if (!hpField.getText().isBlank()) periksaHp();
            }
        });

        // listen tombol dan box
        setujuCheck.addItemListener(evt -> daftarButton.setEnabled(setujuCheck.isSelected()));
        batalButton.addActionListener(evt -> batal());
        daftarButton.addActionListener(evt -> daftar());
    }

    //methods Validasi
    private boolean periksaNpm() {
        return tandai(npmField, npmPesanLabel, Validator.npm(npmField.getText()));
    }

    private boolean periksaNama() {
        return tandai(namaField, namaPesanLabel, Validator.nama(namaField.getText()));
    }

    private boolean periksaProdi() {
        String kesalahan = null;
        if (prodiCombo.getSelectedIndex() == 0) {
            kesalahan = "Pilih salah satu program studi";
        }
        return tandai(prodiCombo, prodiPesanLabel, kesalahan);
    }

    private boolean periksaEmail() {
        return tandai(emailField, emailPesanLabel, Validator.email(emailField.getText()));
    }

    private boolean periksaHp() {
        return tandai(hpField, hpPesanLabel, Validator.nomorHp(hpField.getText()));
    }

    private boolean tandai(JComponent isian, JLabel pesan, String kesalahan) {
        isian.putClientProperty("JComponent.outline", (kesalahan == null) ? null : "error");
        pesan.setText((kesalahan == null) ? " " : kesalahan);
        return kesalahan == null;
    }

    private void daftar() {
        List<JComponent> salah = new ArrayList<>();
        if (!periksaNpm()) salah.add(npmField);
        if (!periksaNama()) salah.add(namaField);
        if (!periksaProdi()) salah.add(prodiCombo);
        if (!periksaEmail()) salah.add(emailField);
        if (!periksaHp()) salah.add(hpField);

        if (!salah.isEmpty()) {
            statusLabel.setText(salah.size() + " isian perlu diperbaiki");
            salah.get(0).requestFocusInWindow();
            return;
        }

        statusLabel.setText("");
        String pesan = "Pendaftaran berhasil."
                + "\n\nNPM: " + npmField.getText()
                + "\nNama: " + namaField.getText().trim()
                + "\nJenis anggota: " + jenisAnggota();
        JOptionPane.showMessageDialog(this, pesan, "Pendaftaran Berhasil", JOptionPane.INFORMATION_MESSAGE);
        kosongkan();
    }

    private String jenisAnggota() {
        if (dosenRadio.isSelected()) return "Dosen";
        if (umumRadio.isSelected()) return "Umum";
        return "Mahasiswa";
    }

    private void batal() {
        if (adaIsian()) {
            int jawab = JOptionPane.showConfirmDialog(this,
                    "Data yang sudah diisi akan dihapus. Lanjutkan?", "Konfirmasi",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (jawab != JOptionPane.YES_OPTION) return;
        }
        kosongkan();
    }

    private boolean adaIsian() {
        return !npmField.getText().isBlank() || !namaField.getText().isBlank()
                || !emailField.getText().isBlank() || !hpField.getText().isBlank()
                || !alamatArea.getText().isBlank();
    }

    private void kosongkan() {
        for (JTextField isian : List.of(npmField, namaField, emailField, hpField)) {
            isian.setText("");
        }
        alamatArea.setText("");
        prodiCombo.setSelectedIndex(0);
        mahasiswaRadio.setSelected(true);
        setujuCheck.setSelected(false);

        tandai(npmField, npmPesanLabel, null);
        tandai(namaField, namaPesanLabel, null);
        tandai(prodiCombo, prodiPesanLabel, null);
        tandai(emailField, emailPesanLabel, null);
        tandai(hpField, hpPesanLabel, null);

        statusLabel.setText("");
        npmField.requestFocusInWindow();
    }

    static Color warnaKesalahan() {
        return FlatLaf.isLafDark() ? new Color(0xFF8A80) : new Color(0xC62828);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> new FormAnggota().setVisible(true));
    }
}
