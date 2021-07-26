package com.example.hp_mini_account;

import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class DataValue
{
    static int vistedHouse;
//    public int inspecton[]=new int[vistedHouse];
//    public static RadioButton insp[]=new RadioButton[vistedHouse];
//    public static Spinner hose[]=new Spinner[vistedHouse];
//    public static Spinner clamp[]=new Spinner[vistedHouse];
//    public static Spinner regulator[]=new Spinner[vistedHouse];
    public ArrayList<ModelClass> data=new ArrayList<>();

    public ArrayList<ModelClass> getData()
    {
        return data;
    }

    public void setData(ArrayList<ModelClass> data) {
        this.data = data;
    }

    public void setVistedHouse(int vistedHouse)
    {
        this.vistedHouse = vistedHouse;
    }

}
