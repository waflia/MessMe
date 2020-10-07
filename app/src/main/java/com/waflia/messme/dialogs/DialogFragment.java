package com.waflia.messme.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.waflia.messme.DialogViewModel;
import com.waflia.messme.R;
import com.waflia.messme.RandomUserAPI.Model.Result;
import com.waflia.messme.chat.ChatFragment;

import java.util.ArrayList;
import java.util.List;

import static com.waflia.messme.chat.ChatFragment.SIGN_IN_CODE;

public class DialogFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Диалоги");

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_CODE);
        }else{
            Toast.makeText(this.getContext(), "Вы авторизованы как "
                    + FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                    Toast.LENGTH_SHORT).show();
        }

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.dialog_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        DialogRecyclerViewAdapter adapter = new DialogRecyclerViewAdapter(new ArrayList<>());
        adapter.getClickEvents().observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                changeFragment(new ChatFragment(result));
            }
        });
        recyclerView.setAdapter(adapter);

        LiveData<List<Result>> results = new ViewModelProvider(this).get(DialogViewModel.class).getData();
        results.observe(this.getViewLifecycleOwner(), adapter::setDialogList);
    }

    private void changeFragment(Fragment f){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }
}
