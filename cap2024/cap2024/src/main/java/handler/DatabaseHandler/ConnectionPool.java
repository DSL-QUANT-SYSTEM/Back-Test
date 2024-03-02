package handler.DatabaseHandler;

import handler.SystemConfigHandler.DataBaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnectionPool {
    private static HashMap<String, Object> pool = new HashMap<>();
    private final DataBaseConfig confTmp;

    public ConnectionPool() {
        this.confTmp = null;
    }

    public ConnectionPool(DataBaseConfig _DataBaseConfig) {
        this.confTmp = _DataBaseConfig;
    }

    public Connection GetConnection(String poolName) {
        if (!pool.containsKey(poolName) || pool.get(poolName) == null) {
            pool.put(poolName, null);
            CreateDBConnection(poolName);
        } else if (pool.get(poolName) == null) {
            CreateDBConnection(poolName);
        }

        Connection ret = null;
        int i = 0;
        do {
            ret = SearchPool(poolName);
        } while (ret == null && i++ < 300);

        return ret;
    }

    public static void RemovePool(String poolName) {
        pool.remove(poolName);
    }

    public static void Reload() {
        pool = new HashMap<>();
    }

    private Connection GetConnectionObject(String poolName) throws SQLException {
        DataBaseConfig conf;
        if (confTmp == null) {
            conf = new DataBaseConfig();
        } else {
            conf = confTmp;
        }

        return DriverManager.getConnection(conf.GetConnectionString(poolName));
    }

    private Connection SearchPool(String poolName) {
        Connection[] temp = (Connection[]) pool.get(poolName);

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                try {
                    if (temp[i].isClosed()) {
                        temp[i].close();
                        temp[i] = GetConnectionObject(poolName);
                        return temp[i];
                    }

                    if (!temp[i].isClosed()) {
                        return temp[i];
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        DataBaseConfig conf = new DataBaseConfig();
        if ("NO".equals(conf.getProperty(poolName + "_ALLOW_WAIT").toUpperCase())) {
            try {
                return GetConnectionObject(poolName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private void CreateDBConnection(String poolName) {
        Connection[] conn;
        if (pool.get(poolName) == null) {
            conn = new Connection[1];
            for (int i = 0; i < conn.length; i++) {
                try {
                    conn[i] = GetConnectionObject(poolName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            pool.put(poolName, conn);
        } else {
            Connection[] temp = (Connection[]) pool.get(poolName);
            conn = new Connection[1];
            for (int i = 0; i < conn.length; i++) {
                if (i >= temp.length) {
                    try {
                        conn[i] = GetConnectionObject(poolName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    conn[i] = temp[i];
                }
            }
            pool.put(poolName, conn);
        }
    }
}
