// Nama: Muhammad Ali Akbar
// NPM : 2410010012
package id.ac.uniska.pbo2.uts;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BelanjaTableModel extends AbstractTableModel {
    private final List<Barang> daftarBarang = new ArrayList<>();
    private final String[] namaKolom = {"Nama Barang", "Kategori", "Harga", "Jumlah", "Subtotal"};
    private final NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public BelanjaTableModel() {
        rupiahFormat.setMaximumFractionDigits(0);
    }

    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
        fireTableRowsInserted(daftarBarang.size() - 1, daftarBarang.size() - 1);
    }

    // pengecekan barang
    public boolean isBarangExist(String nama) {
        for (Barang b : daftarBarang) {
            if (b.getNama().equalsIgnoreCase(nama)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getRowCount() {
        return daftarBarang.size();
    }

    @Override
    public int getColumnCount() {
        return namaKolom.length;
    }

    @Override
    public String getColumnName(int column) {
        return namaKolom[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Barang b = daftarBarang.get(rowIndex);
        switch (columnIndex) {
            case 0: return b.getNama();
            case 1: return b.getKategori().toString();
            case 2: return rupiahFormat.format(b.getHarga());
            case 3: return b.getJumlah();
            case 4: return rupiahFormat.format(b.getSubtotal());
            default: return null;
        }
    }

    // total jumlah seluruh
    public int getTotal() {
        int total = 0;
        for (Barang b : daftarBarang) {
            total += b.getSubtotal();
        }
        return total;
    }

    public int getDiskon() {
        int total = getTotal();
        if (total >= 100000) {
            return (int) Math.floor(total * 0.10);
        }
        return 0;
    }

    // bayar total
    public int getBayar() {
        return getTotal() - getDiskon();
    }

    public String getRingkasanText() {
        return String.format("Total %s, diskon %s, bayar %s",
                rupiahFormat.format(getTotal()),
                rupiahFormat.format(getDiskon()),
                rupiahFormat.format(getBayar()));
    }
}