package com.waflia.messme.chat;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.waflia.messme.Message;
import com.waflia.messme.R;
import com.waflia.messme.RandomUserAPI.Model.Result;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

import static android.app.Activity.RESULT_OK;

public class ChatFragment extends Fragment {

    public static final int SIGN_IN_CODE = 1;
    private ChatViewModel mViewModel;
    private Result result;
    private FirebaseListAdapter<Message> adapter;
    private ImageButton sendBtn;
    private ImageView emojiBtn;
    private EmojIconActions emojIconActions;
    private EmojiconEditText messageField;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    private ChatFragment(){}
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

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_CODE);
        }else{
            Toast.makeText(this.getContext(), "Вы авторизованы как "
                    + FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
            displayAllMessages();
        }

        sendBtn = getView().findViewById(R.id.chat_send_btn);
        emojiBtn = getView().findViewById(R.id.chat_emoji_image_view);
        messageField = getView().findViewById(R.id.message_field);

        emojIconActions = new EmojIconActions(getContext(), getView(), messageField, emojiBtn);
        emojIconActions.ShowEmojIcon();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = messageField.getText().toString();
                if(messageText == ""){
                    return;
                }
                FirebaseDatabase.getInstance().getReference()
                        .push().setValue(new Message(
                                messageText,
                                FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                                result.getName().getFullName())
                );
                messageField.setText("");
            }
        });
    }

    private void displayAllMessages() {
        ListView listOfMessages = getView().findViewById(R.id.messages_view);
        Query query = FirebaseDatabase.getInstance().getReference();
        FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>()
                .setLifecycleOwner(this)
                .setLayout(R.layout.message_item)
                .setQuery(query, Message.class)
                .build();
        adapter = new FirebaseListAdapter<Message>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Message model, int position) {
                TextView mess_text, mess_time;
                mess_text = v.findViewById(R.id.message_tv);
                mess_time = v.findViewById(R.id.message_date_tv);

                mess_text.setText(model.getText());
                mess_time.setText(DateFormat.format("HH:mm", model.getTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_CODE){
            if(resultCode == RESULT_OK){
                Toast.makeText(this.getContext(), "Вы авторизованы", Toast.LENGTH_SHORT).show();
                displayAllMessages();
            }else{
                Toast.makeText(this.getContext(), "Вы не авторизованы", Toast.LENGTH_SHORT).show();
            }
        }
    }
}