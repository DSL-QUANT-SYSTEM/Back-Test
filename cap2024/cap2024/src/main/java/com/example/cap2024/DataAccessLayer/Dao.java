package com.example.cap2024.DataAccessLayer;

public interface Dao {

    String getDataFromDatabase();

    Dao fromJson(String json);

    String toJson();

    public String getStock();

    public String getDate();

    public int getClosePrice();

    public int getOpenPrice();

    public int getHighestPrice();

    public int getLowestPrice();

    public void setStock(String stockInput);
    public void setDate(String dateInput);

    public void setClosePrice(int closePriceInput);

    public void setOpenPrice(int openPriceInput);

    public void setHighestPrice(int highestPriceInput);

    public void setLowestPrice(int lowestPriceInput);

}
