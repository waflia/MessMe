package com.waflia.messme;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.waflia.messme.RandomUserAPI.Model.Result;
import com.waflia.messme.dialogs.DialogRecyclerViewAdapter;
import com.waflia.messme.dialogs.DialogViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.waflia.messme.chat.ChatFragment.SIGN_IN_CODE;

public class MainActivity extends AppCompatActivity {
    public final String TAG = "MessMe";
    public static final String CHAT_USER_EMAIL = "chat_user_email";
    public static String CHAT_USER_FIRST = "chat_user_first";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: MainActivity");
        setContentView(R.layout.activity_main);

        setTitle("Диалоги");

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        checkFirebaseAuth();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(findViewById(R.id.main_fragment), "Fab clicked", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(getColor(R.color.colorMessageView))
                        .setTextColor(Color.BLACK)
                        .show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.dialog_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        DialogRecyclerViewAdapter adapter = new DialogRecyclerViewAdapter(new ArrayList<>());
        adapter.getClickEvents().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra(CHAT_USER_EMAIL, result.getEmail());
                intent.putExtra(CHAT_USER_FIRST, result.getName().getFirst());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        LiveData<List<Result>> results = new ViewModelProvider(this).get(DialogViewModel.class).getData();
        results.observe(this, adapter::setDialogList);
    }

    private void checkFirebaseAuth(){
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_CODE);
        }else{
            Toast.makeText(this, "Вы авторизованы как "
                            + FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: MainActivity");
    }
}