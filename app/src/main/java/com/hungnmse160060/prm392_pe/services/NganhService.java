package com.hungnmse160060.prm392_pe.services;

import com.hungnmse160060.prm392_pe.model.Nganh;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NganhService {
    final String URLbo = "Nganh";
    @GET(URLbo)
    Call<Nganh[]> getAll();
    @GET(URLbo + "/{id}")
    Call<Nganh> getById (@Path("id") Long id);
    @POST(URLbo)
    Call<Nganh> create(@Body Nganh b);
    @PUT(URLbo + "/{id}")
    Call<Nganh> update(@Path("id") Long id, @Body Nganh b);
    @DELETE(URLbo + "/{id}")
    Call<Nganh> delete(@Path("id") Long id);
}
