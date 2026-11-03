// Nama: Muhammad Ali Akbar
// NPM : 2410010012
package id.ac.uniska.pbo2.uts;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class DaftarBelanja extends javax.swing.JFrame {

    private final BelanjaTableModel tableModel = new BelanjaTableModel();

    public DaftarBelanja() {
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        initCustomLogic();
    }

    private void initCustomLogic() {
        setTitle("Daftar Belanja");
        tblBelanja.setModel(tableModel);

        cmbKategori.setModel(new DefaultComboBoxModel<>(Kategori.values()));
        spnJumlah.setModel(new SpinnerNumberModel(1, 1, 99, 1));

        lblError.setForeground(Color.RED);
        lblError.setText("");

        txtHarga.putClientProperty("JTextField.placeholderText", "Tanpa titik, contoh 15000");

        // event listener
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        btnBersihkan.addActionListener(this::btnBersihkanActionPerformed);

        getRootPane().setDefaultButton(btnTambah);

        updateRingkasan();
    }

    private void btnTambahActionPerformed(ActionEvent evt) {
        lblError.setText("");
        String nama = txtNama.getText().trim();
        String strHarga = txtHarga.getText().trim();

        if (nama.isEmpty()) {
            lblError.setText("Nama barang wajib diisi");
            txtNama.requestFocus();
            return;
        }

        if (tableModel.isBarangExist(nama)) {
            lblError.setText("Barang " + nama + " sudah ada di daftar");
            txtNama.requestFocus();
            return;
        }

        int harga;
        try {
            harga = Integer.parseInt(strHarga);
            if (harga < 1 || harga > 10000000) {
                lblError.setText("Harga harus angka 1 sampai 10000000");
                txtHarga.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            lblError.setText("Harga harus angka 1 sampai 10000000");
            txtHarga.requestFocus();
            return;
        }

        Kategori kategori = (Kategori) cmbKategori.getSelectedItem();
        int jumlah = (Integer) spnJumlah.getValue();

        Barang barangBaru = new Barang(nama, kategori, harga, jumlah);
        tableModel.tambahBarang(barangBaru);

        bersihkanForm();
        updateRingkasan();
    }

    private void btnBersihkanActionPerformed(ActionEvent evt) {
        bersihkanForm();
    }

    private void bersihkanForm() {
        txtNama.setText("");
        txtHarga.setText("");
        cmbKategori.setSelectedIndex(0);
        spnJumlah.setValue(1);
        lblError.setText("");
        txtNama.requestFocus();
    }

    private void updateRingkasan() {
        lblRingkasan.setText(tableModel.getRingkasanText());
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            new DaftarBelanja().setVisible(true);
        });
    }

    // komponen guiii
    private javax.swing.JPanel contentPane;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton btnBersihkan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<Kategori> cmbKategori;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblRingkasan;
    private javax.swing.JSpinner spnJumlah;
    private javax.swing.JTable tblBelanja;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNama;
}