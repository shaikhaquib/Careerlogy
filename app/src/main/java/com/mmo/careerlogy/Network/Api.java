package com.mmo.careerlogy.Network;


import com.mmo.careerlogy.Models.CitiesModel;
import com.mmo.careerlogy.Models.StateModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface Api {

    @GET("StateList")
    Call<StateModel> stateList();

    @GET("CitiesInState")
    Call<CitiesModel> getCitiesInState(@Field("statename") String statename);
}
