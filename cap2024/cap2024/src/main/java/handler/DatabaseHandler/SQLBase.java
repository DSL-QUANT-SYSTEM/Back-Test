package handler.DatabaseHandler;

import handler.DatabaseHandler.DBHandler;

public class SQLBase {
    protected DBHandler handler;

    public SQLBase(DBHandler handler) {
        this.handler = handler;
    }

    public SQLBase() {
        this(DBHandler.GetInstance());
    }
}
