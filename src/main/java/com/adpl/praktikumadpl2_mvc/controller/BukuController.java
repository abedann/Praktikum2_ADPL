/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adpl.praktikumadpl2_mvc.controller;

import com.adpl.praktikumadpl2_mvc.model.Buku;
import com.adpl.praktikumadpl2_mvc.model.BukuModel;
import com.adpl.praktikumadpl2_mvc.view.BukuView;

/**
 *
 * @author Abe.dann
 */
public class BukuController {
    private final BukuModel model;
    private final BukuView view;
    
    public BukuController(BukuModel model, BukuView view){
        this.model = model;
        this.view = view;
        
        
        //menggunakan lambda func u/ mengirim param berupa objek
        view.addSimpanListener(event -> simpanBuku());
        view.addHapusListener(event -> hapusBuku());
        view.addClearListener(event -> clearBuku());
        
        view.tampilkanData(model.getSemuaBuku());
    }
    
    //start action listener
    public void simpanBuku(){
        String judul = view.getJudul().trim();
        String penulis = view.getPenulis().trim();
        String teksTahun = view.getTahun().trim();
        
        if(judul.isEmpty() || penulis.isEmpty() || teksTahun.isEmpty()){
            view.tampilkanPeringatan("Input Belum Lengkap", "Judul, Penulis, Tahun Terbit WAJIB DIISI");
            return;
        }
        
        int tahunTerbit;
        try {
            tahunTerbit = Integer.parseInt(teksTahun);
        } catch (Exception e) {
            view.tampilkanPeringatan("Input Tidak Valid", "Tahun Terbit Harus Berupa ANGKA");
            return;
        }
        
        model.tambahBuku(new Buku(judul, penulis, tahunTerbit));
        view.tampilkanData(model.getSemuaBuku());
        view.kosongkanForm();
        view.tampilkanInfo("INFO", "Buku Berhasil Ditambahkan");
    }
    
    public void hapusBuku(){
        int baris = view.getBarisTerpilih();
        
        if(baris == -1){
            view.tampilkanInfo("Hapus Buku", "Pilih Baris Yang Akan Dihapus");
            return;
        }
        
        model.hapusBuku(baris);
        view.tampilkanData(model.getSemuaBuku());
        view.tampilkanInfo("INFO", "Buku Berhasil Dihapus");
    }
    
    public void clearBuku(){
        model.hapusSemua();
        view.tampilkanData(model.getSemuaBuku());
        view.kosongkanForm();
        view.tampilkanInfo("INFO", "Semua Data Buku Berhasil Dihapus");
    }
}
