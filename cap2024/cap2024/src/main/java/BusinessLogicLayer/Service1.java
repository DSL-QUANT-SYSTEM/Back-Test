package BusinessLogicLayer;
import DataAccessLayer.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service1 implements BusinessLogicLayer.Service {

    private final Dao dao;

    @Autowired
    public Service1(Dao dao) {
        this.dao = dao;
    }

    @Override
    public String fetchDataFromDao() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO1를 사용하여 데이터를 가져옵니다.
        //Test 입니다
        return dao.getDataFromDatabase();
    }
}