package com.teamsadara.testsqlite.models.dataProvider;

import java.util.Date;

/**
 * Created by Administrator on 26/05/2017.
 */

public class PearsonDataProvider {

    private int pearsonID;
    private String pearsonName;
    private Date birthDate;
    private int isActive;

    public PearsonDataProvider(int _pearsonID, String _pearsonName, Date _birthDate, int _isActive) {
        this.pearsonID = _pearsonID;
        this.pearsonName = _pearsonName;
        this.birthDate = _birthDate;
        this.isActive = _isActive;
    }

    public int getPearsonID() {
        return pearsonID;
    }

    public void setPearsonID(int pearsonID) {
        this.pearsonID = pearsonID;
    }

    public String getPearsonName() {
        return pearsonName;
    }

    public void setPearsonName(String pearsonName) {
        this.pearsonName = pearsonName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

}
