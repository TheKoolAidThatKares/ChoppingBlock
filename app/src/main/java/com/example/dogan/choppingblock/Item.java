package com.example.dogan.choppingblock;

/**
 * Created by woodh on 4/20/2018.
 */

public class Item{
    private String timePeriodStart;
    private String timePeriodEnd;
    private String priceOpen;
    private String priceClose;
    private String tradeCount;

    Item(String inputTimePeriodStart, String inputTimePeriodEnd, String inputPriceOpen, String inputPriceClose, String inputTradeCount ){
        timePeriodStart = inputTimePeriodStart;
        timePeriodEnd = inputTimePeriodEnd;
        priceOpen = inputPriceOpen;
        priceClose = inputPriceClose;
        tradeCount = inputTradeCount;
    }

    public String getTimePeriodStart(){
        return timePeriodStart;
    }

    public String getTimePeriodEnd(){
        return timePeriodEnd;
    }

    public String getPriceOpen(){
        return  priceOpen;
    }

    public String  getPriceClose(){
        return priceClose;
    }

    public String getTradeCount(){
        return tradeCount;
    }
}
