package id.ac.unand.fti.si;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KoleksiManager {
    
    static String DB_URL = "jdbc:mysql://localhost:3306/tb_java?serverTimezone=Asia/Jakarta";
    static String USERNAME = "root";
    static String PASSWORD = "password";
    
    static Connection connection;
    
    public KoleksiManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Terjadi Kesalahan : Driver tidak ditemukan");
        }
    }
    
    public int tambah(Koleksi koleksi)
    {
        Integer result = 0;
        try {
            String sql = "INSERT INTO koleksi (judul, pengarang, penerbit, isbn, tahun_terbit, tanggal_beli) VALUES(?, ?, ?, ?, ?, now() )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, koleksi.getJudul());
            statement.setString(2, koleksi.getPengarang());
            statement.setString(3, koleksi.getPenerbit());
            statement.setString(4, koleksi.getIsbn());
            statement.setInt(5, koleksi.getTahunTerbit());
            
            result = statement.executeUpdate();
            System.out.println("Berhasil input data");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan input data");
        }
        return result;
    }
    
    public ArrayList<Koleksi> getAll()
    {
        Statement statement;
        ArrayList<Koleksi> listKoleksi = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM koleksi";
            
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                Koleksi koleksi = new Koleksi(
                    result.getInt("id"),
                    result.getString("judul"),
                    result.getString("pengarang"),
                    result.getString("penerbit"),
                    result.getString("isbn"),
                    result.getInt("tahun_terbit")
                );
                listKoleksi.add(koleksi);
            }
        }catch(SQLException e){
            System.out.println("Terjadi Kesalahan. Cek Data");
            System.out.println(e.getMessage());
        }
        return listKoleksi;
    }
    
    public int update(Integer id, Koleksi koleksi)
    {
        Integer result = 0;
        String sql = "UPDATE koleksi SET judul = ?, pengarang = ?, penerbit = ?, isbn = ?, tahun_terbit = ? WHERE id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, koleksi.getJudul());
            statement.setString(2, koleksi.getPengarang());
            statement.setString(3, koleksi.getPenerbit());
            statement.setString(4, koleksi.getIsbn());
            statement.setInt(5, koleksi.getTahunTerbit());
            statement.setInt(6, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan query");
        }
        return result;
    }
    
    public int hapus(Integer id)
    {
        Integer result = 0;
        String sql = "DELETE FROM koleksi WHERE id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            result= statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public Koleksi get(Integer id){
        String sql = "SELECT * FROM koleksi WHERE id = ?";
        Koleksi koleksi = new Koleksi();
        
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            rs.next();
            koleksi = new Koleksi(
                rs.getInt("id"),
                rs.getString("judul"),
                rs.getString("pengarang"),
                rs.getString("penerbit"),
                rs.getString("isbn"),
                rs.getInt("tahun_terbit")
            );
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan query");
        }
        return koleksi;
    }
    
    public ArrayList<Koleksi> cari(String keyword)
    {
        //Statement statement = connection.createStatement();
        ArrayList<Koleksi> listKoleksi = new ArrayList<>();
        
        PreparedStatement statement;
        try {
            
            String sql = "SELECT * FROM koleksi WHERE judul LIKE ?"; 
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" +keyword + "%");
            
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                Koleksi koleksi = new Koleksi(
                result.getInt("id"),
                result.getString("judul"),
                result.getString("pengarang"),
                result.getString("penerbit"),
                result.getString("isbn"),
                result.getInt("tahun_terbit")
                );
                listKoleksi.add(koleksi);
                
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan query");
        }
        return listKoleksi;
    }
    
    
}
