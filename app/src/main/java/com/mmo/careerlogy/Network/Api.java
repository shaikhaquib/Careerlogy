package com.mmo.careerlogy.Network;


import com.mmo.careerlogy.Models.AskQuestionByUserResponse;
import com.mmo.careerlogy.Models.AskQuestionResponse;
import com.mmo.careerlogy.Models.CitiesModel;
import com.mmo.careerlogy.Models.GraphsResponse;
import com.mmo.careerlogy.Models.HistoryResponse;
import com.mmo.careerlogy.Models.LoginResponse;
import com.mmo.careerlogy.Models.ModelUserCategory;
import com.mmo.careerlogy.Models.OTPResponse;
import com.mmo.careerlogy.Models.ProblemCategory;
import com.mmo.careerlogy.Models.ProblemSubCategoryResponse;
import com.mmo.careerlogy.Models.QuestionListResponse;
import com.mmo.careerlogy.Models.RecentResponse;
import com.mmo.careerlogy.Models.RegisterResponse;
import com.mmo.careerlogy.Models.StateModel;
import com.mmo.careerlogy.Models.TestimonialResponse;
import com.mmo.careerlogy.Models.UploadTestimonialResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    @FormUrlEncoded
    @POST("ProblemCategory")
    Call<ProblemCategory>problemCategory(
            @Field("usertype") String usertype
    );
    @FormUrlEncoded
    @POST("ProblemSubCategory")
    Call<ProblemSubCategoryResponse>problemSubCategory(
            @Field("problemCategoryId") String problemCategoryId
    );
    @FormUrlEncoded
    @POST("YouTubeVideoList")
    Call<TestimonialResponse>YouTubeVideoList(
            @Field("offset") String offset
    );

    @FormUrlEncoded
    @POST("AskQuestion")
    Call<AskQuestionResponse> AskQuestion(
            @Field("userId") String userId,
            @Field("pscId") String pscId,
            @Field("questionTitle") String ofquestionTitlefset,
            @Field("question") String question
    );

    @FormUrlEncoded
    @POST("QuestionListOfProbSubCategory")
    Call<QuestionListResponse> QuestionListOfProbSubCategory(
            @Field("probSubCategory") String probSubCategory,
            @Field("offset") String offset
            );

    @FormUrlEncoded
    @POST("GetAskQuestionByUser")
    Call<AskQuestionByUserResponse> GetAskQuestionByUser(
            @Field("usertype") String usertype,
            @Field("offset") String offset
    );

    @FormUrlEncoded
    @POST("QuestionHistoryList")
    Call<HistoryResponse> QuestionHistoryList(
            @Field("userId") String userId,
            @Field("offset") String offset
            );

    @FormUrlEncoded
    @POST("AddYouTubeLink")
    Call<UploadTestimonialResponse> AddYouTubeLink(
            @Field("videolink") String videolink,
            @Field("title") String title,
            @Field("description") String description,
            @Field("userId") String userId
    );


    @FormUrlEncoded
    @POST("SaveAnswer")
    Call<UploadTestimonialResponse> SaveAnswer(
            @Field("Answer") String Answer,
            @Field("QID") String QID,
            @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("EditAnswer")
    Call<UploadTestimonialResponse> EditAnswer(
            @Field("Answer") String Answer,
            @Field("userId") String userId,
            @Field("AID") String AID
    );
    @FormUrlEncoded
            @POST("GetMainGraph")
                    Call<GraphsResponse> GetMainGraph(
                    @Field("Type") String Type
    );

    @FormUrlEncoded
    @POST("GetRecentlyAnsweredQuestions")
    Call<RecentResponse> GetRecentlyAnsweredQuestions(
            @Field("offset") String offset,
            @Field("userType") String userType
    );

    @Multipart
    @POST("UploadDocument")
    Call<UploadTestimonialResponse> UploadDocument(
            @Part("userId") RequestBody userId,
            @Part("docType") RequestBody docType,
            @Part("document") MultipartBody.Part file
    );


}
