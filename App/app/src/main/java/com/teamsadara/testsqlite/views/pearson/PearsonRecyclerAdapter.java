package com.teamsadara.testsqlite.views.pearson;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.teamsadara.testsqlite.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

        /*Definiendo objeto SimpleDateFormat*/
        SimpleDateFormat dateFormat;

        /*Obtener días restantes para cumpleaños*/
        Date now = Calendar.getInstance().getTime();
        Date birthDateThisYear = dataProvider.getBirthDate();

        /*Obtener año actual*/
        dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(now);

        /*Obtener día y mes actual*/
        dateFormat = new SimpleDateFormat("dd/MM");
        String dayAndMonth = dateFormat.format(dataProvider.getBirthDate());

        long days = 0;

        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            birthDateThisYear = dateFormat.parse(dayAndMonth + "/" + year);
            if(now.after(birthDateThisYear) && !now.equals(birthDateThisYear)) {
                year = String.valueOf(Integer.parseInt(year) + 1);
                birthDateThisYear = dateFormat.parse(dayAndMonth + "/" + year);
            }

            //calculando días
            days = (birthDateThisYear.getTime() - now.getTime()) / (24 * 60 * 60 * 1000);
        }
        catch (ParseException ex) { }



        /*Inicializar datos en el Holder*/
        holder.pearsonID.setText("ID: " + String.valueOf(dataProvider.getPearsonID()));
        holder.name.setText(dataProvider.getPearsonName());
        dateFormat = new SimpleDateFormat("dd/MM");
        holder.birthDate.setText(dateFormat.format(dataProvider.getBirthDate()));

        /*Se asignan los días restantes*/
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.isActive.setText("Faltan " + String.valueOf(days) + " días");

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
            this.isActive = (TextView)view.findViewById(R.id.txtDaysForBirthDate);
            this.pearsonID = (TextView)view.findViewById(R.id.txtPearsonId);

        }

    }

}