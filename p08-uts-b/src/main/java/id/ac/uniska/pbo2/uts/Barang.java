// Nama: Muhammad Ali Akbar
// NPM : 2410010012
package id.ac.uniska.pbo2.uts;

// enum
enum Kategori {
    ALAT_TULIS("Alat Tulis"),
    MAKANAN("Makanan"),
    MINUMAN("Minuman"),
    LAINNYA("Lainnya");

    private final String label;

    Kategori(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}

public class Barang {
    private final String nama;
    private final Kategori kategori;
    private final int harga;
    private final int jumlah;

    public Barang(String nama, Kategori kategori, int harga, int jumlah) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public String getNama() { return nama; }
    public Kategori getKategori() { return kategori; }
    public int getHarga() { return harga; }
    public int getJumlah() { return jumlah; }

    // hitung subtotal
    public int getSubtotal() {
        return harga * jumlah;
    }
}