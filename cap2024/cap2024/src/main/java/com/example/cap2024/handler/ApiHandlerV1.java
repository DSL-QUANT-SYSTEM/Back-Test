package com.example.cap2024.handler;

/*
@RestController
@RequestMapping("/api")
public class ApiHandler {

    private final Service service;

    @Autowired
    public ApiHandler(Service service) {
        this.service = service;
    }

    @GetMapping("/getDataFromService1")    //정보 조회 간에 GET method를 사용
    public ResponseEntity getDataFromService1() {

        try{
            //조회 관련 로직 적용 부분
            String result = service.fetchDataFromDao();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            //예외 처리 부분, 나중에 수정 필요
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/postDataFromService1")   //등록, 로그인 과 같은 body 부분에 값을 할당 후 보내야 할때
    public ResponseEntity registerItem(@RequestBody Dao dao){

        try{
            //등록 로직 적용 부분
            //Dao에 input 관련 부분 추출하는 함수 추가 후 해당 값을 통해 Input에 대한 파싱 진행하는 부분
            //inputParseForPost(dao);

            System.out.println("Rest API Server Request Method : POST ");
            return new ResponseEntity("Assign Finished", HttpStatus.OK);
        } catch (Exception e){
            //예외 처리 부분, 나중에 수정 팔요
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("/postDataFromService1")    //특정 값 수정 간에 PUT method를 사용해서 구현
    public ResponseEntity updateItem(@RequestBody Dao dao){
        try{
            //수정 로직 적용 부분
            //Dao에 input 관련 부분 추출하는 함수 추가 후 해당 값을 통해 Input에 대한 파싱 진행하는 부분


            System.out.println("Rest API Server Request Method : PUT ");
            return new ResponseEntity("Update completed", HttpStatus.OK);
        } catch(Exception e){
            //예외 처리 부분, 나중에 수정 팔요
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/items/{id}")     //특정 값의 삭제시 DELETE method를 사용해서 구현
    public ResponseEntity deleteItem(@PathVariable String id){
        try{
            //삭제 로직 적용 부분


            System.out.println("Rest API Server Request Method : DELETE ");
            return new ResponseEntity("Item deleted", HttpStatus.OK);
        } catch (Exception e){
            //예외 처리 부분, 나중에 수정 팔요
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
*/

