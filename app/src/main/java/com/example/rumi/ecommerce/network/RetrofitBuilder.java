package com.example.rumi.ecommerce.network;

import com.example.rumi.ecommerce.BuildConfig;
import com.example.rumi.ecommerce.constants.Url;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitBuilder {

    //    private static final String BASE_URL = "http://192.168.0.10/laravel-android-api/public/api/";
    private static final String BASE_URL = Url.BASE_URL + "/api/"; // wireless lan adapter wifi

    private static OkHttpClient client = buildClient();
    private static Retrofit retrofit = buildRetrofit(client);


    private static OkHttpClient buildClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();

                    Request.Builder builder1 = request.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("X-Requested-With", "XMLHttpRequest")
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .addHeader("Connection", "close");

                    request = builder1.build();

                    return chain.proceed(request);
                });

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        return builder.build();
    }

    private static Retrofit buildRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    public static <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
