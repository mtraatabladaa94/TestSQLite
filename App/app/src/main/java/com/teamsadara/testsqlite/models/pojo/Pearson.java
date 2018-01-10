package com.teamsadara.testsqlite.models.pojo;

/**
 * Created by Ing. Michel Roberto Traña Tablada(github.com/mtraatabladaa94) on 24/05/2017.
 */

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import java.util.Date;

@DatabaseTable(tableName = "tblPearsons")
public class Pearson {

    public static final String PEARSON_ID = "pearsonID";
    public static final String NAME = "pearsonName";
    public static final String BIRTHDATE = "birthDate";
    public static final String IS_ACTIVE = "isActive";

    @DatabaseField(columnName = PEARSON_ID, generatedId = true)
    private int pearsonID;
    public int getPearsonID() {
        return pearsonID;
    }
    public void setPearsonID(int pearsonID) {
        this.pearsonID = pearsonID;
    }


    @DatabaseField(columnName = NAME)
    private String pearsonName;
    public String getPearsonName() {
        return pearsonName;
    }
    public void setPearsonName(String pearsonName) {
        this.pearsonName = pearsonName;
    }

    @DatabaseField(columnName = BIRTHDATE)
    private Date birthDate;
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    @DatabaseField(columnName = IS_ACTIVE)
    private Integer isActive;
    public Integer getIsActive() {
        return isActive;
    }
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

}
