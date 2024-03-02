package handler;

import DataAccessLayer.Dao;
import DataAccessLayer.dao1.Dao1;
import DataAccessLayer.dao2.Dao2;
import DataAccessLayer.dao3.Dao3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testHandler {
    Connection con = null;
    Statement stmt = null;
    String url = "jdbc:mysql://localhost:3306/cap?useSSL=false&useUnicode=true";	//dbstudy스키마
    String user = "root";
    String passwd = "1234";		//MySQL에 저장한 root 계정의 비밀번호를 적어주면 된다.

    public static void main(String[] args) {

        /*
        List<Dao> daoList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String json = ""; // JSON 데이터를 적절히 초기화
            Dao dao = null;
            switch (i) {
                case 1: {
                    dao = new Dao1().fromJson(json);
                    break;
                }
                case 2: {
                    dao = new Dao2().fromJson(json);
                    break;
                }
                case 3: {
                    dao = new Dao3().fromJson(json);
                    break;
                }
                default: {
                    break;
                }
            }
            if (dao != null) {
                daoList.add(dao);
            }
        }
        */

        String test1 = "{\n" +
                "  \"stock\": \"bitcoin\",\n" +
                "  \"date\": \"2024-01-03\",\n" +
                "  \"closePrice\": 100,\n" +
                "  \"openPrice\": 110,\n" +
                "  \"highestPrice\": 120,\n" +
                "  \"lowestPrice\": 90\n" +
                "}";

        Dao test11 = new Dao1();
        test11 = test11.fromJson(test1);

        Dao1 dao1 = new Dao1();
        dao1.setStock("삼성전자");
        dao1.setDate("2024-02-21");
        dao1.setClosePrice(75000);
        dao1.setHighestPrice(79000);
        dao1.setOpenPrice(74000);
        dao1.setLowestPrice(72000);

        testHandler db = new testHandler();

        /* 데이터베이스 관련 코드는 try-catch문으로 예외 처리를 꼭 해주어야 한다. */

        try {
            //데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            db.con = DriverManager.getConnection(db.url, db.user, db.passwd);
            db.stmt = db.con.createStatement();

            int stopNum=0;
            while(stopNum==0){
                //메뉴 리스트
                System.out.println("[1] 유저테이블 생성");
                System.out.println("[2] 유저데이터 추가");
                System.out.println("[3] 유저데이터 삭제");
                System.out.println("[4] 유저데이터 변경");
                System.out.println("[5] 데이터 조회");
                System.out.println("[6] 입력종료");
                System.out.print("= 번호 입력 : ");
                Scanner s = new Scanner(System.in);
                int num = s.nextInt();

                switch(num) {
                    case 1 :
                        db.createUserTable();	//테이블 생성
                        break;
                    case 2 :
                        db.insertUserData(test11);	//데이터 삽입
                        break;
                    case 3 :
                        db.removeUserData(test11);	//데이터 삭제
                        break;
                    case 4 :
                        db.changeUserData(test11);	//데이터 수정
                        break;
                    case 5:
                        db.viewData(test11);		//데이터 조회
                        break;
                    case 6:
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
    void createUserTable() {
        try {
            String createStr = "CREATE TABLE user (stock varchar(20) not null, date varchar(20) not null,"
                    + "closePrice int not null, openPrice int not null, highestPrice int not null,"
                    + "lowestPrice int not null, PRIMARY KEY (stock))";
            stmt.execute(createStr);
            System.out.println("테이블 생성 성공!");
        } catch(Exception e) {
            System.out.println("테이블 생성 실패 이유 : " + e.toString());
        }
    }
    //삽입
    void insertUserData(Dao dao1) {

        String stock=dao1.getStock();
        String date=dao1.getDate();
        int closePrice=dao1.getClosePrice();
        int openPrice=dao1.getOpenPrice();
        int highestPrice=dao1.getHighestPrice();
        int lowestPrice= dao1.getLowestPrice();
        String insertStr = "INSERT INTO user (stock, date, closePrice, openPrice, highestPrice, lowestPrice) VALUES (? ,? ,? ,?, ?, ?)";
        try (PreparedStatement insertStatement = con.prepareStatement(insertStr)) {
            // PreparedStatement에 값 설정
            insertStatement.setString(1, stock);
            insertStatement.setString(2, date);
            insertStatement.setInt(3, closePrice);
            insertStatement.setInt(4, openPrice);
            insertStatement.setInt(5, highestPrice);
            insertStatement.setInt(6, lowestPrice);

            // 데이터 삽입 쿼리 실행
            insertStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println("데이터 추가 실패 이유 : " + e.toString());
        }
    }
    //삭제
    void removeUserData(Dao dao1) {
        String removeStock = dao1.getStock(); // 삭제할 주식 변수 여기
        try {
            String removeStr = "DELETE FROM user where stock=?";
            PreparedStatement pstmt = con.prepareStatement(removeStr);
            pstmt.setString(1, removeStock); // 첫 번째 물음표에 removeStock 변수 값을 설정
            pstmt.executeUpdate();
            System.out.println("데이터 삭제 성공!");
        } catch(Exception e) {
            System.out.println("데이터 삭제 실패 이유 : " + e.toString());
        }

    }
    //수정
    void changeUserData(Dao dao1) {
        String changeStock= dao1.getStock();
        try {
            String changeStr = "UPDATE user SET stock=?";
            PreparedStatement pstmt = con.prepareStatement(changeStr);
            pstmt.setString(1, changeStock); // 첫 번째 물음표에 removeStock 변수 값을 설정
            pstmt.executeUpdate();
            System.out.println("데이터 변경 성공!");
        } catch(Exception e) {
            System.out.println("데이터 변경 실패 이유 : " + e.toString());
        }
    }
    //조회
    void viewData(Dao dao1) {
        try {
            System.out.println("== user 테이블 조회 ==");
            System.out.println("Stock"+"\t"+"date"+"\t"+"closePrice"+"\t"+"openPrice"+"\t"+"highestPrice"+"\t"+"lowestPrice"+"\n");
            String viewStr1 = "SELECT * FROM user";
            ResultSet result1 = stmt.executeQuery(viewStr1);
            int cnt1 = 0;
            while(result1.next()) {
                System.out.print(result1.getString("stock") + "\t\t" + result1.getString("date")
                        + "\t\t" + result1.getString("closePrice") +  "\t\t" + result1.getString("openPrice") +
                        "\t\t" + result1.getString("highestPrice") +  "\t\t" + result1.getString("lowestPrice") +"\n");
                cnt1++;
            }
            System.out.println("");
            System.out.println("데이터 조회 성공!");
        } catch(Exception e) {
            System.out.println("데이터 조회 실패 이유 : " + e.toString());
        }
    }
}