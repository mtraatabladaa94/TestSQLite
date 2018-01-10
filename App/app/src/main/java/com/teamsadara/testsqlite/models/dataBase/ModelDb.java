package com.teamsadara.testsqlite.models.dataBase;

/**
 * Created by Ing. Michel Roberto Traña Tablada(github.com/mtraatabladaa94) on 08/01/2018.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.teamsadara.testsqlite.models.pojo.*;
import java.sql.SQLException;

public class ModelDb extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "test_db.db";
    private static final int DATABASE_VERSION = 1;

    public ModelDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private Dao<Pearson, Integer> pearsonDao;

    @Override
    public void close() {
        super.close();
        this.pearsonDao = null;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Pearson.class);
        }
        catch (SQLException e) {
            System.out.println("Test SQLite App: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        onCreate(sqLiteDatabase, connectionSource);
    }

    //Retornando listado de datos
    public Dao<Pearson, Integer> getPearsonDao() throws SQLException {
        if(this.pearsonDao == null) {
            this.pearsonDao = getDao(Pearson.class);
        }
        return pearsonDao;
    }

}
