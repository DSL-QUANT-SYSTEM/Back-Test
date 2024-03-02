package BusinessLogicLayer;
import DataAccessLayer.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service2 {

    private final Dao dao;

    @Autowired
    public Service2(Dao dao) {
        this.dao = dao;
    }

    public String fetchDataFromDao1() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO를 사용하여 데이터를 가져옵니다.
        // service2 Test입니다.
        return dao.getDataFromDatabase();
    }
}