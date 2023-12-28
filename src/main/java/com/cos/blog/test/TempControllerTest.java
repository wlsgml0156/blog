package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome(){
        System.out.println("test");
        return "/home.html";
    }

    @GetMapping("/temp/tempJsp")
    public String tempJsp(){
        System.out.println("test");
        return "/test";
    }


}
