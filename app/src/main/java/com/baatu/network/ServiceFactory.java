package com.baatu.network;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author Shailendra This is a singleton class
 *In this class, we are calling retrofit network api
 */


public class ServiceFactory {

    private static final String  BASE_URL = "https://jsonplaceholder.typicode.com";

    private static APIs sAPIs;

    public static APIs getServiceAPIs() {
        if(sAPIs == null) {
            sAPIs = createServiceAPIs();
        }
        return sAPIs;
    }

    private ServiceFactory() {
    }


    private static APIs createServiceAPIs() {
        final Retrofit retrofit = createRetrofit();
        return retrofit.create(APIs.class);
    }


    private static OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.readTimeout(40, TimeUnit.SECONDS);
        httpClient.connectTimeout(40, TimeUnit.SECONDS);
        return httpClient.build();
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }
}
