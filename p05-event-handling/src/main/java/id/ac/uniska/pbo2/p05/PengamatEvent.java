package id.ac.uniska.pbo2.p05;

import javax.swing.*;

public class PengamatEvent extends JFrame {

    private JScrollPane logScroll;
    private int nomor = 1;
    private JPanel panel1;
    private JButton klikButton;
    private JCheckBox fiturCheck;
    private JComboBox<String> waktuCombo;
    private JTextField isianField;
    private JTextArea logArea;


    public PengamatEvent() {
        panel1 = new JPanel(new java.awt.BorderLayout());
        klikButton = new JButton("Klik saya");
        fiturCheck = new JCheckBox("Aktifkan fitur");
        waktuCombo = new JComboBox<>(new String[]{"Pagi", "Siang", "Malam"});
        isianField = new JTextField(10);
        logArea = new JTextArea(12, 40);
        logArea.setEditable(false);

        JPanel atasPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 8));
        atasPanel.add(klikButton);
        atasPanel.add(fiturCheck);
        atasPanel.add(waktuCombo);
        atasPanel.add(isianField);

        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));

        panel1.add(atasPanel, java.awt.BorderLayout.NORTH);
        panel1.add(logScroll, java.awt.BorderLayout.CENTER);

        setContentPane(panel1);
        setTitle("Pengamat Event");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        logArea.putClientProperty("FlatLaf.styleClass", "monospaced");


        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent evt) {
                catat("WindowEvent", "jendela dibuka");
            }
        });

        klikButton.addActionListener(e -> catat("ActionEvent", "tombol diklik"));
        klikButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                catat("MouseEvent", "kursor masuk ke tombol");
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                catat("MouseEvent", "kursor keluar dari tombol");
            }
        });

        fiturCheck.addItemListener(evt -> {
            String status = fiturCheck.isSelected() ? "kotak dicentang" : "centang dihapus";
            catat("ItemEvent", status);
        });

        waktuCombo.addItemListener(evt -> {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                catat("ItemEvent", "memilih " + evt.getItem());
            }
        });

        isianField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                catat("KeyEvent", "mengetik huruf " + evt.getKeyChar());
            }
        });

        isianField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                catat("FocusEvent", "isian mendapat fokus");
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                catat("FocusEvent", "isian kehilangan fokus");
            }
        });
    }


    private void catat(String jenis, String keterangan) {
        logArea.append(String.format("%02d %-12s %s\n", nomor++, jenis, keterangan));
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PengamatEvent().setVisible(true));
    }
}