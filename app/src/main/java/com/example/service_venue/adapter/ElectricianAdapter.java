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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ElectricianAdapter extends FirebaseRecyclerAdapter<ViewModel, ElectricianAdapter.myViewHolder> {

    public ElectricianAdapter(@NonNull FirebaseRecyclerOptions<ViewModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull ViewModel model) {
        holder.vName.setText(model.getvName());
        holder.vRate.setText(model.getvRate());
        holder.vDistance.setText(model.getvDistance());


        Glide.with(holder.img.getContext())
                .load(model.getvImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btn_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_vendor_profile))
                        .setExpanded(true, 1400)
                        .create();

                View view = dialogPlus.getHolderView();

                TextView vname = view.findViewById(R.id.vname);
                TextView distance = view.findViewById(R.id.distance);
                TextView rate = view.findViewById(R.id.rate);
                TextView serviceName = view.findViewById(R.id.worktitle);
                TextView vfname = view.findViewById(R.id.vfname);
                TextView vPhone = view.findViewById(R.id.v_phone);
                CircleImageView img = view.findViewById(R.id.v_imageview); // assuming the ImageView ID is 'image_in_dialog'


                vname.setText(model.getvName());
                vPhone.setText(model.getvPhone());
                rate.setText(model.getvRate());
                distance.setText(model.getvDistance());
                serviceName.setText(model.getServiceName());
                vfname.setText(model.getvName());


                Button btn_book_now = view.findViewById(R.id.btn_book_now);

                Glide.with(view.getContext())
                        .load(model.getvImage())
                        .placeholder(com.google.firebase.storage.R.drawable.common_google_signin_btn_icon_dark) // Placeholder image for Glide
                        .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark) // Error image for Glide
                        .circleCrop()
                        .into(holder.img); // Load the image into the ImageView in the dialog


                dialogPlus.show();


            }
        });

        holder.btn_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_confirm_order))
                        .setExpanded(true,1400)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText cName = view.findViewById(R.id.cname);
                EditText cDate  = view.findViewById(R.id.cdate);
                EditText cPhone = view.findViewById(R.id.cphone);
                EditText cAddress = view.findViewById(R.id.caddress);
                EditText cAddressTitle = view.findViewById(R.id.address_title);
                EditText cAppointmentNote = view.findViewById(R.id.appointment_note);
                EditText serviceName = view.findViewById(R.id.worktitle);

                //TextView image = view.findViewById(R.id.c_imageview);

                Button btn_confirm = view.findViewById(R.id.btn_confirm);

                cName.setText(model.getcName());
                serviceName.setText(model.getServiceName());
                cDate.setText(model.getcDate());
                cPhone.setText(model.getcPhone());
                cAddress.setText(model.getcAddress());
                cAddressTitle.setText(model.getcAddressTitle());
                cAppointmentNote.setText(model.getcAppointmentNote());

                dialogPlus.show();

                btn_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("cName",cName.getText().toString());
                        map.put("serviceName",serviceName.getText().toString());
                        map.put("cDate",cDate.getText().toString());
                        map.put("cPhone",cPhone.getText().toString());
                        map.put("cAddress",cAddress.getText().toString());
                        map.put("cAddressTitle",cAddressTitle.getText().toString());
                        map.put("cAppointmentNote",cAppointmentNote.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("serviceVenue").child("request")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.vName.getContext(), "Data Update Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.vName.getContext(), "Error. Please try again", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
        return new myViewHolder(view);
    }



    static class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView vName, vDistance, vRate, serviceName, vfname,vPhone;
        Button btn_view_profile,btn_book_now;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            img = (CircleImageView)itemView.findViewById(R.id.v_imageview);
            vName = itemView.findViewById(R.id.vname);
            vDistance = itemView.findViewById(R.id.distance);
            vRate = itemView.findViewById(R.id.rate);
            serviceName = itemView.findViewById(R.id.worktitle);
            vfname = itemView.findViewById(R.id.vfname);
            vPhone = itemView.findViewById(R.id.v_phone);



            btn_view_profile = (Button) itemView.findViewById(R.id.btn_view_profile);
            btn_book_now = (Button) itemView.findViewById(R.id.btn_book_now);
        }
    }
}