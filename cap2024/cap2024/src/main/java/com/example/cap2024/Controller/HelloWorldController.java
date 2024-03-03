package com.example.cap2024.Controller;

/*
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HelloWorldController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/hello")
    public String test(){
        return "Hello, world!";
    }
}
*/

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/myapp")
public class HelloWorldController {
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to my app!";
    }


    @GetMapping("/getDataFromService1")    //정보 조회 간에 GET method를 사용
    public ResponseEntity getDataFromService1() {

        try{
            //조회 관련 로직 적용 부분
            String result = "Test completed";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            //예외 처리 부분, 나중에 수정 필요
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}