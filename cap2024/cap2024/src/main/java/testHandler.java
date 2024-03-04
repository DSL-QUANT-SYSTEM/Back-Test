import com.example.cap2024.DataAccessLayer.Dao_history;
import com.example.cap2024.DataAccessLayer.Dao_log;
import com.example.cap2024.DataAccessLayer.dao1.Dao_history1;
import com.example.cap2024.DataAccessLayer.dao1.Dao_log1;

import java.sql.*;
import java.util.Scanner;

public class testHandler {
    Connection con = null;
    Statement stmt = null;
    String url = "jdbc:mysql://localhost:3306/cap?useSSL=false&useUnicode=true";	//dbstudy스키마
    String user = "root";
    String passwd = "1234";		//MySQL에 저장한 root 계정의 비밀번호를 적어주면 된다.

    public static void main(String[] args) {

        Dao_log1 daoLog1= new Dao_log1();
        daoLog1.setDate("2023-08-29");
        daoLog1.setSignal(1);
        daoLog1.setSymbol("PYPL");
        daoLog1.setAmount(17);
        daoLog1.setPrice(74.455);
        daoLog1.setLog_name_id("signal_2_20230831_073430");

        Dao_history1 daoHistory1=new Dao_history1();
        daoHistory1.setStock("Agilent_Technologies");
        daoHistory1.setDate("2024-01-03");
        daoHistory1.setClosePrice(40.2074);
        daoHistory1.setOpenPrice(40.8441);
        daoHistory1.setHighestPrice(40.8441);
        daoHistory1.setLowestPrice(40.1645);
        daoHistory1.setAdjClosePrice(37.20211410522461);
        daoHistory1.setVolume(2678850);

        testHandler db = new testHandler();


        try {
            //데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            db.con = DriverManager.getConnection(db.url, db.user, db.passwd);
            db.stmt = db.con.createStatement();

            int stopNum=0;
            while(stopNum==0){
                //메뉴 리스트
                System.out.println("[1] Create TradingLogDataTable");
                System.out.println("[2] Insert TradingLogData");
                System.out.println("[3] Delete TradingLogData");
                System.out.println("[4] Change TradingLogData");
                System.out.println("[5] View TradingLogData");
                System.out.println("[6] Create HistoryDataTable");
                System.out.println("[7] Insert HistoryData");
                System.out.println("[8] Delete HistoryData");
                System.out.println("[9] Change HistoryData");
                System.out.println("[10] View HistoryData");
                System.out.println("[11] Terminate Input");
                System.out.print("= Insert Number : ");
                Scanner s = new Scanner(System.in);
                int num = s.nextInt();

                switch(num) {
                    case 1 :
                        db.createTradingLogTable();	//테이블 생성
                        break;
                    case 2 :
                        db.insertTradingLogData(daoLog1);	//데이터 삽입
                        break;
                    case 3 :
                        db.removeTradingLogData(daoLog1);	//데이터 삭제
                        break;
                    case 4 :
                        db.changeTradingLogData(daoLog1);	//데이터 수정
                        break;
                    case 5:
                        db.viewTradingLogData(daoLog1);		//데이터 조회
                        break;
                    case 6 :
                        db.createHistoryTable();	//테이블 생성
                        break;
                    case 7 :
                        db.insertHistoryData(daoHistory1);	//데이터 삽입
                        break;
                    case 8 :
                        db.removeHistoryData(daoHistory1);	//데이터 삭제
                        break;
                    case 9 :
                        db.changeHistoryData(daoHistory1);	//데이터 수정
                        break;
                    case 10:
                        db.viewHistoryData(daoHistory1);		//데이터 조회
                        break;
                    case 11:
                        stopNum=1;
                        break;
                }
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                db.stmt.close();
                db.con.close();
            } catch(Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    //테이블 생성
    void createTradingLogTable() {
        try {
            String createStr = "CREATE TABLE trading_log_signal (date varchar(20) not null, signal double not null,"
                    + "symbol varchar(20) not null, amount double not null, price double not null,"
                    + "log_name_id varchar(40) not null, PRIMARY KEY (log_name_id))";
            stmt.execute(createStr);
            System.out.println("Create Table Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to create table : " + e.toString());
        }
    }
    //삽입
    void insertTradingLogData(Dao_log daoLog1) {

        String date=daoLog1.getDate();
        double signal=daoLog1.getSignal();
        String symbol=daoLog1.getSymbol();
        double amount=daoLog1.getAmount();
        double price=daoLog1.getPrice();
        String log_name_id=daoLog1.getLog_name_id();

        String insertStr = "INSERT INTO trading_log_signal (date, signal, symbol, amount, price, log_name_id) VALUES (? ,? ,? ,?, ?, ?)";
        try (PreparedStatement insertStatement = con.prepareStatement(insertStr)) {
            // PreparedStatement에 값 설정
            insertStatement.setString(1, date);
            insertStatement.setDouble(2, signal);
            insertStatement.setString(3, symbol);
            insertStatement.setDouble(4, amount);
            insertStatement.setDouble(5, price);
            insertStatement.setString(6, log_name_id);

            // 데이터 삽입 쿼리 실행
            insertStatement.executeUpdate();
            System.out.println("Insert History Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to insert history : " + e.toString());
        }
    }
    //삭제
    void removeTradingLogData(Dao_log daoLog1) {
        String removeId = daoLog1.getLog_name_id(); // 삭제할 주식 변수 여기
        try {
            String removeStr = "DELETE FROM trading_log_signal where stock=?";
            PreparedStatement pstmt = con.prepareStatement(removeStr);
            pstmt.setString(1, removeId); // 첫 번째 물음표에 removeStock 변수 값을 설정
            pstmt.executeUpdate();
            System.out.println("Delete History Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to delete history : " + e.toString());
        }

    }
    //수정
    void changeTradingLogData(Dao_log daoLog1) {
        String changeId= daoLog1.getLog_name_id();
        try {
            String changeStr = "UPDATE trading_log_signal SET stock=?";
            PreparedStatement pstmt = con.prepareStatement(changeStr);
            pstmt.setString(1, changeId); // 첫 번째 물음표에 removeStock 변수 값을 설정
            pstmt.executeUpdate();
            System.out.println("Change History Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to change history: " + e.toString());
        }
    }
    //조회
    void viewTradingLogData(Dao_log daoLog1) {
        try {
            System.out.println("== View History Table ==");
            System.out.println("date"+"\t"+"signal"+"\t"+"symbol"+"\t"+"amount"+"\t"+"price"+"\t"+"log_name_id"+"\n");
            String viewStr1 = "SELECT * FROM trading_log_signal";
            ResultSet result1 = stmt.executeQuery(viewStr1);
            int cnt1 = 0;
            while(result1.next()) {
                System.out.print(result1.getString("date") + "\t\t" + result1.getDouble("signal")
                        + "\t\t" + result1.getString("symbol") +  "\t\t" + result1.getDouble("amount") +
                        "\t\t" + result1.getDouble("price") +  "\t\t" + result1.getString("log_name_id") +"\n");
                cnt1++;
            }
            System.out.println("");
            System.out.println("View History Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to view history : " + e.toString());
        }
    }
    void createHistoryTable() {
        try {
            String createStr = "CREATE TABLE stock_trading_history (stock varchar(20) not null, date varchar(20) not null,"
                    + "closePrice double not null, openPrice double not null, highestPrice double not null,"
                    + "lowestPrice double not null, adj_close double not null, volume double not null  PRIMARY KEY (stock))";
            stmt.execute(createStr);
            System.out.println("Create Table Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to create table : " + e.toString());
        }
    }
    //삽입
    void insertHistoryData(Dao_history daoHistory1) {

        String stock= daoHistory1.getStock();
        String date= daoHistory1.getDate();
        double closePrice=daoHistory1.getClosePrice();
        double openPrice= daoHistory1.getOpenPrice();
        double highestPrice= daoHistory1.getHighestPrice();
        double lowestPrice= daoHistory1.getLowestPrice();
        double adjClosePrice= daoHistory1.getAdjClosePrice();
        double volume=daoHistory1.getVolume();
        String insertStr = "INSERT INTO stock_trading_history (stock, date, closePrice, openPrice, highestPrice, lowestPrice, adj_close, volume) VALUES (? ,? ,? ,? ,? ,?, ?, ?)";
        try (PreparedStatement insertStatement = con.prepareStatement(insertStr)) {
            // PreparedStatement에 값 설정
            insertStatement.setString(1, stock);
            insertStatement.setString(2, date);
            insertStatement.setDouble(3, closePrice);
            insertStatement.setDouble(4, openPrice);
            insertStatement.setDouble(5, highestPrice);
            insertStatement.setDouble(6, lowestPrice);
            insertStatement.setDouble(7, adjClosePrice);
            insertStatement.setDouble(8, volume);

            // 데이터 삽입 쿼리 실행
            insertStatement.executeUpdate();
            System.out.println("Insert History Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to insert history : " + e.toString());
        }
    }
    //삭제
    void removeHistoryData(Dao_history daoHistory1) {
        String removeStock = daoHistory1.getStock(); // 삭제할 주식 변수 여기
        try {
            String removeStr = "DELETE FROM stock_trading_history where stock=?";
            PreparedStatement pstmt = con.prepareStatement(removeStr);
            pstmt.setString(1, removeStock); // 첫 번째 물음표에 removeStock 변수 값을 설정
            pstmt.executeUpdate();
            System.out.println("Delete History Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to delete history : " + e.toString());
        }

    }
    //수정
    void changeHistoryData(Dao_history daoHistory1) {
        String changeStock= daoHistory1.getStock();
        try {
            String changeStr = "UPDATE stock_trading_history SET stock=?";
            PreparedStatement pstmt = con.prepareStatement(changeStr);
            pstmt.setString(1, changeStock); // 첫 번째 물음표에 removeStock 변수 값을 설정
            pstmt.executeUpdate();
            System.out.println("Change History Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to change history: " + e.toString());
        }
    }

    void viewHistoryData(Dao_history daoHistory1){
        try {
            System.out.println("== View Stock Table ==");
            System.out.println("Stock"+"\t"+"date"+"\t"+"closePrice"+"\t"+"openPrice"+"\t"+"highestPrice"+"\t"+"lowestPrice"+"\t"+"adj_close"+"\t"+"volume"+"\n");
            String viewStr1 = "SELECT * FROM stock_trading_history";
            ResultSet result1 = stmt.executeQuery(viewStr1);
            int cnt1 = 0;
            while(result1.next()) {
                System.out.print(result1.getString("stock") + "\t\t" + result1.getString("date")
                        + "\t\t" + result1.getDouble("closePrice") +  "\t\t" + result1.getDouble("openPrice") +
                        "\t\t" + result1.getDouble("highestPrice") +  "\t\t" + result1.getDouble("lowestPrice") +
                        "\t\t" + result1.getDouble("adj_close") +  "\t\t" + result1.getDouble("volume") +"\n");
                cnt1++;
            }
            System.out.println("");
            System.out.println("View History Successfully!");
        } catch(Exception e) {
            System.out.println("Failed to view history : " + e.toString());
        }
    }
}