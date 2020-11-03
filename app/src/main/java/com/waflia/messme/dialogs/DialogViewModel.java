package com.waflia.messme.dialogs;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.waflia.messme.RandomUserAPI.Model.Name;
import com.waflia.messme.RandomUserAPI.Model.Picture;
import com.waflia.messme.RandomUserAPI.Model.RandomUserResponse;
import com.waflia.messme.RandomUserAPI.Model.Result;
import com.waflia.messme.RandomUserAPI.RandomAPIService;
import com.waflia.messme.dialogs.sqlite.MessMeDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class DialogViewModel extends AndroidViewModel {

    public final String messDB = "MessMeDB";

    private MutableLiveData<List<Result>> results;
    private MessMeDatabase meDatabase;

    public DialogViewModel(@NonNull Application application) {
        super(application);
        meDatabase = new MessMeDatabase(getApplication());
    }

    public LiveData<List<Result>> getData(){
        if(results == null){
            results = new MutableLiveData<>();
            if(requestDBData()) {
                return results;
            }else {
                requestWebData();
            }
        }
        return results;
    }

    private boolean requestDBData(){
        SQLiteDatabase sqLiteDatabase = meDatabase.getWritableDatabase();
        Cursor resultCursor = sqLiteDatabase.query("Users", null, null, null, null, null, null);
        List<Result> cursorResults = new ArrayList<>();
        Result cursorResult;
        if(resultCursor.moveToFirst()){
            int colName = resultCursor.getColumnIndex("name");
            int colEmail = resultCursor.getColumnIndex("email");
            int colPicture = resultCursor.getColumnIndex("picture");
            do{
                cursorResult = new Result();

                Name name = new Name();
                name.setFirst(resultCursor.getString(colName));

                Picture picture = new Picture();
                picture.setLarge(resultCursor.getString(colPicture));

                cursorResult.setPicture(picture);
                cursorResult.setEmail(resultCursor.getString(colEmail));
                cursorResult.setName(name);

                cursorResults.add(cursorResult);
            }while (resultCursor.moveToNext());

            results.setValue(cursorResults);
            return  true;
        }else{
            return false;
        }
    }

    private void requestWebData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        RandomAPIService service = retrofit.create(RandomAPIService.class);
        SQLiteDatabase sqLiteDatabase = meDatabase.getWritableDatabase();

        Call<RandomUserResponse> call = service.getRandomUserResponse(10, "haskiesarecute");
        call.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {
                if(response.isSuccessful()){
                    Log.d("MessMe", "Success api response");
                    RandomUserResponse randomUserResponse = response.body();
                    List<Result> resultList = randomUserResponse.getResults();
                    results.setValue(resultList);
                    ContentValues cv;
                    for(int i = 0; i < resultList.size(); i++){
                        cv = new ContentValues();
                        cv.put("id", i);
                        cv.put("name", resultList.get(i).getName().getFirst());
                        cv.put("email", resultList.get(i).getEmail());
                        cv.put("picture", resultList.get(i).getPicture().getLarge());
                        sqLiteDatabase.insert("Users", null, cv);
                    }

                }
            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                Log.d("MessMe", t.getMessage());
            }
        });

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        meDatabase.close();
    }
}
