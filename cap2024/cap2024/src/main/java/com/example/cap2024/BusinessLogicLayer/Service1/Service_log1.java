package com.example.cap2024.BusinessLogicLayer.Service1;
import com.example.cap2024.DataAccessLayer.dao1.Dao_log1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service_log1 {

    private final Dao_log1 daoLog1;

    @Autowired
    public Service_log1(Dao_log1 daoLog1){
        this.daoLog1 = daoLog1;
    }

    public String fetchDataFromDao() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO1를 사용하여 데이터를 가져옵니다.
        //Test 입니다
        return daoLog1.getDataFromDatabase();
    }
}