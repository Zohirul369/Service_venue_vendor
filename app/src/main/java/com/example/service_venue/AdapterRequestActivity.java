package com.example.service_venue;

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

public class AdapterRequestActivity extends FirebaseRecyclerAdapter<ViewModel, AdapterRequestActivity.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private Button btn_edit;
    public AdapterRequestActivity(@NonNull FirebaseRecyclerOptions<ViewModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterRequestActivity.myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull ViewModel model) {
        holder.cname.setText(model.getCname());
        holder.worktile.setText(model.getWorktitle());
        holder.date.setText(model.getDate());




        Glide.with(holder.img.getContext())
                .load(model.getCimage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.view_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_view_order_first))
                        .setExpanded(true,1400)
                        .create();

                dialogPlus.show();

            }
        });

    }

    @NonNull
    @Override
    public AdapterRequestActivity.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }
    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView cname, worktile, date;

        Button view_order;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.c_imageview);
            cname = (TextView)itemView.findViewById(R.id.cname);
            worktile = (TextView)itemView.findViewById(R.id.worktitle);
            date = (TextView)itemView.findViewById(R.id.date);

            view_order = (Button) itemView.findViewById(R.id.view_order);


        }
    }
}
