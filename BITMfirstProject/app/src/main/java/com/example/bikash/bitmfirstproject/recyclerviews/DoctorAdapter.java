package com.example.bikash.bitmfirstproject.recyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikash.bitmfirstproject.R;
import com.example.bikash.bitmfirstproject.models.Doctor;

import java.util.ArrayList;

/**
 * Created by Bikash on 4/27/2018.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>{

    private ArrayList<Doctor>list;
    private Context context;

    public DoctorAdapter(ArrayList<Doctor> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public DoctorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctorAdapter.ViewHolder holder, int position) {

        final Doctor doctor = list.get(position);
        holder.paientName.setText(doctor.getName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+doctor.getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView paientName;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            paientName = itemView.findViewById(R.id.doctorTv);
            linearLayout = itemView.findViewById(R.id.doctorLayout);
        }
    }
}
