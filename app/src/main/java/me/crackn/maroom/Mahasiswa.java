package me.crackn.maroom;

public class Mahasiswa {
    private String nama, nim, prodi, noHP;

    public Mahasiswa() {
    }

    public Mahasiswa(String nama, String nim, String prodi, String noHP) {
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
        this.noHP = noHP;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }
}
