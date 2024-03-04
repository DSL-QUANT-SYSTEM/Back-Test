package com.example.cap2024.BusinessLogicLayer;
import com.example.cap2024.DataAccessLayer.Dao;
import com.example.cap2024.DataAccessLayer.dao1.Dao1;
import com.example.cap2024.DataAccessLayer.dao2.Dao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service2 {

    private final Dao2 dao2;

    @Autowired
    public Service2(Dao2 dao2) {
        this.dao2 = dao2;
    }

    public String fetchDataFromDao() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO1를 사용하여 데이터를 가져옵니다.
        //Test 입니다
        return dao2.getDataFromDatabase();
    }
}