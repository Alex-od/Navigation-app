package com.dan.navigationapp.network;

import com.dan.navigationapp.model.MenuModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("menu.json")
    Call<MenuModel> getMenuById(@Query("dl") Integer id);


}
