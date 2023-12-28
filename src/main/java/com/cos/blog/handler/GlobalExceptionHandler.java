package com.cos.blog.handler;

import com.cos.blog.dto.ReplySaveRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = IllegalArgumentException.class)
//    public String handleArgumentException(IllegalArgumentException e){
//        return "<h2>"+e.getMessage()+"</h2>";
//
//    }

//    @ExceptionHandler(value = IllegalArgumentException.class)
//    public ResponseDto<String> handleArgumentException(IllegalArgumentException e){
//        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
//
//    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ReplySaveRequestDto.ResponseDto<String> handleArgumentException(IllegalArgumentException e){
        return new ReplySaveRequestDto.ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());

    }


}
