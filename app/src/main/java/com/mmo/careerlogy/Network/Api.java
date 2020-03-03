package com.mmo.careerlogy.Network;


import com.mmo.careerlogy.Models.CitiesModel;
import com.mmo.careerlogy.Models.LoginResponse;
import com.mmo.careerlogy.Models.ModelUserCategory;
import com.mmo.careerlogy.Models.OTPResponse;
import com.mmo.careerlogy.Models.RegisterResponse;
import com.mmo.careerlogy.Models.StateModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("StateList")
    Call<StateModel> stateList();

    @FormUrlEncoded
    @POST("CitiesInState")
    Call<CitiesModel> getCitiesInState(@Field("statename") String statename);

    @FormUrlEncoded
    @POST("UserCategory")
    Call<ModelUserCategory> getUserCategory(@Field("usertype") String usertype);

    @FormUrlEncoded
    @POST("Login")
    Call<LoginResponse> Login(@Field("EmailOrMobile") String EmailOrMobile, @Field("Password") String Password);

    @FormUrlEncoded
    @POST("Registration")
    Call<RegisterResponse>Registration(
            @Field("userType") String userType,
            @Field("fullname") String fullname,
            @Field("state") String state,
            @Field("mobile") String mobile,
            @Field("gender") String gender,
            @Field("email") String email,
            @Field("dateOfBirth") String dateOfBirth,
            @Field("country") String country,
            @Field("city") String city,
            @Field("user_category") String user_category,
            @Field("user_sub_category") String user_sub_category,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("VerifyUser")
    Call<RegisterResponse>VerifyUser(
            @Field("mobileOrEmail") String mobileOrEmail,
            @Field("otp") String otp
    );
    @FormUrlEncoded
    @POST("ResendOTP")
    Call<OTPResponse>ResendOTP(
            @Field("mobileOrEmail") String mobileOrEmail
    );


}
