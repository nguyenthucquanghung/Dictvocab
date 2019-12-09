package lomo.hust.dictvocab.service;


import java.util.List;

import lomo.hust.dictvocab.object.LoginToken;
import lomo.hust.dictvocab.object.Word;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    @POST("api/auth/login")
    Call<LoginToken> login(@Body LoginToken token);
}
