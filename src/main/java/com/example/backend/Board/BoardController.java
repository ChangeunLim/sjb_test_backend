package com.example.backend.Board;

import com.example.backend.Board.model.Board;
import com.example.backend.Board.model.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public void create(@RequestBody BoardDto.BoardReg dto) {
        boardService.create(dto);
    }

    @PostMapping("/boards")
    public ResponseEntity<List<BoardDto.BoardRes>> list() {
        List<BoardDto.BoardRes> res = boardService.list();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/read")
    public ResponseEntity<BoardDto.BoardRes> read(@PathVariable Long Idx) {
        BoardDto.BoardRes res = boardService.read(Idx);
        return ResponseEntity.ok(res);
    }
}