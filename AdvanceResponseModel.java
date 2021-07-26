package com.example.hp_mini_account;

public class AdvanceResponseModel
{
    String id,present_adv_date,nameadv,ammount,reason;

    public AdvanceResponseModel(String id, String present_adv_date, String nameadv, String ammount, String reason) {
        this.id = id;
        this.present_adv_date = present_adv_date;
        this.nameadv = nameadv;
        this.ammount = ammount;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPresent_adv_date() {
        return present_adv_date;
    }

    public void setPresent_adv_date(String present_adv_date) {
        this.present_adv_date = present_adv_date;
    }

    public String getNameadv() {
        return nameadv;
    }

    public void setNameadv(String nameadv) {
        this.nameadv = nameadv;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
