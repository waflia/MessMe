package com.waflia.messme.RandomUserAPI;

import com.waflia.messme.RandomUserAPI.Model.RandomUserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomAPIService {
    @GET("api")
    Call<RandomUserResponse> getRandomUserResponse(@Query("results") int results);
}
