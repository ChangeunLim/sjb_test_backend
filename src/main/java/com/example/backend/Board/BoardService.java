package com.example.backend.Board;

import com.example.backend.Board.model.Board;
import com.example.backend.Board.model.BoardDto;
import com.example.backend.Comment.CommentRepository;
import com.example.backend.Comment.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public void create(BoardDto.BoardReg dto) {
        Board board = boardRepository.save(dto.toEntity());

        dto.getComments().forEach(commentReg -> {
            Comment comment = commentRepository.save(commentReg.toEntity(board));
        });
    }

    @Transactional(readOnly = true)
    public List<BoardDto.BoardRes> list() {
        List<Board> boards = boardRepository.findAll();

        return boards.stream().map(BoardDto.BoardRes::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardDto.BoardRes read(Long Idx) {
        Board board = boardRepository.findById(Idx).orElseThrow();
        return BoardDto.BoardRes.from(board);
    }
}
