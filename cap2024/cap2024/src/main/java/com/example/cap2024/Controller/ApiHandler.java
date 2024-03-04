package com.example.cap2024.Controller;

import com.example.cap2024.BusinessLogicLayer.Service1;
import com.example.cap2024.BusinessLogicLayer.Service2;
import com.example.cap2024.BusinessLogicLayer.Service3;
import com.example.cap2024.DataAccessLayer.dao1.Dao1;
import com.example.cap2024.DataAccessLayer.dao2.Dao2;
import com.example.cap2024.DataAccessLayer.dao3.Dao3;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/myapp")
public class ApiHandler {



    Dao1 dao1 = new Dao1();
    Dao2 dao2 = new Dao2();
    Dao3 dao3 = new Dao3();


    Service1 service1 = new Service1(dao1);
    Service2 service2 = new Service2(dao2);
    Service3 service3 = new Service3(dao3);



    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to my app!";
    }

    @GetMapping("/getDataFromService1")
    public ResponseEntity getDataFromService1() {
        try {
            String result = service1.fetchDataFromDao();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDataFromService2")
    public ResponseEntity getDataFromService2() {
        try {
            String result = service2.fetchDataFromDao();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDataFromService3")
    public ResponseEntity getDataFromService3() {
        try {
            String result = service3.fetchDataFromDao();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}