package id.ac.unand.fti.si;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    
    static Scanner scanner;
    
    static KoleksiManager koleksiManager;
    
    
    public static void main(String[] args) throws Exception {
        
        // INISIALISASI
        scanner = new Scanner(System.in);
        koleksiManager = new KoleksiManager();
        Integer option = 0;
        
        do {
            System.out.println(">>> MENU PUSTAKA");
            System.out.println(" 1. Lihat Koleksi");
            System.out.println(" 2. Tambah Koleksi");
            System.out.println(" 3. Hapus Koleksi");
            System.out.println(" 4. Edit Koleksi");
            System.out.println(" 5. Cari Koleksi");
            System.out.println(" 0. Keluar");
            System.out.print("\nPilihan Anda (1/2/3/4/5/0)? ");
            option = Integer.parseInt(scanner.nextLine());
            
            switch (option) {
                case 1:
                lihatKoleksi();
                break;
                case 2:
                tambahKoleksi();
                break;
                case 3:
                hapusKoleksi();
                break;
                case 4:
                editKoleksi();
                break;
                case 5:
                cariKoleksi();
                break;
                case 0:
                break;
                default:
                System.out.println("Input tidak valid");
            }
            tunggu();
            
        } while (option != 0);
        
    }
    
    private static void cariKoleksi() throws SQLException {
        System.out.println(" >> PENCARIAN KOLEKSI");
        
        System.out.print("Masukkan kata kunci (judul) ");
        String keyword = scanner.nextLine();
        
        ArrayList<Koleksi> listKoleksi = koleksiManager.cari(keyword);
        
        for(Koleksi koleksi : listKoleksi){
            System.out.print(koleksi.getJudul());
            System.out.print("\t");
            System.out.print(koleksi.getPengarang());
            System.out.print("\t");
            System.out.println(koleksi.getPenerbit());
        }
    }
    
    private static void editKoleksi() {
        System.out.println(" >> EDIT KOLEKSI");
        
        
            lihatKoleksi();
            System.out.print("ID Koleksi yang akan diedit ? ");
            Integer id = Integer.parseInt(scanner.nextLine());
            
            Koleksi koleksi = koleksiManager.get(id);
            
            System.out.print("Judul ["+koleksi.getJudul()+"]: ");
            String judul = scanner.nextLine();
            
            System.out.print("Pengarang ["+koleksi.getPengarang()+"]: ");
            String pengarang = scanner.nextLine();
            
            System.out.print("Penerbit ["+koleksi.getPenerbit()+"]: ");
            String penerbit = scanner.nextLine();
            
            System.out.print("ISBN ["+koleksi.getIsbn()+"]: ");
            String isbn = scanner.nextLine();
            
            System.out.print("Tahun Terbit ["+koleksi.getTahunTerbit()+"]: ");
            Integer tahunTerbit = Integer.parseInt(scanner.nextLine());
            
            Koleksi koleksiUpdate = new Koleksi(judul, pengarang, penerbit, isbn, tahunTerbit);
            
            if(koleksiManager.update(id, koleksiUpdate) > 0){
                System.out.println("Berhasil mengupdate data");
            }
        
    }
    
    private static void hapusKoleksi(){
        System.out.println(" >> HAPUS KOLEKSI");
        
        lihatKoleksi();
        
        System.out.print("ID Koleksi yang akan dihapus ? ");
        Integer id = Integer.parseInt(scanner.nextLine());
        
        if(koleksiManager.hapus(id) > 0 ){
            System.out.println("Behasil hapus koleksi");
        }
        
        
        
    }
    
    private static void tambahKoleksi() {
        System.out.println(" >> TAMBAH KOLEKSI");
        
        System.out.print("Judul : ");
        String judul = scanner.nextLine();
        
        System.out.print("Pengarang : ");
        String pengarang = scanner.nextLine();
        
        System.out.print("Penerbit : ");
        String penerbit = scanner.nextLine();
        
        System.out.print("ISBN : ");
        String isbn = scanner.nextLine();
        
        System.out.print("Tahun Terbit : ");
        Integer tahunTerbit = Integer.parseInt(scanner.nextLine());
        
        Koleksi koleksi = new Koleksi(judul, pengarang, penerbit, isbn, tahunTerbit);
        
        if(koleksiManager.tambah(koleksi) > 0){
            System.out.println("Berhasil menambah data koleksi");
        }
        
    }
    
    private static void lihatKoleksi(){
        System.out.println(" >> LIHAT KOLEKSI");
        ArrayList<Koleksi> listKoleksi = koleksiManager.getAll();
        
        for(Koleksi koleksi : listKoleksi){
            System.out.print(koleksi.getId());
            System.out.print("\t| ");
            System.out.print(koleksi.getJudul());
            System.out.print("\t| ");
            System.out.print(koleksi.getPengarang());
            System.out.print("\t| ");
            System.out.println(koleksi.getPenerbit());
        }
    }
    
    private static void tunggu(){
        System.out.print("\n\nTekan Enter untuk melanjutkan");
        scanner.nextLine();
    }
}
