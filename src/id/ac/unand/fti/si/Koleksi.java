package id.ac.unand.fti.si;

public class Koleksi {

    private Integer id;
    private String judul;
    private String pengarang;
    private String penerbit;
    private String isbn;
    private Integer tahunTerbit;

    public Koleksi() {

    }

    public Integer getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(Integer tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Koleksi(String judul, String pengarang, String penerbit, String isbn, Integer tahunTerbit) {
        this.setJudul(judul);
        this.setPengarang(pengarang);
        this.setPenerbit(penerbit);
        this.setIsbn(isbn);
        this.setTahunTerbit(tahunTerbit);
    }

    public Koleksi(Integer id, String judul, String pengarang, String penerbit, String isbn, Integer tahunTerbit) {
        this.setId(id);
        this.setJudul(judul);
        this.setPengarang(pengarang);
        this.setPenerbit(penerbit);
        this.setIsbn(isbn);
        this.setTahunTerbit(tahunTerbit);
    }

}
