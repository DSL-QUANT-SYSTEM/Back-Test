package com.example.cap2024.BusinessLogicLayer.Service3;
import com.example.cap2024.DataAccessLayer.dao3.Dao_history3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service_history3 {

    private final Dao_history3 daoHistory3;

    @Autowired
    public Service_history3(Dao_history3 daoHistory3){
        this.daoHistory3 = daoHistory3;
    }

    public String fetchDataFromDao() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO1를 사용하여 데이터를 가져옵니다.
        //Test 입니다
        return daoHistory3.getDataFromDatabase();
    }
}