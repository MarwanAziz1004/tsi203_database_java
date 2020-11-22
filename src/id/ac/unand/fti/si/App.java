package id.ac.unand.fti.si;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    
    static Scanner scanner;
    
    static String DB_URL = "jdbc:mysql://localhost:3306/tb_java?serverTimezone=Asia/Jakarta";
    static String USERNAME = "root";
    static String PASSWORD = "password";
    
    static Connection connection;
    
    public static void main(String[] args) throws Exception {
        
        // INISIALISASI
        scanner = new Scanner(System.in);
        Integer option = 0;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
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
            
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Terjadi Kesalahan : Driver tidak ditemukan");
        }
    }
    
    private static void cariKoleksi() throws SQLException {
        System.out.println(" >> PENCARIAN KOLEKSI");
        
        System.out.print("Masukkan kata kunci (judul) ");
        String keyword = scanner.nextLine();
        
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM koleksi WHERE judul LIKE '%"+keyword+"%'";
        
        ResultSet result = statement.executeQuery(sql);
        
        while(result.next()){
            System.out.print(result.getInt("id"));
            System.out.print("\t: ");
            System.out.print(result.getString("judul"));
            System.out.print("\t: ");
            System.out.print(result.getString("pengarang"));
            System.out.print("\t: ");
            System.out.println(result.getString("isbn"));
        }
        
    }
    
    private static void editKoleksi() {
        System.out.println(" >> EDIT KOLEKSI");
        
        try {
            lihatKoleksi();
            System.out.print("ID Koleksi yang akan diedit ? ");
            Integer id = Integer.parseInt(scanner.nextLine());
            
            String sql = "SELECT * FROM koleksi WHERE id = " + id;
            
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs.next()){
                
                System.out.print("Judul ["+rs.getString("judul")+"]: ");
                String judul = scanner.nextLine();
                
                System.out.print("Pengarang ["+rs.getString("pengarang")+"]: ");
                String pengarang = scanner.nextLine();
                
                System.out.print("Penerbit ["+rs.getString("penerbit")+"]: ");
                String penerbit = scanner.nextLine();
                
                System.out.print("ISBN ["+rs.getString("isbn")+"]: ");
                String isbn = scanner.nextLine();
                
                System.out.print("Tahun Terbit ["+rs.getString("tahun_terbit")+"]: ");
                Integer tahunTerbit = Integer.parseInt(scanner.nextLine());

                sql = "UPDATE koleksi SET " +
                    "judul = '" + judul + "', " +
                    "pengarang = '" + pengarang + "', " +
                    "penerbit = '" + penerbit + "', " +
                    "isbn = '" + isbn + "', " +
                    "tahun_terbit = " + tahunTerbit + " " +
                    "WHERE id = " + id;

                System.out.println(sql);

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data koleksi");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan dalam mengedit data");
            System.out.println(e.getMessage());
        }
    
    }
    
    private static void hapusKoleksi(){
        System.out.println(" >> HAPUS KOLEKSI");
        
        try{
            lihatKoleksi();
            
            System.out.print("ID Koleksi yang akan dihapus ? ");
            Integer id = Integer.parseInt(scanner.nextLine());
            
            String sql = "DELETE FROM koleksi WHERE id = "+ id;
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) > 0){
                System.out.println("Berhasil menghapus data koleksi");
            }
            
        }catch(SQLException e){
            System.out.println("Terjadi kesalahan dalam menghapus data");
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
        
        String sql = "INSERT INTO koleksi (judul, pengarang, penerbit, isbn, tahun_terbit, tanggal_beli) VALUES('"+
        judul+"', '"+
        pengarang+"', '"+
        penerbit+"', '"+
        isbn+"', "+
        tahunTerbit+", now() )";
        
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan input data");
        }
        
    }
    
    private static void lihatKoleksi() throws SQLException {
        System.out.println(" >> LIHAT KOLEKSI");
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM koleksi";
        
        ResultSet result = statement.executeQuery(sql);
        
        while(result.next()){
            System.out.print(result.getInt("id"));
            System.out.print("\t: ");
            System.out.print(result.getString("judul"));
            System.out.print("\t: ");
            System.out.print(result.getString("pengarang"));
            System.out.print("\t: ");
            System.out.println(result.getString("isbn"));
        }
    }
    
    private static void tunggu(){
        System.out.print("\n\nTekan Enter untuk melanjutkan");
        scanner.nextLine();
    }
}
