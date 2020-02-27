package lomo.hust.dictvocab.service;


import java.util.List;

import lomo.hust.dictvocab.object.LoginToken;
import lomo.hust.dictvocab.object.LoginUser;
import lomo.hust.dictvocab.object.ResponseTags;
import lomo.hust.dictvocab.object.SignupUser;
import lomo.hust.dictvocab.object.Word;
import lomo.hust.dictvocab.object.WordPost;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {
    @POST("/api/search")
    Call<List<Word>> search(@Query("lex") String searchedWord);



    @GET("/api/e_search?")
    Call<List<Word>> getAutoComplete(@Query("q") String currentSearch);



    @Headers({
            "Content-Type:application/json",
            "X-Requested-With:XMLHttpRequest"
    })
    @POST("api/auth/login")
    Call<LoginToken> login(@Body LoginUser user);




    @Headers({
            "Content-Type:application/json",
            "X-Requested-With:XMLHttpRequest"
    })
    @POST("api/auth/signup")
    Call<LoginToken> signup(@Body SignupUser user);
    @GET("api/auth/logout")
    Call<LoginToken> logout(@Header("Authorization") String authHeader);

    @GET("api/user_word/show_list")
    Call<ResponseTags> getTags(@Header("Authorization") String authHeader);

    @POST("api/user_word/show_list_word")
    Call<List<String>> getWords(@Header("Authorization") String authHeader, @Body WordPost wordPost);

    @POST("api/user_word/edit")
    Call<ResponseTags> mark(@Header("Authorization") String authHeader,@Body WordPost wordPost);

}
