package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    /**
     * 게시글 작성
     */
    public void boardWrite(Board board, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; // 저장할 경로 지정
        UUID uuid = UUID.randomUUID(); // 파일 이름 앞에 붙일 랜덤한 식별자
        String fileName = uuid + "_" + file.getOriginalFilename(); // 저장될 파일 이름 생성
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);
        boardRepository.save(board);
    }

    /**
     * 게시글 리스트
     */
    public List<Board> list() {
        return boardRepository.findAll(); // findAll(): List<Board> 반환
    }

    /**
     * 특정 게시글 상세보기
     */
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    /**
     * 특정 게시글 삭제
     */
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}
