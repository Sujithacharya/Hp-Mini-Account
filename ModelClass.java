package com.example.hp_mini_account;


import android.widget.RadioButton;
import android.widget.Spinner;

public class ModelClass
{
    public  int consumerNo;
    public String inspection;
    public String  hose,clamp,regulator;


    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public int getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(int consumerNo) {
        this.consumerNo = consumerNo;
    }



    public String getHose() {
        return hose;
    }

    public void setHose(String hose) {
        this.hose = hose;
    }

    public String getClamp() {
        return clamp;
    }

    public void setClamp(String clamp) {
        this.clamp = clamp;
    }

    public String getRegulator() {
        return regulator;
    }

    public void setRegulator(String regulator) {
        this.regulator = regulator;
    }

    ModelClass(int consumerNo, String inspection, String hose, String clamp, String regulator)
    {
        this.consumerNo=consumerNo;
        this.inspection=inspection;
        this.hose=hose;
        this.regulator=regulator;
    }



}
