package com.cos.blog.controller.api;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private HttpSession session;

    // 티스토리 보고 추가함. 나중에 확인해보기
    @Autowired
    private AuthenticationManager authenticationManager;


//    @PostMapping("/api/user")
//    @PostMapping("/auth/joinProc")
//    public ResponseDto<Integer> save(@RequestBody User user){
//        System.out.println("UserApiController save() 호출");
//        int result = userService.회원가입(user);
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),result); // 자바 오브젝트를 JSON으로 변환해서 리턴(Jackson)
//    }

    @PostMapping("/auth/joinProc")
    public ReplySaveRequestDto.ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController save() 호출");
        userService.회원가입(user);
        return new ReplySaveRequestDto.ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

// 전통적인 방식의 로그인 방법
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user){
//        System.out.println("UserApiController login() 호출");
//        User principal = userService.로그인(user); // principal : 접근 주체
//
//        if(principal !=null){
//            session.setAttribute("principal",principal);
//        }
//
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//    }


    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {

        userService.회원수정(user);
//        //여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
//        // 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
//
        // 세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //강제로 session에 값 저장하려 했으나 안됐음
        /*
         * Authentication authentication= new
         * UsernamePasswordAuthenticationToken(principal,
         * null,principal.getAuthorities()); SecurityContext securityContext =
         * SecurityContextHolder.getContext();
         * securityContext.setAuthentication(authentication);
         * session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
         */

        System.out.println("update 호출");
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);//자바오브젝트를 JSON으로 변환해서 리턴
    }

}












