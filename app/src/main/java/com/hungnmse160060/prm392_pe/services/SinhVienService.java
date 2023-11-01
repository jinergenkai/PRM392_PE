package com.hungnmse160060.prm392_pe.services;

import com.hungnmse160060.prm392_pe.model.SinhVien;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SinhVienService {
    String URLSinh = "SinhVien";
    @GET(URLSinh)
    Call<SinhVien[]> getAll();
    @GET(URLSinh + "/{id}")
    Call<SinhVien> getById (@Path("id") Long id);
    @POST(URLSinh)
    Call<SinhVien> create(@Body SinhVien b);
    @PUT(URLSinh + "/{id}")
    Call<SinhVien> update(@Path("id") Long id, @Body SinhVien b);
    @DELETE(URLSinh + "/{id}")
    Call<SinhVien> delete(@Path("id") Long id);
}
