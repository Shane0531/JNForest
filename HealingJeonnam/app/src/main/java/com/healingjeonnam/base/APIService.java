package com.healingjeonnam.base;


import com.healingjeonnam.models.Forest;
import com.healingjeonnam.models.Mountain;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public interface APIService {
    @GET("/forest")
    Call<List<Forest>> listForest();

    @GET("/mountain")
    Call<List<Mountain>> listMountain();

    @GET("/fsearch/{name}")
    Call<List<Forest>> listSForest(@Path("name") String name);

    @GET("/msearch/{name}")
    Call<List<Mountain>> listSMountain(@Path("name") String name);

}
