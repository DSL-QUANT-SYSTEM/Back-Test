package com.example.cap2024.handler.DatabaseHandler;

public class SQLBase {
    protected DBHandler handler;

    public SQLBase(DBHandler handler) {
        this.handler = handler;
    }

    public SQLBase() {
        this(DBHandler.GetInstance());
    }
}
