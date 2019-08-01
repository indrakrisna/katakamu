package edu.unikom.katakamu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebService {

    @FormUrlEncoded
    @POST("api.php?p=insert_quote")
    Call<WebResponse<QuoteData>> insertQuote(@Field("name") String name, @Field("quote") String quote);

    @GET("api.php?p=get_quote")
    Call<WebResponse<List<QuoteData>>> getQuote();

    class Factory {

        private static WebService webService;

        public static WebService Instance() {
            if (webService == null) {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl("https://dontbealone.000webhostapp.com/katakamu/")
                        .client(client)
                        .build();
                webService = retrofit.create(WebService.class);
            }
            return webService;
        }
    }

}