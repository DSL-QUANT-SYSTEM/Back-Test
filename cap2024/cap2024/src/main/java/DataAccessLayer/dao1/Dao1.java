package DataAccessLayer.dao1;

import DataAccessLayer.Dao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Dao1 implements Dao {

    private String stock;
    private String date;
    private int closePrice;
    private int openPrice;
    private int highestPrice;
    private int lowestPrice;

    public String getDataFromDatabase() {
        // DAO1에서는 데이터베이스와 상호 작용을 위한 로직을 구현합니다.
        return "Data from Database (Dao1)";
    }

    @Override
    public Dao fromJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(json, Dao1.class);
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
    public int getClosePrice(){
        return this.closePrice;
    }

    @Override
    public int getOpenPrice(){
        return this.openPrice;
    }

    @Override
    public int getHighestPrice(){
        return this.highestPrice;
    }

    @Override
    public int getLowestPrice(){
        return this.lowestPrice;
    }

    public void setStock(String stockInput){this.stock = stockInput;}
    @Override
    public void setDate(String dateInput){
        this.date = dateInput;
    }

    @Override
    public void setClosePrice(int closePriceInput){
        this.closePrice = closePriceInput;
    }

    @Override
    public void setOpenPrice(int openPriceInput){
        this.openPrice = openPriceInput;
    }

    @Override
    public void setHighestPrice(int highestPriceInput){
        this.highestPrice = highestPriceInput;
    }

    @Override
    public void setLowestPrice(int lowestPriceInput){
        this.lowestPrice = lowestPriceInput;
    }


}