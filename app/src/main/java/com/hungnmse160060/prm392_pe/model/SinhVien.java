package com.hungnmse160060.prm392_pe.model;

public class SinhVien {
    private Long id;
    private String name;
    private String date;
    private String gender;
    private Long idBomon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getIdBomon() {
        return idBomon;
    }

    public void setIdBomon(Long idBomon) {
        this.idBomon = idBomon;
    }

    public SinhVien() {
    }

    public SinhVien(Long id, String name, String date, String gender, Long idBomon) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.idBomon = idBomon;
    }
}
