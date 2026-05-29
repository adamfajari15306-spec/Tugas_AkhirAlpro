import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Buku {
    int id;
    String judul;
    String penulis;
    String kategori;
    int jumlah;
    int dipinjam;
    boolean status;

    public Buku(int id, String judul, String penulis, String kategori, int jumlah) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.dipinjam = 0;
        this.status = true;
    }
}

public class TugasAkhirAlpro {
    static Scanner input = new Scanner(System.in);

    static Buku[] buku = new Buku[100];
    static int jumlahBuku = 0;

    public static void main(String[] args) {
        initData();
        int pilihan;

        do {
            System.out.println("\n=== SISTEM PERPUSTAKAAN DIGITAL ===");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    menuAdmin();
                    break;
                case 2:
                    menuUser();
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 3);
    }

    // ================= ADMIN =================
    public static void menuAdmin() {
    int pilihan;

    do {
        System.out.println("\n=== MENU ADMIN ===");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Tampilkan Buku");
        System.out.println("3. Edit Buku");
        System.out.println("4. Hapus Buku");
        System.out.println("5. Sorting ID (Bubble Sort)");
        System.out.println("6. Sorting Judul (Selection Sort)");
        System.out.println("7. Kembali");
        System.out.print("Pilih: ");
        pilihan = input.nextInt();

        switch (pilihan) {
            case 1:
                tambahBuku();
                break;
            case 2:
                tampilkanBuku();
                break;
            case 3:
                editBuku();
                break;
            case 4:
                hapusBuku();
                break;
            case 5:
                bubbleSortID();
                break;
            case 6:
                selectionSortJudul();
                break;
        }

    } while (pilihan != 7);
}

    // ================= USER =================
    public static void menuUser() {
    int pilihan;

    do {
        System.out.println("\n=== MENU USER ===");
        System.out.println("1. Tampilkan Buku");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("4. Cari Buku (Judul - Linear)");
        System.out.println("5. Cari Buku (ID - Binary)");
        System.out.println("6. Buku dengan Stok Terbanyak");
        System.out.println("7. Cari Berdasarkan Kategori");
        System.out.println("8. Kembali");
        System.out.print("Pilih: ");
        pilihan = input.nextInt();

        switch (pilihan) {
            case 1:
                tampilkanBuku();
                break;
            case 2:
                pinjamBuku();
                break;
            case 3:
                kembalikanBuku();
                break;

            case 4:
                linearSearchJudul();
                break;

            case 5:
                binarySearchID();
                break;

            case 6:
                bukuTerbanyak();
                break;

            case 7:
                cariKategori();
                break;
        }

    } while (pilihan != 8);
}

    // ================= CREATE =================   ( Pada Bagian Admin )
    public static void tambahBuku() {
    int id;

    do {
        System.out.print("ID Buku: ");
        id = input.nextInt();

        if (cekID(id)) {
            System.out.println("ID sudah digunakan! Masukkan ID lain.");
        }

    } while (cekID(id));

    input.nextLine();

    System.out.print("Judul: ");
    String judul = input.nextLine();

    System.out.print("Penulis: ");
    String penulis = input.nextLine();

    int pilihKategori;
    do {
        System.out.println("Pilih Kategori:");
        System.out.println("1. Ilmiah");
        System.out.println("2. Fiksi");
        System.out.println("3. Motivasi");
        System.out.println("4. Pelajaran");
        System.out.print("Pilih (1-4): ");
        pilihKategori = input.nextInt();
    } while (pilihKategori < 1 || pilihKategori > 4);

    input.nextLine();

    String kategori = "";
    switch (pilihKategori) {
        case 1: kategori = "Ilmiah"; break;
        case 2: kategori = "Fiksi"; break;
        case 3: kategori = "Motivasi"; break;
        case 4: kategori = "Pelajaran"; break;
    }

    System.out.print("Jumlah: ");
    int jumlah = input.nextInt();

    buku[jumlahBuku] = new Buku(id, judul, penulis, kategori, jumlah);
    jumlahBuku++;

    System.out.println("Buku berhasil ditambahkan!");
}
    
    //    CEK ID APAKAH ADA DOUBLE
    public static boolean cekID(int id) {
    for (int i = 0; i < jumlahBuku; i++) {
        if (buku[i].id == id && buku[i].status) {
            return true; // ID sudah ada
        }
    }
    return false; // ID belum ada
    }

    // ================= READ ================= (Bagian User & Admin)
    public static void tampilkanBuku() {
        boolean adaData = false;

        for (int i = 0; i < jumlahBuku; i++) {
            if (buku[i].status) {
                adaData = true;
                break;
            }
        }

        if (!adaData) {
            System.out.println("Belum Ada Buku Yang Tersedia!");
            return;
        }

        System.out.println("\n=== DAFTAR BUKU ===");
        for (int i = 0; i < jumlahBuku; i++) {
            if (buku[i].status) {
                System.out.println("ID       : " + buku[i].id);
                System.out.println("Judul    : " + buku[i].judul);
                System.out.println("Penulis  : " + buku[i].penulis);
                System.out.println("Kategori : " + buku[i].kategori);
                System.out.println("Jumlah   : " + buku[i].jumlah);
                System.out.println("Dipinjam : " + buku[i].dipinjam);
                System.out.println("----------------------");
            }
        }
    }

