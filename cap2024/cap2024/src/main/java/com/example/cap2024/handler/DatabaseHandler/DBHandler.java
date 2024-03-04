package com.example.cap2024.handler.DatabaseHandler;

import com.example.cap2024.handler.SystemConfigHandler.DataBaseConfig;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;

public class DBHandler {
    public static final int PAGING_SIZE = 100;
    protected static final String argSymbol = "@";

    public static final String EXIT_CODE = "_ExitCode_";
    public static final String PROC_MESSAGE = "_ProcessMessage_";
    private static final Hashtable<String, DBHandler> self = new Hashtable<>();
    public static String defaultConnName = "TargetDB";
    public Connection activeTransaction;
    public String connectionString = "";
    public boolean initializeFlag = true;
    public String vendor = "";

    public String poolName;
    public Connection activeConnection;

    private DBHandler() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = (stackTrace.length > 5) ? stackTrace[5].getMethodName() : stackTrace[1].getMethodName();

        System.out.printf("%1$tH:%1$tM:%1$tS.%1$tL DBHandler() Constructed from [%2$s]%n",
                new java.sql.Date(System.currentTimeMillis()), methodName);

        DataBaseConfig conf = new DataBaseConfig();
        poolName = defaultConnName;
        vendor = conf.getProperty(poolName + "_VENDOR");
        connectionString = conf.GetConnectionString(poolName);
        InitializeConnection();
        try {
            if (activeConnection != null && activeConnection.isValid(5)){
                // JDBC 드라이버 클래스 로드
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
                // 데이터베이스 연결 정보
                String jdbcUrl = "jdbc:mysql://localhost:3306/cap";
                String user = "root";
                String password = "1234";
                // 데이터베이스 연결
                try {
                    activeConnection = DriverManager.getConnection(jdbcUrl, user, password);
                    // 여기에서 연결하여 다양한 데이터베이스 작업을 수행할 수 있습니다.
                    // 연결 종료
                    activeConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            initializeFlag = false;
            ex.printStackTrace();
        }
    }

    private DBHandler(String poolName, String iniPath) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = (stackTrace.length > 5) ? stackTrace[5].getMethodName() : stackTrace[1].getMethodName();

        System.out.printf("%1$tH:%1$tM:%1$tS.%1$tL DBHandler(poolName:%2$s, iniPath:%3$s) Constructed from [%4$s]%n",
                new java.sql.Date(System.currentTimeMillis()), poolName, iniPath, methodName);

        DataBaseConfig conf = new DataBaseConfig();
        this.poolName = poolName;
        vendor = conf.getProperty(this.poolName + "_VENDOR");
        connectionString = conf.GetConnectionString(poolName);

        System.out.printf("%1$tH:%1$tM:%1$tS.%1$tL DBHandler > Connection String:%2$s%n",
                new java.sql.Date(System.currentTimeMillis()), connectionString);

        InitializeConnection();
        try {
            if (activeConnection != null && activeConnection.isValid(5)){
                // JDBC 드라이버 클래스 로드
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
                // 데이터베이스 연결 정보
                String jdbcUrl = "jdbc:mysql://localhost:3306/cap";
                String user = "root";
                String password = "1234";
                // 데이터베이스 연결
                try {
                    activeConnection = DriverManager.getConnection(jdbcUrl, user, password);
                    // 여기에서 연결을 사용하여 다양한 데이터베이스 작업을 수행할 수 있습니다.
                    // 연결 종료
                    activeConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            initializeFlag = false;
            ex.printStackTrace();
        }
    }

    public static boolean TestDB(String vendor, String userT, String deport, String dbname, String id, String pwd) {
        DataBaseConfig conf = new DataBaseConfig();
        String connStr = conf.GetConnectionString(vendor, dbname, userT, deport, id, pwd);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connStr);
            conn.setAutoCommit(false); // Optional: Set auto-commit to false

            if (!conn.isClosed()) {
                System.out.println("Database connection successful.");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Database connection error: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static DBHandler GetInstance(String poolName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = (stackTrace.length > 5) ? stackTrace[5].getMethodName() : stackTrace[1].getMethodName();

        System.out.printf("%1$tH:%1$tM:%1$tS.%1$tL DBHandler > getInstance(poolName:%2$s) from [%3$s]%n",
                new java.sql.Date(System.currentTimeMillis()), poolName, methodName);

        synchronized (self) {
            DBHandler ret = self.get(poolName);
            if (ret == null) {
                ret = new DBHandler(poolName, null);

                if ("".equals(ret.connectionString))
                    return null;
                self.put(poolName, ret);
            }
            return ret;
        }
    }

    public static DBHandler GetInstance() {
        return GetInstance(defaultConnName);
    }

    public static DBHandler GetInstance(String iniPath, int type) {
        return GetInstance(defaultConnName);
    }

    public void Close() throws SQLException {
        self.put(poolName, null);
        self.remove(poolName);

        if (activeTransaction != null) {
            try {
                activeTransaction.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            activeTransaction = null;
        }

        if (activeConnection != null) {
            if (!activeConnection.isClosed()) {
                try {
                    activeConnection.close();
                } catch (SQLException ex) {
                    activeConnection = null;
                }
            }

            activeConnection = null;
        }

        RemovePoolName(poolName);
    }

    private void InitializeConnection() {
        activeConnection = new ConnectionPool().GetConnection(poolName);
        if (activeConnection == null) throw new RuntimeException("Database Pool not found");
    }

    public void OpenTransaction() throws SQLException {
        activeConnection.beginRequest();
    }

    public boolean IsOpen() {
        try {
            if (!activeTransaction.isClosed()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void CommitTransaction() {
        try {
            if (activeTransaction != null)
                activeTransaction.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void RollbackTransaction() {
        try {
            activeTransaction.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void RemovePoolName(String name) {
        self.remove(name);
        ConnectionPool.RemovePool(name);
    }

    public Object[] CreateCommand(String sqlText, Object[] args) throws SQLException {
        return new PreparedStatement[]{CreateCommand(vendor.toUpperCase(), activeConnection, sqlText, args, activeTransaction)};
    }

    public PreparedStatement CreateCommand(String ven, Connection conn, String sqlText, Object[] args) throws SQLException {
        return CreateCommand(ven, conn, sqlText, args, null);
    }

    public PreparedStatement CreateCommand(String ven, Connection conn, String sqlText, Object[] args,
                                   Connection tran) throws SQLException {
        PreparedStatement preparedStatement;

        if (tran != null) {
            preparedStatement = conn.prepareStatement(sqlText, PreparedStatement.RETURN_GENERATED_KEYS);
        } else {
            preparedStatement = conn.prepareStatement(sqlText);
        }

        for (int i = 0; i < args.length; i++) {
            // Replace ? in the SQL query with actual parameter values
            sqlText = sqlText.replaceFirst("\\?", argSymbol + (i + 1));

            // Set parameter values in the prepared statement
            preparedStatement.setObject(i + 1, (args[i] == null) ? null : args[i]);
        }

        TraceQueryText(preparedStatement);

        return preparedStatement;
    }

    public String[][] ReaderToStringArray(ResultSet resultSet) {
        List<String[]> ret = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int fieldCount = resultSet.getMetaData().getColumnCount();
                String[] temp = new String[fieldCount];

                for (int i = 0; i < fieldCount; i++) {
                    Object value = resultSet.getObject(i + 1);
                    temp[i] = (value != null) ? value.toString() : null;
                }

                ret.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ret.toArray(new String[0][0]);
    }

    public List<ArrayList<Object>> ReaderToListOfLists(ResultSet resultSet) {
        List<ArrayList<Object>> result = new ArrayList<>();

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                ArrayList<Object> row = new ArrayList<>();

                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    row.add(value);
                }

                result.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }


    public List<ArrayList<Object>> Paging(ResultSet resultSet) {
        List<ArrayList<Object>> result = new ArrayList<>();

        try {
            ResultSetMetaData columns = resultSet.getMetaData();
            int columnCount = columns.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                // 가정: 컬럼 이름은 그대로 사용된다고 가정합니다.
                // 필요한 경우 여기에서 수정할 수 있습니다.
                System.out.print(columns.getColumnName(i) + "\t");
            }
            System.out.println();

            int pagingSize = 0;
            while (pagingSize < PAGING_SIZE && resultSet.next()) {
                ArrayList<Object> row = new ArrayList<>();

                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    row.add(value);
                }

                result.add(row);
                pagingSize++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public Process GetProcess() {
        ProcessBuilder procBuilder = new ProcessBuilder();
        procBuilder.redirectErrorStream(true);
        procBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        procBuilder.redirectInput(ProcessBuilder.Redirect.PIPE);
        procBuilder.inheritIO();
        procBuilder.command("cmd.exe", "/c");
        try {
            return procBuilder.start();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Connection ConnectionForAdapter() {
        return new ConnectionPool().GetConnection(poolName);
    }

    private void TraceQueryText(PreparedStatement preparedStatement) {
        String sql = preparedStatement.toString();

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                // 가정: 컬럼 이름은 그대로 사용된다고 가정합니다.
                // 필요한 경우 여기에서 수정할 수 있습니다.
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String val;

                    if (Objects.isNull(value)) {
                        val = "NULL";
                    } else if (value instanceof String || value instanceof java.sql.Date) {
                        val = "'" + value.toString().replace("\\", "\\\\").replace("'", "\\'") + "'";
                    } else {
                        val = value.toString();
                    }

                    sql = sql.replace("?" + i, val);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = (stackTrace.length > 5)
                ? stackTrace[5].getMethodName()
                : stackTrace[1].getMethodName();

        System.out.printf("-- %tT 실행쿼리: [%s]%n%s%n",
                new java.sql.Date(System.currentTimeMillis()), methodName, sql);
        System.out.printf("%tF %tT | 실행 쿼리:%n%s%n", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), sql);
    }
}