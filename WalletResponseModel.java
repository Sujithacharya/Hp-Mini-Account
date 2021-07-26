package com.example.hp_mini_account;

public class WalletResponseModel
{
    String id,data_submit,amount;

    public WalletResponseModel(String id,String date_submit, String amount) {
        this.data_submit = date_submit;
        this.amount = amount;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_submit() {
        return data_submit;
    }

    public void setDate_submit(String date_submit) {
        this.data_submit = date_submit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