    // ================= UPDATE ================= (PADA BAGIAN ADMIN)
    public static void editBuku() {
        System.out.print("Masukkan ID Buku yang ingin diedit: ");
        int cariId = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahBuku; i++) {
            if (buku[i].id == cariId && buku[i].status) {
                System.out.print("Judul baru: ");
                buku[i].judul = input.nextLine();

                System.out.print("Penulis baru: ");
                buku[i].penulis = input.nextLine();

                int pilihKategori;
             do {
                 System.out.println("Pilih Kategori Baru:");
                 System.out.println("1. Ilmiah");
                 System.out.println("2. Fiksi");
                 System.out.println("3. Motivasi");
                 System.out.println("4. Pelajaran");
                 System.out.print("Pilih (1-4): ");
                 pilihKategori = input.nextInt();

                 } while (pilihKategori < 1 || pilihKategori > 4);

                 input.nextLine();

                 switch (pilihKategori) {

                   case 1:
                          buku[i].kategori = "Ilmiah";
                          break;

                   case 2:
                          buku[i].kategori = "Fiksi";
                          break;

                   case 3:
                          buku[i].kategori = "Motivasi";
                          break;

                   case 4:
                          buku[i].kategori = "Pelajaran";
                          break;
                   }

                 System.out.print("Jumlah baru: ");
                 buku[i].jumlah = input.nextInt();

                 System.out.println("Data berhasil diupdate!");
                 return;
            }
        }

        System.out.println("Buku tidak ditemukan!");
    }

    // ================= DELETE =================
    public static void hapusBuku() {
        System.out.print("Masukkan ID Buku yang ingin dihapus: ");
        int cariId = input.nextInt();

        for (int i = 0; i < jumlahBuku; i++) {
            if (buku[i].id == cariId && buku[i].status) {
                buku[i].status = false;
                System.out.println("Buku berhasil dihapus!");
                return;
            }
        }

        System.out.println("Buku tidak ditemukan!");
    }
    //                             PEMINJAMAN BUKU
    public static void pinjamBuku() {
    System.out.print("Masukkan ID Buku yang ingin dipinjam: ");
    int cariId = input.nextInt();

    for (int i = 0; i < jumlahBuku; i++) {

        if (buku[i].id == cariId && buku[i].status) {

            if (buku[i].jumlah > 0) {

                buku[i].jumlah--;
                buku[i].dipinjam++;

                System.out.println("Buku berhasil dipinjam!");
                System.out.println("Sisa stok : " + buku[i].jumlah);
                System.out.println("Dipinjam  : " + buku[i].dipinjam);

            } else {
                System.out.println("Stok buku habis!");
            }

            return;
        }
    }

    System.out.println("Buku tidak ditemukan!");
}
//                    BUBBLE SORT (PADA BAGIAN ADMIN)
public static void bubbleSortID() {
    for (int i = 0; i < jumlahBuku - 1; i++) {
        for (int j = 0; j < jumlahBuku - i - 1; j++) {
            if (buku[j].id > buku[j + 1].id) {
                Buku temp = buku[j];
                buku[j] = buku[j + 1];
                buku[j + 1] = temp;
            }
        }
    }

    System.out.println("Data berhasil diurutkan berdasarkan ID (Ascending)");
}

//                    SELECTION SORT (PADA BAGIAN ADMIN)
public static void selectionSortJudul() {
    for (int i = 0; i < jumlahBuku - 1; i++) {
        int minIndex = i;

        for (int j = i + 1; j < jumlahBuku; j++) {
            if (buku[j].judul.compareToIgnoreCase(buku[minIndex].judul) < 0) {
                minIndex = j;
            }
        }

        Buku temp = buku[i];
        buku[i] = buku[minIndex];
        buku[minIndex] = temp;
    }

    System.out.println("Data berhasil diurutkan berdasarkan Judul (A-Z)");
}

//                            LINEAR SEARCH (Bagian Role User)
public static void linearSearchJudul() {
    input.nextLine();
    System.out.print("Masukkan judul buku: ");
    String cari = input.nextLine();

    boolean ditemukan = false;

    for (int i = 0; i < jumlahBuku; i++) {
        if (buku[i].status && buku[i].judul.equalsIgnoreCase(cari)) {
            System.out.println("\nBuku ditemukan:");
            System.out.println("ID       : " + buku[i].id);
            System.out.println("Judul    : " + buku[i].judul);
            System.out.println("Penulis  : " + buku[i].penulis);
            System.out.println("Kategori : " + buku[i].kategori);
            System.out.println("Jumlah   : " + buku[i].jumlah);
            ditemukan = true;
        }
    }

    if (!ditemukan) {
        System.out.println("Buku tidak ditemukan!");
    }
}

