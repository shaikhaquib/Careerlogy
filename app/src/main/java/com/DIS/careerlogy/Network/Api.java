package com.DIS.careerlogy.Network;


import com.DIS.careerlogy.Models.AskQuestionByUserResponse;
import com.DIS.careerlogy.Models.AskQuestionResponse;
import com.DIS.careerlogy.Models.CategoryOperationsEditResponse;
import com.DIS.careerlogy.Models.CitiesModel;
import com.DIS.careerlogy.Models.DownloadlinksModel;
import com.DIS.careerlogy.Models.GraphsResponse;
import com.DIS.careerlogy.Models.HistoryResponse;
import com.DIS.careerlogy.Models.LoginResponse;
import com.DIS.careerlogy.Models.ModelUserCategory;
import com.DIS.careerlogy.Models.OTPResponse;
import com.DIS.careerlogy.Models.ProblemCategory;
import com.DIS.careerlogy.Models.ProblemSubCategoryResponse;
import com.DIS.careerlogy.Models.QoutesResponse;
import com.DIS.careerlogy.Models.QuestionListResponse;
import com.DIS.careerlogy.Models.RecentResponse;
import com.DIS.careerlogy.Models.RegisterResponse;
import com.DIS.careerlogy.Models.StateModel;
import com.DIS.careerlogy.Models.TestimonialResponse;
import com.DIS.careerlogy.Models.UploadTestimonialResponse;

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
            @Field("categoryId") String Type
    );

    @FormUrlEncoded
    @POST("GetRecentlyAnsweredQuestions")
    Call<RecentResponse> GetRecentlyAnsweredQuestions(
            @Field("offset") String offset,
            @Field("userType") String userType
    );

    @FormUrlEncoded
    @POST("DocumentURL")
    Call<DownloadlinksModel> DocumentURL(
            @Field("docType") String docType
    );

    @FormUrlEncoded
    @POST("Qoutes")
    Call<QoutesResponse> Qouteslist(
            @Field("option") String docType
    );

    @FormUrlEncoded
    @POST("CategoryOperations")
    Call<CategoryOperationsEditResponse> CategoryOperationsEdit(
            @Field("option") String option,
            @Field("cattype") String cattype,
            @Field("catname") String catname,
            @Field("pcid") String pcid,
            @Field("userid") String userid
    );


}
