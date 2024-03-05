package com.example.cap2024.DataAccessLayer.dao2;

import com.example.cap2024.DataAccessLayer.Dao_history;
import com.example.cap2024.DataAccessLayer.Dao_log;
import com.example.cap2024.DataAccessLayer.dao1.Dao_log1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Dao_log2 implements Dao_log {

    private String date;
    private double signal;
    private String symbol;
    private double amount;
    private double price;
    private String log_name_id;
    public String getDataFromDatabase() {
        // DAO2에서는 데이터베이스와 상호 작용을 위한 로직을 구현합니다.
        return "Data from Database (Dao3)";
    }

    @Override
    public Dao_log fromJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(json, Dao_log2.class);
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
    public String getDate(){
        return this.date;
    }

    @Override
    public double getSignal(){
        return this.signal;
    }

    @Override
    public String getSymbol(){
        return this.symbol;
    }

    @Override
    public double getAmount(){
        return this.amount;
    }

    @Override
    public double getPrice(){
        return this.price;
    }
    @Override
    public String getLog_name_id() { return this.log_name_id; }

    @Override
    public void setDate(String dateInput){
        this.date = dateInput;
    }

    @Override
    public void setSignal(double signalInput){
        this.signal = signalInput;
    }

    @Override
    public void setSymbol(String  symbolInput){
        this.symbol = symbolInput;
    }

    @Override
    public void setAmount(double amountInput){
        this.amount = amountInput;
    }

    @Override
    public void setPrice(double priceInput){
        this.price = priceInput;
    }

    @Override
    public void setLog_name_id(String log_name_idInput) { this.log_name_id=log_name_idInput; }
}