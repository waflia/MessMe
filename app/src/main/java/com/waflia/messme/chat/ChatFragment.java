package com.waflia.messme.chat;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waflia.messme.R;
import com.waflia.messme.RandomUserAPI.Model.Result;

public class ChatFragment extends Fragment {

    private ChatViewModel mViewModel;
    private Result result;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    private ChatFragment(){

    }

    public ChatFragment(Result result){
        this.result = result;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        getActivity().setTitle(result.getName().getFullName());
        // TODO: Use the ViewModel
    }

}