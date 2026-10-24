### Tugas Pbo2 p05-event-handling 2410010012
Muhammad Ali Akbar 2410010012 5C

IDE Menggunakan IntelliJ IDEA OSS
### Hasil screenshot log Commit
[img/screenshot-2026-10-24_10.02.57.png]

### Hasil screenshot Properties, Events dan Tombol daftar
[img/screenshot-2026-10-24_08.55.48.png]

[img/screenshot-20screenshot-2026-10-24_08.55.48.png]

[img/screenshot-2026-10-24_08.57.03.png]

[img/screenshot-2026-10-24_08.57.43.png]

[img/screenshot-2026-10-24_08.59.44.png]

### Jawaban Refleksi p05-event-handling

1. Sumber Event (Event Source): Komponen GUI tempat suatu aksi terjadi. Contoh: celsiusField atau klikButton.
Object Event (Event Object): Objek yang membawa informasi detail mengenai aksi yang baru saja terjadi. Contoh: KeyEvent evt membawa informasi tombol keyboard yang diketik, atau ActionEvent evt saat tombol diklik.
Listener Objek yang menunggu (listen) dan mengeksekusi kode respons saat event terjadi. Contoh: ActionListener yang menjalankan fungsi konversi saat tombol diklik.

2. Lambda di Java hanya dapat digunakan untuk functional interface (interface yang hanya memiliki satu abstract method). Sementara itu, KeyListener memiliki tiga method (keyPressed, keyReleased, keyTyped) dan
cara mengatasinya Menggunakan kelas Adapter (KeyAdapter). Adapter menyediakan implementasi kosong untuk semua method KeyListener, sehingga kita cukup override method yang dibutuhkan

3. Kelas KonverterSuhu murni menangani perhitungan tanpa terikat pada tampilan Swing dan Rumus konversi dapat dipanggil dari mana saja (misalnya dari aplikasi konsol, web, atau form lain)
Perhitungan rumus matematika bisa dengan mudah dibuatkan unit test tanpa perlu menjalankan antarmuka GUI

4. Event itemStateChanged dipicu dua kali setiap kali pilihan diganti: sekali untuk melepaskan status item lama (DESELECTED) dan sekali untuk menetapkan item baru (SELECTED)
memastikan agar kode perhitungan atau respons aplikasi hanya dijalankan satu kali saja pada pilihan yang baru.

5. Digunakan untuk aksi form utama yang dibuat melalui antarmuka visual/desain agar terstruktur dan otomatis dikelola oleh IDE
sedangkan lambda Digunakan untuk penanganan aksi singkat/satu baris yang ditulis langsung di kode konstruktor, dan method reference digunakan ketika aksi event langsung memanggil sebuah method spesifik yang sudah ada tanpa perlu manipulasi parameter tambahan



### Deklarasi penggunaan ai/llm:

- Praktikum 1: Saya lumayan dalam mengerjakan praktikum 1 dan memahami materi yang ada di modul, sehingga tidak terlalu membutuhkan bantuan ai/llm

- Praktikum 2: Saya agak kesulitan dalam memahami beberapa bagian materi dan menerapkan kode program jadi saya minta review source code yang mengalami error dan kasih saran dan menjelaskan langkah-langkah pengerjaan

- Praktikum 3: Saya mengalami lumayan kesulitan memahami komponen Java Swing, terutama dalam mengatur komponen pada form menggunakan IntelliJ IDEA. Saya meminta bantuan ai/llm untuk memahami fungsi komponen dan cara mengaturnya

- Praktikum 4: Saya mengalami sedikit kesulitan mengatur layout dan posisi komponen pada form, khususnya saat menggunakan GridBagLayout dan mengatur constraint seperti Grid x, Grid y, Weight x, dan Fill dan saya meminta saran dari ai/llm

- Praktikum 5 : Saya mengalami kendala lumayan kesulitan untuk mengatur function operator, method, sama latihan mandiri saya minta saran dari ai/llm dalam resolve

- ai/llm membantu saya memahami materi yang belum saya kuasai dan kasih saran dalam menyelesaikan kendala selama praktikum, dan meningkatkan pemahaman saya dalam menggunakan Java

