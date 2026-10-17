package id.ac.uniska.pbo2.p04;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class GaleriLayout extends JFrame {

    private JButton button1;

    public GaleriLayout() {
        initComponents();

        catatanJudulLabel.putClientProperty("FlatLaf.styleClass", "h3");
        setSize(640, 400);
        setLocationRelativeTo(null);
    }

    private JLabel catatanJudulLabel;

    private void initComponents() {
        setTitle("Galeri Layout Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane layoutTab = new JTabbedPane();


        JPanel borderPanel = new JPanel();
        borderPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        borderPanel.setLayout(new BorderLayout(8, 8));
        borderPanel.add(new JButton("NORTH"), BorderLayout.NORTH);
        borderPanel.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        borderPanel.add(new JButton("WEST"), BorderLayout.WEST);
        borderPanel.add(new JButton("EAST"), BorderLayout.EAST);
        borderPanel.add(new JButton("CENTER"), BorderLayout.CENTER);
        layoutTab.addTab("BorderLayout", borderPanel);


        JPanel flowPanel = new JPanel();
        flowPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        flowPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 8));
        flowPanel.add(new JButton("Baru"));
        flowPanel.add(new JButton("Buka"));
        flowPanel.add(new JButton("Simpan"));
        flowPanel.add(new JButton("Simpan Sebagai"));
        flowPanel.add(new JButton("Cetak"));
        flowPanel.add(new JButton("Bagikan"));
        flowPanel.add(new JButton("Keluar"));
        layoutTab.addTab("FlowLayout", flowPanel);


        JPanel gridPanel = new JPanel();
        gridPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        gridPanel.setLayout(new GridLayout(3, 3, 8, 8));
        for (int i = 1; i <= 9; i++) {
            gridPanel.add(new JButton(String.valueOf(i)));
        }
        layoutTab.addTab("GridLayout", gridPanel);


        JPanel boxPanel = new JPanel();
        boxPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.add(new JButton("Dashboard"));
        boxPanel.add(new JButton("Data Mahasiswa"));
        boxPanel.add(new JButton("Data Mata Kuliah"));
        boxPanel.add(new JButton("Laporan"));
        layoutTab.addTab("BoxLayout", boxPanel);


        JPanel gabunganPanel = new JPanel(new BorderLayout(8, 8));
        gabunganPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        catatanJudulLabel = new JLabel("Catatan Harian");
        gabunganPanel.add(catatanJudulLabel, BorderLayout.NORTH);

        gabunganPanel.add(new JScrollPane(new JTextArea("Tulis catatan di sini")), BorderLayout.CENTER);

        JPanel hariPanel = new JPanel();
        hariPanel.setLayout(new BoxLayout(hariPanel, BoxLayout.Y_AXIS));
        hariPanel.add(new JButton("Senin"));
        hariPanel.add(new JButton("Selasa"));
        hariPanel.add(new JButton("Rabu"));
        gabunganPanel.add(hariPanel, BorderLayout.WEST);

        JPanel aksiPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        aksiPanel.add(new JButton("Hapus"));
        aksiPanel.add(new JButton("Simpan"));
        gabunganPanel.add(aksiPanel, BorderLayout.SOUTH);

        layoutTab.addTab("Gabungan", gabunganPanel);

        add(layoutTab, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> new GaleriLayout().setVisible(true));
    }
}