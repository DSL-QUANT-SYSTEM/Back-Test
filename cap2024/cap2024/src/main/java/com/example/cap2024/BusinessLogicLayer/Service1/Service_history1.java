package com.example.cap2024.BusinessLogicLayer.Service1;

import com.example.cap2024.DataAccessLayer.Dao_history;
import com.example.cap2024.DataAccessLayer.dao1.Dao_history1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service_history1 {

    private final Dao_history1 daoHistory1;

    @Autowired
    public Service_history1(Dao_history1 daoHistory1) {
        this.daoHistory1 = daoHistory1;
    }

    public String fetchDataFromDao() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO1를 사용하여 데이터를 가져옵니다.
        //Test 입니다
        return daoHistory1.getDataFromDatabase();
    }

}