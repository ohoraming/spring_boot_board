package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
//    <entity, pk의 datatype>

//    <JPA Repository>
//    findBy[column 이름]Containing: column에서 keyword가 포함되는 것을 검색
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
