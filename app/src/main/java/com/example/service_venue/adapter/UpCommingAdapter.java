package com.example.service_venue.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.service_venue.R;
import com.example.service_venue.ViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpCommingAdapter extends FirebaseRecyclerAdapter<ViewModel, UpCommingAdapter.myViewHolder> {
    private Button viewOrder;

    public UpCommingAdapter(@NonNull FirebaseRecyclerOptions<ViewModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ViewModel model) {

        holder.cname.setText(model.getCname());
        holder.date.setText(model.getDate());
        holder.worktitle.setText(model.getWorktitle());
        holder.cimage.setText(model.getCimage());


        Glide.with(holder.img.getContext())
                .load(model.getCimage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }

    @NonNull
    @Override
    public UpCommingAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new UpCommingAdapter.myViewHolder(view);
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView cname,worktitle,cimage,date;

        Button viewOrder;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.c_imageview);
            cname = (TextView) itemView.findViewById(R.id.cname);
            worktitle = (TextView) itemView.findViewById(R.id.service);
            date = (TextView) itemView.findViewById(R.id.date);

            viewOrder = (Button) itemView.findViewById(R.id.view_order);



        }
    }
}
