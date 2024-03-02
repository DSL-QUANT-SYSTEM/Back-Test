package BusinessLogicLayer;
import DataAccessLayer.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Service3 {

    private final Dao dao;

    @Autowired
    public Service3(Dao dao) {
        this.dao = dao;
    }

    public String fetchDataFromDao3() {
        // Service1에서 필요한 비즈니스 로직을 구현하고 DAO를 사용하여 데이터를 가져옵니다.
        return dao.getDataFromDatabase();
    }
}