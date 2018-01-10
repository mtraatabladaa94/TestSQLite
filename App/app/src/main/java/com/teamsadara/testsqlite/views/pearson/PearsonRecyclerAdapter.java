package com.teamsadara.testsqlite.views.pearson;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.teamsadara.testsqlite.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.teamsadara.testsqlite.models.dataProvider.PearsonDataProvider;

/**
 * Created by Administrator on 26/05/2017.
 */

public class PearsonRecyclerAdapter extends RecyclerView.Adapter<PearsonRecyclerAdapter.surveyRecyclerViewHolder> {

    private ArrayList<PearsonDataProvider> arrayList = new ArrayList<PearsonDataProvider>();

    public PearsonRecyclerAdapter(ArrayList<PearsonDataProvider> _arrayList) {
        this.arrayList = _arrayList;
    }

    @Override
    public surveyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pearson_recyclerview, parent, false);
        surveyRecyclerViewHolder recyclerViewHolder = new surveyRecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(surveyRecyclerViewHolder holder, int position) {
        PearsonDataProvider dataProvider = this.arrayList.get(position);

        holder.pearsonID.setText("ID: " + String.valueOf(dataProvider.getPearsonID()));
        holder.name.setText(dataProvider.getPearsonName());
        holder.birthDate.setText(dataProvider.getBirthDate().toString());

        String active;
        if(dataProvider.getIsActive() == 1) {
            active = "Activo";
        }
        else {
            active = "Inactivo";
        }
        holder.isActive.setText(active);

    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    public static class surveyRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView name, birthDate, isActive, pearsonID;

        public surveyRecyclerViewHolder(View view) {
            super(view);

            this.name = (TextView)view.findViewById(R.id.txtPearsonName);
            this.birthDate = (TextView)view.findViewById(R.id.txtBirthDate);
            this.isActive = (TextView)view.findViewById(R.id.txtIsActive);
            this.pearsonID = (TextView)view.findViewById(R.id.txtPearsonId);

        }

    }

}