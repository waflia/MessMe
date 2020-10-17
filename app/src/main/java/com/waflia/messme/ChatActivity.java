package com.waflia.messme;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.waflia.messme.RandomUserAPI.Model.Name;
import com.waflia.messme.RandomUserAPI.Model.Result;
import com.waflia.messme.chat.ChatViewModel;
import com.waflia.messme.chat.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

import static com.waflia.messme.MainActivity.CHAT_USER_EMAIL;
import static com.waflia.messme.MainActivity.CHAT_USER_FIRST;


public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int SIGN_IN_CODE = 1;
    private ChatViewModel mViewModel;
    private Result result;
    private FirebaseListAdapter<Message> adapter;
    private ImageButton sendBtn;
    private ImageView emojiBtn;
    private EmojIconActions emojIconActions;
    private EmojiconEditText messageField;
    private String currentUser;
    private int DP;
    private ListView listOfMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_fragment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(result == null){
            result = new Result();
            result.setEmail(getIntent().getExtras().getString(CHAT_USER_EMAIL));
            Name username = new Name();
            username.setFirst(getIntent().getExtras().getString(CHAT_USER_FIRST));
            result.setName(username);
        }
//        Toolbar actionBar = findViewById(R.id.action_bar);
//        setSupportActionBar(actionBar);

        DP = (int)getResources().getDisplayMetrics().density;

        //mViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
       // if(mViewModel.getResult() == null) mViewModel.setResult(result);
       // result = mViewModel.getResult();
        setTitle(result.getName().getFirst());

        checkFirebaseAuth();

        sendBtn = findViewById(R.id.chat_send_btn);
        emojiBtn = findViewById(R.id.chat_emoji_image_view);
        messageField = findViewById(R.id.message_field);

        emojIconActions = new EmojIconActions(this, findViewById(R.id.activity_chat_root), messageField, emojiBtn);
        emojIconActions.ShowEmojIcon();

        sendBtn.setOnClickListener(this);
    }

    private void checkFirebaseAuth() {
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_CODE);
        }else{
            //Toast.makeText(this, "Вы авторизованы как "
                 //   + FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
            currentUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            displayAllMessages();
        }
    }

    private void displayAllMessages() {
        listOfMessages = findViewById(R.id.messages_view);
        Query query = FirebaseDatabase.getInstance().getReference().orderByChild("chat_id").equalTo(getChatId());
        FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>()
                .setLifecycleOwner(this)
                .setLayout(R.layout.message_item)
                .setQuery(query, Message.class)
                .build();
        getMessagesFromFirebase(options);
        listOfMessages.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        //listOfMessages.scrollBy(0, 4 * DP);
    }

    private void getMessagesFromFirebase(FirebaseListOptions<Message> options) {
        adapter = new FirebaseListAdapter<Message>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Message model, int position) {
                if(model.getFrom_name().equals(result.getEmail()) || model.getTo_name().equals(result.getEmail())) {
                    TextView mess_text, mess_time;
                    mess_text = v.findViewById(R.id.message_tv);
                    mess_time = v.findViewById(R.id.message_date_tv);
                    LinearLayout root = v.findViewById(R.id.message_root);
                    ConstraintLayout message_view = v.findViewById(R.id.message_view);
                    if (model.getFrom_name().equals(currentUser)) {
                        root.setPadding(60 * DP, 0, 4 * DP, 0);
                        message_view.setBackgroundTintList(ColorStateList.valueOf(
                                getResources().getColor(R.color.colorMessageViewRight,
                                        getTheme())));
                        root.setGravity(Gravity.END);
                    }
                    //root.setVisibility(View.VISIBLE);
                    mess_text.setText(model.getText());
                    mess_time.setText(DateFormat.format("HH:mm", model.getTime()));
                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_CODE){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "Вы авторизованы", Toast.LENGTH_SHORT).show();
                currentUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                displayAllMessages();
            }else{
                Toast.makeText(this, "Вы не авторизованы", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        String messageText = messageField.getText().toString();
        if(messageText.equals("")){
            return;
        }
        FirebaseDatabase.getInstance().getReference()
                .push().setValue(new Message(
                messageText,
                FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                result.getEmail())
        );
        messageField.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("menu 1");
        menu.add("menu 2");
        return false;
        //return super.onCreateOptionsMenu(menu);
    }

    public String getChatId(){
        if(currentUser.hashCode() < result.getEmail().hashCode()) {
            return currentUser + result.getEmail();
        }else{
            return result.getEmail() + currentUser;
        }
    }
}