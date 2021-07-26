package com.example.hp_mini_account;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Myapi
{
    @FormUrlEncoded
    @POST("inspection.php")
    Call<ResponseBody> addData(
                             @Field("consumerNo") int consumerNo,
                             @Field("inspection") String inspection,
                             @Field("hose") String hose,
                             @Field("clamp") String clamp,
                             @Field("regulator") String regulator,
                             @Field("inspector")String inspector);
    @FormUrlEncoded
    @POST("updatecost.php")
    Call<ResponseBody> addCost(
            @Field(" costInspection") String costInspection,
            @Field("costtube15") String costtube15,
            @Field("costtube25") String costtube25,
            @Field("costtube35") String costtube35,
            @Field("costtube45") String costtube45,
            @Field("costclamp")String costclamp,
            @Field("costregulator")String costregulator,
            @Field("costNewConnection")String costNewConnection
          );
    @FormUrlEncoded
    @POST("updateprofit.php")
    Call<ResponseBody>addProfit(
            @Field(" profitInspection") String  profitInspection,
            @Field("profittube15") String profittube15,
            @Field("profittube25") String profittube25,
            @Field("profittube35") String profittube35,
            @Field("profittube45") String profittube45,
            @Field("profitclamp")String profitclamp,
            @Field("profitregulator")String profitregulator,
            @Field("profitNewConnection")String profitNewConnection
    );

//    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> addData_login(@Field("name") String name,
                                    @Field("password") String password);
//    Call<LoginData> getUser(@Body String body);

    @FormUrlEncoded
    @POST("newconnection.php")
    Call<ResponseBody>addData_newConnection(@Field("consumerNo") String consumerNo,
                                @Field("place") String place);
    @FormUrlEncoded
    @POST("advance.php")
    Call<ResponseBody> addData_advance(@Field("name")String name,
                                  @Field("amount")String amount,
                                  @Field("reason")String reason);
    @FormUrlEncoded
    @POST("wallet.php")
    Call<ResponseBody> addData_wallet(@Field("submited")String submited);

    @FormUrlEncoded
    @POST("startDate.php")
    Call<ResponseBody> addData_startdate(@Field("startDate")String startDate);

    @FormUrlEncoded
    @POST("endDate.php")
    Call<ResponseBody> addData_enddate(@Field("endDate")String endDate);

    @FormUrlEncoded
    @POST("settleDate.php")
    Call<ResponseBody> addData_settleDate(@Field("settleDate")String settleDate);

    @FormUrlEncoded
    @POST("filterDate.php")
    Call<ResponseBody>addData_FilterDate(@Field("filterdate")String filterdate);
    //    ***********************************************************************************************************************
    //    Getting data
    @GET("getInspection.php")
    Call<List<HomeResponseModel>>getData();

    @GET("getItemCount.php")
    Call<List<ItemSold>>getItemCount();

    @GET("getCost.php")
    Call<GetCost>getCost();

    @GET("getProfit.php")
    Call<GetProfit>getProfit();

    @GET("getAdvance.php")
    Call<List<AdvanceResponseModel>>getAdvance();

    @GET("getnewConnect.php")
    Call<List<NewConnectionResponseModel>>getNewConnect();

    @GET("submittedlist.php")
    Call<List<WalletResponseModel>>getWalletList();

    @GET("getDate.php")
    Call<List<GetDate>>getDate();

    @GET("getSalaryItems.php")
    Call<List<GetSalaryItems>>getSalaryItems();

    @GET("getPriceProfit.php")
    Call<PojoPriceandProfit>getPriceProfit();

    @GET("adv.php")
    Call<List<Pojoadv>>getadv();

    @GET("getSubmittedAmount.php")
    Call<PojoSubmit>getSubAMT();

    @GET("getCountNewConnect.php")
    Call<NewConnectCount>getnewConnectCount();
}
