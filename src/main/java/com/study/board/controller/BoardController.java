package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardwriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardwritePro(Board board) {
//        String title, String content로 일일이 전송받지 않고 Board자체를 @Entity를 이용해 받음
        System.out.println("title" + board.getTitle());
        System.out.println("content" + board.getContent());

        boardService.write(board); // db에 저장
        return "redirect:/";
    }

}
