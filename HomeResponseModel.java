package com.example.hp_mini_account;

public class HomeResponseModel
{
    String id,name_inspector,date_inspected,consumer_num,inspection,hose,clamp,regulator;

    public HomeResponseModel(String id, String name_inspector, String date_inspected, String consumer_num, String inspection, String hose, String clamp, String regulator) {
        this.id = id;
        this.name_inspector = name_inspector;
        this.date_inspected = date_inspected;
        this.consumer_num = consumer_num;
        this.inspection = inspection;
        this.hose = hose;
        this.clamp = clamp;
        this.regulator = regulator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_inspector() {
        return name_inspector;
    }

    public void setName_inspector(String name_inspector) {
        this.name_inspector = name_inspector;
    }

    public String getDate_inspected() {
        return date_inspected;
    }

    public void setDate_inspected(String date_inspected) {
        this.date_inspected = date_inspected;
    }

    public String getConsumer_num() {
        return consumer_num;
    }

    public void setConsumer_num(String consumer_num) {
        this.consumer_num = consumer_num;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
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
}
