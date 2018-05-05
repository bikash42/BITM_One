package com.example.bikash.bitmfirstproject.recyclerviews;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikash.bitmfirstproject.R;
import com.example.bikash.bitmfirstproject.activities.DetailsActivity;
import com.example.bikash.bitmfirstproject.databases.TableDatabaseManager;
import com.example.bikash.bitmfirstproject.models.Appointment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bikash on 4/20/2018.
 */

public class AppiontmentAdapter extends RecyclerView.Adapter<AppiontmentAdapter.ViewHolder>{

    TableDatabaseManager tableDatabaseManager;
    ArrayList<Appointment> appointments;

    private List<AppointInfo>appointInfos;
    private Context context;

    public AppiontmentAdapter(List<AppointInfo> appointInfos, Context context) {
        this.appointInfos = appointInfos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final AppointInfo appointInfo = appointInfos.get(position);
        holder.paientName.setText(appointInfo.getPaient_name());
        holder.contact.setText(appointInfo.getContact());
        holder.specialistDr.setText(appointInfo.getSpecialist());

        ///from databases
        tableDatabaseManager = new TableDatabaseManager(context);
        appointments = tableDatabaseManager.getAllPaientInfo();


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, ""+appointInfo.getPaient_name(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("id",appointments.get(position).getId());
                //intent.putExtra("name",appointInfo.getPaient_name());
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return appointInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView paientName, contact, specialistDr;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            paientName = itemView.findViewById(R.id.nameTv);
            contact = itemView.findViewById(R.id.contactTv);
            specialistDr = itemView.findViewById(R.id.specialist_drTv);
            linearLayout = itemView.findViewById(R.id.linerlayout);
        }
    }
}
