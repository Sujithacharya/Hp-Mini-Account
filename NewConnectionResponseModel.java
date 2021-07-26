package com.example.hp_mini_account;

public class NewConnectionResponseModel
{
    String id,date_new,consumerNo,place;

    public NewConnectionResponseModel(String id, String date_new, String consumerNo, String place) {
        this.id = id;
        this.date_new = date_new;
        this.consumerNo = consumerNo;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_new() {
        return date_new;
    }

    public void setDate_new(String date_new) {
        this.date_new = date_new;
    }

    public String getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(String consumerNo) {
        this.consumerNo = consumerNo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
