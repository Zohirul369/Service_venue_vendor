package com.example.service_venue.adapter;

import android.annotation.SuppressLint;
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

public class RequestAdapter extends FirebaseRecyclerAdapter<ViewModel, RequestAdapter.myViewHolder> {

    public RequestAdapter(@NonNull FirebaseRecyclerOptions<ViewModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull ViewModel model) {
        holder.cName.setText(model.getcName());
        //holder.cWorkTitle.setText(model.getcWorkTitle());
        holder.cDate.setText(model.getcDate());
        holder.cAddress.setText(model.getcAddress());
        holder.serviceName.setText(model.getServiceName());
        holder.address_title.setText(model.getcAddressTitle());
        holder.cPhone.setText(model.getcPhone());



        Glide.with(holder.img.getContext())
                .load(model.getcImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);




    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView cName, cWorkTitle, cDate,cAddress,serviceName,address_title,cPhone;

        Button view_order, btn_accept, btn_cancel;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.client_img);
            cName = (TextView) itemView.findViewById(R.id.cname);
            //cWorkTitle = (TextView) itemView.findViewById(R.id.job_task);
            cDate = (TextView) itemView.findViewById(R.id.cdate);
            cAddress = (TextView) itemView.findViewById(R.id.caddress);
            serviceName = (TextView) itemView.findViewById(R.id.job_task);
            address_title = (TextView) itemView.findViewById(R.id.address_title);
            cPhone = (TextView) itemView.findViewById(R.id.cphone);



            btn_accept = (Button) itemView.findViewById(R.id.btn_accept);
            btn_cancel = (Button) itemView.findViewById(R.id.btn_cancel);


        }
    }
}
