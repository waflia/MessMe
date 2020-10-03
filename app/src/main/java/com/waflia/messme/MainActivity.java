package com.waflia.messme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.waflia.messme.RandomUserAPI.Model.RandomUserResponse;
import com.waflia.messme.RandomUserAPI.Model.Result;
import com.waflia.messme.RandomUserAPI.RandomAPIService;
import com.waflia.messme.dialogs.DialogRecyclerViewAdapter;
import com.waflia.messme.dialogs.UserDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.dialog_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        DialogRecyclerViewAdapter adapter = new DialogRecyclerViewAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        RandomAPIService service = retrofit.create(RandomAPIService.class);
        Call<RandomUserResponse> call = service.getRandomUserResponse(10);
        call.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {
                if(response.isSuccessful()){
                    Log.d("MessMe", "Success api response");
                    RandomUserResponse randomUserResponse = response.body();
                    List<Result> results = randomUserResponse.getResults();
                    adapter.setDialogList(results);
                }
            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                Log.d("MessMe", t.getMessage());
            }
        });

//        List<UserDialog> testUser = new ArrayList<>();
////        for(int i = 0; i < 10; i++){
////            UserDialog userDialog = new UserDialog("User" + i, R.drawable.ic_launcher_foreground);
////            testUser.add(userDialog);
////        }


//        DialogRecyclerViewAdapter adapter = new DialogRecyclerViewAdapter(testUser);
//        recyclerView.setAdapter(adapter);
        //adapter.notifyAll();

    }
}