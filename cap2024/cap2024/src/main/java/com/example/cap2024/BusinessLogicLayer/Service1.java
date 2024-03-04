package com.example.cap2024.BusinessLogicLayer;

import com.example.cap2024.DataAccessLayer.dao1.Dao1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service1 {

    private final Dao1 dao1;

    @Autowired
    public Service1(Dao1 dao1) {
        this.dao1 = dao1;
    }

    public String fetchDataFromDao() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO1를 사용하여 데이터를 가져옵니다.
        //Test 입니다
        return dao1.getDataFromDatabase();
    }

}