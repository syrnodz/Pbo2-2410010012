package id.ac.uniska.pbo2.p07;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.EventQueue;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class AplikasiKontak extends JFrame {
    private final KotakTableModel model = new KotakTableModel();
    private final TableRowSorter<KotakTableModel> sorter = new TableRowSorter<>(model);
    private JPanel mainPanel;
    private JPanel atasPanel;
    private JPanel tombolPanel;
    private JScrollPane tabelScroll;
    private JTable kontakTable;
    private JTextField cariField;
    private JLabel statusLabel;
    private JButton tambahButton;
    private JButton ubahButton;
    private JButton hapusButton;

    // Item Menu Bar
    private JMenuItem tambahItem;
    private JMenuItem ubahItem;
    private JMenuItem hapusItem;
    private JMenuItem keluarItem;
    private JMenuItem tentangItem;

    public AplikasiKontak() {
        super("Daftar Kontak");

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bar
        buatMenu();

        cariField.putClientProperty("JTextField.placeholderText", "Cari nama, telepon, email");
        kontakTable.setModel(model);
        kontakTable.setRowSorter(sorter);
        kontakTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        kontakTable.getColumnModel().getColumn(0).setPreferredWidth(170);
        kontakTable.getColumnModel().getColumn(2).setPreferredWidth(200);

        // Listener Event
        tambahButton.addActionListener(e -> tambah());
        ubahButton.addActionListener(e -> ubah());
        hapusButton.addActionListener(e -> hapus());

        cariField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                saring();
            }
        });

        kontakTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && kontakTable.getSelectedRow() >= 0) {
                    ubah();
                }
            }
        });

        kontakTable.getSelectionModel().addListSelectionListener(e -> perbaruiTombol());

        isiContoh();
        perbaruiTombol();
        perbaruiStatus();

        setSize(780, 460);
        setLocationRelativeTo(null);
    }

    private void buatMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Menu File
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        keluarItem = new JMenuItem("Keluar");
        keluarItem.addActionListener(e -> dispose());
        fileMenu.add(keluarItem);

        // Menu Kontak
        JMenu kontakMenu = new JMenu("Kontak");
        kontakMenu.setMnemonic('K');

        tambahItem = new JMenuItem("Tambah");
        tambahItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        tambahItem.addActionListener(e -> tambah());

        ubahItem = new JMenuItem("Ubah");
        ubahItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        ubahItem.addActionListener(e -> ubah());

        hapusItem = new JMenuItem("Hapus");
        hapusItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        hapusItem.addActionListener(e -> hapus());

        kontakMenu.add(tambahItem);
        kontakMenu.add(ubahItem);
        kontakMenu.addSeparator();
        kontakMenu.add(hapusItem);

        // Menu Bantuan
        JMenu bantuanMenu = new JMenu("Bantuan");
        bantuanMenu.setMnemonic('B');
        tentangItem = new JMenuItem("Tentang");
        tentangItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Daftar Kontak\nMini proyek Pertemuan 7 PBO 2", "Tentang",
                JOptionPane.INFORMATION_MESSAGE));
        bantuanMenu.add(tentangItem);

        menuBar.add(fileMenu);
        menuBar.add(kontakMenu);
        menuBar.add(bantuanMenu);

        setJMenuBar(menuBar);
    }

    private void isiContoh() {
        model.tambah(new Kontak("Siti Rahmah", "081234567890", "siti@contoh.com", Kategori.KAMPUS));
        model.tambah(new Kontak("Budi Santoso", "082112345678", "budi@contoh.com", Kategori.TEMAN));
        model.tambah(new Kontak("Ahmad Fauzi", "085398765432", "", Kategori.KELUARGA));
        model.tambah(new Kontak("Nur Aisyah", "081355512345", "aisyah@contoh.com", Kategori.KAMPUS));
        model.tambah(new Kontak("Rizky Pratama", "087812341234", "rizky@contoh.com", Kategori.KERJA));
    }

    private void saring() {
        String kata = cariField.getText().trim();
        if (kata.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(kata), 0, 1, 2));
        }
        perbaruiStatus();
    }

    private int barisTerpilih() {
        int barisTampilan = kontakTable.getSelectedRow();
        if (barisTampilan < 0) return -1;
        return kontakTable.convertRowIndexToModel(barisTampilan);
    }

    private void tambah() {
        Kontak baru = new DialogKontak(this, "Tambah Kontak", null).tampilkan();
        if (baru != null) {
            model.tambah(baru);
            perbaruiStatus();
        }
    }

    private void ubah() {
        int baris = barisTerpilih();
        if (baris < 0) return;
        Kontak hasil = new DialogKontak(this, "Ubah Kontak", model.get(baris)).tampilkan();
        if (hasil != null) {
            model.ubah(baris, hasil);
        }
    }

    private void hapus() {
        int baris = barisTerpilih();
        if (baris < 0) return;
        String nama = model.get(baris).nama();
        int jawab = JOptionPane.showConfirmDialog(this, "Hapus kontak " + nama + "?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (jawab == JOptionPane.YES_OPTION) {
            model.hapus(baris);
            perbaruiStatus();
        }
    }

    private void perbaruiTombol() {
        boolean adaPilihan = kontakTable.getSelectedRow() >= 0;
        if (ubahButton != null) ubahButton.setEnabled(adaPilihan);
        if (hapusButton != null) hapusButton.setEnabled(adaPilihan);
        if (ubahItem != null) ubahItem.setEnabled(adaPilihan);
        if (hapusItem != null) hapusItem.setEnabled(adaPilihan);
    }

    private void perbaruiStatus() {
        statusLabel.setText("Menampilkan " + kontakTable.getRowCount() + " dari " + model.getRowCount() + " kontak");
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> new AplikasiKontak().setVisible(true));
    }
}