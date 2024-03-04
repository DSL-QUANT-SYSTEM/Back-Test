package com.example.cap2024.BusinessLogicLayer.Service2;
import com.example.cap2024.DataAccessLayer.dao2.Dao_history2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service_history2 {

    private final Dao_history2 daoHistory2;

    @Autowired
    public Service_history2(Dao_history2 daoHistory2){
        this.daoHistory2 = daoHistory2;
    }

    public String fetchDataFromDao() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO1를 사용하여 데이터를 가져옵니다.
        //Test 입니다
        return daoHistory2.getDataFromDatabase();
    }
}