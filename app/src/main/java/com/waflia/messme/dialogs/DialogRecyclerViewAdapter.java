package com.waflia.messme.dialogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.waflia.messme.R;

import java.io.IOException;
import java.util.List;

public class DialogRecyclerViewAdapter extends RecyclerView.Adapter<DialogRecyclerViewAdapter.ViewHolder> {

    private List<UserDialog> dialogList;

    public DialogRecyclerViewAdapter(List<UserDialog> dialogList) {
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
        holder.username.setText(dialogList.get(position).getUsername());

        holder.dialogImage.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return dialogList.size();
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
