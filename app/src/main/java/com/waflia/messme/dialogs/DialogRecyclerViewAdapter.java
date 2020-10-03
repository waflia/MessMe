package com.waflia.messme.dialogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.waflia.messme.R;
import com.waflia.messme.RandomUserAPI.Model.Result;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class DialogRecyclerViewAdapter extends RecyclerView.Adapter<DialogRecyclerViewAdapter.ViewHolder>{

    private List<Result> dialogList;
    private MutableLiveData<Result> onClickObservable = new MutableLiveData<>();

    public DialogRecyclerViewAdapter(List<Result> dialogList) {
        this.dialogList = dialogList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText(dialogList.get(position).getName().getFullName());
        String avatar_url = dialogList.get(position).getPicture().getLarge();
        Picasso.get().load(avatar_url).into(holder.dialogImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickObservable.setValue(dialogList.get(position));
            }
        });
    }

    public LiveData<Result> getClickEvents(){
        return onClickObservable;
    }

    @Override
    public int getItemCount() {
        return dialogList.size();
    }

    public void setDialogList(List<Result> dialogList) {
        this.dialogList = dialogList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView dialogImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.textView);
            dialogImage = itemView.findViewById(R.id.imageView);

        }
    }
}
