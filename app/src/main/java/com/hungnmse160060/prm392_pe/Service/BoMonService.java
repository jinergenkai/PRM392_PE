package com.hungnmse160060.prm392_pe.service;

import com.hungnmse160060.prm392_pe.model.BoMon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BoMonService {
    final String URLbo = "Nganh";
    @GET(URLbo)
    Call<BoMon[]> getAll();
    @GET(URLbo + "/{id}")
    Call<BoMon> getById (@Path("id") Long id);
    @POST(URLbo)
    Call<BoMon> create(@Body BoMon b);
    @PUT(URLbo + "/{id}")
    Call<BoMon> update(@Path("id") Long id, @Body BoMon b);
    @DELETE(URLbo + "/{id}")
    Call<BoMon> delete(@Path("id") Long id);
}
