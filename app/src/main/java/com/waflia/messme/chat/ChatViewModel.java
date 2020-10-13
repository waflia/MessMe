package com.waflia.messme.chat;

import androidx.lifecycle.ViewModel;

import com.waflia.messme.RandomUserAPI.Model.Result;

public class ChatViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}