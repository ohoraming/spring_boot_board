package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardwriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardwritePro(Board board, Model model, MultipartFile file) throws Exception {
//        String title, String content로 일일이 전송받지 않고 Board자체를 @Entity를 이용해 받음
        System.out.println("title: " + board.getTitle());
        System.out.println("content: " + board.getContent());

        boardService.boardWrite(board, file); // db에 저장

//        글 작성이 완료되면 alert을 띄워준 뒤, /board/list로 옮겨줌
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {
        model.addAttribute("list", boardService.list());
                //"list"라는 이름으로 boardService.boardList()를 담아 보냄
        return "boardlist";
    }

    @GetMapping("/board/view") // localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
//    {id}가 @PathVariable("id")로 인식되어 Integer id 형태로 들어감
    public String boardModify(@PathVariable("id") Integer id,
                              Model model) {
        model.addAttribute("board", boardService.boardView(id)); // 해당 글 넘겨주기

        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file) throws Exception {
        Board boardTemp = boardService.boardView(id); // 기존 글 검색하기
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.boardWrite(boardTemp, file);

//        글 수정이 완료되면 alert을 띄워준 뒤, /board/list로 옮겨줌
        model.addAttribute("message", "수정이 완료되었습니다!");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }
}
