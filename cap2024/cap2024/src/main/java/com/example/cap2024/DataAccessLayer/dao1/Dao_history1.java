package com.example.cap2024.DataAccessLayer.dao1;

import com.example.cap2024.DataAccessLayer.Dao_history;
import com.example.cap2024.DataAccessLayer.Dao_log;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Dao_history1 implements Dao_history {

    private String stock;
    private String date;
    private double closePrice;
    private double openPrice;
    private double highestPrice;
    private double lowestPrice;
    private double adjClosePrice;
    private double volume;

    public String getDataFromDatabase() {
        // DAO3에서는 데이터베이스와 상호 작용을 위한 로직을 구현합니다.
        return "Data from Database (Dao3)";
    }

    @Override
    public Dao_history fromJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(json, Dao_history1.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(this);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String getStock(){
        return this.stock;
    }

    @Override
    public String getDate(){
        return this.date;
    }

    @Override
    public double getClosePrice(){
        return this.closePrice;
    }

    @Override
    public double getOpenPrice(){
        return this.openPrice;
    }

    @Override
    public double getHighestPrice(){
        return this.highestPrice;
    }

    @Override
    public double getLowestPrice(){
        return this.lowestPrice;
    }
    @Override
    public double getAdjClosePrice() { return this.adjClosePrice; }
    @Override
    public double getVolume() { return this.volume;}
    @Override
    public void setStock(String stockInput){this.stock = stockInput;}
    @Override
    public void setDate(String dateInput){
        this.date = dateInput;
    }

    @Override
    public void setClosePrice(double closePriceInput){
        this.closePrice = closePriceInput;
    }

    @Override
    public void setOpenPrice(double openPriceInput){
        this.openPrice = openPriceInput;
    }

    @Override
    public void setHighestPrice(double highestPriceInput){
        this.highestPrice = highestPriceInput;
    }

    @Override
    public void setLowestPrice(double lowestPriceInput){
        this.lowestPrice = lowestPriceInput;
    }

    @Override
    public void setAdjClosePrice(double adjClosePriceInput){
        this.adjClosePrice=adjClosePriceInput;
    }
    @Override
    public void setVolume(double volumeInput){
        this.volume=volumeInput;
    }
}