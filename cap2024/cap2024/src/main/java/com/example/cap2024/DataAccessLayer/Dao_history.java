package com.example.cap2024.DataAccessLayer;

public interface Dao_history {

    String getDataFromDatabase();

    Dao_history fromJson(String json);

    String toJson();

    public String getStock();

    public String getDate();

    public double getClosePrice();

    public double getOpenPrice();

    public double getHighestPrice();

    public double getLowestPrice();
    public double getAdjClosePrice();
    public double getVolume();

    public void setStock(String stockInput);
    public void setDate(String dateInput);

    public void setClosePrice(double closePriceInput);

    public void setOpenPrice(double openPriceInput);

    public void setHighestPrice(double highestPriceInput);

    public void setLowestPrice(double lowestPriceInput);

    public void setAdjClosePrice(double adjClosePriceInput);

    public void setVolume(double volumeInput);
}
