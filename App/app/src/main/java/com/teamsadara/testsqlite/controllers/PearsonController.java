package com.teamsadara.testsqlite.controllers;

/**
 * Created by Administrator on 27/05/2017.
 */

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.teamsadara.testsqlite.models.pojo.Pearson;
import com.teamsadara.testsqlite.models.dataBase.ModelDb;
import com.teamsadara.testsqlite.models.dataProvider.PearsonDataProvider;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PearsonController {

    private ModelDb db;

    public PearsonController(Context context) {
        this.db = OpenHelperManager.getHelper(context, ModelDb.class);
    }

    public ArrayList<PearsonDataProvider> getPearsons() throws SQLException {

        return this.getPearsonsList();

    }

    public void Create(String name, Date birthDate) {

        this.CreatePearson(name, birthDate);

    }

    public void onDestroy() {
        if(this.db != null) {
            OpenHelperManager.releaseHelper();
            this.db = null;
        }
    }

    /*Private functions*/
    private ArrayList<PearsonDataProvider> getPearsonsList() throws SQLException {
        ArrayList<PearsonDataProvider> pearsonList = new ArrayList<PearsonDataProvider>();
        Dao dao = this.db.getPearsonDao();

        List<Pearson> pearsons = dao.queryForAll();

        //variables para formato de fecha
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //ciclo de llenado de DataProviders
        for(Pearson item : pearsons) {
            PearsonDataProvider obj = new PearsonDataProvider(
                item.getPearsonID(),
                item.getPearsonName(),
                item.getBirthDate(),
                item.getIsActive()
            );
            pearsonList.add(obj);
        }

        return pearsonList;
    }

    private void CreatePearson(String name, Date birthDate) {

        try {

            Pearson pearson = new Pearson();
            pearson.setPearsonName(name);
            pearson.setBirthDate(birthDate);
            pearson.setIsActive(1);

            Dao dao = this.db.getPearsonDao();
            dao.create(pearson);

        }
        catch (SQLException ex) {
            Log.e("Test SQLite App: ", ex.getMessage());
        }

    }

}