// Pengembalian Buku ( Bagian User )
public static void kembalikanBuku() {

    System.out.print("Masukkan ID Buku yang ingin dikembalikan: ");
    int cariId = input.nextInt();

    for (int i = 0; i < jumlahBuku; i++) {

        if (buku[i].id == cariId && buku[i].status) {

            if (buku[i].dipinjam > 0) {

                buku[i].jumlah++;
                buku[i].dipinjam--;

                System.out.println("Buku berhasil dikembalikan!");
                System.out.println("Stok sekarang : " + buku[i].jumlah);
                System.out.println("Masih dipinjam: " + buku[i].dipinjam);

            } else {

                System.out.println("Buku ini tidak sedang dipinjam!");
            }

            return;
        }
    }

    System.out.println("Buku tidak ditemukan!");
}

//                                 BINARY SEARCH ID (Bagian User)
public static void binarySearchID() {
    System.out.print("Masukkan ID buku: ");
    int cari = input.nextInt();

    int kiri = 0;
    int kanan = jumlahBuku - 1;
    boolean ditemukan = false;

    while (kiri <= kanan) {
        int tengah = (kiri + kanan) / 2;

        if (buku[tengah].id == cari && buku[tengah].status) {
            System.out.println("\nBuku ditemukan:");
            System.out.println("ID       : " + buku[tengah].id);
            System.out.println("Judul    : " + buku[tengah].judul);
            System.out.println("Penulis  : " + buku[tengah].penulis);
            System.out.println("Kategori : " + buku[tengah].kategori);
            System.out.println("Jumlah   : " + buku[tengah].jumlah);
            ditemukan = true;
            break;
        } else if (cari < buku[tengah].id) {
            kanan = tengah - 1;
        } else {
            kiri = tengah + 1;
        }
    }

    if (!ditemukan) {
        System.out.println("Buku tidak ditemukan!");
    }
}
//                       PENCARIAN BUKU TERBANYAK (Bagian User)
public static void bukuTerbanyak() {
    if (jumlahBuku == 0) {
        System.out.println("Belum Ada Buku Yang Tersedia!");
        return;
    }

    int max = -1;

    for (int i = 0; i < jumlahBuku; i++) {
        if (buku[i].status && buku[i].jumlah > max) {
            max = buku[i].jumlah;
        }
    }

    if (max == -1) {
        System.out.println("Belum Ada Buku Yang Tersedia!");
        return;
    }

    System.out.println("\n=== BUKU DENGAN STOK TERBANYAK ===");
    for (int i = 0; i < jumlahBuku; i++) {
        if (buku[i].status && buku[i].jumlah == max) {
            System.out.println("ID       : " + buku[i].id);
            System.out.println("Judul    : " + buku[i].judul);
            System.out.println("Penulis  : " + buku[i].penulis);
            System.out.println("Kategori : " + buku[i].kategori);
            System.out.println("Jumlah   : " + buku[i].jumlah);
            System.out.println("----------------------");
        }
    }
}

//                        PENCARIAN BUKU BERDASARKAN KATEGORI (PADA USER)
public static void cariKategori() {
    System.out.println("Pilih Kategori:");
    System.out.println("1. Ilmiah");
    System.out.println("2. Fiksi");
    System.out.println("3. Motivasi");
    System.out.println("4. Pelajaran");
    System.out.print("Pilih (1-4): ");
    int pilih = input.nextInt();

    String kategori = "";

    switch (pilih) {
        case 1:
            kategori = "Ilmiah";
            break;
        case 2:
            kategori = "Fiksi";
            break;
        case 3:
            kategori = "Motivasi";
            break;
        case 4:
            kategori = "Pelajaran";
            break;
        default:
            System.out.println("Kategori tidak valid!");
            return;
    }

    boolean ditemukan = false;

    System.out.println("\n=== HASIL PENCARIAN ===");
    for (int i = 0; i < jumlahBuku; i++) {
        if (buku[i].status && buku[i].kategori.equalsIgnoreCase(kategori)) {
            System.out.println("ID       : " + buku[i].id);
            System.out.println("Judul    : " + buku[i].judul);
            System.out.println("Penulis  : " + buku[i].penulis);
            System.out.println("Kategori : " + buku[i].kategori);
            System.out.println("Jumlah   : " + buku[i].jumlah);
            System.out.println("----------------------");
            ditemukan = true;
        }
    }

    if (!ditemukan) {
        System.out.println("Tidak ada buku dalam kategori tersebut!");
    }
}
// DATA 30 BUKU
public static void initData() {

    try {

        BufferedReader br = new BufferedReader(new FileReader("dataBuku.txt"));

        String baris;

        while ((baris = br.readLine()) != null) {

            String[] data = baris.split(";");

            int id = Integer.parseInt(data[0]);
            String judul = data[1];
            String penulis = data[2];
            String kategori = data[3];
            int jumlah = Integer.parseInt(data[4]);

            buku[jumlahBuku++] = new Buku(id, judul, penulis, kategori, jumlah);
        }

        br.close();

    } catch (IOException e) {
        System.out.println("File data tidak ditemukan!");
    }
}
}