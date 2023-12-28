package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ReplySaveRequestDto.ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.글쓰기(board,principal.getUser());
        return new ReplySaveRequestDto.ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ReplySaveRequestDto.ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.삭제하기(id);
        return new ReplySaveRequestDto.ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/board/{id}")
    public ReplySaveRequestDto.ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board, Model model){
        boardService.글수정하기(id,board);
        return new ReplySaveRequestDto.ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    // 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
    // dto 사용하지 않은 이유는!!
    @PostMapping("/api/board/{boardId}/reply")
    public ReplySaveRequestDto.ResponseDto<Integer> replaySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        boardService.댓글쓰기3(replySaveRequestDto);
        System.out.println("testC_2");
        return new ReplySaveRequestDto.ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
//    public ResponseDto<Integer> replaySave(@PathVariable int boardId,@RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal){
//        reply.setUser(principal.getUser());
//        System.out.println("testC_1");
//        boardService.댓글쓰기(principal.getUser(),boardId,reply);
//        System.out.println("testC_2");
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ReplySaveRequestDto.ResponseDto<Integer> replyDelete(@PathVariable int replyId){
        System.out.println("replyId : "+replyId);
        boardService.댓글삭제(replyId);
        return new ReplySaveRequestDto.ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }


}
