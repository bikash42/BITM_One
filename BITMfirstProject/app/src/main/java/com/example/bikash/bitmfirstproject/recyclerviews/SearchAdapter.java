package com.example.bikash.bitmfirstproject.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bikash.bitmfirstproject.R;
import com.example.bikash.bitmfirstproject.activities.DetailsActivity;
import com.example.bikash.bitmfirstproject.databases.TableDatabaseManager;
import com.example.bikash.bitmfirstproject.models.Appointment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bikash on 4/28/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {

    TableDatabaseManager tableDatabaseManager;
    ArrayList<Appointment> appointments;

    private List<AppointInfo> appointInfos;
    private List<AppointInfo> PatientFilteredList;
    private Context context;
    private SearchAdapterListener searchAdapterListener;

    public SearchAdapter(List <AppointInfo> appointInfos, Context context,SearchAdapterListener searchAdapterListener) {
        this.appointInfos = appointInfos;
        this.context = context;
        this.searchAdapterListener = searchAdapterListener;
        this.PatientFilteredList = appointInfos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView paientName;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            paientName = itemView.findViewById(R.id.singleTv);
            linearLayout = itemView.findViewById(R.id.linerlayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchAdapterListener.onNameSelected(PatientFilteredList.get(getAdapterPosition()));
                    /*Intent intent = new Intent(context,DetailsActivity.class);
                    intent.putExtra("id",appointments.get(getAdapterPosition()).getId());
                    context.startActivity(intent);*/
                }
            });
        }
    }


    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, final int position) {

        final AppointInfo appointInfo = PatientFilteredList.get(position);
        holder.paientName.setText(appointInfo.getPaient_name());

        ///from databases
        tableDatabaseManager = new TableDatabaseManager(context);
        appointments = tableDatabaseManager.getAllPaientInfo();


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("id",appointments.get(position).getId());
                //intent.putExtra("name",appointInfo.getPaient_name());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return PatientFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString=charSequence.toString();
                if(charString.isEmpty()){
                    PatientFilteredList=appointInfos;
                }else{
                    ArrayList<AppointInfo>filterList=new ArrayList<>();
                    for(AppointInfo appointInfo:appointInfos){
                        if(appointInfo.getPaient_name().toLowerCase().contains(charString.toLowerCase())){
                            filterList.add(appointInfo);
                        }
                    }
                    PatientFilteredList=filterList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=PatientFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                PatientFilteredList= (ArrayList<AppointInfo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface SearchAdapterListener{

        void onNameSelected(AppointInfo appointInfo);
    }


}
