package com.example.cap2024.Controller;

import com.example.cap2024.BusinessLogicLayer.Service1;
import com.example.cap2024.DataAccessLayer.dao1.Dao1;
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


    Service1 service = new Service1(dao1);



    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to my app!";
    }

    @GetMapping("/getDataFromService1")
    public ResponseEntity getDataFromService1() {
        try {
            String result = service.fetchDataFromDao();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}