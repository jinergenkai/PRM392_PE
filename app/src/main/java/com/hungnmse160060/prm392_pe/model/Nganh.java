package com.hungnmse160060.prm392_pe.model;

public class Nganh {
    private String nameBM;
    private Long id;

    public String getNameBM() {
        return nameBM;
    }

    public void setNameBM(String nameBM) {
        this.nameBM = nameBM;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nganh(String nameBM, Long id) {
        this.nameBM = nameBM;
        this.id = id;
    }

    public Nganh() {
    }
}
