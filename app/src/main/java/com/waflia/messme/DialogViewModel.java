package com.waflia.messme;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.waflia.messme.RandomUserAPI.Model.RandomUserResponse;
import com.waflia.messme.RandomUserAPI.Model.Result;
import com.waflia.messme.RandomUserAPI.RandomAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class DialogViewModel extends ViewModel {

    private MutableLiveData<List<Result>> results;

    public LiveData<List<Result>> getData(){
        if(results == null){
            results = new MutableLiveData<>();
            requestData();
        }
        return results;
    }

    private void requestData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        RandomAPIService service = retrofit.create(RandomAPIService.class);

        Call<RandomUserResponse> call = service.getRandomUserResponse(10, "haskiesarecute");
        call.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {
                if(response.isSuccessful()){
                    Log.d("MessMe", "Success api response");
                    RandomUserResponse randomUserResponse = response.body();
                    results.setValue(randomUserResponse.getResults());
                }
            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                Log.d("MessMe", t.getMessage());
            }
        });

    }
}
