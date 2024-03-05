package com.example.cap2024.DataAccessLayer;

public interface Dao_log {

    String getDataFromDatabase();

    Dao_log fromJson(String json);

    String toJson();

    public String getDate();

    public double getSignal();

    public String getSymbol();

    public double getAmount();

    public double getPrice();

    public String getLog_name_id();

    public void setDate(String dateInput);

    public void setSignal(double signalInput);

    public void setSymbol(String symbolInput);

    public void setAmount(double amountInput);

    public void setPrice(double priceInput);

    public void setLog_name_id(String log_name_idInput);
}
