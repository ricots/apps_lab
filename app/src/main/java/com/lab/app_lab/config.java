package com.lab.app_lab;

/**
 * Created by ACER on 2016-02-27.
 */
public class config {
    public static final String LOGIN_URL = "http://ricots.hol.es/login.php";
    public static final String REGIS_URL = "http://ricots.hol.es/regis.php";
    public static final String BERITA_URL = "http://ricots.hol.es/list_berita.php?offset=";
    public static final String JADWAL_ap1 = "http://ricots.hol.es/jadwal.php?nama_ruang=";
    public static final String DETAIL_JADWAL = "http://ricots.hol.es/detail_jadwal.php?kode_jadwal=";
    public static final String KRITIK_SARAN = "http://ricots.hol.es/kritik_saran.php";
    public static final String DETAIL_KRITIK = "http://ricots.hol.es/detail_kritik.php";
    public static final String DETAIL_LOGIN = "http://ricots.hol.es/detail_login.php?npm=";
    //Keys for email and password as defined in our $_POST['key'] in login.php

    public static final String TAG_NO = "no";
    public static final String KEY_TGL_KRITIK = "tanggal";
    public static final String KEY_NPM_KRITIK = "npm";
    public static final String KEY_KRITIK = "kritik";
    public static final String KEY_LOKASI = "lokasi";
    public static final String KEY_USER = "npm";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PRODI = "prodi";
    public static final String KEY_NAMA = "nama";
    public static final String TAG_JSON_ARRAY = "jadwal_lab";

    public static final String KEY_JUDUL = "judul";
    public static final String KEY_GAMBAR = "gambar";
    public static final String KEY_TGL = "tanggal";
    public static final String KEY_MULAI = "jam_mulai";
    public static final String KEY_SELESAI = "jam_berahir";
    public static final String KEY_ID = "id_kegiatan";
    public static final String KEY_DESKIRIPSI = "deskripsi";

    public static final String KEY_GAMBAR_KRITIK = "photo";
    public static final String KEY_JUDUL_GAMBAR = "judul_gambar";

    public static final String KEY_ID_RUANG = "id_ruang";
    public static final String KEY_KODE_JADWAL = "kode_jadwal";
    public static final String KEY_NAMA_RUANG = "nama_ruang";
    public static final String KEY_HARI = "hari";
    public static final String KEY_JAM = "jam";
    public static final String KEY_JAM_SELESAI = "selesai";
    public static final String KEY_KEGIATAN_LAB = "kegiatan";
    public static final String KEY_PRODI_LAB = "prodi";
    public static final String KEY_MHS = "jumlah_mahasiswa";
    public static final String KEY_detail_gambar_kritik = "photo";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "npm";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}

