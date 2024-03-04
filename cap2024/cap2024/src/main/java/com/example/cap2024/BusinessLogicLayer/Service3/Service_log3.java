package com.example.cap2024.BusinessLogicLayer.Service3;
import com.example.cap2024.DataAccessLayer.dao3.Dao_log3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service_log3 {

    private final Dao_log3 daoLog3;

    @Autowired
    public Service_log3(Dao_log3 daoLog3){
        this.daoLog3 = daoLog3;
    }

    public String fetchDataFromDao() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO1를 사용하여 데이터를 가져옵니다.
        //Test 입니다
        return daoLog3.getDataFromDatabase();
    }
}