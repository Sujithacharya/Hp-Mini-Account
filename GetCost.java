package com.example.hp_mini_account;

public class GetCost
{
    String  costInspection,costtube15,costtube25,costtube35,costtube45,costclamp,costregulator,costNewConnection;

    public GetCost(String costInspection, String costtube15, String costtube25, String costtube35, String costtube45, String costclamp, String costregulator, String costNewConnection) {
        this.costInspection = costInspection;
        this.costtube15 = costtube15;
        this.costtube25 = costtube25;
        this.costtube35 = costtube35;
        this.costtube45 = costtube45;
        this.costclamp = costclamp;
        this.costregulator = costregulator;
        this.costNewConnection = costNewConnection;
    }

    public String getCostInspection() {
        return costInspection;
    }

    public void setCostInspection(String costInspection) {
        this.costInspection = costInspection;
    }

    public String getCosttube15() {
        return costtube15;
    }

    public void setCosttube15(String costtube15) {
        this.costtube15 = costtube15;
    }

    public String getCosttube25() {
        return costtube25;
    }

    public void setCosttube25(String costtube25) {
        this.costtube25 = costtube25;
    }

    public String getCosttube35() {
        return costtube35;
    }

    public void setCosttube35(String costtube35) {
        this.costtube35 = costtube35;
    }

    public String getCosttube45() {
        return costtube45;
    }

    public void setCosttube45(String costtube45) {
        this.costtube45 = costtube45;
    }

    public String getCostclamp() {
        return costclamp;
    }

    public void setCostclamp(String costclamp) {
        this.costclamp = costclamp;
    }

    public String getCostregulator() {
        return costregulator;
    }

    public void setCostregulator(String costregulator) {
        this.costregulator = costregulator;
    }

    public String getCostNewConnection() {
        return costNewConnection;
    }

    public void setCostNewConnection(String costNewConnection) {
        this.costNewConnection = costNewConnection;
    }
}
