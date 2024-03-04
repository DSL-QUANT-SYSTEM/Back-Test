package com.example.cap2024.BusinessLogicLayer;
import com.example.cap2024.DataAccessLayer.Dao;
import com.example.cap2024.DataAccessLayer.dao1.Dao1;
import com.example.cap2024.DataAccessLayer.dao3.Dao3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service3 {

    private final Dao3 dao3;

    @Autowired
    public Service3(Dao3 dao3){
        this.dao3 = dao3;
    }

    public String fetchDataFromDao() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO1를 사용하여 데이터를 가져옵니다.
        //Test 입니다
        return dao3.getDataFromDatabase();
    }
}