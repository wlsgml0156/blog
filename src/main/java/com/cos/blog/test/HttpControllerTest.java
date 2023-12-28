package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {


    // http://localhost:8080/http/lombok
    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m = new Member(1,"jinny","1234","email");
        System.out.println(m.getId());
        m.setId(5000);
        System.out.println(m.getId());
        return "lombok Test 완료";
    }

    // http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(){
        return "get 요청";
    }

    // 값을 쿼리스트링으로 전달
    // http://localhost:8080/http/get1?id=1&username=test&password=1234&email=test@test.com
    @GetMapping("/http/get1")
    public String getTest1(@RequestParam int id, @RequestParam String username, @RequestParam String password, @RequestParam String email ){
        return "get 요청 : id="+id+", username="+username+", password="+password+", email="+email;
    }

    // http://localhost:8080/http/get2?id=1&username=test&password=1234&email=test@test.com
    @GetMapping("/http/get2")
    public String getTest2(Member m){
        return "get 요청 : id="+m.getId()+", username="+m.getUsername()+", password="+m.getPassword()+", email="+m.getEmail();
    }

    // http://localhost:8080/http/post (insert) form태그 이용방식
    @PostMapping("/http/post")
    public String postTest(Member m){
        return "post 요청 : id="+m.getId()+", username="+m.getUsername()+", password="+m.getPassword()+", email="+m.getEmail();
    }

    // http://localhost:8080/http/post2 (insert) text 받아보기
    @PostMapping("/http/post2")
    public String postTest2(@RequestBody String text){
        return "post 요청 : "+text;
    }

    // http://localhost:8080/http/post3 (insert) json으로 받아보기
    @PostMapping("/http/post3")
    public String postTest3(@RequestBody Member m){ // MessageConverter(스프링부트)
        return "post 요청 : id="+m.getId()+", username="+m.getUsername()+", password="+m.getPassword()+", email="+m.getEmail();
    }

    // http://localhost:8080/http/put (update)
    @PutMapping("http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청 : id="+m.getId()+", username="+m.getUsername()+", password="+m.getPassword()+", email="+m.getEmail();
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("http/delete")
    public String deleteTest(@RequestBody Member m){
        return "delete 요청 : id="+m.getId()+", username="+m.getUsername()+", password="+m.getPassword()+", email="+m.getEmail();
    }

}
