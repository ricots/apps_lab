package com.lab.app_lab;

import java.sql.Time;
import java.util.Date;

/**
 * Created by ACER on 2016-02-27.
 */
public class Item {
    public String jdl,kegiatan,gambar,id_ruang, nama_ruang, hari, kegiatan_lab, prodi,jumlah_mahasiswa,detail_gambar_kritik,
            npm, password, jam,selesai, jam_lab, selesai_lab,kode_jadwal,prodi_lab, detail_kritik_npm, detail_lokasinya ,detail_keteranganya,nama;
    public String id_kegiatan;
    public Date tgl;

    public String getNpm() {
        return npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getPassword() {
        return password;
    }

    public String getKode_jadwal() {
        return kode_jadwal;
    }

    public void setKode_jadwal(String kode_jadwal) {
        this.kode_jadwal = kode_jadwal;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getSelesai() {
        return selesai;
    }

    public void setSelesai(String selesai) {
        this.selesai = selesai;
    }

    public String getJam_lab() {
        return jam_lab;
    }

    public String getDetail_kritik_npm() {
        return detail_kritik_npm;
    }

    public void setDetail_kritik_npm(String detail_kritik_npm) {
        this.detail_kritik_npm = detail_kritik_npm;
    }

    public String getDetail_lokasinya() {
        return detail_lokasinya;
    }

    public void setDetail_lokasinya(String detail_lokasinya) {
        this.detail_lokasinya = detail_lokasinya;
    }

    public String getDetail_keteranganya() {
        return detail_keteranganya;
    }

    public void setDetail_keteranganya(String detail_keteranganya) {
        this.detail_keteranganya = detail_keteranganya;
    }

    public void setJam_lab(String jam_lab) {
        this.jam_lab = jam_lab;
    }

    public String getSelesai_lab() {
        return selesai_lab;
    }

    public void setSelesai_lab(String selesai_lab) {
        this.selesai_lab = selesai_lab;
    }

    public String getProdi_lab() {
        return prodi_lab;
    }

    public void setProdi_lab(String prodi_lab) {
        this.prodi_lab = prodi_lab;
    }

    public String getDetail_gambar_kritik() {
        return detail_gambar_kritik;
    }

    public void setDetail_gambar_kritik(String detail_gambar_kritik) {
        this.detail_gambar_kritik = detail_gambar_kritik;
    }

    public Item(String jdl, String kegiatan, String gambar, String id_kegiatan, Date tgl, String jam, String selesai,
                String id_ruang, String nama_ruang, String hari, String kegiatan_lab, String Prodi, String jumlah_mahasiswa
            , String jam_lab, String selesai_lab, String npm, String password, String prodi_lab, String detail_kritik_npm, String detail_lokasinya, String detail_keteranganya,
                String nama, String detail_gambar_kritik){
        this.jdl = jdl;
        this.kegiatan = kegiatan;
        this.gambar = gambar;

        this.id_kegiatan = id_kegiatan;
        this.tgl = tgl;
        this.id_ruang = id_ruang;

        this.nama_ruang =nama_ruang;
        this.hari = hari;
        this.kegiatan_lab = kegiatan_lab;
        this.prodi = prodi;
        this.jumlah_mahasiswa = jumlah_mahasiswa;
        this.npm = npm;

        this.jam = jam;

        this.selesai = selesai;
        this.selesai_lab = selesai_lab;
        this.prodi_lab = prodi_lab;
        this.detail_kritik_npm = detail_kritik_npm;
        this.detail_lokasinya = detail_lokasinya;
        this.detail_keteranganya = detail_keteranganya;
        this.detail_gambar_kritik = detail_gambar_kritik;
    }

    public String getId_ruang() {
        return id_ruang;
    }

    public void setId_ruang(String id_ruang) {
        this.id_ruang = id_ruang;
    }

    public String getNama_ruang() {
        return nama_ruang;
    }

    public void setNama_ruang(String nama_ruang) {
        this.nama_ruang = nama_ruang;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getKegiatan_lab() {
        return kegiatan_lab;
    }

    public void setKegiatan_lab(String kegiatan_lab) {
        this.kegiatan_lab = kegiatan_lab;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getJumlah_mahasiswa() {
        return jumlah_mahasiswa;
    }

    public void setJumlah_mahasiswa(String jumlah_mahasiswa) {
        this.jumlah_mahasiswa = jumlah_mahasiswa;
    }


    public Item(){
    }


    public String getJdl() {
        return jdl;
    }

    public void setJdl(String jdl) {
        this.jdl = jdl;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getId_kegiatan() {
        return id_kegiatan;
    }

    public void setId_kegiatan(String id_kegiatan) {
        this.id_kegiatan = id_kegiatan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

}
