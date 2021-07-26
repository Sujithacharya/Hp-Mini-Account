package com.example.hp_mini_account;

public class GetDate
{
  String id,startDate,endDate,settle;

    public GetDate(String id, String startDate, String endDate, String settle) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.settle = settle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSettle() {
        return settle;
    }

    public void setSettle(String settle) {
        this.settle = settle;
    }
}
